package com.example.workertimer2;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TimeDisplay {
    private final Label hoursLabel;
    private final Label minutesLabel;
    private final Label secondsLabel;

    public TimeDisplay(Label hoursLabel, Label minutesLabel, Label secondsLabel) {
        this.hoursLabel = hoursLabel;
        this.minutesLabel = minutesLabel;
        this.secondsLabel = secondsLabel;

        this.hoursLabel.setText("00");
        this.minutesLabel.setText("00");
        this.secondsLabel.setText("00");
    }


    public void updateTime(int hours, int minutes, int seconds) {
        hoursLabel.setText(String.format("%02d", hours));
        minutesLabel.setText(String.format("%02d", minutes));
        secondsLabel.setText(String.format("%02d", seconds));
        if(minutes!=0)
        {
            if(hours!=0)
            {
                hoursLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                minutesLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                secondsLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

            }
            else {
                minutesLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                secondsLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            }
        }
        else
        {
            secondsLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        }

    }
}
