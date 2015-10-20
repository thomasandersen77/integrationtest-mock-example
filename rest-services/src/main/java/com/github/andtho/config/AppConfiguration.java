package com.github.andtho.config;

import com.github.andtho.resources.StubMappingResource;
import com.github.andtho.resources.IntegrationService;
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
public class AppConfiguration {

    @Inject
    Environment environment;

    public String get_mapping_url() {
        return environment.getProperty("mapping.api.url");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public StubMappingResource personResource(){
        return new StubMappingResource();
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


