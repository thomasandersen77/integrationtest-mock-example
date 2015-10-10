package com.github.andtho.resources;

import com.github.andtho.domain.Product;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.Optional;

@Slf4j
@Path("product")
@Produces("application/json")
public class ProductResource {

    @Inject
    private IntegrationService integrationService;

    @GET
    @Path("{id}")
    public Optional<Product> getProduct(@PathParam("id") String id) {
        log.info("Get Product by Id: [{}]", id);
        return integrationService.getProduct(id);
    }

}
