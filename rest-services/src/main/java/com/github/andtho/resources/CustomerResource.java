package com.github.andtho.resources;

import com.github.andtho.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Path("customer")
@Produces("application/json")
public class CustomerResource {

    @Autowired
    private IntegrationService integrationService;

    @GET
    @Path("{customerId}")
    public Optional<Customer> getCustomer(@PathParam("customerId") String ssn) throws NoContentException {
        log.info("GET customer. ID={}", ssn);
        Optional<Customer> customer = integrationService.getCustomer(ssn);
        if(customer.isPresent()) {
            return customer;
        } else {
            throw new NoContentException("No customer with id = " + ssn);
        }
    }


    @GET
    public Response getCustomers() throws NoContentException {
        Optional<List> optList = integrationService.getCustomerList();
        if(optList.isPresent()) {
            return Response.ok(optList).build();
        } else {
            throw new NoContentException("No customers");
        }
    }
}
