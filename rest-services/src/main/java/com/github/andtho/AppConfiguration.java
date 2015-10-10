package com.github.andtho;

import com.github.andtho.resources.CustomerResource;
import com.github.andtho.resources.IntegrationService;
import com.github.andtho.resources.ProductResource;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import javax.ws.rs.client.Client;

@Slf4j
@Configuration
@ComponentScan(basePackages = "com.github.andtho")
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy
public class AppConfiguration {

    @Inject
    Environment environment;

    public String getCustomerApi_url() {
        return environment.getProperty("customer.api.url");
    }

    public String getProductApi_url() {
        return environment.getProperty("product.api.url");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public CustomerResource personResource(){
        return new CustomerResource();
    }

    @Bean
    public ProductResource productResource(){
        return new ProductResource();
    }

    @Bean
    public IntegrationService integrationService(){
        return new IntegrationService();
    }

    @Bean
    public Client createClient(){
        return new JerseyClientBuilder().build();
    }

}


