package com.ride_share.driverdashboards;

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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DriverPendingRequestPage extends ParentDriver {

    private Stage primaryStage;

    public DriverPendingRequestPage(Stage primaryStage) {
        this.primaryStage = primaryStage;

    }

    public DriverPendingRequestPage() {
        // showDriverPendingRequestPage();

    }

    public void showDriverPendingRequestPage() {

        backtry();

        Image backgroundImage = new Image(
                "com/ride_share/Assets/map photo.jpeg");
        BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        Label confirmationLabel = new Label("No active Passengers.......\n Please Wait for Passengers");
        confirmationLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        HBox centerContent = new HBox(20, confirmationLabel);
        // centerContent.setStyle("-fx-background-color: LightBlue;");
        centerContent.setPadding(new Insets(20));
        centerContent.setAlignment(Pos.CENTER);

        // Static bottom bar
        commonBottomBar();

        HBox bottomBar = new HBox(20, backButton, ratingButton, responseButton, profileButton, earningButton);
        bottomBar.setPadding(new Insets(10));
        bottomBar.setAlignment(Pos.CENTER);
        bottomBar.setStyle("-fx-background-color: black;");

        VBox blackBox = new VBox(centerContent);
        blackBox.setPadding(new Insets(100, 0, 0, 0));
        blackBox.setMaxWidth(500); // Set max width to match stage width
        blackBox.setMaxHeight(620); // Set max height to match stage height
        blackBox.setStyle("-fx-background-color: black; " +
                "-fx-background-radius: 20px; " + // Set radius for rounded corners
                "-fx-border-radius: 20px;"); // Optional: Set border radius for consistency

        // Main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(blackBox);
        mainLayout.setBottom(bottomBar);
        mainLayout.setBackground(new Background(bgImage));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(mainLayout);

        // Setting the scene
        Scene scene = new Scene(stackPane, 1470, 830);
        primaryStage.setScene(scene);
        // primaryStage.setFullScreen(true);
        primaryStage.setTitle("Share Ride- Driver Waiting Page");
        primaryStage.show();

        // Adding actions to the buttons
        backButton.setOnAction(event -> new DriverHomePage().start(primaryStage)); // Navigate back to home page
        ratingButton.setOnAction(event -> new DriverRatingUserPage(primaryStage).showRatingUserPage());
        responseButton.setOnAction(event -> new DriverResponsePage(primaryStage).showResponsePage());
        profileButton.setOnAction(event -> new DriverProfilePage(primaryStage).showProfilePage());
        earningButton.setOnAction(event -> new DriverEarningPage(primaryStage).showEarningPage());
    }
}