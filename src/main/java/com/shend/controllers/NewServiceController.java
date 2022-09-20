package com.shend.controllers;

import com.shend.db.DBHandler;
import com.shend.entities.Service;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewServiceController {

    @FXML
    private Button BackButton;

    @FXML
    private Button createServiceButton;

    @FXML
    private Label description1;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private Label name1;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label price1;

    @FXML
    private TextField priceTextField;

    @FXML
    void initialize() {
        Service service = OperatorController.service;

        if (OperatorController.outerkeyService == 1) {
            nameTextField.setText(service.getName());
            descriptionTextField.setText(service.getDescription());
            priceTextField.setText(service.getPrice().toString());
        }
        BackButton.setOnAction(actionEvent -> {
            BackButton.getScene().getWindow().hide();
            String src = "/com/shend/graphicFiles/mainWindow.fxml";
            LoadHelper helper = LoadHelper.getInstance();
            helper.loadWindow(src);
        });

        createServiceButton.setOnAction(actionEvent -> {
            name1.setText("");
            price1.setText("");
            description1.setText("");
            DBHandler dbHandler = new DBHandler();
            int key = 0;
            if (nameTextField.getText().equals("")) {
                errorLabel.setText("   Помилка у введенні даних!");
                name1.setText("*");
                key = 1;
            }
            if (descriptionTextField.getText().equals("")) {
                errorLabel.setText("   Помилка у введенні даних!");
                description1.setText("*");
                key = 1;
            }
            if (priceTextField.getText().equals("")) {
                errorLabel.setText("   Помилка у введенні даних!");
                price1.setText("*");
                key = 1;
            }
            try {
                Double.parseDouble(priceTextField.getText());
            } catch (NumberFormatException e) {
                errorLabel.setText("   Помилка у введенні даних!");
                price1.setText("*");
                key = 1;
            }
            if (key == 0) {
                service.setName(nameTextField.getText());
                service.setDescription(descriptionTextField.getText());
                service.setPrice(Double.parseDouble(priceTextField.getText()));
                if (dbHandler.checkService(service) == 1 && OperatorController.outerkeyService == 0) {
                    errorLabel.setStyle("-fx-text-fill:red");
                    errorLabel.setText("Така послуга вже зареєстрована");
                    name1.setText("*");
                    key = 1;
                }
                if (key == 0 && OperatorController.outerkeyService == 0) {
                    dbHandler.createService(service);
                    errorLabel.setStyle("-fx-text-fill:green");
                    errorLabel.setText("          Успішно збережено");
                }
                if (key == 0 && OperatorController.outerkeyService == 1) {
                    dbHandler.updateService(service);
                    errorLabel.setStyle("-fx-text-fill:green");
                    errorLabel.setText("          Успішно збережено");
                }
            }
        });
    }

}
