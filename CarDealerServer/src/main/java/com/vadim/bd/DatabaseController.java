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
        }
        catch (SQLException se) {
            se.printStackTrace();
            return new User();
        }
    }


}
