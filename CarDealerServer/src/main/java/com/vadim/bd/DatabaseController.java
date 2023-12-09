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

    public static ArrayList<String> GetMailsByStatus(String status) {
        Statement stmt = null;
        String mail;

        Car car;
        ArrayList<String> mails = new ArrayList<>();
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            ResultSet res = stmt.executeQuery("SELECT users.mail FROM users WHERE users.status = '%s'".formatted(status));

            while (res.next()) {
                mail = res.getString("mail");
                mails.add(mail);
            }
            return mails;
        } catch (SQLException se) {
            se.printStackTrace();
            return mails;
        }
    }

    public static ArrayList<Tables> GetCarsByStatus(String status) {
        Statement stmt = null;
        String make, model, body, mail;
        Integer price, userID;

        Tables car;
        ArrayList<Tables> cars = new ArrayList<>();
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            ResultSet res = stmt.executeQuery("SELECT cars.make, cars.model, cars.body, cars.price, cars.user_id, users.mail FROM cars INNER JOIN users ON cars.user_id = users.id AND users.status = '%s'".formatted(status));

            while (res.next()) {
                make = res.getString("make");
                model = res.getString("model");
                body = res.getString("body");
                price = res.getInt("price");
                userID = res.getInt("user_id");
                mail = res.getString("mail");
                System.out.println(make + " " + model);
                car = new Tables(make, model, body, price, mail, userID);
                cars.add(car);
            }
            return cars;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return cars;
        }
    }

    public static boolean AddRequest(Integer user, String make, Integer iD) {
        Statement stmt = null;

        String make1;
        Integer user_id;
        boolean exist = false;
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            ResultSet res = stmt.executeQuery("SELECT cars.make, cars.model, cars.user_id FROM cars INNER JOIN users ON cars.user_id = users.id AND users.status = 'user'");

            while (res.next()) {
                make1 = res.getString("make");
                user_id = res.getInt("user_id");
                System.out.println(make1 + " " + make + " " + user_id + " " + iD);
                if (make.equals(make1) && user_id.equals(iD)) {
                    exist = true;
                    break;
                }
            }
            System.out.println(exist);
            if (!exist) return false;
            else {
                int i = stmt.executeUpdate("INSERT INTO requests(user_id, request_id, make) VALUES('%d','%d','%s');".formatted(iD, user, make));
                if (i == 0) {
                    return false;
                }
                return true;
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    public static boolean AddRequestNewCar(Integer request_id, String make, String model) {
        Statement stmt = null;

        String make1, model1;
        Integer admin_id = 0;
        boolean exist = false;

        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            ResultSet res = stmt.executeQuery("SELECT cars.make, cars.model, cars.user_id FROM cars INNER JOIN users ON cars.user_id = users.id AND users.status = 'admin'");

            while (res.next()) {
                make1 = res.getString("make");
                model1 = res.getString("model");
                if (make.equals(make1) && model.equals(model1)) {
                    admin_id = res.getInt("user_id");
                    exist = true;
                    break;
                }
            }
            if (!exist) return false;
            else {
                int i = stmt.executeUpdate("INSERT INTO requests(user_id, request_id, make) VALUES('%d','%d','%s');".formatted(admin_id, request_id, make));
                if (i == 0) {
                    return false;
                }
                return true;
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    public static ArrayList<IdMailMake> GetSended(Integer user_id) {
        // SELECT requests.request_id, requests.make, users.mail FROM requests INNER JOIN users ON requests.user_id = users.user_id AND requests.user_id = '%d';

        Statement stmt = null;
        String make, mail;
        Integer request_id;

        IdMailMake car;
        ArrayList<IdMailMake> cars = new ArrayList<>();
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            System.out.println(user_id);
            String str = Integer.toString(user_id);
            ResultSet res = stmt.executeQuery("SELECT requests.user_id, requests.make, requests.request_id, requests.status, users.mail FROM users INNER JOIN requests ON requests.user_id = users.id AND requests.request_id = '%s' AND requests.status='0'".formatted(str));

            while (res.next()) {
                make = res.getString("make");
                mail = res.getString("mail");
                request_id = res.getInt("user_id");
                System.out.println(make + request_id + mail);
                car = new IdMailMake(make, request_id, mail);
                cars.add(car);
            }
            return cars;
        } catch (SQLException se) {
            se.printStackTrace();
            return cars;
        }
    }

    public static ArrayList<IdMailMake> GetIncoming(Integer user_id) {
        Statement stmt = null;
        String make, mail;
        Integer request_id;

        IdMailMake car;
        ArrayList<IdMailMake> cars = new ArrayList<>();
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            System.out.println(user_id);
            String str = Integer.toString(user_id);
            ResultSet res = stmt.executeQuery("SELECT requests.user_id, requests.make, requests.request_id, users.mail FROM users INNER JOIN requests ON requests.request_id = users.id AND requests.user_id = '%s'".formatted(str));

            while (res.next()) {
                make = res.getString("make");
                mail = res.getString("mail");
                request_id = res.getInt("request_id");
                System.out.println(make + request_id + mail);
                car = new IdMailMake(make, request_id, mail);
                cars.add(car);
            }
            return cars;
        } catch (SQLException se) {
            se.printStackTrace();
            return cars;
        }
    }

    public static ArrayList<SuperMMID> GetIncoming_() {
        Statement stmt = null;
        String make, model, mail;
        Integer id;

        SuperMMID car;
        ArrayList<SuperMMID> cars = new ArrayList<>();
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            ResultSet res = stmt.executeQuery("SELECT	requests.make, requests.request_id, users.mail FROM users INNER JOIN requests ON requests.user_id=users.id AND users.status='admin' AND requests.status='0'");

            while (res.next()) {
                make = res.getString("make");
                mail = res.getString("mail");
                model = "";
                id = res.getInt("request_id");

                car = new SuperMMID(make, model, id, mail);
                cars.add(car);
            }
            return cars;
        } catch (SQLException se) {
            se.printStackTrace();
            return cars;
        }
    }

    public static ArrayList<User> GetAllUsers() {
        Statement stmt = null;
        String mail, login, name, surname;
        Integer id;

        ArrayList<User> users = new ArrayList<>();
        User user;
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE status = 'user'");

            while (res.next()) {
                id = res.getInt("id");
                name = res.getString("first_name");
                surname = res.getString("last_name");
                mail = res.getString("mail");
                login = res.getString("login");
                user = new User(name, surname, mail, login);
                user.setId(id);
                users.add(user);
            }
            return users;
        } catch (SQLException se) {
            se.printStackTrace();
            return users;
        }
    }

    public static ArrayList<User> GetAllAdmins() {
        Statement stmt = null;
        String mail, login, name, surname;
        Integer id;

        ArrayList<User> users = new ArrayList<>();
        User user;
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE status = 'admin'");

            while (res.next()) {
                id = res.getInt("id");
                name = res.getString("first_name");
                surname = res.getString("last_name");
                mail = res.getString("mail");
                login = res.getString("login");
                user = new User(name, surname, mail, login);
                user.setId(id);
                users.add(user);
            }
            return users;
        } catch (SQLException se) {
            se.printStackTrace();
            return users;
        }
    }

    public static boolean BlockUser(Integer id) {
        Statement stmt = null;
        String login = "";
        boolean exist = false;

        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            ResultSet res = stmt.executeQuery("SELECT id, login FROM users where id='%d'".formatted(id));

            while (res.next()) {
                login = res.getString("login");
                exist = true;
                break;
            }
            if (!exist) {
                return false;
            } else {
                int i = stmt.executeUpdate("INSERT INTO black_list(id,login) VALUES('%d','%s')".formatted(id, login));
                if (i == 0) {
                    return false;
                }
                return true;
            }
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    public static boolean UnblockUser(Integer id) {
        Statement stmt = null;

        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();


            int i = stmt.executeUpdate("DELETE FROM black_list WHERE id='%d'".formatted(id));
            System.out.println(i);
            if (i == 0) {
                return false;
            }
            return true;

        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    public static boolean AcceptRequest(Integer accept_id, int user_id, String make) {
        Statement stmt = null;

        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();


            int i = stmt.executeUpdate("UPDATE requests SET status = '1' WHERE user_id = '%d' AND request_id = '%d' AND make = '%s'".formatted(user_id, accept_id, make));
            System.out.println(i);
            if (i == 0) {
                return false;
            }
            return true;

        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }
    }

    public static ArrayList<SuperMMID> GetAccepted(Integer user_id) {
        Statement stmt = null;
        String make, model;
        Integer request_id;

        SuperMMID car;
        ArrayList<SuperMMID> cars = new ArrayList<>();
        try {
            JDBC.connect();
            stmt = JDBC.connection.createStatement();
            System.out.println(user_id);
            ResultSet res = stmt.executeQuery("SELECT requests.make, cars.model, requests.request_id FROM requests INNER JOIN cars ON requests.make = cars.make AND requests.user_id = '%d' AND requests.status='1'".formatted(user_id));

            while (res.next()) {
                make = res.getString("make");
                model = res.getString("model");
                request_id = res.getInt("request_id");

                car = new SuperMMID(make, model, request_id, "");
                cars.add(car);
            }
            return cars;
        } catch (SQLException se) {
            se.printStackTrace();
            return cars;
        }
    }
}
