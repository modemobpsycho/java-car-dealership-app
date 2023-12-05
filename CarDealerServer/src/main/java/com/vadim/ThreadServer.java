package com.vadim;

import java.io.*;
import java.net.*;
import java.util.*;

public class ThreadServer {
    private static List<Server> clientList = new ArrayList<>();
    private static ServerSocket serverSocket;

    private static void start(int port) throws IOException {
        if (serverSocket != null) {
            return;
        }
        serverSocket = new ServerSocket(port);
    }

    public static void main(String[] args) throws IOException {
        start(8080);
        acceptingLoop();
    }

    private static void acceptingLoop() throws IOException {
        while (true) {
            Socket client = serverSocket.accept();
            Server server = new Server(client);
            clientList.add(server);
            server.start();
        }
    }
}
