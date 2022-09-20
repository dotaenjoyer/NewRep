package com.shend.entities;

public class Abonent {

    private String name;
    private String surname;
    private String patronymic;
    private String telephone;
    private Double balance;
    private String addInfo;

    public Abonent(String telephone, String name, String surname, String patronymic, Double balance, String addInfo) {
        this.telephone = telephone;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.balance = balance;
        this.addInfo = addInfo;
    }

    @Override
    public String toString() {
        return telephone;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
