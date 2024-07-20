package com.ride_share.dashboards;

import java.util.concurrent.ExecutionException;

import com.ride_share.firebaseConfig.MatchRide;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class UserRatingsDriver extends ParentUser {

    private Stage primaryStage;
    public static String rideAcceptFlagInUser;

    public UserRatingsDriver(Stage primaryStage) {
        this.primaryStage = primaryStage;

    }

    public void showDriverRatingsPage() {
        // Background color

        backtry();

        // Navigation buttons
        commonBottomBar();

        // constant bottom bar
        bottomHbox();

        ground();

        // Background Image
        Image backgroundImage = new Image(
                "com/ride_share/Assets/map photo.jpeg");
        BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        // Driver's profile information (static for demonstration)
        Label nameLabel = new Label("Driver: Abhishek Dere");
        nameLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 24px;");
        Label ratingLabel = new Label("Rating: 4.5/5");
        ratingLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 18px;");

        VBox driverProfileBox = new VBox(10, nameLabel, ratingLabel);
        driverProfileBox.setPadding(new Insets(10));
        driverProfileBox.setAlignment(Pos.CENTER);

        // Rating options
        ToggleGroup ratingGroup = new ToggleGroup();
        HBox ratingOptions = new HBox(10);
        for (int i = 1; i <= 5; i++) {
            RadioButton star = new RadioButton(Integer.toString(i));
            star.setToggleGroup(ratingGroup);
            star.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
            ratingOptions.getChildren().add(star);
        }
        ratingOptions.setAlignment(Pos.CENTER);

        Label lb1 = new Label("Rate the driver:");
        lb1.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 18px;");
        VBox ratingOptionsBox = new VBox(10, lb1, ratingOptions);
        ratingOptionsBox.setPadding(new Insets(10));
        ratingOptionsBox.setAlignment(Pos.CENTER);

        // Comment box
        TextArea commentBox = new TextArea();
        commentBox.setPrefHeight(200);
        commentBox.setMaxWidth(300);
        commentBox.setStyle("-fx-control-inner-background: white; -fx-text-fill: white;");
        commentBox.setPromptText("Write your experience with the driver...");

        Label commentLabel = new Label("Comments");
        commentLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 18px;");

        VBox commentBoxBox = new VBox(10, commentLabel, commentBox);
        commentBoxBox.setMaxHeight(200);
        commentBoxBox.setPadding(new Insets(20));
        commentBoxBox.setAlignment(Pos.CENTER);

        // Payment button
        Button paymentButton = new Button("Pay Now");
        paymentButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-weight: bold;");
        paymentButton.setOnAction(event -> {
            // Placeholder for payment logic
            Alert paymentAlert = new Alert(Alert.AlertType.INFORMATION, "Payment successful!");
            paymentAlert.showAndWait();
        });

        VBox paymentButtonBox = new VBox(10, paymentButton);
        paymentButtonBox.setPadding(new Insets(20));
        paymentButtonBox.setAlignment(Pos.CENTER);

        // bottom bar

        // Main content VBox with 16:9 aspect ratio and rounded corners
        VBox mainContent = new VBox(20, driverProfileBox, ratingOptionsBox, commentBoxBox, paymentButtonBox);
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setMaxWidth(500); // Set max width to match stage width
        mainContent.setMaxHeight(620); // Set max height to match stage height
        mainContent.setStyle("-fx-background-color: black; " +
                "-fx-background-radius: 20px; " + // Set radius for rounded corners
                "-fx-border-radius: 20px;"); // Optional: Set border radius for consistency

        BorderPane mainLayout2 = new BorderPane();
        mainLayout2.setCenter(mainContent);
        mainLayout2.setBottom(bottomBar);
        // mainLayout.setBackground(new Background(bgImage));
        // mainLayout2.setMaxWidth(500); // Set max width to match stage width
        // mainLayout2.setMaxHeight(620); // Set max height to match stage height
        // mainLayout2.setStyle("-fx-background-color: black; " +
        // "-fx-background-radius: 20px; " + // Set radius for rounded corners
        // "-fx-border-radius: 20px;"); // Optional: Set border radius

        mainLayout2.setBackground(new Background(bgImage));

        // StackPane to hold the yellow background and main content
        StackPane root = new StackPane();
        root.getChildren().addAll(mainLayout2);

        // Setting the scene
        Scene scene = new Scene(root, 1470, 830);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rate Your Driver");
        // primaryStage.setFullScreen(true);
        primaryStage.show();

        // Adding actions to the buttons
        backButton.setOnAction(event -> {
            // Placeholder for navigation back to previous page
            new HomePage().start(primaryStage);
            // primaryStage.setFullScreen(true);
        });

        responseButton.setOnAction(event -> {
            // To generate User ticket
            MatchRide ma = new MatchRide();
            try {
                ma.readRecDriverToGenerateTicket(ViewPage.storeEmailDriver);
                rideAcceptFlagInUser = MatchRide.rideAcceptFlag;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (rideAcceptFlagInUser == "0") {
                UserPendingRequestPage requestPage = new UserPendingRequestPage(primaryStage);
                requestPage.showUserPendingRequestPage();
            } else {
                // method for store data for driver in ticket
                try {
                    ma.readRecDriverDataOnUserTicket(ViewPage.storeEmailDriver);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                TicketSummaryPage ticket = new TicketSummaryPage(primaryStage);
                ticket.showUserTicketSummaryPage();
            }

        });
        ratingsButton.setOnAction(event -> {
            UserRatingsDriver ratingsPage = new UserRatingsDriver(primaryStage);

            ratingsPage.showDriverRatingsPage();
        });

        profileButton.setOnAction(event -> {
            ProfilePage profilePage = new ProfilePage(primaryStage);
            // primaryStage.setFullScreen(true);
            profilePage.showProfilePage();
        });
    }
}