package com.examen.modules.gender.domain;

public class Gender {
    int id;
    String type;

    public Gender (){}

    public Gender (String type){
        this.type = type;
    }

    public Gender(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
