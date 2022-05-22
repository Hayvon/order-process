package com.example.workflow;

import com.example.workflow.api.dto.Customer;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.util.logging.Logger;

@Named("prefill")
public class PrefillCustomerData implements JavaDelegate {
    private final static Logger LOGGER = Logger.getLogger("ORDER_PROCESS");

    RestTemplate restTemplate = new RestTemplate();

    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Processing customer data request");
        String customerId = execution.getVariable("customer_id").toString();

        LOGGER.info("Searching for info of customer " + customerId);
        String customerUri = "http://localhost:8080/api/customer/" + customerId;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Customer> customerEntity = restTemplate.exchange(customerUri, HttpMethod.GET, entity, Customer.class);
        Customer customer = customerEntity.getBody();

        execution.setVariable("customer_name", customer.firstname);
        execution.setVariable("customer_surname", customer.lastname);
        execution.setVariable("customer_company", customer.companyname);
        execution.setVariable("customer_adress", customer.address_street);
        execution.setVariable("customer_id", customer.customerid);
        execution.setVariable("customer_class", customer.customer_class);

    }
}
