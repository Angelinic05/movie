package com.examen.modules.country.domain;

public class Country {
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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
