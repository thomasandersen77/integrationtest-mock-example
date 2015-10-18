package com.github.andtho.resources;

import com.github.andtho.config.AppConfiguration;
import com.github.andtho.domain.Customer;
import com.github.andtho.domain.Product;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class IntegrationService {

    @Inject
    private AppConfiguration config;

    @Inject
    @Setter
    private Client client;

    public Optional<Customer> getCustomer(String customerId) {

        String url = config.getCustomerApi_url() + "/" + customerId;
        log.info("Get customer with id: [{}]. Customer API Url: [{}]", customerId, url);
        return Optional.ofNullable(client.target(url)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(Customer.class));
    }

    public Optional<List> getCustomerList(){
        String productApi_url = config.getProductApi_url();
        log.info("Get customer list. Customer API Url: [{}]", productApi_url);
        List list = client.target(
                productApi_url)
                .request().get(List.class);
        return Optional.ofNullable(list);
    }

    public Optional<List> getProducts() {
        String all_products_url = config.getProductApi_url();
        log.info("Get productlist. Product API Url: ", all_products_url);
        return Optional.ofNullable(client.target(all_products_url).request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(List.class));
    }

    public Optional<Product> getProduct(String id) {
        String request_product_by_id = config.getProductApi_url() + "/" +id;
        log.info("Get productlist. Product API Url: ", request_product_by_id);
        return Optional.ofNullable(client.target(request_product_by_id).request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(Product.class));
    }
}
