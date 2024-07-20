package com.ride_share.dashboards;

import com.ride_share.firebaseConfig.MatchRide;

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

public class TicketSummaryPage extends ParentUser {

    private Stage primaryStage;
    private String pickupLocation;
    private String dropLocation;
    private String date;
    private String vehicleType;
    private int seats;
    private double ticketPrice;

    public TicketSummaryPage(Stage primaryStage) {
        this.primaryStage = primaryStage;

    }

    public void showUserTicketSummaryPage() {

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

        Label confirmationLabel = new Label("Ride Confirmed");
        confirmationLabel.setStyle("-fx-text-fill: white;-fx-font-size: 24px; -fx-font-weight: bold;");

        Label pickupLabel = new Label("Driver Name: " + MatchRide.nameDriver);
        Label phoneNoD = new Label("Driver Name: " + MatchRide.phoneNoDriver);
        Label dropLabel = new Label("Drop Location: " + HomePage.stopValue);
        Label dateLabel = new Label("Date: " + ViewPage.goingDateDriver);
        Label timeLabel = new Label("Time: " + ViewPage.driverGoingTimeH + ":" + ViewPage.driverGoingTimeM);
        Label vehicleLabel = new Label("Vehicle Type: " + ViewPage.vehicleType);
        Label vehicleNo = new Label("Vehicle Type: " + MatchRide.vehicleNoDriver);
        Label seatsLabel = new Label("Booked Seats: " + HomePage.seatWithUser);
        Label priceLabel = new Label("Ticket Price: ₹" + HomePage.costTicket);

        pickupLabel.setStyle("-fx-text-fill: white;-fx-font-size: 18px; -fx-font-weight: bold;");
        phoneNoD.setStyle("-fx-text-fill: white;-fx-font-size: 18px; -fx-font-weight: bold;");
        timeLabel.setStyle("-fx-text-fill: white;-fx-font-size: 18px; -fx-font-weight: bold;");
        vehicleNo.setStyle("-fx-text-fill: white;-fx-font-size: 18px; -fx-font-weight: bold;");
        dropLabel.setStyle("-fx-text-fill: white;-fx-font-size: 18px; -fx-font-weight: bold;");
        dateLabel.setStyle("-fx-text-fill: white;-fx-font-size: 18px; -fx-font-weight: bold;");
        vehicleLabel.setStyle("-fx-text-fill: white;-fx-font-size: 18px; -fx-font-weight: bold;");
        seatsLabel.setStyle("-fx-text-fill: white;-fx-font-size: 18px; -fx-font-weight: bold;");
        priceLabel.setStyle("-fx-text-fill: white;-fx-font-size: 18px; -fx-font-weight: bold;");

        VBox centerContent = new VBox(20, confirmationLabel, pickupLabel, phoneNoD, dropLabel, dateLabel, timeLabel,
                vehicleLabel, vehicleNo,
                seatsLabel, priceLabel);
        centerContent.setStyle("-fx-background-color: black;");
        centerContent.setPadding(new Insets(20));
        centerContent.setAlignment(Pos.CENTER);

        VBox blackbox = new VBox(centerContent);
        blackbox.setPadding(new Insets(100, 0, 0, 0));
        blackbox.setMaxWidth(500); // Set max width to match stage width
        blackbox.setMaxHeight(620); // Set max height to match stage height
        blackbox.setStyle("-fx-background-color: black; " +
                "-fx-background-radius: 20px; " + // Set radius for rounded corners
                "-fx-border-radius: 20px;");

        // Static bottom bar

        // Main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(blackbox);
        mainLayout.setBottom(bottomBar);
        // mainLayout.setBackground(new Background(bgImage));
        mainLayout.setMaxWidth(500); // Set max width to match stage width
        mainLayout.setMaxHeight(620); // Set max height to match stage height
        mainLayout.setStyle("-fx-background-color: yellow; " +
                "-fx-background-radius: 20px; " + // Set radius for rounded corners
                "-fx-border-radius: 20px;"); // Optional: Set border radius
        mainLayout.setBackground(new Background(bgImage));

        // Setting the scene
        Scene scene = new Scene(mainLayout, 1470, 830);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Share Ride - Ticket Summary");
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