package com.github.andtho.resources;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Slf4j
@Component
@Path("customer")
@Produces("application/json")
public class CustomerResource {

    @Autowired
    @Setter private CustomerService customerService;

    @GET
    @Path("{customerId}")
    public Customer getCustomer(@PathParam("customerId") String customerId) {
        log.info("GET customer. ID={}", customerId);
        return customerService.getCustomer(customerId);
    }

}
