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
        CarSalon carsalon = new CarSalon(client);

        try {
            carsalon.getRequest();
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
