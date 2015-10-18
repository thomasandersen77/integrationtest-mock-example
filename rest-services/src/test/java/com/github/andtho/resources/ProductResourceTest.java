package com.github.andtho.resources;


import com.github.andtho.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
public class ProductResourceTest extends TestBase {

    @BeforeClass public static void setTestUrl(){
        System.setProperty("product.api.url", "http://localhost:"+PORT_NUMBER+"/api/product");
    }

    @Test
    public void test_get_product_by_id() throws Exception {
        stubFor(get(urlEqualTo("/api/product/1"))
                .withHeader("accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(productJson())));

        Response response = target("/product/1")
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(Response.class);

        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Override
    protected Application configure() {
        ResourceConfig config = new ResourceConfig();
        config.register(ProductResource.class);
        return config;
    }

    private String productJson() {
        return "{\"id\" : \"1\", \"name\" : \"IPad Mini\",        \"price\" : 850}";
    }

}
