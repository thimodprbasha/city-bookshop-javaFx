package com.company.models;

public class Manager extends User {
    private String managerId;

    public Manager(String name, String pass, String address, int age, int telNumber, String managerId) {
        super(name, pass, address, age, telNumber);
        this.managerId = managerId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId='" + managerId + '\'' +
                '}';
    }
}
