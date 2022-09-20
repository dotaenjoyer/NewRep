package com.shend.entities;

public class Tariff {

    private int id;
    private String name;
    private Double price;
    private String description;
    private Integer term;

    public Tariff(int id, String name, Double price, String description, Integer term) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.term = term;
    }

    @Override
    public String toString() {
        return name;
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

    public Double getPrice() {
        return price;
    }

    public Integer getTerm() {
        return term;
    }

    public void setName(String name1) {
        this.name = name1;
    }

    public void setPrice(Double price1) {
        this.price = price1;
    }

    public void setDescription(String description1) {
        this.description = description1;
    }

    public void setTerm(Integer term1) {
        this.term = term1;
    }
}
