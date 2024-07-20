package com.ride_share.dashboards;

import com.ride_share.driverdashboards.DriverEarningPage;
import com.ride_share.driverdashboards.DriverHomePage;
import com.ride_share.driverdashboards.DriverProfilePage;
import com.ride_share.driverdashboards.DriverRatingUserPage;
import com.ride_share.driverdashboards.DriverResponsePage;

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

public class UserPendingRequestPage extends ParentUser {

    private Stage primaryStage;

    public UserPendingRequestPage(Stage primaryStage) {
        this.primaryStage = primaryStage;

    }

    public void showUserPendingRequestPage() {
        // Background color

        backtry();

        // Navigation buttons
        commonBottomBar();

        // constant bottom bar
        bottomHbox();

        ground();

        Image backgroundImage = new Image("com/ride_share/Assets/generalBg.jpeg");
        BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        Label confirmationLabel = new Label("Please Wait until your Ride gets Confirmed by Driver.......");
        confirmationLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        HBox centerContent = new HBox(20, confirmationLabel);
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
                "-fx-border-radius: 20px;"); // Optional: Set border radius for consistency
        mainLayout.setBackground(new Background(bgImage));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(mainLayout);

        // Setting the scene
        Scene scene = new Scene(stackPane, 1470, 830);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Share Ride- Waiting Page");
        // primaryStage.setFullScreen(true);
        primaryStage.show();

        // Adding actions to the buttons
        backButton.setOnAction(event -> new DriverHomePage().start(primaryStage)); // Navigate back to home page
        ratingsButton.setOnAction(event -> new DriverRatingUserPage(primaryStage).showRatingUserPage());
        responseButton.setOnAction(event -> new DriverResponsePage(primaryStage).showResponsePage());
        profileButton.setOnAction(event -> new DriverProfilePage(primaryStage).showProfilePage());
        // earningButton.setOnAction(event -> new
        // DriverEarningPage(primaryStage).showEarningPage());

    }
}
