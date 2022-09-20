package com.shend.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.shend.App.windowHeight;
import static com.shend.App.windowWidth;

public class LoadHelper {
    private static LoadHelper instance = new LoadHelper();
    public static LoadHelper getInstance() {
        return instance;
    }
    public void loadWindow(String src)
    {
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(src));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Teleph1");
        primaryStage.setScene(new Scene(root, windowWidth, windowHeight));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
 }
