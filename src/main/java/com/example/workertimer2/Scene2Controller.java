package com.example.workertimer2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Scene2Controller {

    private Timeline timeline;
    private TimeDisplay timeDisplay;
    private int secondsPassed = 0;

    @FXML
    private Label timerLabel;
    @FXML
    private  Label hoursLabel;
    @FXML
    private Label minutesLabel;
    @FXML
    private Label secondsLabel;

    //buttons
    @FXML
    private Button BreakButton;

    @FXML
    private PieChart pieChart;
    @FXML
    private Circle donutHole;
    @FXML
    private Circle imageCircle;

    @FXML
    public void initialize() {

        Image image = null;
        try {
            image = new Image(getClass().getResource("/com/example/workertimer2/0d64989794b1a4c9d89bff571d3d5842-removebg-preview.png").toExternalForm());
        } catch (NullPointerException e) {
            System.err.println("Image not found!");
        }

        if (image != null) {
            imageCircle.setFill(new ImagePattern(image));
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Workfolio", 40),
                new PieChart.Data("Figma", 20),
                new PieChart.Data("Visual Studio", 15)
        );

        pieChart.setData(pieChartData);

        // Show legend on the left
        pieChart.setLegendVisible(true);
        pieChart.setLegendSide(Side.LEFT);
        pieChart.setLabelsVisible(false);
        donutHole.radiusProperty().bind(pieChart.widthProperty().divide(8));

        // Center the circle inside the pie chart
        donutHole.centerXProperty().bind(pieChart.layoutBoundsProperty().map(b -> b.getWidth() / 2));
        donutHole.centerYProperty().bind(pieChart.layoutBoundsProperty().map(b -> b.getHeight() / 2));
    }


    public void breakIN()
    {
     System.out.println("controller 2 is running");
     timeDisplay = new TimeDisplay(hoursLabel, minutesLabel, secondsLabel);
        timeDisplay.updateTime(
                WorkSession.getHours(),
                WorkSession.getMinutes(),
                WorkSession.getSeconds()
        );
     startWorkTimer();

    }
    @FXML
    private void onBreakButtonClick(ActionEvent event) {
        if (timeline != null) {
            if (timeline.getStatus() == Timeline.Status.RUNNING) {
                timeline.pause();
                BreakButton.setText("Resume");
                System.out.println("Break started..."+timeline.getStatus());
            } else {
                timeline.play();
                BreakButton.setText("Break");
                System.out.println("Break ended, resuming work...");
            }
        }
    }
    @FXML
    private void onClockOutButtonClick(ActionEvent event) {
        if (timeline != null) {
            timeline.stop();
            timeline = null;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scene1.fxml"));
            Parent root = loader.load();

            HelloController helloController = loader.getController();
            helloController.updateTimeLabels();


            Stage stage = (Stage) BreakButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startWorkTimer() {

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            WorkSession.increment();
            this.timeDisplay.updateTime(
                    WorkSession.getHours(),
                    WorkSession.getMinutes(),
                    WorkSession.getSeconds()
            );
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
