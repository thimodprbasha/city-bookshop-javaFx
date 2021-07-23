package com.company.models;

import java.io.Serializable;

abstract class User implements Serializable {
    private String name;
    private String pass;
    private String address;
    private int age;
    private int telNumber;


    public User(String name, String pass, String address, int age, int telNumber) {
        this.name = name;
        this.pass = pass;
        this.address = address;
        this.age = age;
        this.telNumber = telNumber;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(int telNumber) {
        this.telNumber = telNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", telNumber=" + telNumber +
                '}';
    }
}
