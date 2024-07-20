package com.ride_share.driverdashboards;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.ride_share.firebaseConfig.DataService;

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

public class DriverResponsePage {

    public static String userName;
    public static String userPhoneNumber;
    public static String stopValue;
    public static Object seatWithUser;
    public static String rideAcceptFlag = "0";
    public static Object distance = 0;
    static Object costTicket;
    static String dFlag;

    private Stage primaryStage;

    public DriverResponsePage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public DriverResponsePage() {

    }

    public void showResponsePage() {
        Region background = new Region();
        background.setStyle("-fx-background-color: yellow;");
        background.setPrefSize(600, 400);

        // Background Image
        Image backgroundImage = new Image(
                "com/ride_share/Assets/map photo.jpeg");
        BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        Label userInfo = new Label("Passenger - " + userName);
        userInfo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");

        Label phoneNo = new Label("Phone Number:" + userPhoneNumber);
        phoneNo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");

        Label pickupLabel = new Label("Destination - " + stopValue);
        pickupLabel.setStyle("-fx-font: 15 arial; -fx-base: #00FF;");
        Label seatsLabel = new Label("Seats - " + seatWithUser);
        seatsLabel.setStyle("-fx-font: 15 arial; -fx-base: #00FF;");
        Label costsLabel = new Label("Cost - " + costTicket);
        costsLabel.setStyle("-fx-font: 15 arial; -fx-base: #00FF;");

        VBox userProfile = new VBox(10, userInfo, phoneNo, pickupLabel, seatsLabel, costsLabel);
        userProfile.setPadding(new Insets(100));
        userProfile.setAlignment(Pos.CENTER);

        Button acceptButton = new Button("Accept");
        Button declineButton = new Button("Decline");

        HBox responseButtons = new HBox(30, acceptButton, declineButton);
        responseButtons.setPadding(new Insets(10));
        responseButtons.setAlignment(Pos.CENTER);

        VBox centerContent = new VBox(30, userProfile, responseButtons);
        centerContent.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button responseButton = new Button("Response");
        responseButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button profileButton = new Button("Profile");
        profileButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button ratingButton = new Button("Ratings");
        ratingButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button earningButton = new Button("Earnings");
        earningButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        HBox bottomBar = new HBox(150, backButton, ratingButton, responseButton, profileButton, earningButton);
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

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(blackBox);
        mainLayout.setBottom(bottomBar);
        mainLayout.setBackground(new Background(bgImage));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(mainLayout);

        Scene scene = new Scene(stackPane, 1470, 830);
        primaryStage.setScene(scene);
        // primaryStage.setFullScreen(true);
        primaryStage.setTitle("Driver Response Page");
        primaryStage.show();

        backButton.setOnAction(event -> new DriverHomePage().start(primaryStage));
        responseButton.setOnAction(event -> showResponsePage());
        profileButton.setOnAction(event -> new DriverProfilePage(primaryStage).showProfilePage());
        ratingButton.setOnAction(event -> new DriverRatingUserPage(primaryStage).showRatingUserPage());
        earningButton.setOnAction(event -> new DriverEarningPage(primaryStage).showEarningPage());

        acceptButton.setOnAction(event -> {
            // Handle the acceptance of the ride request
            System.out.println("Ride accepted");
            rideAcceptFlag = "1";
            DriverHomePage.flag = "2";
            dFlag = DriverHomePage.flag;
            DriverResponsePage obj1 = new DriverResponsePage();
            obj1.handleDestinationAcceptedFlag(primaryStage, DriverProfilePage.emailD, rideAcceptFlag);
            DriverTicketSummaryPage obj = new DriverTicketSummaryPage(primaryStage);
            obj.showDriverTicketSummaryPage();

        });

        declineButton.setOnAction(event -> {
            // Handle the decline of the ride request
            rideAcceptFlag = "0";
            System.out.println("Ride declined");
            DriverPendingRequestPage obj = new DriverPendingRequestPage();
            obj.showDriverPendingRequestPage();
        });
    } // Retrive record of user from fire store
      // Display Record to Driver

    public void readRecUser(String email) throws InterruptedException, ExecutionException {

        DocumentReference docRef = DataService.db.collection("UserRide").document(email);

        ApiFuture<DocumentSnapshot> snapShot = docRef.get();
        DocumentSnapshot docSnap = snapShot.get();

        if (docSnap.exists()) {
            // Firebase varun yenara data type Object aahe , so convert it into string
            Object obj = (docSnap.get("name"));
            userName = obj.toString();
            Object obj2 = docSnap.get("phoneNumber");
            userPhoneNumber = obj2.toString();
            Object obj3 = docSnap.get("stopValue");
            stopValue = obj3.toString();
            distance = docSnap.get("distance");
            seatWithUser = docSnap.get("seatWithUser");
            costTicket = docSnap.get("costTicket");
            // seatWithUser = obj3.toString();
            System.out.println(userName);

        } else {
            System.out.println("Document Not Found");
        }
    }

    private void handleDestinationAcceptedFlag(Stage primaryStage, String email, String rideAcceptFlag) {
        DataService dataService; // Local instance of DataService
        try {
            dataService = new DataService(); // Initialize DataService instance
            // Create a map to hold user data
            Map<String, Object> data = new HashMap<>();
            data.put("rideAcceptFlag", rideAcceptFlag);
            // Add user data to Firestore
            dataService.addData("driverRideFlag", email, data);
            System.out.println("Ride registered successfully");

            // Navigate back to the login scene
            // loginController.showLoginScene();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}