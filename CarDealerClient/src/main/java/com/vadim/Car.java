package com.vadim;

import java.io.Serializable;

public class Car implements Serializable {
    protected String make;
    protected String model;
    protected String body;
    protected Integer price;
    protected Integer userID;

    public Car(String make2, String model2, String body2, Integer price2, Integer userID2) {
        make = make2;
        model = model2;
        body = body2;
        price = price2;
        userID = userID2;
    }

    public Car(String make2, String model2, String body2, Integer price2) {
        make = make2;
        model = model2;
        body = body2;
        price = price2;
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

}
