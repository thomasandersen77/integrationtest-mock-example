package com.github.andtho.resources;

import com.github.andtho.domain.Product;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.NoContentException;
import java.util.Optional;

@Slf4j
@Path("product")
@Produces("application/json")
public class ProductResource {

    @Inject
    private IntegrationService integrationService;

    @GET
    @Path("{id}")
    public Product getProduct(@PathParam("id") String id) throws NoContentException {
        log.info("Get Product by Id: [{}]", id);
        Optional<Product> product = integrationService.getProduct(id);
        if(product.isPresent()) {
            return product.get();
        }

        throw new NoContentException("Found no products with id = {}"+ id);

    }

}
