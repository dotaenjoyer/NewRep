package com.shend.controllers;

import com.shend.dao.AbonentDAO;
import com.shend.db.DBHandler;
import com.shend.entities.User;
import com.shend.entities.Abonent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.shend.services.AbonentService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewAbonentController {

    @FXML
    private Button BackButton;

    @FXML
    private Button createAbonentButton;

    @FXML
    private Label errorLabel;

    @FXML
    private Label name1;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label password1;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label patronymic1;

    @FXML
    private TextField patronymicTextField;

    @FXML
    private Label surname1;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField telephoneTextField;

    @FXML
    private Label telnumber1;

    @FXML
    void initialize() {
        Abonent abonent = OperatorController.abonent;
        User user = LoginController.user;
        if (OperatorController.outerkeyAbonent == 1) {
            nameTextField.setText(abonent.getName());
            surnameTextField.setText(abonent.getSurname());
            patronymicTextField.setText(abonent.getPatronymic());
            passwordTextField.setText(user.getPassword());
            telephoneTextField.setText(abonent.getTelephone());
        }
        BackButton.setOnAction(actionEvent -> {
            BackButton.getScene().getWindow().hide();
            String src = "/com/shend/graphicFiles/mainWindow.fxml";
            LoadHelper helper = LoadHelper.getInstance();
            helper.loadWindow(src);
        });
        createAbonentButton.setOnAction(actionEvent -> {
            surname1.setText("");
            name1.setText("");
            patronymic1.setText("");
            telnumber1.setText("");
            password1.setText("");
            AbonentService abonentService = new AbonentService();
            DBHandler dbHandler = new DBHandler();
            int key = 0;
            if (nameTextField.getText().equals("")) {
                errorLabel.setText("Помилка у введенні даних!");
                name1.setText("*");
                key = 1;
            }
            if (surnameTextField.getText().equals("")) {
                errorLabel.setText("Помилка у введенні даних!");
                surname1.setText("*");
                key = 1;
            }
            if (patronymicTextField.getText().equals("")) {
                errorLabel.setText("Помилка у введенні даних!");
                patronymic1.setText("*");
                key = 1;
            }
            try {
                Integer.parseInt(telephoneTextField.getText());
            } catch (NumberFormatException e) {
                errorLabel.setText("Помилка у введенні даних!");
                telnumber1.setText("*");
                key = 1;
            }
            if (telephoneTextField.getText().equals("")) {
                errorLabel.setText("Помилка у введенні даних!");
                telnumber1.setText("*");
                key = 1;
            }
            if (passwordTextField.getText().equals("")) {
                errorLabel.setText("Помилка у введенні даних!");
                password1.setText("*");
                key = 1;
            }
            if (key == 0) {
                try {
                    System.out.print(dbHandler.checkTel(abonent));
                } catch (SQLException ex) {
                    Logger.getLogger(NewAbonentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    abonent.setName(nameTextField.getText());
                    abonent.setSurname(surnameTextField.getText());
                    abonent.setPatronymic(patronymicTextField.getText());
                    abonent.setTelephone(telephoneTextField.getText());
                    user.setPassword(passwordTextField.getText());
                    user.setLogin(telephoneTextField.getText());
                    if (dbHandler.checkTel(abonent) == 1 && OperatorController.outerkeyAbonent == 0) {
                        errorLabel.setStyle("-fx-text-fill:red");
                        errorLabel.setText("Такий номер вже зареєстрований");
                        telnumber1.setText("*");
                        key = 1;
                    }
                    if (key == 0 && OperatorController.outerkeyAbonent == 0) {
                        abonentService.add(abonent,user);
                        errorLabel.setStyle("-fx-text-fill:green");
                        errorLabel.setText("     Успішно збережено");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(NewAbonentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (key == 0 && OperatorController.outerkeyAbonent == 1) {
                    abonentService.update(abonent, user);
                    errorLabel.setStyle("-fx-text-fill:green");
                    errorLabel.setText("     Успішно збережено");
                }
            }
        });
    }
}
