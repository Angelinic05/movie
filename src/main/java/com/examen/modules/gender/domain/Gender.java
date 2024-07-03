package com.examen.modules.gender.domain;

public class Gender {
    int id;
    String name;

    public Country (){}

    public Country (String name){
        this.name = name;
    }

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
