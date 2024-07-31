import cv2
import numpy as np
import face_recognition
from pymongo import MongoClient
from PIL import Image
import io

# MongoDB setup
client = MongoClient("mongodb://localhost:27017/")
db = client['DineManage-ProDB'] 
collection = db['Customer']  

# Initialize YOLO
weights_path = "c:/Users/luido/Desktop/ManageDinePro/python_scripts/yolov3/yolov3.weights"
cfg_path = "c:/Users/luido/Desktop/ManageDinePro/python_scripts/yolov3/yolov3.cfg"
classes_file = "c:/Users/luido/Desktop/ManageDinePro/python_scripts/yolov3/coco.names"

net = cv2.dnn.readNet(weights_path, cfg_path)
layer_names = net.getLayerNames()
out_layers_indices = net.getUnconnectedOutLayers()
out_layers_indices = out_layers_indices.flatten() if isinstance(out_layers_indices, np.ndarray) else out_layers_indices
output_layers = [layer_names[i - 1] for i in out_layers_indices]

with open(classes_file, "r") as f:
    classes = [line.strip() for line in f.readlines()]

video_capture = cv2.VideoCapture(0)

# Define a scaling factor
scaling_factor = 2.0  # Increase the size by 2x

def get_face_encodings_from_db():
    customer_encodings = []
    for customer in collection.find():
        image_data = customer.get('customer_image')
        if image_data:
            try:
                image_bytes = io.BytesIO(image_data)
                pil_image = Image.open(image_bytes)
                cv_image = np.array(pil_image)
                face_encodings = face_recognition.face_encodings(cv_image)
                if face_encodings:
                    customer_encodings.append({
                        'customer_id': customer.get('customer_name'),
                        'encoding': face_encodings[0]
                    })
            except Exception as e:
                print(f"Error processing image data: {e}")
    return customer_encodings

# Load face encodings from the database
customer_encodings = get_face_encodings_from_db()

def recognize_face(face_encoding):
    for customer in customer_encodings:
        stored_encoding = customer['encoding']
        distance = face_recognition.face_distance([stored_encoding], face_encoding)
        if distance[0] < 0.6:  # Adjust threshold if needed
            return customer['customer_id']
    return None

while True:
    ret, frame = video_capture.read()
    if not ret:
        print("Failed to grab frame")
        break

    rgb_frame = frame[:, :, ::-1]
    blob = cv2.dnn.blobFromImage(frame, 0.00392, (416, 416), (0, 0, 0), True, crop=False)
    net.setInput(blob)
    outs = net.forward(output_layers)

    class_ids = []
    confidences = []
    boxes = []

    for out in outs:
        for detection in out:
            if detection.ndim == 1:  # Ensure detection is 2D
                detection = np.expand_dims(detection, axis=0)
            for obj in detection:
                obj = np.array(obj)  # Ensure obj is a numpy array
                scores = obj[5:]
                class_id = np.argmax(scores)
                confidence = scores[class_id]
                if confidence > 0.5:
                    center_x = int(obj[0] * frame.shape[1])
                    center_y = int(obj[1] * frame.shape[0])
                    w = int(obj[2] * frame.shape[1])
                    h = int(obj[3] * frame.shape[0])
                    x = int(center_x - w / 2)
                    y = int(center_y - h / 2)
                    boxes.append([x, y, w, h])
                    confidences.append(float(confidence))
                    class_ids.append(class_id)

    indices = cv2.dnn.NMSBoxes(boxes, confidences, 0.5, 0.4)
    for i in indices.flatten():
        box = boxes[i]
        x, y, w, h = box
        label = str(classes[class_ids[i]])
        color = (0, 255, 0)
        cv2.rectangle(frame, (x, y), (x + w, y + h), color, 2)
        cv2.putText(frame, label, (x, y - 10), cv2.FONT_HERSHEY_SIMPLEX, 0.75, color, 2)

        # Extract face region
        face = frame[y:y + h, x:x + w]
        if face.size == 0:  # Check if face region is empty
            continue

        try:
            face_rgb = cv2.cvtColor(face, cv2.COLOR_BGR2RGB)
            face_encodings = face_recognition.face_encodings(face_rgb)
            if face_encodings:
                face_encoding = face_encodings[0]
                customer_id = recognize_face(face_encoding)
                if customer_id:
                    # Display the customer ID
                    cv2.putText(frame, f"Customer: {customer_id}", (x, y - 30), cv2.FONT_HERSHEY_SIMPLEX, 0.75, color, 2)
                else:
                    # Optionally display a message if the face is not recognized
                    cv2.putText(frame, "Unknown", (x, y - 30), cv2.FONT_HERSHEY_SIMPLEX, 0.75, color, 2)
        except Exception as e:
            print(f"Error processing face region: {e}")

    # Resize the frame to make it larger
    frame_resized = cv2.resize(frame, (int(frame.shape[1] * scaling_factor), int(frame.shape[0] * scaling_factor)))

    cv2.imshow('Video', frame_resized)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

video_capture.release()
cv2.destroyAllWindows()
