package com.example.workflow;

import com.example.workflow.api.dto.StockEntry;
import com.google.gson.Gson;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

public class RetrieveStockExisting implements JavaDelegate {
    private final static Logger LOGGER = Logger.getLogger("ORDER_PROCESS");

    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Processing new stock request");

        // TODO: Get relevant variables from process
        String productId = "";

        // TODO: Get information from api (similar to PrefillCustomerData)
        String stockUri = "http://localhost:8080/api/stock/" + productId;

        String stockResponse = "TODO: Get actual response";

        // Converts Json Response to a type of StockEntry
        StockEntry stockEntry = new Gson().fromJson(stockResponse.toString(), StockEntry.class);
        Long amount = stockEntry.Amount;

        // TODO: Check amount with ordered amount and set variables accordingly
    }
}
