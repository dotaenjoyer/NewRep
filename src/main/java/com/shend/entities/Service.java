package com.shend.entities;

public class Service {

    private int id;
    private String name;
    private Double price;
    private String description;

    public Service(int id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name1) {
        this.name = name1;
    }

    public void setDescription(String description1) {
        this.description = description1;
    }

    public void setPrice(Double price1) {
        this.price = price1;
    }

    public Double getPrice() {
        return price;
    }
}
