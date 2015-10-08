package com.github.andtho;

import lombok.Cleanup;

import java.io.IOException;
import java.net.ServerSocket;

public class HttpUtil {

    public static int dynamicPort() {
        try {
            @Cleanup ServerSocket serverSocket = new ServerSocket(0);
            return serverSocket.getLocalPort();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
