package com.example.swara.wersolarv5;

/**
 * Created by swara on 7/20/2017.
 */
public class Person {
    private String name;
    private String address;
    private String date;

    public Person(String name, String address, String date) {
        this.name = name;
        this.address = address;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
