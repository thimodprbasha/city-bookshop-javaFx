package com.company.models;

public class Cashier extends User {
    private String cashierId;

    public Cashier(String name, String pass, String address, int age, int telNumber, String cashierId) {
        super(name, pass, address, age, telNumber);
        this.cashierId = cashierId;
    }

    public String getCashierId() {
        return cashierId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "cashierId='" + cashierId + '\'' +
                '}';
    }
}
