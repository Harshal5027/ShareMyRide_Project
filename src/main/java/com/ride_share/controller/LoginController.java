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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.concurrent.ExecutionException;
import com.ride_share.dashboards.HomePage;
import com.ride_share.dashboards.ProfilePage;
import com.ride_share.driverdashboards.DriverHomePage;
import com.ride_share.driverdashboards.DriverProfilePage;
import com.ride_share.firebaseConfig.DataService;

public class LoginController {

    private Stage primaryStage;
    private Scene loginScene;
    // private Scene userScene;
    private DataService dataService;
    public static String key;

    public LoginController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.dataService = new DataService();
        initScenes();
    }

    private void initScenes() {
        initLoginScene();
    }

    private void initLoginScene() {
        Label userLabel = new Label("Email Id");
        userLabel.setStyle("-fx-font-size: 18; -fx-text-fill: #333;-fx-text-fill:Bold");

        TextField userTextField = new TextField();
        userTextField.setStyle("-fx-background-color: #fff; -fx-background-radius: 5; -fx-padding: 10;");

        Label passLabel = new Label("Password");
        passLabel.setStyle("-fx-font-size: 18; -fx-text-fill: #333;");

        PasswordField passField = new PasswordField();
        passField.setStyle("-fx-background-color: #fff; -fx-background-radius: 5; -fx-padding: 10;");

        Button loginButton = new Button("Login");
        loginButton.setStyle(
                "-fx-background-color: #009CFF ; -fx-text-fill: #fff; -fx-font-size: 14; -fx-padding: 10; -fx-border-radius: 5;");

        Button signupButton = new Button("Signup");
        signupButton.setStyle(
                "-fx-background-color: #009CFF ; -fx-text-fill: #fff; -fx-font-size: 14; -fx-padding: 10; -fx-border-radius: 5;");

        Button forgotButton = new Button("Forgot Password?");
        forgotButton.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: #009CFF ; -fx-font-size: 16; -fx-padding: 10;");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLogin(userTextField.getText(), passField.getText());
                userTextField.clear();
                passField.clear();
            }

        });

        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSignupScene();
                userTextField.clear();
                passField.clear();
            }
        });

        ImageView imageView = new ImageView(new Image("file:src/main/java/com/ride_share/Assets/logo.png"));
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        userLabel.setStyle("-fx-text-fill: black;");
        passLabel.setStyle("-fx-text-fill: black;");

        VBox fieldBox1 = new VBox(15, userLabel, userTextField);
        fieldBox1.setMaxSize(300, 30);
        fieldBox1.setStyle("-fx-padding: 10;");

        VBox fieldBox2 = new VBox(15, passLabel, passField);
        fieldBox2.setMaxSize(300, 30);
        fieldBox2.setStyle("-fx-padding: 10;");

        HBox buttonBox = new HBox(40, loginButton, signupButton);
        buttonBox.setMaxSize(350, 30);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setStyle("-fx-padding: 10;");

        HBox buttonBox1 = new HBox(20, forgotButton);
        buttonBox1.setMaxSize(350, 30);
        buttonBox1.setAlignment(Pos.CENTER);
        buttonBox1.setStyle("-fx-padding: 10;");

        userTextField.setStyle("-fx-set-pref-width:350");
        passField.setStyle("-fx-set-pref-width:350");

        VBox vbox = new VBox(10, fieldBox1, fieldBox2, buttonBox, buttonBox1);
        vbox.setAlignment(Pos.CENTER);

        // vbox.setMaxHeight(800);
        // vbox.setMaxWidth(400);
        vbox.setStyle("-fx-background-color: #F9F9F9; -fx-padding: 20; -fx-border-radius: 10;");

        Image backgroundImage = new Image(
                "file:src/main/java/com/ride_share/Assets/generated_video.gif");

        BackgroundImage bgImage = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1400, 1160, false, false, false, false));

        VBox layout = new VBox(vbox);
        layout.setPadding(new Insets(200, 0, 0, 250));
        layout.setMaxHeight(800);
        layout.setMaxWidth(500);

        StackPane stack = new StackPane(layout);
        // stack.setStyle("-fx-background-color: #F7C333");
        stack.setAlignment(Pos.CENTER_LEFT);
        stack.setBackground(new Background(bgImage));

        loginScene = new Scene(stack, 1470, 830);
    }

    public Scene getLoginScene() {
        return loginScene;
    }

    public void showLoginScene() {
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    DriverProfilePage obj1 = new DriverProfilePage();

    ProfilePage obj = new ProfilePage();

    private void handleLogin(String email, String password) {

        try {

            if (dataService.authenticateUser(email, password)) {
                key = email;
                obj.readRecUser(email);
                // Alert alert = new Alert(Alert.AlertType.INFORMATION, "valid Password or
                // Username");
                // alert.showAndWait();
                showHomePage();
            } else if (dataService.authenticateDriver(email, password)) {
                key = email;
                obj1.readRecDriver(email);
                showDriverHomePage();
            } else {
                System.out.println("Invalid credentials");
                key = null;
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Invalid Password or Username");
                alert.showAndWait();

            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void showHomePage() {
        HomePage homePage = new HomePage();
        try {
            homePage.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDriverHomePage() {
        DriverHomePage homePage = new DriverHomePage();
        try {
            homePage.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showSignupScene() {
        SignupController signupController = new SignupController(this);
        Scene signupScene = signupController.createSignupScene(primaryStage);
        primaryStage.setScene(signupScene);
        primaryStage.setTitle("Signup");
        primaryStage.show();
    }
}