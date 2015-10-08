package com.github.andtho.config;

import org.springframework.stereotype.Component;
import wiremock.org.apache.commons.lang.StringUtils;

import javax.el.PropertyNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@Component
public class ResourceLocator {
    Properties properties;

    public ResourceLocator() {
        try {
            this.properties = new Properties();
            this.properties.load(ResourceLocator.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String key) {
        Objects.requireNonNull(properties, "Properties not loaded");
        String overriddenProperty = System.getProperty(key);
        if(overriddenProperty != null && StringUtils.isNotEmpty(overriddenProperty)) {
            return overriddenProperty;
        } else if( StringUtils.isNotEmpty(properties.getProperty(key))){
            return properties.getProperty(key);
        } else {
            throw new PropertyNotFoundException(key);
        }

    }
}
