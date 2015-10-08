package com.github.andtho;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;


@SpringBootApplication
public class AppMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AppMain.class);
    }

    public static void main(String[] args) {
        new AppMain().configure(
                new SpringApplicationBuilder(AppMain.class)).run(args);
    }

}
