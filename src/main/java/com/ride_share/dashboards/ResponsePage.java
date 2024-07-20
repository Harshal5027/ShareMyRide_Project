package com.ride_share.dashboards;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResponsePage extends ParentUser {

    private Stage primaryStage;

    public ResponsePage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        // primaryStage.setFullScreen(true);

    }

    public void showResponsePage() {

        // Background color

        backtry();

        // Navigation buttons
        commonBottomBar();

        // constant bottom bar
        bottomHbox();

        ground();
        Image backgroundImage = new Image(
                "com/ride_share/Assets/map photo.jpeg");
        BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        // Driver's profile information
        Label phoneLabel = new Label("Phone Number: +9234567890");
        phoneLabel.setStyle("-fx-text-fill: white;");
        Label ratingLabel = new Label("Driver Rating: 4.8");
        ratingLabel.setStyle("-fx-text-fill: white;");
        Label locationLabel = new Label("Location: Nashik");
        locationLabel.setStyle("-fx-text-fill: white;");

        VBox driverProfile = new VBox(10, phoneLabel, ratingLabel, locationLabel);
        driverProfile.setPadding(new Insets(10));
        driverProfile.setStyle(
                "-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white;-fx-background-color: black;");
        driverProfile.setAlignment(Pos.CENTER);

        // Seats filled
        Label seatsFilledLabel = new Label("Seats Filled: 1/4");
        seatsFilledLabel.setStyle("-fx-text-fill: white;");

        // Travel cost
        Label costLabel = new Label("Cost of Travel: Rs 150");
        costLabel.setStyle("-fx-text-fill: white;");

        // Accept and Reject buttons
        Button acceptButton = new Button("Accept");
        acceptButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Button rejectButton = new Button("Reject");
        rejectButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        HBox actionButtons = new HBox(50, acceptButton, rejectButton);
        actionButtons.setAlignment(Pos.CENTER);

        VBox centerContent = new VBox(20, driverProfile, seatsFilledLabel, costLabel, actionButtons);
        centerContent.setPadding(new Insets(20));
        centerContent.setStyle(
                "-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white;-fx-background-color: black;");

        centerContent.setAlignment(Pos.CENTER);

        // Static bottom bar

        VBox blackbox = new VBox(centerContent);
        blackbox.setAlignment(Pos.CENTER);
        blackbox.setMaxWidth(500); // Set max width to match stage width
        blackbox.setMaxHeight(620); // Set max height to match stage height
        blackbox.setStyle("-fx-background-color: black; " +
                "-fx-background-radius: 20px; " + // Set radius for rounded corners
                "-fx-border-radius: 20px;");

        // Main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(blackbox);
        mainLayout.setBottom(bottomBar);
        // mainLayout.setBackground(new Background(bgImage));
        // mainLayout.setMaxWidth(500); // Set max width to match stage width
        // mainLayout.setMaxHeight(620); // Set max height to match stage height
        // mainLayout.setStyle("-fx-background-color: yellow; ");
        // "-fx-background-radius: 20px; " + // Set radius for rounded corners
        // "-fx-border-radius: 20px;"); // Optional: Set border radius
        mainLayout.setBackground(new Background(bgImage));

        // Setting the scene
        Scene scene = new Scene(mainLayout, 1470, 830);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Share Ride - Response Page");
        // primaryStage.setFullScreen(true);
        primaryStage.show();

        // Adding actions to the buttons
        backButton.setOnAction(event -> new HomePage().start(primaryStage)); // Navigate back to home page
        responseButton.setOnAction(event -> showResponsePage()); // Stay on the same page

        profileButton.setOnAction(event -> {
            ProfilePage profilePage = new ProfilePage(primaryStage);
            // primaryStage.setFullScreen(true);
            profilePage.showProfilePage();
        });

        ratingsButton.setOnAction(event -> {
            UserRatingsDriver ratingsPage = new UserRatingsDriver(primaryStage);
            // primaryStage.setFullScreen(true);
            ratingsPage.showDriverRatingsPage();
        }); // Navigate to ratings page
    }

}