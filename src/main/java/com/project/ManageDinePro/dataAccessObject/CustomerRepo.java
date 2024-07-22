package com.project.ManageDinePro.dataAccessObject;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.project.ManageDinePro.entity.Customer;
// import java.util.*;

public interface CustomerRepo extends MongoRepository<Customer, String> {

}
