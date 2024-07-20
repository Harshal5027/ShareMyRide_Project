package com.ride_share.dashboards;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.ride_share.firebaseConfig.DataService;
import com.ride_share.firebaseConfig.MatchRide;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HomePage extends Application {

    private ComboBox<String> selectDestination;
    private ComboBox<String> endDestination;
    private DatePicker datePicker;
    private ComboBox<Integer> seatCount;
    private ComboBox<String> searchBox;
    public static String selectedValue;
    public static String endValue;
    public static String stopValue;
    public static int totalDistance;
    public static LocalDate goingDateUser;
    public static int seatWithUser;
    public static String emailD = "abhishek07@gmail.com";
    private static String name = ProfilePage.nameU;
    private static String phoneN = ProfilePage.phoneNoU;
    public static String rideAcceptFlagInUser;
    static int costTicket;
    private Button responseButton;
    // Path for store email with destination
    public static String emailUser = ProfilePage.emailU;

    public HomePage() {
    }

    @Override
    public void start(Stage primaryStage) {

        Region backgroundColor = new Region();
        // backgroundColor.setStyle("-fx-background-color: yellow;");
        backgroundColor.setPrefSize(1200, 700);

        // Background Image
        Image backgroundImage = new Image(
                "com/ride_share/Assets/map photo.jpeg");
        BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

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
                endDestination.getItems().addAll("Katraj");
            } else if (selectedValue == "Mumbai") {
                endDestination.getItems().addAll("Katraj");
            }

        });

        endDestination = new ComboBox<>();
        endDestination.setPrefSize(150, 0);
        endDestination.setPromptText("Going To");

        // Stop is depend upon selected location and end location

        endDestination.setOnAction(event -> {
            endValue = endDestination.getValue();
            if (selectedValue == "Satara" && endValue == "Katraj") {
                searchBox.getItems().addAll("Satara-Shirwal", "Shirwal-Katraj", "Satara-Katraj");
            } else if (selectedValue == "Katraj" && endValue == "Satara") {
                searchBox.getItems().addAll("Katraj-Shirwal", "Shirwal-Satara", "Katraj-Satara");
            } else if (selectedValue == "Solapur" && endValue == "Katraj") {
                searchBox.getItems().addAll("Solapur-Daund", "Daund-Katraj", "Solapur-Katraj");
            } else if (selectedValue == "Mumbai" && endValue == "Katraj") {
                searchBox.getItems().addAll("Mumbai-Lonavala", "Lonavala-Katraj", "Mumbai-Katraj");
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
            if (stopValue == "Satara-Katraj") {
                totalDistance = 100;
            } else if (stopValue == "Katraj-Satara") {
                totalDistance = 100;
            } else if (stopValue == "Solapur-Katraj") {
                totalDistance = 252;
            } else if (stopValue == "Mumbai-Katraj") {
                totalDistance = 157;
            }
            System.out.println(totalDistance);

        });

        HBox searchBoxBox = new HBox(10, searchBox);
        searchBoxBox.setAlignment(Pos.BASELINE_CENTER);

        // Creating DatePicker for selecting date and time
        datePicker = new DatePicker();
        datePicker.setPromptText("Today");
        // When select on date then store date
        datePicker.setOnAction(event -> {
            goingDateUser = datePicker.getValue();
            System.out.println(goingDateUser);
        });

        // Creating a ComboBox for selecting number of seats
        seatCount = new ComboBox<>();
        for (int i = 1; i <= 10; i++) {
            seatCount.getItems().add(i);
        }
        seatCount.setPromptText("No. of seats");
        // When select seat then store seat count
        seatCount.setOnAction(event -> {
            seatWithUser = seatCount.getValue();
            System.out.println(seatWithUser);
        });

        // Creating a request button
        Button viewButton = new Button("View");
        viewButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Button cheackResponse = new Button("Check Response");
        cheackResponse.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        cheackResponse.setOnAction(event -> {
            MatchRide driver = new MatchRide();
            try {
                driver.readRecDriverToGenerateTicket(ViewPage.storeEmailDriver);// Also call in responseButton
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception ee) {
                // if passenger directly click on check Response without join ride or without
                // fillig details
                System.out.println("Wait");
            }
            if (MatchRide.rideAcceptFlag == "1")
                responseButton.setStyle("-fx-font-size: 24px; -fx-background-color: red;-fx-font-weight: bold;");
            else {
                System.out.println("No Response");
            }

        });

        // Layout for the center content
        VBox centerContent = new VBox(30, setDestination, searchBoxBox, datePicker, seatCount, viewButton,
                cheackResponse);
        centerContent.setAlignment(Pos.BASELINE_CENTER);
        centerContent.setPadding(new Insets(20));

        // Creating the static bottom bar with back, response, profile, and ratings
        // buttons
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        responseButton = new Button("Response");
        // change kelay
        responseButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button profileButton = new Button("Profile");
        profileButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button ratingsButton = new Button("Ratings");
        ratingsButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Button aboutusButton = new Button("About Us");
        aboutusButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        HBox bottomBar = new HBox(150, backButton, ratingsButton, responseButton, profileButton, aboutusButton);
        bottomBar.setPadding(new Insets(10));
        bottomBar.setAlignment(Pos.CENTER);
        bottomBar.setStyle(
                "-fx-background-color:black; ");
        // bottomBar.setStyle("-fx-background-radius: 20px; " + "-fx-border-radius:
        // 20px;\");");

        VBox blackbox = new VBox(centerContent);
        blackbox.setPadding(new Insets(100, 0, 0, 0));
        blackbox.setMaxWidth(500); // Set max width to match stage width
        blackbox.setMaxHeight(620); // Set max height to match stage height
        blackbox.setStyle("-fx-background-color: black; " +
                "-fx-background-radius: 20px; " + // Set radius for rounded corners
                "-fx-border-radius: 20px;"); // Optional: Set border radius for consistency

        // Main layout
        BorderPane mainLayout = new BorderPane();
        // mainLayout.setPadding(new Insets(100, 0, 0, 0));
        mainLayout.setCenter(blackbox);
        mainLayout.setBottom(bottomBar);
        mainLayout.setBackground(new Background(bgImage));

        // StackPane to hold the background color and the main layout
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(mainLayout);

        // Setting the scene
        Scene scene = new Scene(stackPane, 1470, 830);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Share Ride - Home Page");
        primaryStage.show();

        // Adding action to navigate to the ViewPage

        viewButton.setOnAction(event -> {
            new ViewPage(primaryStage).showViewPage();
            // Path for store email with destination
            System.out.println(emailUser);

            generateCostTicket();

            handleDestination(primaryStage, name, emailUser, selectedValue, endValue, stopValue, seatWithUser, phoneN,
                    totalDistance, goingDateUser, costTicket);

            try {
                MatchRide.matchRide(emailD, selectedValue, endValue);
                // ViewPage.ViewPage(primaryStage).showViewPage();
            } catch (InterruptedException | ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        });

        /*
         * // Adding action to navigate to the ResponsePage
         * responseButton.setOnAction(event -> {
         * // primaryStage.setFullScreen(true);
         * ResponsePage responsePage = new ResponsePage(primaryStage);
         * primaryStage.setFullScreen(true);
         * responsePage.showResponsePage();
         * });
         */

        // Adding action to navigate to the ResponsePage
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
            } else if (cancelRide == "0") {
                System.out.println("Sorry, Driver Reject Ride due to some Reason");
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

        // Adding action to navigate to the UserRatingsDriver page
        ratingsButton.setOnAction(event -> {
            UserRatingsDriver ratingsPage = new UserRatingsDriver(primaryStage);

            ratingsPage.showDriverRatingsPage();
        });

        // Adding action to navigate to the ProfilePage
        profileButton.setOnAction(event -> {
            ProfilePage profilePage = new ProfilePage(primaryStage);

            profilePage.showProfilePage();
        });

        aboutusButton.setOnAction(event -> {
            AboutUsPage aboutus = new AboutUsPage();

            aboutus.showAboutUsPage(primaryStage);
        });
    }

    // *Fire Store data Store Driver Start destination and End destination*

    private void handleDestination(Stage primaryStage, String name, String email, String selectedValue, String endValue,
            String stopValue, int seatWithUser, String phoneN, int totalDistance, LocalDate goingDate,
            int costTicket) {
        DataService dataService; // Local instance of DataService
        try {
            dataService = new DataService(); // Initialize DataService instance
            // Create a map to hold user data
            Map<String, Object> data = new HashMap<>();
            data.put("startDestination", selectedValue);
            data.put("endDestination", endValue);
            data.put("name", name);
            data.put("phoneNumber", phoneN);
            data.put("stopValue", stopValue);
            data.put("seatWithUser", seatWithUser);
            data.put("distance", totalDistance);
            data.put("costTicket", costTicket);
            // Add user data to Firestore
            dataService.addData("UserRide", email, data);
            System.out.println("Ride registered successfully");

            // Navigate back to the login scene
            // loginController.showLoginScene();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static String cancelRide = "1";

    // cheack ride status ,driver is cancel Ride
    // call in response button
    public void cheackRideStatus(String email) throws InterruptedException, ExecutionException {

        DocumentReference docRef = DataService.db.collection("cancelRide").document(email);

        ApiFuture<DocumentSnapshot> snapShot = docRef.get();
        DocumentSnapshot docSnap = snapShot.get();

        if (docSnap.exists()) {
            // Firebase varun yenara data type Object aahe , so convert it into string
            Object obj1 = (docSnap.get("cancelRideD"));// field jr chukli tr nullPointerException yeti
            cancelRide = obj1.toString();
            System.out.println(cancelRide);

        } else {
            System.out.println("Document Not Found");
        }
    }

    // Call in view button in home page
    private void generateCostTicket() {
        int perKm = 4;
        costTicket = perKm * seatWithUser * totalDistance;
        System.out.println(costTicket);
    }

    public static void main(String[] args) {
        launch(HomePage.class, args);
    }
}