package com.github.andtho;

import com.github.andtho.config.PropertyReader;
import com.github.andtho.resources.CustomerResource;
import com.github.andtho.resources.CustomerService;
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
    public PropertyReader resourceLocator() {
        return new PropertyReader();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public CustomerResource customerResource(){
        CustomerResource customerResource = new CustomerResource();
        customerResource.setCustomerService(customerService());
        return customerResource;
    }

    @Bean
    public CustomerService customerService(){
        return new CustomerService();
    }
}


