package com.github.andtho.resources;


import com.github.andtho.TestBase;
import com.github.andtho.domain.Customer;
import com.github.andtho.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
public class CustomerResourceTest extends TestBase {

    @BeforeClass public static void setTestUrl(){
        System.setProperty("customer.api.url", "http://localhost:"+PORT_NUMBER+"/api/customer");
    }

    @Test
    public void test_get_person_by_id() throws Exception {
        stubFor(get(urlEqualTo("/api/customer/123456789"))
                .withHeader("accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(personJson())));

        @SuppressWarnings("unchecked")
        Customer customer= target("/customer/123456789")
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(Customer.class);

        assertNotNull(customer);

        log.info(JsonUtil.toJson(customer));
        assertEquals("thomas", customer.getFirstname());
        assertEquals("123456789", customer.getSsn());

    }

    @Test
    public void test_get_person_by_id_missing_firstname() throws Exception {
        stubFor(get(urlEqualTo("/api/customer/123456789"))
                .withHeader("accept", equalTo("application/json"))
                .willReturn(aResponse()

                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(personMissingFirstnameJson())));

        @SuppressWarnings("unchecked")
        Customer customer= target("/customer/123456789")
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(Customer.class);

        assertNotNull(customer);

        log.info(JsonUtil.toJson(customer));
        assertEquals(null, customer.getFirstname());
        assertEquals("123456789", customer.getSsn());

    }

    @Override
    protected Application configure() {
        ResourceConfig config = new ResourceConfig();
        config.register(CustomerResource.class);
        return config;
    }

    private String personJson() {
        return "{\n" +
                "    \"firstname\": \"thomas\",\n" +
                "    \"lastname\": \"andersen\",\n" +
                "    \"ssn\": \"123456789\",\n" +
                "    \"address\": {\n" +
                "        \"street\": \"stryken\",\n" +
                "        \"number\": 45,\n" +
                "        \"city\": \"Hokksund\"\n" +
                "    }\n" +
                "}";
    }

    private String personMissingFirstnameJson() {
        return "{\n" +
                "    \"lastname\": \"andersen\",\n" +
                "    \"ssn\": \"123456789\",\n" +
                "    \"address\": {\n" +
                "        \"street\": \"stryken\",\n" +
                "        \"number\": 45,\n" +
                "        \"city\": \"Hokksund\"\n" +
                "    }\n" +
                "}";
    }

}
