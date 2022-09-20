package com.shend.entities;

public class User {

    public String login;
    private String password;
    public String user_position;

    public User() {
    }

    public User(String login, String password, String user_position) {
        this.login = login;
        this.password = password;
        this.user_position = user_position;
    }

    public void setLogin(String login1) {
        this.login = login1;
    }

    public void setPassword(String password1) {
        this.password = password1;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setUser_position(String user_position) {
        this.user_position = user_position;
    }

    public String getUser_position() {
        return user_position;
    }
}
