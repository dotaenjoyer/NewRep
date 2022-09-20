package com.shend.entities;

import java.time.*;

public class Call {

    private int id;
    private String tel;
    private String receiverNumber;
    private LocalDate date;
    private LocalTime time;
    private Integer duration;
    private Double price;

    public Call(int id, String tel, String receiverNumber, LocalDate date, LocalTime time, int duration, Double price) {
        this.id = id;
        this.tel = tel;
        this.receiverNumber = receiverNumber;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
    }

    public Call(String receiverNumber, LocalDate date, LocalTime time, Integer duration, Double price) {
        this.receiverNumber = receiverNumber;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiverNumber() {
        return receiverNumber;
    }

    public void setReceiverNumber(String receiverNumber) {
        this.receiverNumber = receiverNumber;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
