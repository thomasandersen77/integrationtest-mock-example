package com.github.andtho.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {
    static ObjectWriter o;
    static{
        o = new ObjectMapper().writerWithDefaultPrettyPrinter().without(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    public static  <T> String toJson(T t){
        try {
            return o.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
