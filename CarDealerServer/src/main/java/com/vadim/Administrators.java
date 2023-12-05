package com.vadim;

import java.io.Serializable;

public class Administrators implements Serializable {
    protected int id;
    protected String adminLevel;

    public Administrators(int id, String adminLevel) {
        this.id = id;
        this.adminLevel = adminLevel;
    }

    public Administrators() {
    }

    public int getId() {
        return id;
    }

    public String getAdminLevel() {
        return adminLevel;
    }
}
