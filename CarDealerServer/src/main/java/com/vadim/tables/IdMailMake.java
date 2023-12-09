package com.vadim.tables;

import java.io.Serializable;

public class IdMailMake implements Serializable {
    private String make;
    private String mail;
    private Integer id;

    public IdMailMake(String mk, Integer i, String ml) {
        make = mk;
        id = i;
        mail = ml;
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
