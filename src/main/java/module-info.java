module com.shend {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires kernel;
    requires layout;
    requires mysql.connector.java;
    opens com.shend.animations to javafx.fxml;
    exports com.shend.animations;
    exports com.shend to javafx.graphics;
    opens com.shend.controllers to javafx.fxml;
    exports com.shend.controllers;
    opens com.shend.entities to javafx.fxml;
    exports com.shend.entities;
    opens com.shend.db to javafx.fxml;
    exports com.shend.db;
    opens com.shend.links to javafx.fxml;
    exports com.shend.links;
    opens com.shend.tables to javafx.fxml;
    exports com.shend.tables;
}