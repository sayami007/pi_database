package com.planetinnovative.pi_database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserDb extends RealmObject {

    @PrimaryKey
    int id;
    String userName;
    String name;

    public void setterWithPK(int id, String userName, String name) {
        this.id = id;
        this.userName = userName;
        this.name = name;
    }

    public void setter(String userName, String name) {
        this.userName = userName;
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
