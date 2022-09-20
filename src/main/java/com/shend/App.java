package com.shend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static final int windowWidth = 900;
    public static final int windowHeight = 600;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/shend/graphicFiles/Welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), windowWidth, windowHeight);
        stage.setTitle("Teleph1");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
