package com.shend.dao;

import com.shend.entities.Abonent;
import com.shend.entities.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface AbonentDAO {
    //adding
    void add(Abonent abonent, User user);
    //reading
    ObservableList<Abonent> getAll();
    ObservableList<Abonent> getAbonentbySurname(String surname);
    //updating
    void update(Abonent abonent, User user);
    //deleting
    void delete(Abonent abonent) throws SQLException, ClassNotFoundException;
}
