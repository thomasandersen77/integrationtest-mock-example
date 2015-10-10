package com.github.andtho.resources;


import com.github.andtho.AppConfiguration;
import com.github.andtho.domain.Customer;
import com.github.andtho.util.HttpUtils;
import com.github.andtho.util.JsonUtil;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.inmemory.InMemoryTestContainerFactory;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {AppConfiguration.class})
public class RestResourceTest extends JerseyTest {

    public static final int PORT_NUMBER = HttpUtils.dynamicPort();

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(
            WireMockConfiguration.wireMockConfig().port(PORT_NUMBER));

    public RestResourceTest() {
        super(new InMemoryTestContainerFactory());
        set(TestProperties.LOG_TRAFFIC, Boolean.TRUE);
    }

    @BeforeClass public static void setTestUrl(){
        System.setProperty("customer.api.url", "http://localhost:"+PORT_NUMBER+"/api/customer");
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



    @Test
    public void test_get_person_by_id() throws Exception {
        stubFor(get(urlEqualTo("/api/customer/09077745367"))
                .withHeader("accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(personJson())));

        @SuppressWarnings("unchecked")
        Optional<Customer> optionalReq= target("/customer/09077745367")
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(Optional.class);

        assertNotNull(optionalReq);
        assertNotNull(optionalReq.get());
        log.info(JsonUtil.toJson(optionalReq.get()));
        //assertEquals("thomas", customer.get().getFirstname());
        //assertEquals("09077745367", customer.get().getSsn());

    }


    @Override
    protected Application configure() {
        ResourceConfig config = new ResourceConfig();
        config.register(CustomerResource.class);
        config.register(ProductResource.class);
        
        return config;
    }

    public String personListJson() {
        return "";
    }

    private String productJson() {
        return "{\"id\" : \"1\", \"name\" : \"IPad Mini\",        \"price\" : 850}";
    }


    private String personJson() {
        return "{\n" +
                "    \"firstname\": \"thomas\",\n" +
                "    \"lastname\": \"andersen\",\n" +
                "    \"ssn\": \"09077745367\",\n" +
                "    \"address\": {\n" +
                "        \"street\": \"stryken\",\n" +
                "        \"number\": 45,\n" +
                "        \"city\": \"Hokksund\"\n" +
                "    }\n" +
                "}";
    }
}
