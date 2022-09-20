package com.shend.db;

import com.shend.links.AbonentPrivilege;
import com.shend.links.AbonentTariff;
import com.shend.entities.Call;
import com.shend.entities.Tariff;
import com.shend.entities.User;
import com.shend.entities.Service;
import com.shend.entities.Abonent;
import com.shend.tables.GetCount;
import com.shend.tables.GetCount1;
import com.shend.tables.GetDesc;
import com.shend.tables.GetDesc1;
import com.shend.entities.JournalLog;
import com.shend.controllers.LoginController;
import com.shend.tables.MaxCallDuration;
import com.shend.controllers.OperatorController;
import com.shend.entities.Payment;
import com.shend.entities.Privilege;
import com.shend.tables.Queries20;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static com.shend.db.Configs.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBHandler extends Configs {

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException,
            SQLException {
        String connectionString = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/"

                + DB_NAME;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, DB_USER,
                DB_PASS);
        return dbConnection;
    }

    /*public void createAbonent(Abonent abonent, User user) {
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
    }*/

 /*   public void updateAbonent(Abonent abonent, User user) {
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
    }*/

    public void updateService(Service service) {
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "'," + " 'оновлення послуги " + OperatorController.service.getName() + "')";
        String update1 = "UPDATE services SET service_name = ?, service_description = ?, service_price = ? WHERE service_id = " + OperatorController.service.getId();
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(update1);
            prst.setString(1, service.getName());
            prst.setString(2, service.getDescription());
            prst.setDouble(3, service.getPrice());
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
    }

    public void updateTariff(Tariff tariff) {
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "'," + " 'оновлення тарифу " + OperatorController.tariff.getName() + "')";
        String update1 = "UPDATE tariff SET tariff_name = ?, tariff_price = ?, tariff_description = ?, tariff_term = ? WHERE tariff_id = " + OperatorController.tariff.getId();
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(update1);
            prst.setString(1, tariff.getName());
            prst.setDouble(2, tariff.getPrice());
            prst.setString(3, tariff.getDescription());
            prst.setInt(4, tariff.getTerm());
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
    }

    public void updatePrivilege(Privilege privilege) {
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "'," + " 'оновлення привілегії " + OperatorController.privilege.getName() + "')";
        String update1 = "UPDATE privilege JOIN privilege_names ON privilege.privilege_name_id = privilege_names.privilege_name_id SET privilege_name = ?, privilege_description = ? WHERE privilege_id = " + OperatorController.privilege.getId();
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(update1);
            prst.setString(1, privilege.getName());
            prst.setString(2, privilege.getDescription());
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
    }

    public int checkTel(Abonent abonent) throws SQLException {
        ResultSet rs = null;
        int m = 1;
        String check = "SELECT COUNT(tel_number) FROM abonent WHERE tel_number = " + abonent.getTelephone();
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(check);
            rs = prst.executeQuery();
            rs.next();
            m = rs.getInt(1);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return m;
    }

    public int checkTariff(Tariff tariff) {
        ResultSet rs = null;
        int m = 1;
        String check = "SELECT COUNT(tariff_id) FROM tariff WHERE tariff_name = '" + tariff.getName() + "'";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(check);
            rs = prst.executeQuery();
            rs.next();
            m = rs.getInt(1);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return m;
    }

    public int checkPrivilege(Privilege privilege) {
        ResultSet rs = null;
        int m = 1;
        String check = "SELECT COUNT(privilege_name_id) FROM privilege_names WHERE privilege_name = '" + privilege.getName() + "'";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(check);
            rs = prst.executeQuery();
            rs.next();
            m = rs.getInt(1);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return m;
    }

    public int checkService(Service service) {
        ResultSet rs = null;
        int m = 1;
        String check = "SELECT COUNT(service_id) FROM services WHERE service_name = '" + service.getName() + "'";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(check);
            rs = prst.executeQuery();
            rs.next();
            m = rs.getInt(1);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return m;
    }

    public void createTariff(Tariff tariff) {
        String insert1 = "INSERT INTO tariff (tariff_price, tariff_description,"
                + " tariff_term, tariff_name) VALUES(?,?,?,?)";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(insert1);
            prst.setDouble(1, tariff.getPrice());
            prst.setString(2, tariff.getDescription());
            prst.setInt(3, tariff.getTerm());
            prst.setString(4, tariff.getName());
            prst.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createService(Service service) {
        String insert1 = "INSERT INTO services (service_name, service_description,"
                + " service_price) VALUES(?,?,?)";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(insert1);
            prst.setString(1, service.getName());
            prst.setString(2, service.getDescription());
            prst.setDouble(3, service.getPrice());
            prst.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createPrivilege(Privilege privilege) {
        String insert1 = "INSERT INTO privilege_names (privilege_name) VALUES(?)";
        String insert2 = "INSERT INTO privilege (privilege_description, privilege_name_id) VALUES(?,(SELECT privilege_name_id"
                + " FROM privilege_names WHERE privilege_name = ?))";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(insert1);
            prst.setString(1, privilege.getName());
            prst.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(insert2);
            prst.setString(1, privilege.getDescription());
            prst.setString(2, privilege.getName());
            prst.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getUser(User user) throws SQLException {
        ResultSet resSet;
        String select = "SELECT user_position FROM users WHERE user_id = " + "'"
                + user.getLogin() + "'" + " AND password = " + "'" + user.getPassword() + "'";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "'," + "'увійшов як " + LoginController.user.getUser_position() + "')";
        String m = "";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                m = resSet.getString("user_position");
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
        return m;
    }

    public Double getAvg() {
        ResultSet resSet = null;
        String select = "SELECT AVG (call_duration) as avg FROM calls JOIN abonent ON calls.tel_number = abonent.tel_number\n"
                + "WHERE abonent.tel_number = " + LoginController.user.getLogin();
        Double m = 0.0;
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            resSet.next();
            m = resSet.getDouble("avg");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return m;
    }

    public int getCallCount() {
        ResultSet resSet = null;
        String select = "SELECT COUNT(call_id) as count FROM calls JOIN abonent ON calls.tel_number = abonent.tel_number\n"
                + "WHERE abonent.tel_number =" + LoginController.user.getLogin();
        int m = 0;
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            resSet.next();
            m = resSet.getInt("count");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return m;
    }

    /*public ObservableList<Abonent> getAbonentList() throws SQLException {
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
    }*/

    public ObservableList<Tariff> getTariffList() throws SQLException {
        ObservableList<Tariff> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT * FROM tariff";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список тарифів')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new Tariff(resSet.getInt("tariff_id"),
                        resSet.getString("tariff_name"),
                        resSet.getDouble("tariff_price"),
                        resSet.getString("tariff_description"),
                        resSet.getInt("tariff_term")));
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

    public ObservableList<Privilege> getPrivilegeList() throws SQLException {
        ObservableList<Privilege> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT privilege_id, privilege_name, privilege_description "
                + "FROM privilege_names JOIN privilege ON privilege_names.privilege_name_id = "
                + "privilege.privilege_name_id";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список пільг')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new Privilege(resSet.getInt("privilege_id"), resSet.getString("privilege_name"), resSet.getString("privilege_description")));
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

    public ObservableList<Service> getServiceList() throws SQLException {
        ObservableList<Service> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT * FROM services";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список послуг')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new Service(resSet.getInt("service_id"), resSet.getString("service_name"), resSet.getString("service_description"), resSet.getDouble("service_price")));
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

    public ObservableList<JournalLog> getJournalList() throws SQLException {
        ObservableList<JournalLog> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT * FROM journal_log";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'відкрит журнал')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (resSet.next()) {
                arrayList.add(new JournalLog(resSet.getInt("log_id"), resSet.getString("login"), LocalDateTime.parse(resSet.getString("log_time"), df), resSet.getString("description")));
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

    public ObservableList<Call> getCallList() throws SQLException {
        ObservableList<Call> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT * FROM calls";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список дзвінків')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new Call(resSet.getInt("call_id"),
                        resSet.getString("tel_number"),
                        resSet.getString("receiver_number"),
                        LocalDate.parse(resSet.getString("call_date")),
                        LocalTime.parse(resSet.getString("call_time")),
                        resSet.getInt("call_duration"), resSet.getDouble("call_price")));

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

    public ObservableList<Call> getCallListForAbonent() throws SQLException {
        ObservableList<Call> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT * FROM calls WHERE tel_number = '" + LoginController.user.getLogin() + "' AND call_date BETWEEN '2020-12-12 ' AND '2022-12-12 '";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список дзвінків')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new Call(resSet.getString("receiver_number"),
                        LocalDate.parse(resSet.getString("call_date")),
                        LocalTime.parse(resSet.getString("call_time")),
                        resSet.getInt("call_duration"), resSet.getDouble("call_price")
                ));
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

    public ObservableList<Payment> getPaymentList() throws SQLException, ClassNotFoundException {
        ObservableList<Payment> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT payment_id, tel_number, service_name, payment_date FROM "
                + "payment JOIN services ON payment.service_id = services.service_id";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список послуг')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (resSet.next()) {
                arrayList.add(new Payment(resSet.getInt("payment_id"),
                        resSet.getString("tel_number"),
                        resSet.getString("service_name"),
                        LocalDateTime.parse(resSet.getString("payment_date"), df)));
            }
            prst = getDbConnection().prepareStatement(log);
            try {
                prst.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (Exception throwables) {
        }
        System.out.println(arrayList);
        return arrayList;
    }

    public ObservableList<Payment> getPaymentListForAbonent() throws SQLException, ClassNotFoundException {
        ObservableList<Payment> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT service_name, service_description, service_price, payment_date FROM services join payment on services.service_id = payment.service_id WHERE tel_number = '" + LoginController.user.getLogin() + "' AND payment_date BETWEEN '2020-12-12 ' AND '2022-12-12 '";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список оплат')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (resSet.next()) {
                arrayList.add(new Payment(resSet.getString("service_name"),
                        resSet.getString("service_description"),
                        resSet.getDouble("service_price"),
                        LocalDateTime.parse(resSet.getString("payment_date"), df)));
            }
            prst = getDbConnection().prepareStatement(log);
            try {
                prst.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (Exception throwables) {
        }
        return arrayList;
    }

    public ObservableList<AbonentPrivilege> getAbonentPrivilegeList() throws SQLException, ClassNotFoundException {
        ObservableList<AbonentPrivilege> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT * FROM "
                + "abonent JOIN abonent_privilege ON abonent.tel_number = abonent_privilege.tel_number JOIN privilege ON privilege.privilege_id = abonent_privilege.privilege_id JOIN privilege_names ON privilege_names.privilege_name_id = privilege.privilege_name_id";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список підключень до пільг')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (resSet.next()) {
                arrayList.add(new AbonentPrivilege(resSet.getInt("abonent_privilege_id"), new Abonent(resSet.getString("tel_number"), resSet.getString("name"), resSet.getString("surname"), resSet.getString("patronymic"), resSet.getDouble("balance"), resSet.getString("additional_info")),
                        new Privilege(resSet.getInt("privilege_id"), resSet.getString("privilege_name"), resSet.getString("privilege_description")),
                        (resSet.getString("connection_date") != null) ? LocalDateTime.parse(resSet.getString("connection_date"), df) : null, (resSet.getString("disconnection_date") != null) ? LocalDateTime.parse(resSet.getString("disconnection_date"), df) : null));
            }
            prst = getDbConnection().prepareStatement(log);
            try {
                prst.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
        }
        System.out.println(arrayList);
        return arrayList;
    }

    public ObservableList<AbonentTariff> getAbonentTariffList() throws SQLException, ClassNotFoundException {
        ObservableList<AbonentTariff> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT * FROM "
                + "abonent JOIN abonent_tariff ON abonent.tel_number = abonent_tariff.tel_number JOIN tariff ON tariff.tariff_id = abonent_tariff.tariff_id";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список підключень до тарифів')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (resSet.next()) {
                arrayList.add(new AbonentTariff(resSet.getInt("abonent_tariff_id"), new Abonent(resSet.getString("tel_number"), resSet.getString("name"), resSet.getString("surname"), resSet.getString("patronymic"), resSet.getDouble("balance"), resSet.getString("additional_info")),
                        new Tariff(resSet.getInt("tariff_id"), resSet.getString("tariff_name"), resSet.getDouble("tariff_price"), resSet.getString("tariff_description"), resSet.getInt("tariff_term")),
                        (resSet.getString("connection_date") != null) ? LocalDateTime.parse(resSet.getString("connection_date"), df) : null, (resSet.getString("disconnection_date") != null) ? LocalDateTime.parse(resSet.getString("disconnection_date"), df) : null));
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

   /* public void deleteAbonent(Abonent abonent) throws SQLException, ClassNotFoundException {

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
    }*/

    public void deleteTariff(Tariff tariff) throws SQLException, ClassNotFoundException {

        String delete = "DELETE FROM tariff WHERE tariff_id = " + tariff.getId();
        PreparedStatement prst = getDbConnection().prepareStatement(delete);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "','видалено послугу " + tariff.getName() + "'" + ')';
            prst = getDbConnection().prepareStatement(log);
            prst.executeUpdate();
        }
    }

    public void deletePrivilege(Privilege privilege) throws SQLException, ClassNotFoundException {

        String delete = "DELETE FROM privilege WHERE privilege_id = " + privilege.getId();
        PreparedStatement prst = getDbConnection().prepareStatement(delete);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "','видалено пільгу " + privilege.getName() + "'" + ')';
        prst = getDbConnection().prepareStatement(log);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteService(Service service) throws SQLException, ClassNotFoundException {

        String delete = "DELETE FROM services WHERE service_id = " + service.getId();
        PreparedStatement prst = getDbConnection().prepareStatement(delete);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "','видалено послугу " + service.getName() + "'" + ')';
        prst = getDbConnection().prepareStatement(log);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteCall(Call call) throws SQLException, ClassNotFoundException {

        String delete = "DELETE FROM calls WHERE call_id = " + call.getId();
        PreparedStatement prst = getDbConnection().prepareStatement(delete);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'видалено дзвінок до " + call.getReceiverNumber() + " час " + call.getDate() + " " + call.getTime() + "'" + ')';
        prst = getDbConnection().prepareStatement(log);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deletePayment(Payment payment) throws SQLException, ClassNotFoundException {

        String delete = "DELETE FROM payment WHERE payment_id = " + payment.getId();
        PreparedStatement prst = getDbConnection().prepareStatement(delete);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'видалено оплату id " + payment.getId() + ", дата " + payment.getDate() + ", послуга " + payment.getService() + "')";
        prst = getDbConnection().prepareStatement(log);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAbonentTariff(AbonentTariff abonentTariff) throws SQLException, ClassNotFoundException {

        String delete = "DELETE FROM abonent_tariff WHERE abonent_tariff_id = " + abonentTariff.getId();
        PreparedStatement prst = getDbConnection().prepareStatement(delete);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'видалено підключення " + abonentTariff.getId() + "')";
        prst = getDbConnection().prepareStatement(log);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAbonentPrivilege(AbonentPrivilege abonentPrivilege) throws SQLException, ClassNotFoundException {

        String delete = "DELETE FROM abonent_privilege WHERE abonent_privilege_id = " + abonentPrivilege.getId();
        PreparedStatement prst = getDbConnection().prepareStatement(delete);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'видалено підключення " + abonentPrivilege.getId() + "')";
        prst = getDbConnection().prepareStatement(log);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void clearJournalLog() throws SQLException, ClassNotFoundException {

        String delete = "DELETE FROM journal_log";
        PreparedStatement prst = getDbConnection().prepareStatement(delete);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'журнал очищено')";
        prst = getDbConnection().prepareStatement(log);
        try {
            prst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void tariffConnect(Tariff tariff) throws SQLException, ClassNotFoundException {
        String connect = "INSERT INTO abonent_tariff(tel_number, tariff_id, connection_date) VALUES(?,?,now())";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'підключення абонента" + "')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(connect);
            prst.setString(1, OperatorController.abonent.getTelephone());
            prst.setInt(2, tariff.getId());
            prst.executeUpdate();
            prst = getDbConnection().prepareStatement(log);
            prst.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void privilegeConnect(Privilege privilege) throws SQLException, ClassNotFoundException {
        String connect = "INSERT INTO abonent_privilege(tel_number,privilege_id,connection_date,disconnection_date) VALUES(?,?,now(),?)";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'підключення абонента" + "')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(connect);
            prst.setString(1, OperatorController.abonent.getTelephone());
            prst.setInt(2, privilege.getId());
            prst.setString(3, null);
            prst.executeUpdate();
            prst = getDbConnection().prepareStatement(log);
            prst.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    /*public ObservableList<Abonent> getBySurname(String sur) throws SQLException {
        ObservableList<Abonent> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT * FROM abonent WHERE surname LIKE '" + sur + "%'";
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
    }*/

    public ObservableList<Tariff> getByTariff(String tar) throws SQLException {
        ObservableList<Tariff> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT * FROM tariff WHERE tariff_name LIKE '" + tar + "%'";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'пошук по тарифу')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new Tariff(resSet.getInt("tariff_id"),
                        resSet.getString("tariff_name"),
                        resSet.getDouble("tariff_price"),
                        resSet.getString("tariff_description"),
                        resSet.getInt("tariff_term")));
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

    public void updateAbonent1() {

        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "'," + " 'оновлення абоненту, що використав найбільшу кількість тарифів')";
        String update1 = "UPDATE abonent SET additional_info = 'Використав найбільшу кількість тарифів' WHERE abonent.tel_number IN(SELECT count(tel_number) FROM abonent_tariff group by tel_number having count(*) >= all(SELECT tel_number FROM abonent_tariff group by tel_number))";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(update1);
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
    }

    public void updateAbonent2() {

        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "'," + " 'оновлення абоненту, що зробив більше всього дзвінків')";
        String update1 = "UPDATE abonent SET additional_info = 'Зробив найбільшу кількість дзвінків' WHERE abonent.tel_number IN(SELECT tel_number FROM calls group by tel_number having count(*) >= all(SELECT count(tel_number) FROM calls group by tel_number))";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(update1);
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
    }

    public ObservableList<Queries20> getAbonent3() throws SQLException {
        ObservableList<Queries20> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT abonent.tel_number, surname, name, patronymic, COUNT(tariff. tariff_id) AS count	FROM abonent JOIN abonent_tariff ON abonent.tel_number = abonent_tariff.tel_number JOIN tariff ON abonent_tariff.tariff_id  = tariff.tariff_id GROUP BY surname, name, patronymic HAVING COUNT(tariff. tariff_id) >= ALL(SELECT COUNT(tariff. tariff_id) FROM abonent JOIN abonent_tariff ON abonent.tel_number = abonent_tariff.tel_number JOIN tariff ON abonent_tariff.tariff_id  = tariff.tariff_id GROUP BY surname, name, patronymic);";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список абонентів, що мали більше 2 тарифів')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new Queries20(resSet.getString("tel_number"),
                        resSet.getString("name"),
                        resSet.getString("surname"),
                        resSet.getString("patronymic"),
                        resSet.getInt("count")));
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

    public ObservableList<Queries20> getCallSum() throws SQLException {
        ObservableList<Queries20> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT name, surname, patronymic, SUM(call_duration) AS sum	FROM abonent JOIN calls ON abonent.tel_number = calls.tel_number GROUP BY name, surname, patronymic HAVING SUM(calls.call_duration) >= ALL(SELECT SUM(calls.call_duration) FROM abonent JOIN calls ON abonent.tel_number = calls.tel_number GROUP BY name, surname, patronymic);";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано абонент, що має найдовшу сумарну тривалість дзвінків')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new Queries20(resSet.getString("name"),
                        resSet.getString("surname"),
                        resSet.getString("patronymic"),
                        resSet.getInt("sum")));
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

    public ObservableList<Queries20> getMaxSum() throws SQLException {
        ObservableList<Queries20> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT abonent.tel_number, name, surname, patronymic, service_price FROM abonent JOIN payment ON abonent.tel_number = payment.tel_number JOIN services ON services.service_id = payment.service_id WHERE services.service_price IN (SELECT MAX(services.service_price) FROM payment JOIN services ON services.service_id = payment.service_id WHERE payment.tel_number = abonent.tel_number)";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано максимальну оплату для кожного абонента')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new Queries20(resSet.getString("abonent.tel_number"),
                        resSet.getString("name"),
                        resSet.getString("surname"),
                        resSet.getString("patronymic"),
                        resSet.getDouble("service_price")));
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

    public ObservableList<MaxCallDuration> getMaxDur() throws SQLException {
        ObservableList<MaxCallDuration> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT abonent.tel_number, name, surname, patronymic, calls.call_price FROM abonent JOIN calls ON abonent.tel_number = calls.tel_number WHERE calls.call_price IN (SELECT MAX(calls.call_price) FROM calls WHERE calls.tel_number = abonent.tel_number) GROUP BY abonent.tel_number";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано максимальну тривалість дзвінків для кожного абонента')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new MaxCallDuration(resSet.getString("abonent.tel_number"),
                        resSet.getString("name"),
                        resSet.getString("surname"),
                        resSet.getString("patronymic"),
                        resSet.getDouble("calls.call_price")));
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

    public ObservableList<Queries20> getNoPayers() throws SQLException {
        ObservableList<Queries20> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT abonent.tel_number, name, surname, patronymic FROM abonent WHERE tel_number NOT IN (SELECT NULL FROM payment WHERE payment_date < curdate() - 30);";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список абонентів, нічого не оплачували за останній місяць')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new Queries20(resSet.getString("abonent.tel_number"),
                        resSet.getString("name"),
                        resSet.getString("surname"), resSet.getString("patronymic")));
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

    public ObservableList<Queries20> getNoCallers() throws SQLException {
        ObservableList<Queries20> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT abonent.tel_number, name, surname, patronymic FROM abonent LEFT JOIN (SELECT * FROM calls WHERE call_date > curdate() - 30) a ON abonent.tel_number = a.tel_number WHERE a.call_id is null;";
        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список абонентів, які не дзвонили за останній місяць')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new Queries20(resSet.getString("abonent.tel_number"),
                        resSet.getString("name"),
                        resSet.getString("surname"), resSet.getString("patronymic")));
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

    public ObservableList<Queries20> getAbonentListOnTariff(Tariff tariff) throws SQLException {
        ObservableList<Queries20> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT abonent.tel_number, name, surname, patronymic FROM abonent JOIN abonent_tariff JOIN tariff ON abonent.tel_number = abonent_tariff.tel_number AND abonent_tariff.tariff_id = tariff.tariff_id WHERE tariff_name = '" + tariff.getName() + "'";

        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список абонентів, що підключені до тарифу')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new Queries20(resSet.getString("abonent.tel_number"),
                        resSet.getString("name"),
                        resSet.getString("surname"), resSet.getString("patronymic")));
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

    public ObservableList<GetCount> getCount() throws SQLException {
        ObservableList<GetCount> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT tariff.tariff_name, COUNT(abonent.tel_number) AS count\n"
                + "FROM abonent JOIN abonent_tariff ON abonent_tariff.tel_number = abonent.tel_number\n"
                + "JOIN tariff ON abonent_tariff.tariff_id = tariff.tariff_id\n"
                + "GROUP BY tariff.tariff_name;";

        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список абонентів, що підключені до тарифу')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new GetCount(resSet.getString("tariff.tariff_name"),
                        resSet.getInt("count")));
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

    public ObservableList<GetDesc> getDesc() throws SQLException {
        ObservableList<GetDesc> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT tariff_name, \"most popular\" AS description FROM tariff\n"
                + "JOIN abonent_tariff ON tariff.tariff_id = abonent_tariff.tariff_id\n"
                + "	GROUP BY tariff_name\n"
                + "	HAVING COUNT(tariff.tariff_id) >= ALL(\n"
                + "SELECT COUNT(tariff.tariff_id) FROM tariff\n"
                + "JOIN abonent_tariff ON tariff.tariff_id = abonent_tariff.tariff_id\n"
                + "	GROUP BY tariff_name\n"
                + ")\n"
                + "UNION\n"
                + "SELECT tariff_name, \"not yet used\" AS description FROM tariff\n"
                + "LEFT JOIN abonent_tariff ON tariff.tariff_id = abonent_tariff.tariff_id\n"
                + "WHERE abonent_tariff.tariff_id is null ";

        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список абонентів, що підключені до тарифу')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new GetDesc(resSet.getString("tariff_name"),
                        resSet.getString("description")));
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

    public ObservableList<GetCount1> getCount1() throws SQLException {
        ObservableList<GetCount1> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT privilege_names.privilege_name, COUNT(abonent.tel_number) AS count\n"
                + "FROM abonent JOIN abonent_privilege ON abonent.tel_number = abonent_privilege.tel_number\n"
                + "JOIN privilege ON privilege.privilege_id = abonent_privilege.privilege_id\n"
                + "JOIN privilege_names ON privilege_names.privilege_name_id = privilege.privilege_id\n"
                + "GROUP BY privilege_names.privilege_name";

        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список абонентів, що підключені до тарифу')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new GetCount1(resSet.getString("privilege_names.privilege_name"),
                        resSet.getInt("count")));
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

    public ObservableList<GetDesc1> getDesc1() throws SQLException {
        ObservableList<GetDesc1> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT service_name , \"most popular\" as description FROM services\n"
                + "JOIN payment ON services.service_id = payment.service_id\n"
                + "	GROUP BY service_name\n"
                + "	HAVING COUNT(services.service_id) >= ALL(\n"
                + "SELECT COUNT(services.service_id) FROM services\n"
                + "JOIN payment ON services.service_id = payment.service_id\n"
                + "	GROUP BY service_name\n"
                + ")\n"
                + "UNION\n"
                + "SELECT service_name, \"not yet used\" as description FROM services\n"
                + "LEFT JOIN payment ON payment.service_id = services.service_id\n"
                + "WHERE payment.service_id is null";

        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано найпопулярнішу пільгу та той, що жодного разу не використовувалася')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new GetDesc1(resSet.getString("service_name"),
                        resSet.getString("description")));
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

    public ObservableList<Queries20> getCallsOnAbonent(Abonent abonent) throws SQLException {
        ObservableList<Queries20> arrayList = FXCollections.observableArrayList();
        ResultSet resSet = null;
        String select = "SELECT receiver_number, call_duration, call_price, call_date, call_time FROM abonent JOIN calls ON abonent.tel_number = calls.tel_number WHERE abonent.name = '" + abonent.getName() + "'";

        String log = "INSERT INTO journal_log (log_time, login, description) VALUES (now(), '" + LoginController.user.getLogin() + "', 'отримано список абонентів, що підключені до тарифу')";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resSet = prst.executeQuery();
            while (resSet.next()) {
                arrayList.add(new Queries20(resSet.getString("receiver_number"),
                        resSet.getInt("call_duration"),
                        resSet.getDouble("call_price"), LocalDate.parse(resSet.getString("call_date")), LocalTime.parse(resSet.getString("call_time"))));
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
}
