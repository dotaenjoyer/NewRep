package com.shend.tables;

import java.time.LocalDate;
import java.time.LocalTime;

public class Queries20 {

    String receivingnumber;
    Integer duration;
    Double price;
    LocalDate date;
    LocalTime time;
    String tel_number;
    String name;
    String surname;
    String patronymic;
    Integer count;
    Integer sum;

    public Queries20(String receivingnumber, Integer duration, Double price, LocalDate date, LocalTime time) {
        this.receivingnumber = receivingnumber;
        this.duration = duration;
        this.price = price;
        this.date = date;
        this.time = time;
    }

    public String getReceivingnumber() {
        return receivingnumber;
    }

    public void setReceivingnumber(String receivingnumber) {
        this.receivingnumber = receivingnumber;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Queries20(String tel_number, String name, String surname, String patronymic, Integer count) {
        this.tel_number = tel_number;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.count = count;
    }

    public Queries20(String tel_number, String name, String surname, String patronymic) {
        this.tel_number = tel_number;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public Queries20(String tel_number, String name, String surname, String patronymic, Double price) {
        this.tel_number = tel_number;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Queries20(String name, String surname, String patronymic, Integer sum) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.sum = sum;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
