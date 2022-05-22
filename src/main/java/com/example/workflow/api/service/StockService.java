package com.example.workflow.api.service;

import com.example.workflow.api.dto.StockEntry;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

@Service
public class StockService {

    private StockEntry[] stockEntries;

    public StockService() throws IOException {
        URL resource = this.getClass().getResource("/stockdata.json");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(resource.openStream(), Charset.forName("UTF-8")));

        StringBuilder stockentries = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            stockentries.append(inputLine);
        in.close();

        stockEntries = new Gson().fromJson(stockentries.toString(), StockEntry[].class);
    }

    public ResponseEntity get() {
        return ResponseEntity.ok(new Gson().toJson(stockEntries));
    }

    public ResponseEntity getByProductId(String productId) {
        for (StockEntry stockEntry: stockEntries){
            if(stockEntry.ProductId.equals(productId)){
                return ResponseEntity.ok(new Gson().toJson(stockEntry));
            }
        }
        return ResponseEntity.status(404).build();
    }

}
