package com.github.andtho;

import com.github.andtho.resources.PersonResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        register(PersonResource.class);
    }
}
