package com.github.andtho.resources;

import com.github.andtho.domain.Customer;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Component
@Path("customer")
public class CustomerResource {

    @Inject
    private CustomerService customerService;

    @GET
    @Path("{customerId}")
    public Customer getCustomer(@PathParam("customerId") String customerId) {
        return customerService.getCustomer(customerId);
    }

}
