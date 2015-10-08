package com.github.andtho.resources;

import com.github.andtho.config.PropertyReader;
import com.github.andtho.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    PropertyReader propertyReader;

    Client client = ClientBuilder.newClient();

    public Customer getCustomer(String customerId) {
        String customerServiceUrl = propertyReader.getProperty("customer.service.url");
        String url = customerServiceUrl + "/" + customerId;
        log.info("Get customer with id: [{}]. CustomerService Url: [{}]", customerId, url);
        Client client = ClientBuilder.newClient();
        Customer customer = client.target(url)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(Customer.class);

        return customer;
    }
}
