package com.github.andtho.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class JsonUtil {
    static ObjectMapper mapper = new ObjectMapper();

    public static <T> Optional<String> toJson(T type, boolean pretty) {
        if(pretty) {
            try {
                return Optional.of(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(type));
            } catch (JsonProcessingException e) {
                log.error("Exception writing {} as Json. Exception: ", type, e);
            }
        } else {
            try {
                return Optional.of(mapper.writeValueAsString(type));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

}
