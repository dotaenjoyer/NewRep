package com.shend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static com.shend.App.windowHeight;
import static com.shend.App.windowWidth;

public class HelloController {

    @FXML
    private Button entrybutton;
    @FXML
    void initialize() {
        entrybutton.setOnAction(actionEvent -> {
            entrybutton.getScene().getWindow().hide();
            String src = "/com/shend/graphicFiles/simple.fxml";
            LoadHelper helper = LoadHelper.getInstance();
            helper.loadWindow(src);
        });
    }
}


