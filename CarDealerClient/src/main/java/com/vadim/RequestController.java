package com.vadim;

import java.io.*;
import java.util.ArrayList;
import java.net.*;

import com.vadim.fxml_controllers.EnterController;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.vadim.tables.SuperMMID;
import com.vadim.tables.IdMailMake;
import com.vadim.tables.Tables;

public class RequestController {
    public static User user;

    public static void Authorization(String login, String pass) throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.AUTHORIZATION);
        Client.ostream.writeObject(login);
        Client.ostream.writeObject(pass);

        String message;
        message = (String) Client.istream.readObject();
        System.out.println(message);
        if (message.equals(Constants.OK)) {
            user = (User) Client.istream.readObject();
            if (user.getStatus().equals("admin"))
                Client.setRoot("AdminMenu");
            else
                Client.setRoot("UserMenu");
        } else if (message.equals(Constants.BLOCKED)) {
            EnterController.ThrowAlert("Error", "Error", "User is blocked");
        } else {
            EnterController.ThrowAlert("Error", "Error", "User doesn't exist");
        }
    }

    public static void Registration(String name, String surname, String mail, String login, String pass) throws IOException, ClassNotFoundException {
        System.out.println(name + surname + mail + login + pass);
        Client.ostream.writeObject(Constants.REGISTRATION);
        Client.ostream.writeObject(name);
        Client.ostream.writeObject(surname);
        Client.ostream.writeObject(mail);
        Client.ostream.writeObject(login);
        Client.ostream.writeObject(pass);

        String message;
        message = (String) Client.istream.readObject();
        System.out.println(message);
        if (message.equals(Constants.OK)) {
            Client.setRoot("mainWindow");
        } else {
            EnterController.ThrowAlert("Error", "Error", "User does exist");
        }
    }

    public static void RegistrationAdmin(String name, String surname, String mail, String login, String pass) throws IOException, ClassNotFoundException {
        System.out.println(name + surname + mail + login + pass);
        Client.ostream.writeObject(Constants.REGISTRATION_ADMIN);
        Client.ostream.writeObject(name);
        Client.ostream.writeObject(surname);
        Client.ostream.writeObject(mail);
        Client.ostream.writeObject(login);
        Client.ostream.writeObject(pass);

        String message;
        message = (String) Client.istream.readObject();
        System.out.println(message);
        if (message.equals(Constants.OK)) {
            Client.setRoot("AdminMenu");
        } else {
            EnterController.ThrowAlert("Error", "Error", "User does exist");
        }
    }

    public static void UserMenu() {

    }

    public static void AdminMenu() {

    }

    public static boolean SoldCar(Car car) throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.SOLD_CAR);
        Client.ostream.writeObject(car);

        String message;
        message = (String) Client.istream.readObject();
        System.out.println(message);
        if (message.equals(Constants.OK)) {
            EnterController.ThrowMessage("", "", "Successful");
            return true;
        } else {
            EnterController.ThrowAlert("Error", "Error", "Car can't be added");
            return false;
        }
    }

    public static void DeleteAcc(Integer id) throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.DELETE_ACC);
        User us = new User(id, user.getName(), user.getSurname(), user.getMail(), user.getLogin(), user.getPass(), user.getStatus());
        Client.ostream.writeObject(us);

        String message;
        message = (String) Client.istream.readObject();
        System.out.println(message);
        Integer i = (Integer) user.getID();
        if (message.equals(Constants.OK)) {
            if (i.equals(id)) {
                Client.changeStageSize(Client.st, 640, 480);
                Client.setRoot("mainWindow");
            } else {
                EnterController.ThrowMessage("", "", "Successfully");
            }
        } else {
            EnterController.ThrowAlert("Error", "Error", "User can't be deleted");
        }
    }

    public static void ChangeUserInfo(String name, String surname, String mail, String login, String pass) throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.CHANGE_INFO);
        User us = new User(user.getId(), name, surname, mail, login, pass, user.getStatus());
        System.out.println(us.getName() + us.getSurname() + us.getMail() + " " + us.getLogin() + " " + us.getPass());

        Client.ostream.writeObject(us);

        String message;
        message = (String) Client.istream.readObject();
        System.out.println(message);
        if (message.equals(Constants.OK)) {
            user = (User) Client.istream.readObject();
            System.out.println(user.getName() + user.getSurname() + user.getMail() + " " + user.GetLogin() + " " + user.getPass());
            if (user.getStatus().equals("admin")) {
                Client.setRoot("AdminMenu");
            } else
                Client.setRoot("UserMenu");
        } else {
            EnterController.ThrowAlert("Error", "Error", "Error");
        }
    }

    public static ArrayList<Car> GetCarsTable() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_CARS_TABLE);
        ArrayList<Car> cars = (ArrayList<Car>) Client.istream.readObject();
        return cars;
    }

    public static ArrayList<Tables> GetOldTable() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_OLD_CARS_TABLE);
        ArrayList<Tables> cars = (ArrayList<Tables>) Client.istream.readObject();
        return cars;
    }

    public static void AddRequest(String user, String make, Integer iD) throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.ADD_REQUEST);
        Client.ostream.writeObject(user);
        Client.ostream.writeObject(make);
        Client.ostream.writeObject(iD);

        String message;
        message = (String) Client.istream.readObject();
        System.out.println(message);
        if (message.equals(Constants.OK)) {
            EnterController.ThrowMessage("", "", "Sended");
        } else {
            EnterController.ThrowAlert("Error", "Error", "Error");
        }
    }

    public static ArrayList<IdMailMake> GetSended() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_SENDED);
        Client.ostream.writeObject(user.getId());

        ArrayList<IdMailMake> cars = (ArrayList<IdMailMake>) Client.istream.readObject();
        return cars;
    }

    public static ArrayList<SuperMMID> GetIncoming_() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_INCOMING_REQ);
        ArrayList<SuperMMID> cars = (ArrayList<SuperMMID>) Client.istream.readObject();
        return cars;
    }

    public static ArrayList<IdMailMake> GetIncoming() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_INCOMING);
        Client.ostream.writeObject(user.getId());

        ArrayList<IdMailMake> cars = (ArrayList<IdMailMake>) Client.istream.readObject();
        return cars;
    }

    public static ArrayList<Car> GetNewCars() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_NEW_CARS_TABLE);
        ArrayList<Car> cars = (ArrayList<Car>) Client.istream.readObject();
        return cars;
    }

    public static void AddRequestNewCar(int request_id, String make, String model) throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.ADD_REQUEST_NEW_CAR);
        Client.ostream.writeObject(request_id);
        Client.ostream.writeObject(make);
        Client.ostream.writeObject(model);

        String message;
        message = (String) Client.istream.readObject();
        System.out.println(message);
        if (message.equals(Constants.OK)) {
            EnterController.ThrowMessage("", "", "Sended");
        } else {
            EnterController.ThrowAlert("Error", "Error", "Error");
        }
    }

    public static ArrayList<User> GetAllUsers() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_USERS);
        ArrayList<User> users = (ArrayList<User>) Client.istream.readObject();
        return users;
    }

    public static ArrayList<User> GetAllAdmins() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_ADMINS);
        ArrayList<User> admins = (ArrayList<User>) Client.istream.readObject();
        return admins;
    }

    public static void BlockUser(Integer id) throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.BLOCK_USER);
        Client.ostream.writeObject(id);

        String message;
        message = (String) Client.istream.readObject();
        System.out.println(message);
        if (message.equals(Constants.OK)) {
            EnterController.ThrowMessage("", "", "Blocked");
        } else {
            EnterController.ThrowAlert("Error", "Error", "Error");
        }
    }

    public static void UnblockUser(Integer id) throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.UNBLOCK_USER);
        Client.ostream.writeObject(id);

        String message;
        message = (String) Client.istream.readObject();
        System.out.println(message);
        if (message.equals(Constants.OK)) {
            EnterController.ThrowMessage("", "", "Unblocked");
        } else {
            EnterController.ThrowAlert("Error", "Error", "Error");
        }
    }

    public static void AcceptRequest(Integer id, String make) throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.ACCEPT_REQUEST);
        Client.ostream.writeObject(id);
        Client.ostream.writeObject(user.getId());
        Client.ostream.writeObject(make);

        String message;
        message = (String) Client.istream.readObject();
        System.out.println(message);
        if (message.equals(Constants.OK)) {
            EnterController.ThrowMessage("", "", "Accepted");
        } else {
            EnterController.ThrowAlert("Error", "Error", "Error");
        }
    }

    public static ArrayList<SuperMMID> GetAccepted() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_ACCEPTED_REQ);
        ArrayList<SuperMMID> accepted = (ArrayList<SuperMMID>) Client.istream.readObject();
        return accepted;
    }

    public static ArrayList<Car> getCarsTable() {
        return ;
    }

    public static Object getAccepted() {
    }
}
