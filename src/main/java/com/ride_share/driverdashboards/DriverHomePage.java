package com.ride_share.driverdashboards;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.ride_share.dashboards.ProfilePage;

import com.ride_share.firebaseConfig.DataService;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.scene.layout.*;

public class DriverHomePage extends Application {

    private ComboBox<String> selectDestination;
    private ComboBox<String> endDestination;
    private DatePicker datePicker;
    private ComboBox<Integer> seatCount;
    private ComboBox<String> searchBox;
    public static String selectedValue;
    public static String endValue;
    public static String stopValue;
    private static int totalDistance;
    public static String goingDateDriver;
    public static String goingTimeDriverH;
    public static String goingTimeDriverM;
    public static int seatWithDriver;
    public static String readEmailUser;
    public static String driverEmail = DriverProfilePage.emailD;
    // flag for send request to driver
    public static String flag = "0";
    // Path for store email with destination
    public static String email = DriverProfilePage.emailD;

    private Button responseButton;

    @Override
    public void start(Stage primaryStage) {
        // icon added

        Image icon = new Image("file:src/main/java/com/ride_share/Assets/icon.jpeg");
        primaryStage.getIcons().add(icon);

        // added background color
        Region backgroundColor = new Region();
        backgroundColor.setStyle("-fx-background-color: yellow;");
        backgroundColor.setPrefSize(600, 400);

        // Background Image
        Image backgroundImage = new Image(
                "com/ride_share/Assets/map photo.jpeg");
        BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        // Welcome label
        Label welcomeLabel = new Label("Welcome,  " + DriverProfilePage.nameD);
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");

        selectDestination = new ComboBox<>();
        selectDestination.setPrefSize(150, 0);
        selectDestination.setPromptText("Leaving from");

        selectDestination.getItems().addAll("Satara", "Katraj", "Solapur", "Mumbai");

        // end Location is depend upon selected location

        selectDestination.setOnAction(event -> {
            endDestination.getItems().clear();
            searchBox.getItems().clear();
            selectedValue = selectDestination.getValue();
            if (selectedValue == "Satara") {
                endDestination.getItems().addAll("Katraj");
            } else if (selectedValue == "Katraj") {
                endDestination.getItems().addAll("Satara");
            } else if (selectedValue == "Solapur") {
                endDestination.getItems().addAll("Hadpsar");
            } else if (selectedValue == "Mumbai") {
                endDestination.getItems().addAll("Pune");
            }

        });

        endDestination = new ComboBox<>();
        endDestination.setPrefSize(150, 0);
        endDestination.setPromptText("Going To");

        endDestination.setOnAction(event -> {

            endValue = endDestination.getValue();
            if (selectedValue == "Satara" && endValue == "Katraj") {
                searchBox.getItems().addAll("Satara-Shirwal", "Shirwal-Katraj", "Satara-Katraj");
            } else if (selectedValue == "Katraj" && endValue == "Satara") {
                searchBox.getItems().addAll("Katraj-Shirwal", "Shirwal-Satara", "Katraj-Satara");
            } else if (selectedValue == "Solapur" && endValue == "Hadpsar") {
                searchBox.getItems().addAll("Solapur-Daund", "Daund-Hadpsar", "Solapur-Hadpsar");
            } else if (selectedValue == "Mumbai" && endValue == "Pune") {
                searchBox.getItems().addAll("Mumbai-Lonavala", "Lonavala-Pune", "Mumbai-Pune");
            }

        });

        HBox setDestination = new HBox(30, selectDestination, endDestination);
        setDestination.setAlignment(Pos.BASELINE_CENTER);

        // Creating a ComboBox for Searching the Destination
        searchBox = new ComboBox<>();
        searchBox.setPrefSize(330, 0);
        searchBox.setPromptText("Stops");

        // To calculate distance(km) from start and end location

        searchBox.setOnAction(event -> {
            stopValue = searchBox.getValue();
            switch (stopValue) {
                case "Satara-Shirwal":
                    totalDistance = 59;
                    break;

                case "Shirwal-Katraj":
                    totalDistance = 42;
                    break;

                case "Satara-Katraj":
                    totalDistance = 100;
                    break;

                case "Katraj-Shirwal":
                    totalDistance = 42;
                    break;

                case "Shirwal-Satara":
                    totalDistance = 59;
                    break;

                case "Katraj-Satara":
                    totalDistance = 100;
                    break;

                case "Solapur-Daund":
                    totalDistance = 184;
                    break;

                case "Daund-Katraj":
                    totalDistance = 85;
                    break;

                case "Solapur-Katraj":
                    totalDistance = 254;
                    break;

                case "Mumbai-Lonavla":
                    totalDistance = 86;
                    break;

                case "Lonawla-Katraj":
                    totalDistance = 73;
                    break;

                case "Mumbai-Katraj":
                    totalDistance = 159;
                    break;

                default:
                    break;
            }
            System.out.println(totalDistance);

        });

        HBox searchBoxBox = new HBox(10, searchBox);
        searchBoxBox.setAlignment(Pos.BASELINE_CENTER);

        // Label dropLabel = new Label("Date");
        TextField tx1 = new TextField();
        tx1.setMaxWidth(150);
        tx1.setPromptText(" Date-DD/MM/YY");

        // Creating DatePicker for selecting date and time
        /*
         * DatePicker datePicker = new DatePicker();
         * datePicker.setPromptText("Today");
         * datePicker.setOnAction(event ->{
         * goingDateDriver=datePicker.getValue();
         * System.out.println(goingDateDriver);
         * });
         */

        // datePicker.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;
        // -fx-text-fill:white");

        ComboBox<String> hourPicker = new ComboBox<>();
        ComboBox<String> minutePicker = new ComboBox<>();
        hourPicker.setPromptText("Hour");
        minutePicker.setPromptText("Minute");

        for (int hour = 0; hour < 24; hour++) {
            hourPicker.getItems().add(String.format("%02d", hour));
        }
        hourPicker.setOnAction(event -> {
            goingTimeDriverH = hourPicker.getValue();
            System.out.println(goingTimeDriverH);
            goingDateDriver = tx1.getText();
            System.out.println(goingDateDriver);
        });

        for (int minute = 0; minute < 60; minute += 15) {
            minutePicker.getItems().add(String.format("%02d", minute));
        }
        minutePicker.setOnAction(event -> {
            goingTimeDriverM = minutePicker.getValue();
            System.out.println(goingTimeDriverM);
        });

        HBox timePicker = new HBox(10, hourPicker, new Label(":"), minutePicker);
        timePicker.setAlignment(Pos.CENTER);

        // Creating a ComboBox for selecting number of seats
        ComboBox seatCount = new ComboBox<>();
        for (int i = 1; i <= 10; i++) {
            seatCount.getItems().add(i);
        }
        seatCount.setPromptText("No. of seats");

        // Creating a request button
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button cheackRide = new Button("Find Ride");
        cheackRide.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        cheackRide.setOnAction(event -> {
            DriverHomePage driver = new DriverHomePage();
            try {
                driver.readDataForDriverResponse(email);// Also call in responseButton
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (flag == "0") {
                System.out.println("No Passenger Yet");
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Wait For Passenger Request or Register Your Ride");
                alert.showAndWait();
            } else {
                responseButton.setStyle("-fx-font-size: 24px; -fx-background-color: red;-fx-font-weight: bold;");
            }
        });

        // Layout for the center content
        VBox centerContent = new VBox(40, welcomeLabel, setDestination, tx1, timePicker, seatCount,
                submitButton, cheackRide);
        centerContent.setAlignment(Pos.BASELINE_CENTER);
        centerContent.setPadding(new Insets(20));

        // Buttons ,bottom line approch navigation bar
        /*
         * DriverHomePage obj=new DriverHomePage();
         * obj.
         */responseButton = new Button("Response");
        responseButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button profileButton = new Button("Profile");
        profileButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button ratingButton = new Button("Ratings");
        ratingButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button earningButton = new Button("Earnings");
        earningButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button homeButton = new Button("Home");
        homeButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Bottom navigation added buttons
        HBox bottomBar = new HBox(150, homeButton, ratingButton, responseButton, profileButton, earningButton);
        bottomBar.setPadding(new Insets(10));
        bottomBar.setAlignment(Pos.CENTER);
        bottomBar.setStyle("-fx-background-color:black;");

        VBox blackBox = new VBox(centerContent);
        blackBox.setPadding(new Insets(100, 0, 0, 0));
        blackBox.setMaxWidth(500); // Set max width to match stage width
        blackBox.setMaxHeight(620); // Set max height to match stage height
        blackBox.setStyle("-fx-background-color: black; " +
                "-fx-background-radius: 20px; " + // Set radius for rounded corners
                "-fx-border-radius: 20px;"); // Optional: Set border radius for consistency

        // Main layout with center content and bottom bar
        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(blackBox);
        mainLayout.setBottom(bottomBar);
        mainLayout.setBackground(new Background(bgImage));

        // added all main layout to show background color
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(mainLayout);

        // Create scene with the stackpane
        Scene scene = new Scene(stackPane, 1470, 830);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Share Ride - Driver Home Page");
        // primaryStage.setFullScreen(true);
        primaryStage.show();

        // When click in submit button then store destination in firestore

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*
                 * endDestination.getItems().clear();
                 * searchBox.getItems().clear();
                 * selectDestination.getItems().clear();
                 */
                handleDestination(primaryStage, email, selectedValue, endValue, goingDateDriver, goingTimeDriverH,
                        goingTimeDriverM, DriverResponsePage.rideAcceptFlag);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ride Registered Succesfully");
                alert.showAndWait();

            }
        });
        // submitButton.setOnAction(event -> new
        // DriverTicketSummaryPage(primaryStage).showTicketSummaryPage());
        responseButton.setOnAction(event -> {
            DriverHomePage driver = new DriverHomePage();
            try {
                driver.readDataForDriverResponse(driverEmail);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception ee) {
                // ee.printStackTrace();

            }
            System.out.println(DriverHomePage.readEmailUser);
            System.out.println(DriverHomePage.flag);

            DriverResponsePage read = new DriverResponsePage();
            try {
                read.readRecUser(DriverHomePage.readEmailUser);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (DriverHomePage.flag == "0" /* || DriverTicketSummaryPage.cancelRideD=="0" */) {

                DriverPendingRequestPage page1 = new DriverPendingRequestPage(primaryStage);
                page1.showDriverPendingRequestPage();
            } else if (DriverResponsePage.dFlag == "2") {
                DriverTicketSummaryPage obj = new DriverTicketSummaryPage(primaryStage);
                obj.showDriverTicketSummaryPage();
            }

            else {

                DriverResponsePage page2 = new DriverResponsePage(primaryStage);
                page2.showResponsePage();
            }
        });
        profileButton.setOnAction(event -> new DriverProfilePage(primaryStage).showProfilePage());
        ratingButton.setOnAction(event -> new DriverRatingUserPage(primaryStage).showRatingUserPage());
        earningButton.setOnAction(event -> new DriverEarningPage(primaryStage).showEarningPage());
    }

    // Fire Store data Store Driver Start destination and End destination

    public void handleDestination(Stage primaryStage, String email, String selectedValue,
            String endValue, String goingDateD, String goingTimeDriverH,
            String goingTimeDriverM, String rideAcceptFlag) {
        DataService dataService; // Local instance of DataService
        try {
            dataService = new DataService(); // Initialize DataService instance
            // Create a map to hold user data
            Map<String, Object> data = new HashMap<>();
            data.put("startDestinationDriver", selectedValue);
            data.put("endDestinationDriver", endValue);
            data.put("goingDate", goingDateD);
            data.put("goingTimeDriverH", goingTimeDriverH);
            data.put("goingTimeDriverM", goingTimeDriverM);
            data.put("rideAcceptFlag", rideAcceptFlag);
            // Add user data to Firestore
            dataService.addData("driverRide", email, data);
            System.out.println("Ride registered successfully");

            // Navigate back to the login scene
            // loginController.showLoginScene();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // read data from firestore for chech request of passenger or check passenger
    // want to join ride
    public void readDataForDriverResponse(String email) throws InterruptedException, ExecutionException {

        DocumentReference docRef = DataService.db.collection("requestToDriver").document(email);

        ApiFuture<DocumentSnapshot> snapShot = docRef.get();
        DocumentSnapshot docSnap = snapShot.get();

        if (docSnap.exists()) {
            // Firebase varun yenara data type Object aahe , so convert it into string
            Object obj1 = (docSnap.get("passengerEmail"));// field jr chukli tr nullPointerException yeti
            readEmailUser = obj1.toString();
            System.out.println(readEmailUser);
            Object obj2 = docSnap.get("flag");
            flag = obj2.toString();
            System.out.println(flag);

        } else {
            System.out.println("Document Not Found");
        }
    }

    public static void main(String[] args) {
        launch(DriverHomePage.class, args);
    }

}