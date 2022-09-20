package com.shend.entities;

import java.time.LocalDateTime;

public class Payment {

    private int id;
    private String service;
    private String description;
    private Double price;
    private LocalDateTime date;
    private String tel;

    public Payment(String service, String description, Double price, LocalDateTime date) {
        this.service = service;
        this.description = description;
        this.price = price;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Payment(int id, String tel, String service, LocalDateTime date) {
        this.id = id;
        this.service = service;
        this.date = date;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
