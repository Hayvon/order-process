package com.example.workflow.api.controller;

import com.example.workflow.api.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/stock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping
    public ResponseEntity get(){
        return stockService.get();
    }

    @GetMapping(value = "/{productid}")
    public ResponseEntity getByProductId(@PathVariable("productid") String productId){
        return stockService.getByProductId(productId);
    }
}
