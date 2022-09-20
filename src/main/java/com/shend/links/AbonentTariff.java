package com.shend.links;

import com.shend.entities.Tariff;
import com.shend.entities.Abonent;
import java.time.LocalDateTime;

public class AbonentTariff {

    private int id;
    private Tariff tariff;
    private Abonent abonent;
    private LocalDateTime connectionDate;
    private LocalDateTime disconnectionDate;

    public AbonentTariff(int id, Abonent abonent, Tariff tariff, LocalDateTime connectionDate, LocalDateTime disconnectionDate) {
        this.id = id;
        this.tariff = tariff;
        this.abonent = abonent;
        this.connectionDate = connectionDate;
        this.disconnectionDate = disconnectionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public Abonent getAbonent() {
        return abonent;
    }

    public void setAbonent(Abonent abonent) {
        this.abonent = abonent;
    }

    public LocalDateTime getConnectionDate() {
        return connectionDate;
    }

    public void setConnectionDate(LocalDateTime connectionDate) {
        this.connectionDate = connectionDate;
    }

    public LocalDateTime getDisconnectionDate() {
        return disconnectionDate;
    }

    public void setDisconnectionDate(LocalDateTime disconnectionDate) {
        this.disconnectionDate = disconnectionDate;
    }

}
