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

                    ArrayList<SuperMMID> incoming = DatabaseController.GetIncoming_();
                    ostream.writeObject(incoming);
                    continue;

                } else if (request.equals(Constants.GET_USERS)) {
                    System.out.println("GET_USERS");

                    ArrayList<User> users = DatabaseController.GetAllUsers();
                    ostream.writeObject(users);
                    continue;

                } else if (request.equals(Constants.GET_ADMINS)) {
                    System.out.println("GET_ADMINS");

                    ArrayList<User> admins = DatabaseController.GetAllAdmins();
                    ostream.writeObject(admins);
                    continue;

                } else if (request.equals(Constants.BLOCK_USER)) {
                    System.out.println("BLOCK_USER");

                    Integer id = (Integer) istream.readObject();
                    BlockUser(id);
                    continue;

                } else if (request.equals(Constants.UNBLOCK_USER)) {
                    System.out.println("UNBLOCK_USER");

                    Integer id = (Integer) istream.readObject();
                    UnblockUser(id);
                    continue;

                } else if (request.equals(Constants.ACCEPT_REQUEST)) {
                    System.out.println("ACCEPT_REQUEST");

                    Integer accept_id = (Integer) istream.readObject();
                    int user_id = istream.readInt();
                    String make = (String) istream.readObject();
                    AcceptRequest(accept_id, user_id, make);
                    continue;

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
        User user;

        user = DatabaseController.GetUser(login, pass);
        System.out.println(user.GetLogin());
        try {
            if (user.getLogin().equals("blocked")) {
                ostream.writeObject(Constants.BLOCKED);
            } else {
                if (user.GetLogin().equals("")) {
                    ostream.writeObject(Constants.NOT_OK);
                } else {
                    ostream.writeObject(Constants.OK);
                    ostream.writeObject(user);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Registration(String name, String surname, String mail, String login, String pass) throws IOException, ClassNotFoundException {
        if(DatabaseController.AddUser(name, surname, mail, login, pass) == true) {
            ostream.writeObject(Constants.OK);
        } else {
            ostream.writeObject(Constants.NOT_OK);
        }
    }

    public void RegistrationAdmin(String name, String surname, String mail, String login, String pass) throws IOException, ClassNotFoundException {
        if(DatabaseController.AddAdmin(name, surname, mail, login, pass) == true) {
            ostream.writeObject(Constants.OK);
        } else {
            ostream.writeObject(Constants.NOT_OK);
        }
    }

    private void SoldCar(Car car) throws IOException, ClassNotFoundException {
        if(DatabaseController.AddCarToSoldlist(car) == true) {
            ostream.writeObject(Constants.OK);
        } else {
            ostream.writeObject(Constants.NOT_OK);
        }
    }

    private void DeleteAcc(User user) throws IOException, ClassNotFoundException{
        if(DatabaseController.DeleteAccFromDB(user) == true) {
            ostream.writeObject(Constants.OK);
        } else {
            ostream.writeObject(Constants.NOT_OK);
        }
    }

    private void ChangeInfo(User user) throws IOException, ClassNotFoundException {
        System.out.println(user.getName() + user.getSurname() + user.getMail() + " " + user.GetLogin() + " " + user.getPass());
        if (DatabaseController.ChangeUserInfo(user) == true) {
            ostream.writeObject(Constants.OK);
            System.out.println(user.getName() + user.getSurname() + user.getMail() + " " + user.GetLogin() + " " + user.getPass());

            ostream.writeObject(user);

        } else
            ostream.writeObject(Constants.NOT_OK);
    }

    private void AddRequest(Integer user, String make, Integer ID) throws IOException, ClassNotFoundException{
        if (DatabaseController.AddRequest(user, make, ID) == true) {
            ostream.writeObject(Constants.OK);
        } else
            ostream.writeObject(Constants.NOT_OK);
    }

    private void AddRequestNewCar(Integer request_id, String make, String model) throws IOException, ClassNotFoundException {
        if (DatabaseController.AddRequestNewCar(request_id, make, model) == true) {
            ostream.writeObject(Constants.OK);
        } else
            ostream.writeObject(Constants.NOT_OK);
    }

    private void BlockUser(Integer id) throws IOException, ClassNotFoundException {
        if (DatabaseController.BlockUser(id) == true) {
            ostream.writeObject(Constants.OK);
        } else
            ostream.writeObject(Constants.NOT_OK);
    }

    private void UnblockUser(Integer id) throws IOException, ClassNotFoundException {
        if (DatabaseController.UnblockUser(id) == true) {
            ostream.writeObject(Constants.OK);
        } else
            ostream.writeObject(Constants.NOT_OK);
    }

    private void AcceptRequest(Integer accept_id, int user_id, String make) throws IOException, ClassNotFoundException {
        if (DatabaseController.AcceptRequest(accept_id, user_id, make) == true) {
            ostream.writeObject(Constants.OK);
        } else
            ostream.writeObject(Constants.NOT_OK);
    }

}
