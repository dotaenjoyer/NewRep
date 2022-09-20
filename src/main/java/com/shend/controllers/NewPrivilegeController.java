package com.shend.controllers;

import com.shend.entities.Privilege;
import com.shend.db.DBHandler;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewPrivilegeController {

    @FXML
    private Button BackButton;

    @FXML
    private Button createPrivilegeButton;

    @FXML
    private Label description1;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private Label name1;

    @FXML
    private TextField nameTextField;

    @FXML
    void initialize() {
        Privilege privilege = OperatorController.privilege;
        if (OperatorController.outerkeyPrivilege == 1) {
            nameTextField.setText(privilege.getName());
            descriptionTextField.setText(privilege.getDescription());
        }
        BackButton.setOnAction(actionEvent -> {
            BackButton.getScene().getWindow().hide();
            String src = "/com/shend/graphicFiles/mainWindow.fxml";
            LoadHelper helper = LoadHelper.getInstance();
            helper.loadWindow(src);
        });

        createPrivilegeButton.setOnAction(actionEvent -> {
            name1.setText("");
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
            if (key == 0) {
                privilege.setName(nameTextField.getText());
                privilege.setDescription(descriptionTextField.getText());

                if (dbHandler.checkPrivilege(privilege) == 1 && OperatorController.outerkeyPrivilege == 0) {
                    errorLabel.setStyle("-fx-text-fill:red");
                    errorLabel.setText("Така пільга вже зареєстрована");
                    name1.setText("*");
                    key = 1;
                }
                if (key == 0 && OperatorController.outerkeyPrivilege == 0) {
                    dbHandler.createPrivilege(privilege);
                    errorLabel.setStyle("-fx-text-fill:green");
                    errorLabel.setText("          Успішно збережено");
                }
                if (key == 0 && OperatorController.outerkeyPrivilege == 1) {
                    dbHandler.updatePrivilege(privilege);
                    errorLabel.setStyle("-fx-text-fill:green");
                    errorLabel.setText("          Успішно збережено");
                }
            }

        });
    }
}
