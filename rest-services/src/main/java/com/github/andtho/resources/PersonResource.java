package com.github.andtho.resources;

import com.github.andtho.domain.Person;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Slf4j
@Component
@Path("person")
@Produces("application/json")
public class PersonResource {

    @Autowired
    @Setter private PersonService personService;

    @GET
    @Path("{ssn}")
    public Person getCustomer(@PathParam("ssn") String ssn) {
        log.info("GET customer. ID={}", ssn);
        return personService.getPerson(ssn);
    }

}
