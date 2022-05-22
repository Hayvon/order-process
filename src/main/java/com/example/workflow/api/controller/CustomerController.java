package com.example.workflow.api.controller;

import com.example.workflow.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @GetMapping
    public ResponseEntity get(){
        return customerService.get();
    }


    @GetMapping(value = "/{customerid}")
    public ResponseEntity getByCustomerId(@PathVariable("customerid") String customerid){
        return customerService.getByCustomerId(customerid);
    }

}
