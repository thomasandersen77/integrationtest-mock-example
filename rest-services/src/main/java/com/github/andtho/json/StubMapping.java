package com.github.andtho.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;

@Builder
@Getter @Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class StubMapping {

    private Request request;
    private Response response;

    public StubMapping() {
    }

    @SuppressWarnings("unchecked")
    @JsonIgnore
    public <T> void setJsonResponseBody(T type) {
        response.setJsonBody(type);
    }
}
