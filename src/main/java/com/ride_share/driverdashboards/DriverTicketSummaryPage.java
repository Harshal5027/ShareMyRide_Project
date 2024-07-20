package com.ride_share.driverdashboards;

import java.util.HashMap;
import java.util.Map;

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

public class DriverTicketSummaryPage extends ParentDriver {

        private Stage primaryStage;
        private String dropLocation;
        private String date;
        private String vehicleType;
        private int seats;
        private double ticketPrice;
        static String cancelRideD = "0";

        public DriverTicketSummaryPage(Stage primaryStage) {
                this.primaryStage = primaryStage;
                /*
                 * this.pickupLocation = pickupLocation;
                 * this.dropLocation = dropLocation;
                 * this.date = date;
                 * this.vehicleType = vehicleType;
                 * this.seats = seats;
                 * this.ticketPrice = ticketPrice;
                 */
        }

        public DriverTicketSummaryPage() {
                showDriverTicketSummaryPage(); // TODO Auto-generated constructor stub
        }

        public void showDriverTicketSummaryPage() {

                backtry();

                Image backgroundImage = new Image(
                                "com/ride_share/Assets/map photo.jpeg");
                BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

                Label passengerN = new Label("Passenger Name: " + DriverResponsePage.userName);
                passengerN.setStyle(" -fx-text-fill:white;-fx-font-size: 18px; -fx-font-weight: bold;");
                Label passengerP = new Label("Passenger PhoneNo.: " + DriverResponsePage.userPhoneNumber);
                passengerP.setStyle(" -fx-text-fill:white;-fx-font-size: 18px; -fx-font-weight: bold;");
                Label dropLabel = new Label("Pickup Location: " + DriverResponsePage.stopValue);
                dropLabel.setStyle(" -fx-text-fill:white;-fx-font-size: 18px; -fx-font-weight: bold;");
                Label dateLabel = new Label("Date: " + DriverHomePage.goingDateDriver);
                dateLabel.setStyle(" -fx-text-fill:white;-fx-font-size: 18px; -fx-font-weight: bold;");
                Label timeLabel = new Label(
                                ": " + DriverHomePage.goingTimeDriverH + ":" + DriverHomePage.goingTimeDriverM);
                timeLabel.setStyle(" -fx-text-fill:white;-fx-font-size: 18px; -fx-font-weight: bold;");
                Label seatsLabel = new Label("Passenger Counts: " + DriverResponsePage.seatWithUser);
                seatsLabel.setStyle(" -fx-text-fill:white;-fx-font-size: 18px; -fx-font-weight: bold;");
                Label priceLabel = new Label("Ticket Price: ₹" + DriverResponsePage.costTicket);
                priceLabel.setStyle(" -fx-text-fill:white;-fx-font-size: 18px; -fx-font-weight: bold;");

                VBox centerContent = new VBox(20, passengerN, passengerP, dropLabel, dateLabel,
                                /* vehicleLabel, */ seatsLabel, priceLabel);
                // centerContent.setStyle("-fx-background-color: LightBlue;");
                centerContent.setPadding(new Insets(20));
                centerContent.setAlignment(Pos.CENTER);

                Button cancelRide = new Button("Cancel Ride");
                cancelRide.setStyle(
                                " -fx-text-fill:blue;-fx-background-color: transperent;-fx-font-size: 18px; -fx-font-weight: bold;");

                cancelRide.setOnAction(event -> {
                        // for Cancel Ride
                        /*
                         * Pass Null Data to passenger , so i create all private parameter of below
                         * method with pass null value, so i can cancel
                         * ticket with information with passenger
                         */
                        // Method is below or last the code
                        handleDestination(primaryStage, cancelRideD);
                        System.out.println("Ride Cancelled");
                        DriverPendingRequestPage page1 = new DriverPendingRequestPage(primaryStage);
                        page1.showDriverPendingRequestPage();
                });

                VBox cancel = new VBox(cancelRide);
                cancel.setPadding(new Insets(20));
                cancel.setAlignment(Pos.CENTER);

                // Static bottom bar
                commonBottomBar();

                bottomHbox();

                VBox blackBox = new VBox(centerContent, cancel);
                blackBox.setPadding(new Insets(100, 0, 0, 0));
                blackBox.setMaxWidth(500); // Set max width to match stage width
                blackBox.setMaxHeight(620); // Set max height to match stage height
                blackBox.setStyle("-fx-background-color: black; " +
                                "-fx-background-radius: 20px; " + // Set radius for rounded corners
                                "-fx-border-radius: 20px;"); // Optional: Set border radius for consistency

                // Main layout
                BorderPane mainLayout = new BorderPane();
                mainLayout.setCenter(blackBox);
                mainLayout.setBottom(bottomBar);
                mainLayout.setBackground(new Background(bgImage));

                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(mainLayout);

                // Setting the scene
                Scene scene = new Scene(stackPane, 1470, 830);
                primaryStage.setScene(scene);
                // primaryStage.setFullScreen(true);
                primaryStage.setTitle("Share Ride - Ticket Summary");
                primaryStage.show();

                // Adding actions to the buttons
                backButton.setOnAction(event -> new DriverHomePage().start(primaryStage)); // Navigate back to home page
                ratingButton.setOnAction(event -> new DriverRatingUserPage(primaryStage).showRatingUserPage());
                responseButton.setOnAction(event -> new DriverResponsePage(primaryStage).showResponsePage());
                profileButton.setOnAction(event -> new DriverProfilePage(primaryStage).showProfilePage());
                earningButton.setOnAction(event -> new DriverEarningPage(primaryStage).showEarningPage());
        }

        // call in above
        public void handleDestination(Stage primaryStage, String cancelRideD) {
                DataService dataService; // Local instance of DataService
                try {
                        dataService = new DataService(); // Initialize DataService instance
                        // Create a map to hold user data
                        Map<String, Object> data = new HashMap<>();
                        data.put("cancelRideD", cancelRideD);
                        // Add user data to Firestore
                        dataService.addData("cancelRide", DriverHomePage.email, data);
                        System.out.println("Ride registered successfully");

                        // Navigate back to the login scene
                        // loginController.showLoginScene();
                } catch (Exception ex) {
                        ex.printStackTrace();
                }
        }
}