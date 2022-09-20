package com.shend.tables;

public class MaxCallDuration {

    String tel_number;
    String name;
    String surname;
    String patronymic;
    Double max;

    public MaxCallDuration(String tel_number, String name, String surname, String patronymic, Double max) {
        this.tel_number = tel_number;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.max = max;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
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

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

}
