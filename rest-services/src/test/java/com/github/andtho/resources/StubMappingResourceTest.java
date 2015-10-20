package com.github.andtho.resources;


import com.github.andtho.TestBase;
import com.github.andtho.domain.Customer;
import com.github.andtho.json.Request;
import com.github.andtho.json.Response;
import com.github.andtho.json.StubMapping;
import com.github.andtho.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.Arrays.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@Slf4j
public class StubMappingResourceTest extends TestBase {

    @BeforeClass public static void setTestUrl(){
        System.setProperty("mapping.api.url", "http://localhost:"+PORT_NUMBER+"/api/mapping");
    }

    @Test
    public void test_get_person_by_id() throws Exception {
        stubFor(get(urlEqualTo("/api/mapping/123456789"))
                .withHeader("accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(mapping_missing_response())));

        StubMapping mapping= target("/mapping/123456789")
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(StubMapping.class);

        assertNotNull(mapping);
        assertNull(mapping.getResponse());
    }

    @Test
    public void test_get_person_by_id_missing_firstname() throws Exception {
        stubFor(get(urlEqualTo("/api/mapping/123456789"))
                .withHeader("accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(mapping())));

        StubMapping mapping = target("/mapping/123456789")
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(StubMapping.class);

        assertNotNull(mapping);

        log.info("\n{}",JsonUtil.toJson(mapping));
        assertNotNull(mapping.getRequest());
        assertEquals("GET", mapping.getRequest().getMethod());

    }

    @Override
    protected Application configure() {
        ResourceConfig config = new ResourceConfig();
        config.register(StubMappingResource.class);
        return config;
    }

    private String mapping() {
        Customer customer = new Customer();
        customer.setFirstname("thomas");
        customer.setLastname("andersen");
        @SuppressWarnings("unchecked")
        StubMapping mapping = StubMapping.builder()
                .request(Request.
                        builder()
                        .urlPattern("/customer/1")
                        .method("GET")
                        .headers(
                                Request.Headers.builder().accept("application/json").build())
                        .build())
                .response(Response.builder()
                        .status(200)
                        .headers(
                                Response.Headers.builder().contentType("application/json").build())
                        .jsonBody(
                                customer)
                        .build())
                .build();
        return JsonUtil.toJson(mapping);
    }

    private String mapping_missing_response() {
        StubMapping mapping = StubMapping.builder()
                .request(Request.
                        builder()
                        .method("GET").build())
                .build();
        return JsonUtil.toJson(mapping);
    }

}
