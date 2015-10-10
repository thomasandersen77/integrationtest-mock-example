package com.github.andtho;

import com.github.andtho.resources.CustomerResource;
import com.github.andtho.resources.ProductResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        register(CustomerResource.class);
        register(ProductResource.class);
    }
}
