package com.ride_share.driverdashboards;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class ParentDriver {
    protected Region backgroundColor;

    public void backtry() {
        backgroundColor = new Region();
        backgroundColor.setStyle("-fx-background-color: yellow;");
        backgroundColor.setPrefSize(600, 400);
    }

    protected Button responseButton;
    protected Button profileButton;
    protected Button ratingButton;
    protected Button earningButton;
    protected Button homeButton;
    protected Button backButton;

    public void commonBottomBar() {

        responseButton = new Button("Response");
        responseButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        profileButton = new Button("Profile");
        profileButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        ratingButton = new Button("Ratings");
        ratingButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        earningButton = new Button("Earnings");
        earningButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        homeButton = new Button("Home");
        homeButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        backButton = new Button("back");
        backButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

    }

    protected HBox bottomBar;

    public void bottomHbox() {
        bottomBar = new HBox(150, backButton, ratingButton, responseButton, profileButton, earningButton);
        bottomBar.setPadding(new Insets(10));
        bottomBar.setAlignment(Pos.CENTER);
        bottomBar.setStyle("-fx-background-color: black;");
    }

}