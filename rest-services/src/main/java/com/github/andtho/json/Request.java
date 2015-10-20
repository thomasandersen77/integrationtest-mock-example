package com.github.andtho.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Builder;

import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Request {

    private String method;
    private String urlPattern;

    @JsonProperty("headers")
    private Headers headers;

    @Builder @NoArgsConstructor @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter @Setter
    public static class Headers {
        private String accept;
    }

}
