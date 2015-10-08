package com.github.andtho;

import com.github.andtho.config.PropertyReader;
import com.github.andtho.resources.PersonResource;
import com.github.andtho.resources.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.inject.Singleton;

@Slf4j
@Configuration
@ComponentScan(basePackages = "com.github.andtho")
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class BeanConfiguration {

    @Bean
    @Singleton
    public PropertyReader propertyReader() {
        return new PropertyReader();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public PersonResource customerResource(){
        PersonResource personResource = new PersonResource();
        personResource.setPersonService(customerService());
        return personResource;
    }

    @Bean
    public PersonService customerService(){
        return new PersonService();
    }
}


