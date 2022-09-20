package com.shend.entities;

import java.time.LocalDateTime;

public class JournalLog {

    int id;
    String login;
    LocalDateTime time;
    String description;

    public JournalLog(int id, String login, LocalDateTime time, String description) {
        this.id = id;
        this.login = login;
        this.time = time;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
