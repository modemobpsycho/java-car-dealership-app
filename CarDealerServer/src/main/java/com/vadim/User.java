package com.vadim;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String name = "";
    private String surname = "";
    private String mail = "";
    private String login = "";
    private String pass = "";
    private String role = "";
    private String status = "user";

    public User(int id, String name, String surname, String mail, String login, String pass, String status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.login = login;
        this.pass = pass;
        this.role = role;
        this.status = status;
    }

    public User(String name, String surname, String mail, String login) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.login = login;
    }

    public User() {
    }

    public String getStatus() {
        return status;
    }

    public String GetLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
