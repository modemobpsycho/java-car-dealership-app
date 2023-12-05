package com.vadim.tables;

public class SuperMMID extends IdMailMake {
    private String model;

    public SuperMMID(String make, String model, Integer id, String mail) {
        super(id, mail, make);
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}
