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

public class DriverEarningPage extends ParentDriver {

    private Stage primaryStage;

    public DriverEarningPage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showEarningPage() {
        backtry();

        // Background Image
        Image backgroundImage = new Image(
                "com/ride_share/Assets/map photo.jpeg");
        BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        Label totalEarningsLabel = new Label("Total Earnings: ₹5000");
        totalEarningsLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");
        Label completedRidesLabel = new Label("Completed Rides: 20");
        completedRidesLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");
        Label pendingPaymentsLabel = new Label("Pending Payments: ₹1000");
        pendingPaymentsLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");

        VBox earningDetails = new VBox(20, totalEarningsLabel, completedRidesLabel, pendingPaymentsLabel);
        earningDetails.setPadding(new Insets(20));
        earningDetails.setAlignment(Pos.CENTER);

        // Static bottom bar
        commonBottomBar();

        HBox bottomBar = new HBox(150, backButton, ratingButton, responseButton, profileButton, earningButton);
        bottomBar.setPadding(new Insets(10));
        bottomBar.setAlignment(Pos.CENTER);
        bottomBar.setStyle("-fx-background-color: black;");

        VBox blackBox = new VBox(earningDetails);
        blackBox.setPadding(new Insets(100, 0, 0, 0));
        blackBox.setMaxWidth(500); // Set max width to match stage width
        blackBox.setMaxHeight(620); // Set max height to match stage height
        blackBox.setStyle("-fx-background-color: black; " +
                "-fx-background-radius: 20px; " + // Set radius for rounded corners
                "-fx-border-radius: 20px;"); // Optional: Set border radius for consistency

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(blackBox);
        mainLayout.setBottom(bottomBar);
        mainLayout.setBackground(new Background(bgImage));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(mainLayout);

        Scene scene = new Scene(stackPane, 1470, 830);
        primaryStage.setScene(scene);
        // primaryStage.setFullScreen(true);
        primaryStage.setTitle("Driver Earnings Page");
        primaryStage.show();

        backButton.setOnAction(event -> new DriverHomePage().start(primaryStage));
        responseButton.setOnAction(event -> new DriverResponsePage(primaryStage).showResponsePage());
        profileButton.setOnAction(event -> new DriverProfilePage(primaryStage).showProfilePage());
        ratingButton.setOnAction(event -> new DriverRatingUserPage(primaryStage).showRatingUserPage());
        earningButton.setOnAction(event -> showEarningPage());
    }
}