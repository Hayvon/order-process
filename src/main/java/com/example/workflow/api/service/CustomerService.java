package com.example.workflow.api.service;

import com.example.workflow.api.dto.Customer;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

@Service
public class CustomerService {


    private Customer[] customerList;

    public CustomerService() throws IOException {
        URL resource = this.getClass().getResource("/customerdata.json");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(resource.openStream(), Charset.forName("UTF-8")));

        StringBuilder customerdata = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            customerdata.append(inputLine);
        in.close();

        customerList = new Gson().fromJson(customerdata.toString(), Customer[].class);
    }

    public ResponseEntity get() {
        return ResponseEntity.ok(new Gson().toJson(customerList));
    }

    public ResponseEntity getByCustomerId(String customerId) {
        for (Customer customer : customerList){
            if(customer.customerid.equals(customerId)){
                return ResponseEntity.ok(new Gson().toJson(customer));
            }
        }
        return ResponseEntity.status(404).build();
    }

}
