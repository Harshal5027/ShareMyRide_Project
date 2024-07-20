package com.ride_share.dashboards;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.ride_share.firebaseConfig.DataService;

public class ViewPage extends Application {

    private ToggleGroup vehicleGroup;
    private static VBox centerContent;
    private static String nameDriver;
    private static Object goingDateD;
    public static String vehicleType;
    public static String storeEmailUser;
    public static String storeEmailDriver;
    public static String driverGoingTimeH;
    public static String driverGoingTimeM;
    public static Object goingDateDriver;
    // flag for send request to driver
    public static String flag = "0";
    private static Stage primaryStage;

    public ViewPage() {
        // default
    }

    public ViewPage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showViewPage() {
        start(primaryStage);
    }

    @Override
    public void start(Stage primaryStage) {
        if (this.primaryStage == null) {
            this.primaryStage = primaryStage;
        }

        Region backgroundColor = new Region();
        backgroundColor.setStyle("-fx-background-color: yellow;");
        backgroundColor.setPrefSize(600, 400);

        // Background Image
        Image backgroundImage = new Image(
                "com/ride_share/Assets/map photo.jpeg");
        BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        // Radio buttons for vehicle
        RadioButton cab = new RadioButton("Cab");
        RadioButton suv = new RadioButton("SUV");
        RadioButton pickup = new RadioButton("Pickup");
        RadioButton minibus = new RadioButton("Minibus");
        RadioButton truck = new RadioButton("Truck");
        cab.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        suv.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        pickup.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        minibus.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        truck.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        // Grouping radio buttons
        vehicleGroup = new ToggleGroup();
        cab.setToggleGroup(vehicleGroup);
        suv.setToggleGroup(vehicleGroup);
        pickup.setToggleGroup(vehicleGroup);
        minibus.setToggleGroup(vehicleGroup);
        truck.setToggleGroup(vehicleGroup);

        HBox vehicleOptions = new HBox(30, cab, suv, pickup, minibus, truck);
        vehicleOptions.setAlignment(Pos.BASELINE_CENTER);

        // Layout for the center content
        centerContent = new VBox(30, vehicleOptions);
        centerContent.setAlignment(Pos.BASELINE_CENTER);
        centerContent.setPadding(new Insets(20));

        // Creating the static bottom bar with back, response, profile, and ratings
        // buttons
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button responseButton = new Button("Response");
        responseButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button profileButton = new Button("Profile");
        profileButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button ratingsButton = new Button("Ratings");
        ratingsButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        HBox bottomBar = new HBox(150, backButton, ratingsButton, responseButton, profileButton);
        bottomBar.setPadding(new Insets(10));
        bottomBar.setAlignment(Pos.CENTER);
        bottomBar.setStyle("-fx-background-color: black;");

        VBox blackbox = new VBox(centerContent);
        blackbox.setPadding(new Insets(100, 0, 0, 0));
        // blackbox.setTop(vehicleOptions);
        blackbox.setMaxWidth(500); // Set max width to match stage width
        blackbox.setMaxHeight(620); // Set max height to match stage height
        blackbox.setStyle("-fx-background-color: black; " +
                "-fx-background-radius: 20px; " + // Set radius for rounded corners
                "-fx-border-radius: 20px;"); // Optional: Set border radius for consistency

        // Main layout

        // Main layout
        BorderPane mainLayout = new BorderPane(blackbox);
        // mainLayout.setTop(vehicleOptions);
        BorderPane.setMargin(vehicleOptions, new Insets(180, 0, 20, 0)); // Adding margin to top layout
        // mainLayout.setCenter(centerContent);
        mainLayout.setBottom(bottomBar);
        mainLayout.setBackground(new Background(bgImage));

        // StackPane to hold the background color and the main layout
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundColor, mainLayout);

        // Setting the scene
        Scene scene = new Scene(stackPane, 1470, 830);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Share Ride - View Page");
        // primaryStage.setFullScreen(true);
        primaryStage.show();

        // Adding event handlers to radio buttons
        cab.setOnAction(event -> updateCenterVbox()); // method in MatchRide
        /*
         * suv.setOnAction(event -> updateCenterVbox("SUV"));
         * pickup.setOnAction(event -> updateCenterVbox("Pickup"));
         * minibus.setOnAction(event -> updateCenterVbox("Minibus"));
         * truck.setOnAction(event -> updateCenterVbox("Truck"));
         */

        // Adding action to navigate to the ResponsePage
        responseButton.setOnAction(event -> {
            ResponsePage responsePage = new ResponsePage(primaryStage);
            // primaryStage.setFullScreen(true);
            responsePage.showResponsePage();
        });

        // Adding action to navigate to the UserRatingsDriver page
        ratingsButton.setOnAction(event -> {
            UserRatingsDriver ratingsPage = new UserRatingsDriver(primaryStage);
            // primaryStage.setFullScreen(true);
            ratingsPage.showDriverRatingsPage();
        });

        // Adding action to navigate to the ProfilePage
        profileButton.setOnAction(event -> {
            ProfilePage profilePage = new ProfilePage(primaryStage);
            // primaryStage.setFullScreen(true);
            profilePage.showProfilePage();
        });

