package com.github.andtho;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.github.andtho")
@SpringBootApplication
public class MainAppConfiguration extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MainAppConfiguration.class);
    }

    public static void main(String[] args) {
        new MainAppConfiguration().configure(
                new SpringApplicationBuilder(MainAppConfiguration.class)).run(args);
    }

}
