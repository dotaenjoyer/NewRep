package com.shend.tables;

public class GetDesc {

    String tariff;
    String description;

    public GetDesc(String tariff, String description) {
        this.tariff = tariff;
        this.description = description;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