        // Adding action to navigate to the HomePage
        backButton.setOnAction(event -> {
            HomePage homePage = new HomePage();
            // primaryStage.setFullScreen(true);
            homePage.start(primaryStage);
        });
    }

    // Call in MatchRide
    // Retrive Driver Name and VehicleType from fire store
    public void readRecDriverForMatchRide(String email) throws InterruptedException, ExecutionException {

        DocumentReference docRef = DataService.db.collection("drivers").document(email);

        ApiFuture<DocumentSnapshot> snapShot = docRef.get();
        DocumentSnapshot docSnap = snapShot.get();

        if (docSnap.exists()) {
            // Firebase varun yenara data type Object aahe , so convert it into string
            Object obj1 = (docSnap.get("name"));// field jr chukli tr nullPointerException yeti
            nameDriver = obj1.toString();
            System.out.println(nameDriver);
            Object obj2 = docSnap.get("vehicleType");
            vehicleType = obj2.toString();
            System.out.println(vehicleType);

        } else {
            System.out.println("Document Not Found");
        }

        DocumentReference docRef1 = DataService.db.collection("driverRide").document(email);

        ApiFuture<DocumentSnapshot> snapShot1 = docRef1.get();
        DocumentSnapshot docSnap1 = snapShot1.get();

        if (docSnap1.exists()) {
            // Firebase varun yenara data type Object aahe , so convert it into string
            Object obj3 = (docSnap1.get("goingTimeDriverH"));
            driverGoingTimeH = obj3.toString();
            System.out.println(obj3);
            Object obj4 = (docSnap1.get("goingTimeDriverM"));
            driverGoingTimeM = obj4.toString();

            goingDateDriver = (docSnap1.get("goingDate"));
            System.out.println(goingDateDriver);

        } else {
            System.out.println("Document Not Found");
        }

    }

    public static void updateCenterVbox() {
        // centerContent.getChildren().clear();

        // Create a list of rides

        /* for (Ride ride : rides) { */
        Label passengerNameLabel = new Label("Driver Name: " + nameDriver/* Ride.getPassengerName() */);
        passengerNameLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15px;");
        // Label dateLabel = new Label("Date: " + goingDateD/*Ride.getDate()*/);
        Label timeLabel = new Label("Time: " + driverGoingTimeH + ":" + driverGoingTimeM);
        timeLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15px;");
        // Label driverRatingLabel = new Label("Driver Rating: " +
        // Ride.getDriverRating());
        Label dateLabel = new Label("Date:" + goingDateDriver);
        dateLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15px;");

        // "Join this ride" button with transparent background
        Button joinButton = new Button("Join this ride");
        joinButton.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: lightgreen; -fx-font-weight: bold; -fx-font-size: 18px;");

        // To send request to Driver
        joinButton.setOnAction(event -> {

            System.out.println("Request Send to Driver, Please wait for driver responce");
            flag = "1";
            System.out.println(flag);
            storeEmailUser = HomePage.emailUser;
            System.out.println(storeEmailUser);
            storeEmailDriver = HomePage.emailD;
            System.out.println(storeEmailDriver);
            // MatchRide obj=new MatchRide();
            ViewPage obj = new ViewPage();
            obj.storeDataForDriverRequest(storeEmailDriver, storeEmailUser, flag);
            /*
             * try {
             * 
             * System.out.println(storeEmailUser);
             * // obj.readRecUserInDriverResponse(storeEmailDriver);
             * } catch (InterruptedException e) {
             * // TODO Auto-generated catch block
             * e.printStackTrace();
             * } catch (ExecutionException e) {
             * // TODO Auto-generated catch block
             * e.printStackTrace();
             * }
             */
            // Create an alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Please wait for driver response...");

            // Set the alert's modality and owner
            alert.initOwner(primaryStage);
            alert.initModality(Modality.WINDOW_MODAL);

            // Show the alert and wait for user response
            alert.showAndWait();

        });

        VBox vBox = new VBox(10, passengerNameLabel, dateLabel);

        HBox hBox1 = new HBox(20, timeLabel);
        hBox1.setPadding(new Insets(30, 0, 0, 0));

        VBox hBox = new VBox(joinButton);

        HBox hBoxMain = new HBox(10, vBox, hBox1, hBox);
        hBoxMain.setPadding(new Insets(10, 0, 0, 0));
        hBoxMain.setAlignment(Pos.CENTER);
        centerContent.getChildren().add(hBoxMain);

    }

    // Store data on fire store
    // send request to driver in driver response page
    private void storeDataForDriverRequest(String storeEmailDriver, String storeEmailUser, String flag) {
        DataService dataService; // Local instance of DataService
        try {
            dataService = new DataService(); // Initialize DataService instance
            // Create a map to hold user data
            Map<String, Object> data = new HashMap<>();
            data.put("passengerEmail", storeEmailUser);
            data.put("DriverEmail", storeEmailDriver);
            data.put("flag", flag);
            // data.put("goingDate", goingDate);
            // Add user data to Firestore
            dataService.addData("requestToDriver", storeEmailDriver, data);
            System.out.println("store data successfully");

            // Navigate back to the login scene
            // loginController.showLoginScene();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
