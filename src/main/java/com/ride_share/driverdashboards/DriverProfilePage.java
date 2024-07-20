package com.ride_share.driverdashboards;

import java.io.File;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DriverProfilePage extends ParentDriver {

    private Stage primaryStage;
    public static String nameD;
    public static String emailD;
    public static String accountNoD;
    public static String phoneNoD;
    public static String ifscCodeD;
    public static String upiIdD;
    public static String driverLicenseNoD;
    public static String vehicleNoD;
    public static String vehicleInsurenceNoD;
    public static String photoUrlD; // URL or path to the driver's photo

    public DriverProfilePage() {
    }

    public DriverProfilePage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Retrieve data from Firestore
    public void readRecDriver(String email) throws InterruptedException, ExecutionException {
        DocumentReference docRef = DataService.db.collection("drivers").document(email);

        ApiFuture<DocumentSnapshot> snapShot = docRef.get();
        DocumentSnapshot docSnap = snapShot.get();

        if (docSnap.exists()) {
            nameD = docSnap.getString("name");
            emailD = docSnap.getString("email");
            phoneNoD = docSnap.getString("phoneNo");
            accountNoD = docSnap.getString("accountNo");
            ifscCodeD = docSnap.getString("ifsc");
            upiIdD = docSnap.getString("upid");
            driverLicenseNoD = docSnap.getString("driverLicense");
            vehicleNoD = docSnap.getString("vehicleNo");
            vehicleInsurenceNoD = docSnap.getString("vehicleInsurance");
            photoUrlD = docSnap.getString("photoUrl");
        } else {
            System.out.println("Document Not Found");
        }
    }

    public void showProfilePage() {
        backtry();

        // Create labels for displaying driver information
        Label nameLabel = new Label("Name: " + nameD);
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");

        Label emailLabel = new Label("Email: " + emailD);
        emailLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");

        Label phoneLabel = new Label("Phone: " + phoneNoD);
        phoneLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");

        Label accountNoLabel = new Label("Account no: " + accountNoD);
        accountNoLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");

        Label ifscCodeLabel = new Label("IFSC Code: " + ifscCodeD);
        ifscCodeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");

        Label upiIdLabel = new Label("UPI ID: " + upiIdD);
        upiIdLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");

        Label driverLicenseNoLabel = new Label("Driver License No: " + driverLicenseNoD);
        driverLicenseNoLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");

        Label vehicleNoLabel = new Label("Vehicle No: " + vehicleNoD);
        vehicleNoLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");

        Label vehicleInsurenceNoLabel = new Label("Vehicle Insurence No: " + vehicleInsurenceNoD);
        vehicleInsurenceNoLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");

        // Create an ImageView for the profile photo
        ImageView photoView = new ImageView();
        photoView.setFitWidth(100);
        photoView.setFitHeight(100);
        if (photoUrlD != null) {
            photoView.setImage(new Image(photoUrlD));
        }

        // Clip the ImageView to make the photo circular
        Circle clip = new Circle(50, 50, 50);
        photoView.setClip(clip);

        // Add a border around the circular photo
        Circle border = new Circle(50, 50, 50);
        border.setStyle("-fx-stroke: white; -fx-stroke-width: 4px; -fx-fill: transparent;");

        // Stack the photo and the border
        StackPane photoStack = new StackPane();
        photoStack.getChildren().addAll(photoView, border);

        Button updatePhotoButton = new Button("Update Profile Photo");
        updatePhotoButton.setOnAction(e -> updateProfilePhoto());

        HBox Forputu = new HBox(20, photoStack);
        Forputu.setPadding(new Insets(0, 0, 0, 110));
        // Forputu.setAlignment(Pos.TOP_CENTER);

        VBox Forputu2 = new VBox(20, Forputu, updatePhotoButton);
        Forputu2.setPadding(new Insets(0, 0, 0, 100));

        VBox profileLayout = new VBox(15, Forputu, Forputu2, nameLabel, emailLabel, phoneLabel,
                accountNoLabel, ifscCodeLabel, upiIdLabel, driverLicenseNoLabel, vehicleNoLabel,
                vehicleInsurenceNoLabel);
        profileLayout.setAlignment(Pos.BASELINE_LEFT);
        profileLayout.setPadding(new Insets(0, 0, 0, 80));

        Region background = new Region();
        background.setPrefSize(600, 400);

        // Background Image
        Image backgroundImage = new Image(
                "com/ride_share/Assets/map photo.jpeg");
        BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        // Static common bottom bar
        commonBottomBar();
        bottomHbox();

        VBox blackBox = new VBox(profileLayout);
        blackBox.setPadding(new Insets(40, 0, 0, 0));
        blackBox.setMaxWidth(500);
        blackBox.setMaxHeight(620);
        blackBox.setStyle("-fx-background-color: black; " +
                "-fx-background-radius: 20px; " +
                "-fx-border-radius: 20px;");

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(blackBox);
        mainLayout.setBottom(bottomBar);
        mainLayout.setBackground(new Background(bgImage));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(mainLayout);

        Scene profileScene = new Scene(stackPane, 1470, 830);
        primaryStage.setScene(profileScene);
        primaryStage.setTitle("Driver Profile Page");

        backButton.setOnAction(event -> new DriverHomePage().start(primaryStage));
        responseButton.setOnAction(event -> new DriverResponsePage(primaryStage).showResponsePage());
        ratingButton.setOnAction(event -> new DriverRatingUserPage(primaryStage).showRatingUserPage());
        earningButton.setOnAction(event -> new DriverEarningPage(primaryStage).showEarningPage());
    }

    private void updateProfilePhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            String photoPath = selectedFile.toURI().toString();
            photoUrlD = photoPath;

            // Optionally, you can upload the file to a cloud storage and update the
            // Firestore with the URL
            // updateFirestoreWithPhotoUrl(photoPath);

            // Refresh the profile page to show the new photo
            showProfilePage();
        }
    }

    // Optionally, update Firestore with the photo URL
    private void updateFirestoreWithPhotoUrl(String photoUrl) {
        DocumentReference docRef = DataService.db.collection("drivers").document(emailD);
        docRef.update("photoUrl", photoUrl);
    }
}