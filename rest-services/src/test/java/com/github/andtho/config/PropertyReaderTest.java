package com.github.andtho.config;

import com.github.andtho.BeanConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BeanConfiguration.class})
public class PropertyReaderTest {

    @Autowired
    PropertyReader propertyReader;

    @Test
    public void testGetProperty() throws Exception {
        String property = propertyReader.getProperty("customer.service.url");
        assertNotNull(property);
        assertEquals("http://wiremock/customer", property);
    }

    @Test
    public void testGetEnvironmentProperty() throws Exception {
        System.setProperty("customer.service.url", "http://localhost");
        String property = propertyReader.getProperty("customer.service.url");
        assertNotNull(property);
        assertEquals("http://localhost", property);
    }
}