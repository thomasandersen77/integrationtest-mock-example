package com.github.andtho.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Builder;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Response<T> {
    private int status;
    private T jsonBody;

    @JsonProperty("headers")
    private Headers headers;

    @Builder @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter @Setter
    public static class Headers {

        @JsonProperty("Content-Type")
        private String contentType;
    }

}
