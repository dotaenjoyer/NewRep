package com.shend.controllers;

import com.shend.db.DBHandler;
import com.shend.entities.User;
import com.shend.animations.Shake;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    public static User user = new User();

    @FXML
    void initialize() {
        signUpButton.setOnAction(actionEvent -> {
            String loginText = loginField.getText().trim();
            String PasswordText = passwordField.getText().trim();
            try {
                if (loginUser(loginText, PasswordText) == 0) {
                    user.setUser_position("admin");
                    signUpButton.getScene().getWindow().hide();
                    String src = "/com/shend/graphicFiles/mainWindow.fxml";
                    LoadHelper helper = LoadHelper.getInstance();
                    helper.loadWindow(src);
                } else if (loginUser(loginText, PasswordText) == 1) {
                    user.setUser_position("abonent");
                    signUpButton.getScene().getWindow().hide();
                    String src = "/com/shend/graphicFiles/abonentWindow.fxml";
                    LoadHelper helper = LoadHelper.getInstance();
                    helper.loadWindow(src);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private int loginUser(String loginText, String loginPassword) throws
            SQLException, ClassNotFoundException {
        DBHandler dbHandler = new DBHandler();
        if (loginText.equals("") || loginPassword.equals("")) {
            Shake animlogin = new Shake(loginField);
            Shake animpass = new Shake(passwordField);
            animlogin.playAnim();
            animpass.playAnim();
            return 2;
        }
        user.setLogin(loginText);
        user.setPassword(loginPassword);
        switch (dbHandler.getUser(user)) {
            case "admin":
                return 0;
            case "abonent":
                return 1;
            default:
                Shake animlogin = new Shake(loginField);
                Shake animpass = new Shake(passwordField);
                animlogin.playAnim();
                animpass.playAnim();
                return 3;
        }
    }

}
