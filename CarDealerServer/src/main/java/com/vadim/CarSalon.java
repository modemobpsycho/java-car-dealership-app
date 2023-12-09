package com.vadim;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.vadim.bd.DatabaseController;
import com.vadim.tables.*;

import javax.xml.crypto.Data;

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
                    System.out.println("AUTHORIZATION");

                    String login, pass;
                    login = (String) istream.readObject();
                    pass = (String) istream.readObject();
                    System.out.println(login + " " + pass);
                    Authorization(login, pass);
                    System.out.println("break_first");
                    continue;

                } else if (request.equals(Constants.REGISTRATION)) {
                    System.out.println("REG");

                    String name, surname, mail, login, pass;
                    name = (String) istream.readObject();
                    surname = (String) istream.readObject();
                    mail = (String) istream.readObject();
                    login = (String) istream.readObject();
                    pass = (String) istream.readObject();
                    System.out.println(name + surname + mail + login + pass);
                    Registration(name, surname, mail, login, pass);
                    continue;

                } else if (request.equals(Constants.REGISTRATION_ADMIN)) {
                    System.out.println("REG_ADMIN");

                    String name, surname, mail, login, pass;
                    name = (String) istream.readObject();
                    surname = (String) istream.readObject();
                    mail = (String) istream.readObject();
                    login = (String) istream.readObject();
                    pass = (String) istream.readObject();
                    System.out.println(name + surname + mail + login + pass);
                    RegistrationAdmin(name, surname, mail, login, pass);
                    continue;

                } else if (request.equals(Constants.SOLD_CAR)) {
                    System.out.println("SOLD_CAR");

                    Car car = (Car) istream.readObject();
                    SoldCar(car);
                    continue;

                } else if (request.equals(Constants.DELETE_ACC)) {
                    System.out.println("DELETE_ACC");

                    User user = (User) istream.readObject();
                    DeleteAcc(user);
                    continue;

                } else if (request.equals(Constants.CHANGE_INFO)) {
                    System.out.println("CHANGE_INFO");

                    User user = (User) istream.readObject();
                    ChangeInfo(user);
                    continue;

                } else if (request.equals(Constants.GET_CARS_TABLE)) {
                    System.out.println("GET_CARS_TABLE");

                    ArrayList<Car> CarsData = DatabaseController.GetAllCars();
                    ostream.writeObject(CarsData);
                    continue;

                } else if (request.equals(Constants.GET_OLD_CARS_TABLE)) {
                    System.out.println("GET_OLD_CARS_TABLE");

                    ArrayList<Tables> CarsData = DatabaseController.GetCarsByStatus("user");
                    ostream.writeObject(CarsData);
                    continue;

                } else if (request.equals(Constants.GET_NEW_CARS_TABLE)) {
                    System.out.println("GET_NEW_CARS_TABLE");

                    ArrayList<Tables> CarsData = DatabaseController.GetCarsByStatus("admin");
                    ArrayList<Car> c = new ArrayList<>();
                    for (Tables f : CarsData) {
                        c.add(new Car(f.getMake(), f.getModel(), f.getBody(), f.getPrice()));
                        System.out.println(f.getMake() + f.getModel() + f.getBody() + f.getPrice());
                    }
                    ostream.writeObject(c);
                    continue;

                } else if (request.equals(Constants.ADD_REQUEST)) {
                    System.out.println("ADD_REQUEST");

                    Integer user = (Integer) istream.readObject();
                    String make = (String) istream.readObject();
                    Integer ID = (Integer) istream.readObject();
                    System.out.println(user + make + ID);
                    AddRequest(user, make, ID);
                    continue;

                } else if (request.equals(Constants.ADD_REQUEST_NEW_CAR)) {
                    System.out.println("ADD_REQUEST_NEW_CAR");

                    Integer request_id = (Integer) istream.readObject();
                    String make = (String) istream.readObject();
                    String model = (String) istream.readObject();
                    AddRequestNewCar(request_id, make, model);
                    continue;

                } else if (request.equals(Constants.GET_SENDED)) {
                    System.out.println("GET_SENDED");

                    Integer user_id = (Integer) istream.readObject();
                    ArrayList<IdMailMake> sended = DatabaseController.GetSended(user_id);
                    ostream.writeObject(sended);
                    continue;

                } else if (request.equals(Constants.GET_INCOMING)) {
                    System.out.println("GET_INCOMING");

                    Integer user_id = (Integer) istream.readObject();
                    ArrayList<IdMailMake> incoming = DatabaseController.GetIncoming(user_id);
                    ostream.writeObject(incoming);
                    continue;

                } else if (request.equals(Constants.GET_ACCEPTED_REQ)) {
                    System.out.println("GET_ACCEPTED_REQ");

                    Integer user_id = (Integer) istream.readObject();
                    ArrayList<SuperMMID> accepted = DatabaseController.GetAccepted(user_id);
                    ostream.writeObject(accepted);
                    continue;

                } else if (request.equals(Constants.GET_INCOMING_REQ)) {
                    System.out.println("GET_INCOMING_REQ");

                    Integer user_id = (Integer) istream.readObject();
                    ArrayList<IdMailMake> sended = DatabaseController.GetSended(user_id);
                    ostream.writeObject(sended);
                    continue;

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

    public void Registration(String name, String surname, String mail, String login, String pass) {

    }

    public void RegistrationAdmin(String name, String surname, String mail, String login, String pass) {

    }

    private void SoldCar(Car car) {
    }

    private void DeleteAcc(User user) {
    }

    private void ChangeInfo(User user) {
    }

    private void AddRequest(Integer user, String make, Integer ID) {

    }

    private void AddRequestNewCar(Integer requestId, String make, String model) {
    }


}
