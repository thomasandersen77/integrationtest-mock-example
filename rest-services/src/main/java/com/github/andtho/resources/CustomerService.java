package com.github.andtho.resources;

import com.github.andtho.config.ResourceLocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    ResourceLocator resourceLocator;

    Client client = ClientBuilder.newClient();

    public Customer getCustomer(String customerId) {
        String customerServiceUrl = resourceLocator.getProperty("customer.service.url");
        String url = customerServiceUrl + "/" + customerId;
        log.info("Get customer with id: [{}]. CustomerService Url: [{}]", customerId, url);
        Customer customer = client.target(url)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(Customer.class);

        return customer;
    }
}
