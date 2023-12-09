package com.vadim.bd;

import java.sql.*;
import java.sql.Statement;

import com.vadim.Car;
import com.vadim.User;
import com.vadim.tables.Tables;
import com.vadim.tables.SuperMMID;
import com.vadim.tables.IdMailMake;

import java.util.ArrayList;
import java.util.Optional;

public class DatabaseController {

    public static User GetUser(String login, String pass) {
        Statement stmt = null;
        int id;
        String name;
        String surname;
        String mail;
        String status;

        User user;
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            ResultSet res1 = stmt.executeQuery("SELECT * FROM black_list WHERE login = '%s'".formatted(login));

            while (res1.next()) {
                System.out.println("Success zahod");
                if (res1.getString("login").equals(login)) {
                    User us = new User();
                    us.setLogin("blocked");
                    return us;
                }
                break;
            }

            ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE login = '%s' AND pass = '%s'".formatted(login, pass));

            while (res.next()) {
                id = res.getInt("id");
                name = res.getString("first_name");
                surname = res.getString("last_name");
                mail = res.getString("mail");
                status = res.getString("status");
                user = new User(id, name, surname, mail, login, pass, status);
                return user;
            }
            return new User();
        } catch (SQLException se) {
            se.printStackTrace();
            return new User();
        }
    }

    public static boolean AddAdmin(String name, String surname, String mail, String login, String pass) {
        Statement stmt = null;

        User user;
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            int i = stmt.executeUpdate("INSERT INTO users(first_name,last_name,mail,login,pass,status) VALUES('%s','%s','%s','%s','%s','admin');".formatted(name, surname, mail, login, pass));
            if (i == 0) return false;
            else return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Car> GetAllCars() {
        Statement stmt = null;
        String make, model, body;
        Integer price, userID;

        Car car;
        ArrayList<Car> cars = new ArrayList<>();
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM cars");

            while (res.next()) {
                make = res.getString("make");
                model = res.getString("model");
                body = res.getString("body");
                price = res.getInt("price");
                userID = res.getInt("user_id");
                car = new Car(make, model, body, price, userID);
                cars.add(car);
            }
            return cars;
        } catch (SQLException se) {
            se.printStackTrace();
            return cars;

        }
    }

    public static boolean AddCarToSoldlist(Car car) {
        Statement stmt = null;
        String make = car.getMake();
        String model = car.getModel();
        String body = car.getBody();

        Integer price = car.getPrice();
        Integer user_id = car.getUserID();
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            int i = stmt.executeUpdate("INSERT INTO cars(make,model,body,price,user_id) VALUES('%s','%s','%s','%d','%d');".formatted(make, model, body, price, user_id));
            if (i == 0) {
                return false;
            }
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    public static boolean DeleteAccFromDB(User user) {
        Statement stmt = null;
        Integer id = user.getId();


        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            int i = stmt.executeUpdate("DELETE FROM users WHERE id='%d'".formatted(id));
            if (i == 0) {
                return false;
            }
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    public static boolean ChangeUserInfo(User user) {
        Statement stmt = null;
        Integer id = user.getId();
        int i = 0;


        try {
            JDBC.connect();
            JDBC.connection.createStatement();

            if (!user.getName().isEmpty())
                i += stmt.executeUpdate("UPDATE users SET first_name = '%s', last_name ='%s' WHERE id = '%d'".formatted(user.getName(), user.getSurname(), id));

            if (!user.getMail().isEmpty())
                i += stmt.executeUpdate("UPDATE users SET mail = '%s' WHERE id = '%d'".formatted(user.getMail(), id));

            if (!user.getLogin().isEmpty())
                i += stmt.executeUpdate("UPDATE users SET login = '%s' WHERE id = '%d'".formatted(user.getLogin(), id));

            if (!user.getPass().isEmpty())
                i += stmt.executeUpdate("UPDATE users SET pass = '%s' WHERE id = '%d'".formatted(user.getPass(), id));

            ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE id='%d'".formatted(id));
            String name = "", surname = "", mail = "", login = "", pass = "";
            while (res.next()) {
                //id = res.getInt("id");
                name = res.getString("first_name");
                surname = res.getString("last_name");
                mail = res.getString("mail");
                login = res.getString("login");
                pass = res.getString("pass");
                //user.User(id,name,surname,mail,login,pass);
                user.setId(id);
                user.setName(name);
                user.setSurname(surname);
                user.setMail(mail);
                user.setLogin(login);
                user.setPass(pass);
                break;
            }
            System.out.println(user.getName() + user.getSurname() + user.getMail() + " " + user.GetLogin() + " " + user.getPass());
            if (i == 0) {
                return false;
            }
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }


}
