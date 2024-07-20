package com.ride_share.driverdashboards;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
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

public class DriverRatingUserPage extends ParentDriver {

    private Stage primaryStage;

    public DriverRatingUserPage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showRatingUserPage() {
        backtry();

        // Background Image
        Image backgroundImage = new Image(
                "com/ride_share/Assets/map photo.jpeg");
        BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        Label userLabel = new Label("Rate Your User:");
        userLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");

        ToggleGroup ratingGroup = new ToggleGroup();
        HBox ratingOptions = new HBox(10);
        for (int i = 1; i <= 5; i++) {
            RadioButton star = new RadioButton(Integer.toString(i));
            star.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");
            star.setToggleGroup(ratingGroup);
            ratingOptions.getChildren().add(star);
        }
        ratingOptions.setAlignment(Pos.CENTER);

        VBox ratingBox = new VBox(10, userLabel, ratingOptions);
        ratingBox.setPadding(new Insets(10));
        ratingBox.setAlignment(Pos.CENTER);

        TextArea commentBox = new TextArea();
        commentBox.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");
        commentBox.setPromptText("Write your experience with the user...");
        commentBox.setMaxWidth(300);
        commentBox.setPrefHeight(200);

        Label commentLabel = new Label("Comments:");
        commentLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");

        VBox commentBoxBox = new VBox(10, commentLabel, commentBox);
        commentBoxBox.setPadding(new Insets(20));
        commentBoxBox.setMaxHeight(300);
        commentBoxBox.setAlignment(Pos.CENTER);

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        submitButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Rating submitted successfully!");
            alert.showAndWait();
        });

        VBox submitButtonBox = new VBox(10, submitButton);
        submitButtonBox.setPadding(new Insets(20));
        submitButtonBox.setAlignment(Pos.CENTER);

        // Static bottom bar
        commonBottomBar();

        HBox bottomBar = new HBox(150, backButton, ratingButton, responseButton, profileButton, earningButton);
        bottomBar.setPadding(new Insets(10));
        bottomBar.setAlignment(Pos.CENTER);
        bottomBar.setStyle("-fx-background-color: black;");

        VBox blackBox = new VBox(20, ratingBox, commentBoxBox, submitButtonBox);
        blackBox.setPadding(new Insets(80, 0, 0, 0));
        blackBox.setMaxWidth(500); // Set max width to match stage width
        blackBox.setMaxHeight(620); // Set max height to match stage height
        blackBox.setStyle("-fx-background-color: black; " +
                "-fx-background-radius: 20px; " + // Set radius for rounded corners
                "-fx-border-radius: 20px;"); // Optional: Set border radius for consistency

        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(30, 0, 0, 0));
        mainLayout.setCenter(blackBox);
        mainLayout.setBottom(bottomBar);
        mainLayout.setBackground(new Background(bgImage));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(mainLayout);

        Scene scene = new Scene(stackPane, 1470, 830);
        primaryStage.setScene(scene);
        // primaryStage.setFullScreen(true);
        primaryStage.setTitle("Driver Rating User Page");
        primaryStage.show();

        backButton.setOnAction(event -> new DriverHomePage().start(primaryStage));
        responseButton.setOnAction(event -> new DriverResponsePage(primaryStage).showResponsePage());
        profileButton.setOnAction(event -> new DriverProfilePage(primaryStage).showProfilePage());
        earningButton.setOnAction(event -> new DriverEarningPage(primaryStage).showEarningPage());
    }
}