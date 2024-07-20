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

public class RequestRejectedPage extends ParentUser {

    private Stage primaryStage;

    public RequestRejectedPage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showRequestRejectedPage() {
        // Background color

        backtry();

        // Navigation buttons
        commonBottomBar();

        // constant bottom bar
        bottomHbox();

        // Background Image
        ground();

        Image backgroundImage = new Image(
                "com/ride_share/Assets/map photo.jpeg");
        BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        Label rejectionLabel = new Label("Your ride request has been rejected by the driver.");
        rejectionLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button backButton = new Button("Back to Home");

        VBox centerContent = new VBox(20, rejectionLabel, backButton);
        // centerContent.setStyle("-fx-background-color: LightBlue;");
        centerContent.setPadding(new Insets(20));
        centerContent.setAlignment(Pos.CENTER);

        // Static bottom bar

        // Main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(centerContent);
        mainLayout.setBottom(bottomBar);
        // mainLayout.setBackground(new Background(bgImage));
        mainLayout.setMaxWidth(500); // Set max width to match stage width
        mainLayout.setMaxHeight(620); // Set max height to match stage height
        mainLayout.setStyle("-fx-background-color: black; " +
                "-fx-background-radius: 20px; " + // Set radius for rounded corners
                "-fx-border-radius: 20px;"); // Optional: Set border radius

        mainLayout.setBackground(new Background(bgImage));

        // Setting the scene
        Scene scene = new Scene(mainLayout, 1470, 830);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Share Ride - Request Rejected");
        // primaryStage.setFullScreen(true);
        primaryStage.show();

        // Adding actions to the buttons
        backButton.setOnAction(event -> new HomePage().start(primaryStage)); // Navigate back to home page
        ratingsButton.setOnAction(event -> showRatingPage()); // Navigate to rating page (needs to be implemented)
        responseButton.setOnAction(event -> new ResponsePage(primaryStage).showResponsePage()); // Navigate to response
                                                                                                // page
        profileButton.setOnAction(event -> showProfilePage()); // Navigate to profile page (needs to be implemented)
    }

    private void showRatingPage() {
        // Implementation for showing the rating page
    }

    private void showProfilePage() {
        // Implementation for showing the profile page
    }
}