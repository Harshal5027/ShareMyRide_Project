package com.ride_share.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

import com.ride_share.driverdashboards.DriverProfilePage;
import com.ride_share.firebaseConfig.DataService;

public class SignupController {

    private LoginController loginController;

    public SignupController(LoginController loginController) {
        this.loginController = loginController;
    }

    public Scene createSignupScene(Stage primaryStage) {

        Label userLabel = new Label("Enter Your Name:");
        TextField userTextField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailTextField = new TextField();
        Label phoneLabel = new Label("Phone No:");
        TextField phoneTextField = new TextField();
        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();
        Button signupButtonUser = new Button("Signup as User");
        Button backButton = new Button("Back To Login");

        signupButtonUser.setStyle(
                "-fx-background-color: #009CFF ; -fx-text-fill: #fff; -fx-font-size: 12.5; -fx-padding: 10; -fx-border-radius: 5;");
        Button signupButtonDriver = new Button("Signup as Driver");
        signupButtonDriver.setStyle(
                "-fx-background-color: #009CFF ; -fx-text-fill: #fff; -fx-font-size: 12.5; -fx-padding: 10; -fx-border-radius: 5;");
        backButton.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: #009CFF; -fx-font-size: 15; -fx-padding: 10; -fx-border-radius: 5;");

        HBox buttonBox = new HBox(30, signupButtonUser, signupButtonDriver);
        buttonBox.setMaxSize(350, 40);
        buttonBox.setAlignment(Pos.CENTER);

        VBox back = new VBox(30, buttonBox, backButton);
        back.setMaxSize(350, 40);
        back.setAlignment(Pos.CENTER);

        VBox fieldBox1 = new VBox(10, userLabel, userTextField);
        fieldBox1.setMaxSize(300, 30);

        VBox fieldBox2 = new VBox(10, emailLabel, emailTextField);
        fieldBox2.setMaxSize(300, 30);

        VBox fieldBox3 = new VBox(10, phoneLabel, phoneTextField);
        fieldBox3.setMaxSize(300, 30);

        VBox fieldBox4 = new VBox(10, passLabel, passField);
        fieldBox4.setMaxSize(300, 30);

        signupButtonUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleSignup(primaryStage, userTextField.getText(), emailTextField.getText(), phoneTextField.getText(),
                        passField.getText());
            }
        });

        signupButtonDriver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DriverRegistration driverRegistration = new DriverRegistration(loginController);
                Scene driverSignupScene = driverRegistration.createDriverSignupScene(primaryStage);
                primaryStage.setScene(driverSignupScene);
            }
        });

        backButton.setOnAction(event -> {
            loginController.showLoginScene();
        });

        VBox vbox = new VBox(30, fieldBox1, fieldBox2, fieldBox3, fieldBox4, back);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMaxHeight(600);
        vbox.setMaxWidth(400);
        vbox.setStyle("-fx-background-color: #F9F9F9; -fx-padding: 20; -fx-border-radius: 10;");

        Image backgroundImage = new Image("file:src/main/java/com/ride_share/Assets/generated_video.gif");

        BackgroundImage bgImage = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1400, 1160, false, false, false, false));

        VBox layout = new VBox(vbox);
        layout.setPadding(new Insets(200, 0, 0, 200));
        layout.setMaxHeight(800);
        layout.setMaxWidth(550);

        StackPane stack = new StackPane(layout);
        stack.setAlignment(Pos.CENTER_LEFT);
        stack.setBackground(new Background(bgImage));

        return new Scene(stack, 1470, 830);
    }

    private void handleSignup(Stage primaryStage, String name, String email, String phoneNo, String password) {
        DataService dataService; // Local instance of DataService
        try {
            dataService = new DataService(); // Initialize DataService instance
            // Create a map to hold user data
            Map<String, Object> data = new HashMap<>();
            data.put("name", name);
            data.put("email", email);
            data.put("phoneNo", phoneNo);
            data.put("password", password);
            // Add user data to Firestore
            dataService.addData("users", email, data);
            System.out.println("User registered successfully");

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Registered as User");
            alert.showAndWait();

            // Navigate back to the login scene
            loginController.showLoginScene();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}