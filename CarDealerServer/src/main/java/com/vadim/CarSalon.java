package com.vadim;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CarSalon {
    ObjectOutputStream ostream;
    ObjectInputStream istream;
    private Socket client;

    public CarSalon(Socket client) {
        this.client = client;
    }

    public void getRequest() {
        String request;
        int index = 1;
    }
}
