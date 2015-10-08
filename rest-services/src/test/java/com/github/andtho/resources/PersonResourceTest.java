package com.github.andtho.resources;


import com.github.andtho.BeanConfiguration;
import com.github.andtho.HttpUtil;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.inmemory.InMemoryTestContainerFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {BeanConfiguration.class})
public class PersonResourceTest extends JerseyTest {

    public static final int PORT_NUMBER = HttpUtil.dynamicPort();

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(
            WireMockConfiguration.wireMockConfig().port(PORT_NUMBER).bindAddress("localhost"));

    public PersonResourceTest() {
        super(new InMemoryTestContainerFactory());
        set(TestProperties.LOG_TRAFFIC, Boolean.TRUE);
    }

    @Override
    protected Application configure() {
        ResourceConfig config = new ResourceConfig();
        config.register(PersonResource.class);

        return config;
    }

    @Test
    public void test_get_person_by_ssn() throws Exception {
        System.setProperty("personinfo.service.url", "http://localhost:"+PORT_NUMBER+"/personinfo");

        stubFor(get(urlEqualTo("/personinfo/09077745367"))
                .withHeader("accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(personJson())));

        Response response = target("/person/09077745367")
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(Response.class);

        assertEquals(200, response.getStatus());


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
