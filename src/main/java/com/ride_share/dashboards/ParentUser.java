package com.ride_share.dashboards;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class ParentUser {
    protected Region backgroundColor;

    public void backtry() {
        backgroundColor = new Region();
        backgroundColor.setStyle("-fx-background-color: yellow;");
        backgroundColor.setPrefSize(600, 400);
    }

    protected Image backgroundImage;
    protected BackgroundImage bgImage;

    public void ground() {

        Image backgroundImage = new Image(
                "com/ride_share/Assets/map photo.jpeg");
        BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
    }

    protected Button responseButton;
    protected Button profileButton;
    protected Button ratingsButton;
    protected Button homeButton;
    protected Button backButton;
    protected Button aboutusButton;

    public void commonBottomBar() {

        backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        responseButton = new Button("Response");
        responseButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        profileButton = new Button("Profile");
        profileButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        ratingsButton = new Button("Ratings");
        ratingsButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        aboutusButton = new Button("About Us");
        aboutusButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

    }

    protected HBox bottomBar;

    public void bottomHbox() {
        bottomBar = new HBox(150, backButton, ratingsButton, responseButton, profileButton, aboutusButton);
        bottomBar.setPadding(new Insets(10));
        bottomBar.setAlignment(Pos.CENTER);
        bottomBar.setStyle(
                "-fx-background-color:black; ");
    }

}
