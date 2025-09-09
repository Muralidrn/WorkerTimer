package com.example.workertimer2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/workertimer2/scene1.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 800);
            stage.setTitle("Work Timer");
            stage.setResizable(false);
            stage.setX(932);
            stage.setY(0);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.println("App starting...");
        launch();
    }
}

