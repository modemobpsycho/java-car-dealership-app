package com.vadim;

import java.io.IOException;
import java.net.Socket;

public class Server extends Thread {
    private Socket client;

    public Server(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        Carsalon carsalon = new Carsalon(client);

        try {
            carsalon.GetRequest();
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
