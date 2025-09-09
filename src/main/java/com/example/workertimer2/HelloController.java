package com.example.workertimer2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private  Label welcomeText2;
    @FXML
    private Label DateLabel;
    @FXML
    private Label hoursLabel;
    @FXML
    private Label minutesLabel;
    @FXML
    private Label secondsLabel;

    @FXML
    private Circle imageCircle;

    public HelloController()
    {

    }
    @FXML
    private void initialize() {
        DateLabel.setText(LocalDate.now().toString());

        // Load image from resources folder
        Image image = null;
        try {
            image = new Image(getClass().getResource("/com/example/workertimer2/0d64989794b1a4c9d89bff571d3d5842-removebg-preview.png").toExternalForm());
        } catch (NullPointerException e) {
            System.err.println("Image not found!");
        }

        if (image != null) {
            imageCircle.setFill(new ImagePattern(image));
        }

    }


    @FXML
    private Button onHelloButtonClick;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {

        FXMLLoader loader=new FXMLLoader(getClass().getResource("scene2.fxml"));
        Parent root=loader.load();

        Scene2Controller scene2Controller=loader.getController();
        scene2Controller.breakIN();

        Stage stage=(Stage) onHelloButtonClick.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void updateTimeLabels() {
        int hours=WorkSession.getHours();
        int minutes=WorkSession.getMinutes();
        int seconds=WorkSession.getSeconds();
        if(minutes!=0)
        {
            if(hours!=0)
            {
                hoursLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                minutesLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                secondsLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                welcomeText2.setText("You worked "+hours+"h "+minutes+"s "+seconds+"s....");

            }
            else {
                minutesLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                secondsLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                welcomeText2.setText("You worked "+minutes+"s "+seconds+"s....");
            }
        }
        else
        {
            secondsLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            welcomeText2.setText("You worked "+seconds+"s....");
        }
        hoursLabel.setText(String.format("%02d", hours));
        minutesLabel.setText(String.format("%02d", minutes));
        secondsLabel.setText(String.format("%02d", seconds));

    }

}