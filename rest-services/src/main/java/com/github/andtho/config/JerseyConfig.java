package com.github.andtho.config;

import com.github.andtho.resources.CustomerResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        register(CustomerResource.class);
    }
}
