package com.vadim.tables;

import java.io.Serializable;

public class IdMailMake implements Serializable {
    private String make;
    private String mail;
    private Integer id;

    public IdMailMake(Integer i, String ml, String mk) {
        id = i;
        mail = ml;
        make = ml;
    }

    public String getMake() {
        return make;
    }

    public Integer getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }
}
