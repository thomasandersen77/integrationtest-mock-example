package com.github.andtho.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.github.andtho.domain.Customer;
import com.github.andtho.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class StubMappingJsonSchemaParserTest {

    @Test
    public void setUp() throws Exception {
        StubMappingJsonSchemaParser pjp = new StubMappingJsonSchemaParser();
        JsonSchema jsonSchema = pjp.getJsonSchema(Customer.class);
        assertNotNull(jsonSchema);

        String schemaAsJson = pjp.getJsonSchemaAsJson(Customer.class);
        assertNotNull(schemaAsJson);
        assertTrue(schemaAsJson.contains("Customer"));
        log.info(schemaAsJson);
    }
}