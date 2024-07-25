package com.project.ManageDinePro.restController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ManageDinePro.dataAccessObject.BillService;
import com.project.ManageDinePro.entity.Bill;

@RestController
@RequestMapping("/api")
public class BillRestController {
    
    private BillService billService;

    public BillRestController(BillService billService) {
        this.billService = billService;
    }

}
