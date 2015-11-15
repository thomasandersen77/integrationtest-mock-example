package com.github.andtho;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;


@SpringBootApplication
@EnableAutoConfiguration
public class MockdataMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MockdataMain.class);
    }
    public static void main(String[] args) {
        new MockdataMain().configure(
                new SpringApplicationBuilder(MockdataMain.class)).run(args);
    }

}
