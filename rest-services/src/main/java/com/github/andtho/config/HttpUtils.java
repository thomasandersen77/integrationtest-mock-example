package com.github.andtho.config;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;

import javax.inject.Inject;

@Component
public class HttpUtils {

    @Inject private PropertyReader propertyReader;

    public static int dynamicPort() {
        try {
            @Cleanup ServerSocket serverSocket = new ServerSocket(0);
            return serverSocket.getLocalPort();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void overrideUrl(String key, int port, String path) {
        if(propertyReader.getProperty(key) != null) {
            String newUrl = "http://localhost:" + port + "/" + path;
            System.setProperty(key, newUrl);
        }

    }
}
