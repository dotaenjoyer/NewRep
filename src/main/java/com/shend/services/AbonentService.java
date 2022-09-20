package com.shend.services;

import com.shend.controllers.LoginController;
import com.shend.controllers.OperatorController;
import com.shend.dao.AbonentDAO;
import com.shend.db.DBHandler;
import com.shend.entities.Abonent;
import com.shend.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AbonentService extends DBHandler implements AbonentDAO {
DBHandler handler = new DBHandler();
    @Override
    public void add(Abonent abonent, User user) {
        String insert1 = "INSERT INTO abonent (tel_number, name, surname, patronymic) VALUES(?,?,?,?)";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(insert1);
            prst.setString(1, abonent.getTelephone());
            prst.setString(2, abonent.getName());
            prst.setString(3, abonent.getSurname());
            prst.setString(4, abonent.getPatronymic());
            prst.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        String insert2 = "INSERT INTO users (user_id, password, user_position) VALUES(?,?,?)";
        try {
            PreparedStatement prst2 = getDbConnection().prepareStatement(insert2);
            prst2.setString(1, user.getLogin());
            prst2.setString(2, user.getPassword());
            prst2.setString(3, "abonent");
            prst2.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ObservableList<Abonent> getAll() {
        ObservableList<Abonent> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT * FROM abonent";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список абонентів')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new Abonent(resSet.getString("tel_number"),
                        resSet.getString("name"),
                        resSet.getString("surname"),
                        resSet.getString("patronymic"),
                        resSet.getDouble("balance"),
                        resSet.getString("additional_info")));
            }
            prst = getDbConnection().prepareStatement(log);
            try {
                prst.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }

    @Override
    public ObservableList<Abonent> getAbonentbySurname(String surname) {
        ObservableList<Abonent> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT * FROM abonent WHERE surname LIKE '" + surname + "%'";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'пошук по прізвищу')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new Abonent(resSet.getString("tel_number"),
                        resSet.getString("name"),
                        resSet.getString("surname"),
                        resSet.getString("patronymic"),
                        resSet.getDouble("balance"),
                        resSet.getString("additional_info")));
            }
            prst = getDbConnection().prepareStatement(log);
            try {
                prst.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }

    @Override
    public void update(Abonent abonent, User user) {
        String update1 = "UPDATE abonent SET tel_number = ?, name = ?, surname = ?, patronymic = ? WHERE tel_number = " + OperatorController.abonent.getTelephone();
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "'," + "'оновлення абоненту')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(update1);
            prst.setString(1, abonent.getTelephone());
            prst.setString(2, abonent.getName());
            prst.setString(3, abonent.getSurname());
            prst.setString(4, abonent.getPatronymic());
            prst.executeUpdate();
            prst = getDbConnection().prepareStatement(log);
            try {
                prst.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        String update2 = "UPDATE users SET user_id = ?, password = ?, user_position = ? WHERE user_id = " + LoginController.user.getLogin();
        try {
            PreparedStatement prst2 = getDbConnection().prepareStatement(update2);
            prst2.setString(1, user.getLogin());
            prst2.setString(2, user.getPassword());
            prst2.setString(3, user.getUser_position());
            prst2.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Abonent abonent) throws SQLException, ClassNotFoundException {
        String delete = "DELETE FROM abonent WHERE tel_number = " + abonent.getTelephone();
        String delete1 = "DELETE FROM users WHERE user_id = " + abonent.getTelephone();
        PreparedStatement prst = getDbConnection().prepareStatement(delete);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        prst = getDbConnection().prepareStatement(delete1);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "','видалено послугу " + abonent.getName() + "'" + ')';
        prst = getDbConnection().prepareStatement(log);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
