package com.shend.controllers;

import com.shend.db.DBHandler;
import com.shend.entities.Tariff;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewTariffController {

    @FXML
    private Button BackButton;

    @FXML
    private Button createTariffButton;

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
    private Label term1;

    @FXML
    private TextField termTextField;

    @FXML
    void initialize() {
        Tariff tariff = OperatorController.tariff;
        if (OperatorController.outerkeyTariff == 1) {
            nameTextField.setText(tariff.getName());
            descriptionTextField.setText(tariff.getDescription());
            priceTextField.setText(tariff.getPrice().toString());
            termTextField.setText(tariff.getTerm().toString());
        }
        BackButton.setOnAction(actionEvent -> {
            BackButton.getScene().getWindow().hide();
            String src = "/com/shend/graphicFiles/mainWindow.fxml";
            LoadHelper helper = LoadHelper.getInstance();
            helper.loadWindow(src);
        });

        createTariffButton.setOnAction(actionEvent -> {
            name1.setText("");
            description1.setText("");
            price1.setText("");
            term1.setText("");
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
            if (termTextField.getText().equals("")) {
                errorLabel.setText("   Помилка у введенні даних!");
                term1.setText("*");
                key = 1;
            }
            try {
                Double.parseDouble(termTextField.getText());
            } catch (NumberFormatException e) {
                errorLabel.setText("   Помилка у введенні даних!");
                term1.setText("*");
                key = 1;
            }
            if (key == 0) {
                tariff.setName(nameTextField.getText());
                tariff.setDescription(descriptionTextField.getText());
                tariff.setPrice(Double.parseDouble(priceTextField.getText()));
                tariff.setTerm(Integer.parseInt(termTextField.getText()));

                if (dbHandler.checkTariff(tariff) == 1 && OperatorController.outerkeyTariff == 0) {
                    errorLabel.setStyle("-fx-text-fill:red");
                    errorLabel.setText("Такий тариф вже зареєстрований");
                    name1.setText("*");
                    key = 1;
                }
                if (key == 0 && OperatorController.outerkeyTariff == 0) {
                    dbHandler.createTariff(tariff);
                    errorLabel.setStyle("-fx-text-fill:green");
                    errorLabel.setText("          Успішно збережено");
                }
                if (key == 0 && OperatorController.outerkeyTariff == 1) {
                    dbHandler.updateTariff(tariff);
                    errorLabel.setStyle("-fx-text-fill:green");
                    errorLabel.setText("          Успішно збережено");
                }
            }
        });
    }
}
