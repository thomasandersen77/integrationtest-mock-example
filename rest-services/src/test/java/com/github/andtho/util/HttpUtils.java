package com.github.andtho.util;

import lombok.Cleanup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;

@Component
public class HttpUtils {

    public static int dynamicPort() {
        try {
            @Cleanup ServerSocket serverSocket = new ServerSocket(0);
            return serverSocket.getLocalPort();
        } catch (IOException e) {
            throw new RuntimeException("Could not allocate port", e);
        }
    }
}
