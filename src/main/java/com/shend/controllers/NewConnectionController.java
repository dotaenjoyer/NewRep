package com.shend.controllers;

import com.shend.entities.Privilege;
import com.shend.db.DBHandler;
import com.shend.entities.Tariff;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class NewConnectionController {

    @FXML
    private Button BackButton;

    @FXML
    private ComboBox<Privilege> PrivilegeChoice;

    @FXML
    private ComboBox<Tariff> TariffChoice;

    @FXML
    private Button createAbonentButton;

    @FXML
    private Label errorLabel;

    @FXML
    void initialize() {
        DBHandler dbHandler = new DBHandler();
        try {
            TariffChoice.setItems(dbHandler.getTariffList());
            TariffChoice.setStyle("-fx-font-size:16");
            errorLabel.setStyle("-fx-text-fill:green");
        } catch (SQLException ex) {
            Logger.getLogger(NewConnectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            PrivilegeChoice.getItems().addAll(dbHandler.getPrivilegeList());
            PrivilegeChoice.setStyle("-fx-font-size:16");
        } catch (SQLException ex) {
            Logger.getLogger(NewConnectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        createAbonentButton.setOnAction(actionEvent -> {
            if (PrivilegeChoice.getValue() == null && TariffChoice.getValue() != null) {
                try {
                    dbHandler.tariffConnect(TariffChoice.getSelectionModel().getSelectedItem());
                    errorLabel.setText("Успішно підключено");
                } catch (SQLException ex) {
                    Logger.getLogger(NewConnectionController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewConnectionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (PrivilegeChoice.getValue() != null && TariffChoice.getValue() == null) {
                try {
                    dbHandler.privilegeConnect(PrivilegeChoice.getSelectionModel().getSelectedItem());
                    errorLabel.setText("          Успішно підключено");
                } catch (SQLException ex) {
                    Logger.getLogger(NewConnectionController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewConnectionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (PrivilegeChoice.getValue() != null && TariffChoice.getValue() != null) {
                try {
                    dbHandler.privilegeConnect(PrivilegeChoice.getSelectionModel().getSelectedItem());
                    dbHandler.tariffConnect(TariffChoice.getSelectionModel().getSelectedItem());
                    errorLabel.setText("         Успішно підключено");
                } catch (SQLException ex) {
                    Logger.getLogger(NewConnectionController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewConnectionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        BackButton.setOnAction(actionEvent -> {

            BackButton.getScene().getWindow().hide();
            String src = "/com/shend/graphicFiles/mainWindow.fxml";
            LoadHelper helper = LoadHelper.getInstance();
            helper.loadWindow(src);
        });
    }
}
