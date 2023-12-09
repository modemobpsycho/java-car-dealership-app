package com.vadim;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.vadim.bd.DatabaseController;
import com.vadim.tables.*;

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

        try {
            ostream = new ObjectOutputStream(client.getOutputStream());
            istream = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                System.out.println(index++);
                request = (String) istream.readObject();

                if (request.equals(Constants.AUTHORIZATION)) {
                    String login, pass;
                    login = (String) istream.readObject();
                    pass = (String) istream.readObject();
                    System.out.println(login + " " + pass);

                } else if (request.equals(Constants.REGISTRATION)) {

                } else if (request.equals(Constants.REGISTRATION_ADMIN)) {

                } else if (request.equals(Constants.SOLD_CAR)) {

                } else if (request.equals(Constants.DELETE_ACC)) {

                } else if (request.equals(Constants.CHANGE_INFO)) {

                } else if (request.equals(Constants.GET_CARS_TABLE)) {

                } else if (request.equals(Constants.GET_OLD_CARS_TABLE)) {

                } else if (request.equals(Constants.GET_NEW_CARS_TABLE)) {

                } else if (request.equals(Constants.ADD_REQUEST)) {

                } else if (request.equals(Constants.ADD_REQUEST_NEW_CAR)) {

                } else if (request.equals(Constants.GET_INCOMING)) {

                } else if (request.equals(Constants.GET_SENDED)) {

                } else if (request.equals(Constants.GET_ACCEPTED_REQ)) {

                } else if (request.equals(Constants.GET_INCOMING_REQ)) {

                } else if (request.equals(Constants.GET_USERS)) {

                } else if (request.equals(Constants.GET_ADMINS)) {

                } else if (request.equals(Constants.BLOCK_USER)) {

                } else if (request.equals(Constants.UNBLOCK_USER)) {

                } else if (request.equals(Constants.ACCEPT_REQUEST)) {

                }
                ostream.close();
                istream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void Authorization(String login, String pass) {

    }
}
