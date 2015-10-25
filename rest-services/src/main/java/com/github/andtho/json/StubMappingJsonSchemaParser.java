package com.github.andtho.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.fasterxml.jackson.module.jsonSchema.types.ObjectSchema;

import java.io.IOException;
import java.util.Optional;

public class StubMappingJsonSchemaParser {
    ObjectMapper m = new ObjectMapper();
    JsonSchemaGenerator visitor = new JsonSchemaGenerator(m);

    JsonSchema getJsonSchema(Class clazz) throws IOException {
        ObjectSchema objectSchema = visitor.generateSchema(StubMapping.class).asObjectSchema();
        objectSchema.putProperty(clazz.getSimpleName(), visitor.generateSchema(clazz).asObjectSchema());
        return objectSchema;
    }

    public String getJsonSchemaAsJson(Class clazz) throws IOException {
        Optional<String> json = JsonUtil.toJson(getJsonSchema(clazz), true);
        if(json.isPresent())
            return json.get();
        else
            throw new RuntimeException("Exception generating JsonSchema");
    }
}
