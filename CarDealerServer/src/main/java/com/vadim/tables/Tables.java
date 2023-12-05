package com.vadim.tables;

import java.io.Serializable;

import com.vadim.Car;

public class Tables extends Car {
    private String mail;

    public Tables(String make2, String model2, String body2, Integer price2, String mail, Integer userID2) {
        super(make2, model2, body2, price2, userID2);
        this.mail = mail;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getBody() {
        return body;
    }

    public Integer getUserID() {
        return userID;
    }

    public int getPrice() {
        return price;
    }

    public String getMail() {
        return mail;
    }
}
