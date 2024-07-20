package com.ride_share.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

import com.ride_share.firebaseConfig.DataService;

public class DriverRegistration {

    private LoginController loginController;
    private static String vehicleType;

    public DriverRegistration(LoginController loginController) {
        this.loginController = loginController;
    }

    // Creates the driver sign-up scene
    public Scene createDriverSignupScene(Stage primaryStage) {

        // Creating input fields and labels for user information
        Label userLabel = new Label("Enter Your Name:");
        TextField userTextField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailTextField = new TextField();
        Label phoneLabel = new Label("Phone No:");
        TextField phoneTextField = new TextField();
        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();

        Button backButton = new Button("Back To Login");
        backButton.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: #009CFF; -fx-font-size: 15; -fx-padding: 10; -fx-border-radius: 5;");

        // Arranging the user information fields in VBox
        VBox fieldBox1 = new VBox(10, userLabel, userTextField);
        fieldBox1.setMaxSize(300, 30);

        VBox fieldBox2 = new VBox(10, emailLabel, emailTextField);
        fieldBox2.setMaxSize(300, 30);

        VBox fieldBox3 = new VBox(10, phoneLabel, phoneTextField);
        fieldBox3.setMaxSize(300, 30);

        VBox fieldBox4 = new VBox(10, passLabel, passField);
        fieldBox4.setMaxSize(300, 30);

        // Creating input fields and labels for bank information
        GridPane bankGrid = new GridPane();
        bankGrid.setHgap(0);
        bankGrid.setVgap(10);
        bankGrid.setPadding(new Insets(10));

        Label accountNoLabel = new Label("Account No:");
        TextField accountNoField = new TextField();
        bankGrid.add(accountNoLabel, 0, 0);
        bankGrid.add(accountNoField, 1, 0);

        Label ifscCodeLabel = new Label("IFSC Code:");
        TextField ifscCodeField = new TextField();
        bankGrid.add(ifscCodeLabel, 0, 1);
        bankGrid.add(ifscCodeField, 1, 1);

        Label upiIdLabel = new Label("UPI ID:");
        TextField upiIdField = new TextField();
        bankGrid.add(upiIdLabel, 0, 2);
        bankGrid.add(upiIdField, 1, 2);

        // Creating input fields and labels for vehicle information
        GridPane vehicleGrid = new GridPane();
        vehicleGrid.setHgap(10);
        vehicleGrid.setVgap(10);
        vehicleGrid.setPadding(new Insets(10));

        Label vehicleTypeLabel = new Label("Vehicle Type:");
        ComboBox<String> vehicleTypeCombo = new ComboBox<>();
        vehicleTypeCombo.getItems().addAll("Cab", "SUV", "Minibus", "Truck");
        vehicleGrid.add(vehicleTypeLabel, 0, 0);
        vehicleGrid.add(vehicleTypeCombo, 1, 0);

        vehicleTypeCombo.setOnAction(event -> {

            vehicleType = vehicleTypeCombo.getValue();
        });

        Label driverLicenseNoLabel = new Label("Driver License No:");
        TextField driverLicenseNoField = new TextField();
        vehicleGrid.add(driverLicenseNoLabel, 0, 1);
        vehicleGrid.add(driverLicenseNoField, 1, 1);

        Label vehicleNoLabel = new Label("Vehicle No:");
        TextField vehicleNoField = new TextField();
        vehicleGrid.add(vehicleNoLabel, 0, 2);
        vehicleGrid.add(vehicleNoField, 1, 2);

        Label vehicleInsuranceNoLabel = new Label("Vehicle Insurance No:");
        TextField vehicleInsuranceNoField = new TextField();
        vehicleGrid.add(vehicleInsuranceNoLabel, 0, 3);
        vehicleGrid.add(vehicleInsuranceNoField, 1, 3);

        HBox hbox1 = new HBox(10, vehicleGrid, bankGrid);
        hbox1.setAlignment(Pos.TOP_CENTER);
        hbox1.setMaxWidth(700);
        hbox1.setStyle("-fx-background-color: #F9F9F9; -fx-padding: 20; -fx-border-radius: 10;");

        // Creating VBox to hold all input fields and grids
        VBox vbox = new VBox(20, fieldBox1, fieldBox2, fieldBox3, fieldBox4, hbox1);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setMaxWidth(700);
        vbox.setStyle("-fx-background-color: #F9F9F9; -fx-padding: 20; -fx-border-radius: 10;");

        // Creating sign-up button
        Button signupButton = new Button("Sign up");
        signupButton.setStyle(
                "-fx-background-color: #009CFF; -fx-text-fill: #fff; -fx-font-size: 18; -fx-padding: 10; -fx-border-radius: 5;");

        // Handling sign-up button action
        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleDriverSignup(primaryStage, userTextField.getText(), emailTextField.getText(),
                        phoneTextField.getText(), passField.getText(),
                        accountNoField.getText(), ifscCodeField.getText(), upiIdField.getText(),
                        driverLicenseNoField.getText(), vehicleType, vehicleNoField.getText(),
                        vehicleInsuranceNoField.getText());
            }
        });

        backButton.setOnAction(event -> {
            loginController.showLoginScene();
        });

        // Creating main VBox to hold all elements including the sign-up button
        VBox mainVBox = new VBox(5);
        mainVBox.getChildren().addAll(vbox, signupButton, backButton);
        mainVBox.setAlignment(Pos.CENTER);

        Image backgroundImage = new Image(
                "file:src/main/java/com/ride_share/Assets/generated_video.gif");

        BackgroundImage bgImage = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1400, 1160, false, false, false, false));

        VBox layout = new VBox(mainVBox);
        layout.setPadding(new Insets(80, 0, 0, 20));
        layout.setMaxHeight(700);
        layout.setMaxWidth(600);
        layout.setTranslateX(-350);

        // Creating StackPane to hold the background image and main VBox
        StackPane stack = new StackPane(layout);
        stack.setBackground(new Background(bgImage));
        stack.setAlignment(Pos.CENTER);

        // Create the scene and set it to full screen
        Scene scene = new Scene(stack, 1470, 830);
        primaryStage.setScene(scene);

        return new Scene(stack, 1470, 830);
    }

    // Handles driver sign-up process
    private void handleDriverSignup(Stage primaryStage, String name, String email, String phoneNo, String password,
            String accountNo, String ifsc, String upid,
            String driverLicense, String vehicleType, String vehicleNo, String vehicleInsurance) {
        DataService dataService; // Local instance of DataService
        try {
            dataService = new DataService(); // Initialize DataService instance
            // Create a map to hold user data
            Map<String, Object> data = new HashMap<>();
            data.put("name", name);
            data.put("email", email);
            data.put("phoneNo", phoneNo);
            data.put("password", password);
            data.put("accountNo", accountNo);
            data.put("ifsc", ifsc);
            data.put("upid", upid);
            data.put("driverLicense", driverLicense);
            data.put("vehicleNo", vehicleNo);
            data.put("vehicleType", vehicleType);
            data.put("vehicleInsurance", vehicleInsurance);
            // Add user data to Firestore
            dataService.addData("drivers", email, data);
            System.out.println("Driver registered successfully");

            // Navigate back to the login scene
            loginController.showLoginScene();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}