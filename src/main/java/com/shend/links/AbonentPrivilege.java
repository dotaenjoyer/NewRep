package com.shend.links;

import com.shend.entities.Privilege;
import com.shend.entities.Abonent;
import java.time.LocalDateTime;

public class AbonentPrivilege {

    private int id;
    private Abonent abonent;
    private Privilege privilege;
    private LocalDateTime connectionDate;
    private LocalDateTime disconnectionDate;

    public AbonentPrivilege(int id, Abonent abonent, Privilege privilege, LocalDateTime connectionDate, LocalDateTime disconnectionDate) {
        this.id = id;
        this.abonent = abonent;
        this.privilege = privilege;
        this.connectionDate = connectionDate;
        this.disconnectionDate = disconnectionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Abonent getAbonent() {
        return abonent;
    }

    public void setAbonent(Abonent abonent) {
        this.abonent = abonent;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
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
