package com.vadim;


import java.io.*;
import java.util.ArrayList;

import com.vadim.fxml_controllers.EnterController;
import com.vadim.tables.IdMailMake;
import com.vadim.tables.SuperMMID;
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
            // System.out.println("mda");;
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
            // System.out.println("mda");;
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
            // System.out.println("mda");;
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
            EnterController.ThrowMessage("", "", "Succesfull");
            return true;
        } else {
            EnterController.ThrowAlert("Error", "Error", "Car can't be added");
            return false;
            // System.out.println("mda");;
        }
    }

    public static void DeleteAcc(Integer id) throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.DELETE_ACC);
        User us = new User(id, user.getName(), user.getSurname(), user.getMail(), user.getLogin(), user.getPass(), user.getStatus());
        Client.ostream.writeObject(us);

        String message;
        message = (String) Client.istream.readObject();
        System.out.println(message);
        Integer i = (Integer) user.getId();
        if (message.equals(Constants.OK)) {
            if (i.equals(id)) {
                Client.changeStageSize(Client.st, 640, 480);
                Client.setRoot("mainWindow");
            } else {
                EnterController.ThrowMessage("", "", "Succesfull");
            }
        } else {
            EnterController.ThrowAlert("Error", "Error", "User can't be deleted");
            // System.out.println("mda");;
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
            if (user.getStatus().equals("admin"))
                Client.setRoot("AdminMenu");
            else
                Client.setRoot("UserMenu");
        } else {
            EnterController.ThrowAlert("Error", "Error", "Error");
            // System.out.println("mda");;
        }
    }

    public static ArrayList<Car> GetCarsTable() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_CARS_TABLE);
        ArrayList<Car> cars = (ArrayList<Car>) Client.istream.readObject();
        return cars;
    }

    public static ArrayList<Tables> GetOldCars() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_OLD_CARS_TABLE);
        ArrayList<Tables> cars = (ArrayList<Tables>) Client.istream.readObject();
        return cars;
    }


    public static void AddRequest(Integer user, String make, Integer iD) throws IOException, ClassNotFoundException {
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
            // System.out.println("mda");;
        }
    }

    public static ArrayList<IdMailMake> GetSended() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_SENDED);
        Client.ostream.writeObject(user.getId());

        ArrayList<IdMailMake> cars = (ArrayList<IdMailMake>) Client.istream.readObject();
        return cars;
    }

    public static ArrayList<IdMailMake> GetIncoming() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_INCOMING);
        Client.ostream.writeObject(user.getId());

        ArrayList<IdMailMake> cars = (ArrayList<IdMailMake>) Client.istream.readObject();
        return cars;
    }

    public static ArrayList<SuperMMID> GetIncoming_() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_INCOMING_REQ);

        ArrayList<SuperMMID> cars = (ArrayList<SuperMMID>) Client.istream.readObject();
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
            EnterController.ThrowAlert("Error", "Error", "Error, try again");
            // System.out.println("mda");;
        }
    }

    public static ArrayList<User> GetAllUsers() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_USERS);
        // Client.ostream.writeObject(user.getId());

        ArrayList<User> users = (ArrayList<User>) Client.istream.readObject();
        return users;
    }

    public static ArrayList<User> GetAllAdmins() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_ADMINS);
        // Client.ostream.writeObject(user.getId());

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
            EnterController.ThrowAlert("Error", "Error", "Error, try again");
            // System.out.println("mda");;
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
            EnterController.ThrowAlert("Error", "Error", "Doesn't exist");
            // System.out.println("mda");;
        }
    }

    public static void AcceptRequest(Integer id, String make) throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.ACCEPT_REQUEST);
        Client.ostream.writeObject(id);
        Client.ostream.writeInt(user.getId());
        Client.ostream.writeObject(make);


        String message;
        message = (String) Client.istream.readObject();
        System.out.println(message);
        if (message.equals(Constants.OK)) {
            EnterController.ThrowMessage("", "", "Accepted");
        } else {
            EnterController.ThrowAlert("Error", "Error", "Error");
            // System.out.println("mda");;
        }
    }

    public static ArrayList<SuperMMID> GetAccepted() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject(Constants.GET_ACCEPTED_REQ);
        Client.ostream.writeObject(user.getId());

        ArrayList<SuperMMID> accepted = (ArrayList<SuperMMID>) Client.istream.readObject();
        return accepted;
    }


}
