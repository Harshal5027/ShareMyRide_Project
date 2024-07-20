package com.ride_share.dashboards;

import java.io.File;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

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

import com.ride_share.firebaseConfig.DataService;

public class ProfilePage extends ParentUser {
    private Stage primaryStage;
    public static String nameU;
    public static String emailU;
    public static String phoneNoU;
    public static String photoUrlU; // URL or path to the user's photo

    public ProfilePage() {
    }

    public ProfilePage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void readRecUser(String email) throws InterruptedException, ExecutionException {
        DocumentReference docRef = DataService.db.collection("users").document(email);
        ApiFuture<DocumentSnapshot> snapShot = docRef.get();
        DocumentSnapshot docSnap = snapShot.get();

        if (docSnap.exists()) {
            Object obj = docSnap.get("name");
            nameU = obj.toString();
            Object obj2 = docSnap.get("email");
            emailU = obj2.toString();
            Object obj3 = docSnap.get("phoneNo");
            phoneNoU = obj3.toString();
            Object obj4 = docSnap.get("photoUrl"); // Retrieve photo URL
            photoUrlU = obj4 != null ? obj4.toString() : null;
            System.out.println(nameU);
        } else {
            System.out.println("Document Not Found");
        }
    }

    public void showProfilePage() {
        backtry();
        commonBottomBar();
        bottomHbox();

        ground();
        Image backgroundImage = new Image(
                "com/ride_share/Assets/map photo.jpeg");
        BackgroundImage bgImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        Label nameLabel = new Label("Name :- ");
        nameLabel.setStyle("-fx-background-color:white;-fx-padding:7px;");
        Label emailLabel = new Label("Email :- ");
        emailLabel.setStyle("-fx-background-color:white;-fx-padding:7px;");
        Label phoneLabel = new Label("Phone :- ");
        phoneLabel.setStyle("-fx-background-color:white;-fx-padding:7px;");

        Label nameField = new Label("" + nameU);
        nameField.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");
        // nameField.setAlignment(Pos.BOTTOM_CENTER);
        Label emailField = new Label("" + emailU);
        emailField.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");
        Label phoneField = new Label("" + phoneNoU);
        phoneField.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill:white");

        ImageView photoView = new ImageView();
        photoView.setFitWidth(100);
        photoView.setFitHeight(100);
        if (photoUrlU != null) {
            photoView.setImage(new Image(photoUrlU));

        }

        Circle clip = new Circle(50, 50, 50);
        photoView.setClip(clip);

        // Add a border around the circular photo
        Circle border = new Circle(50, 50, 50);
        // border.setStyle("-fx-stroke: white; -fx-stroke-width: 2px; -fx-fill:
        // transparent;");

        // Stack the photo and the border
        StackPane photoStack = new StackPane();
        photoStack.getChildren().addAll(photoView, border);

        Button updatePhotoButton = new Button("Update Profile Photo");
        updatePhotoButton.setOnAction(e -> updateProfilePhoto());

        HBox nameBox = new HBox(5, nameLabel, nameField);
        HBox emailBox = new HBox(5, emailLabel, emailField);
        HBox phoneBox = new HBox(5, phoneLabel, phoneField);

        VBox profileLayout = new VBox(20, photoView, updatePhotoButton, nameBox, emailBox, phoneBox);
        profileLayout.setAlignment(Pos.TOP_CENTER);
        profileLayout.setPadding(new Insets(5, 0, 0, 30));

        commonBottomBar();
        bottomHbox();

        VBox blackbox = new VBox(profileLayout);
        blackbox.setPadding(new Insets(100, 0, 0, 0));
        blackbox.setMaxWidth(500);
        blackbox.setMaxHeight(620);
        blackbox.setStyle("-fx-background-color: black; " +
                "-fx-background-radius: 20px; " +
                "-fx-border-radius: 20px;");

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(blackbox);
        mainLayout.setBottom(bottomBar);
        mainLayout.setBackground(new Background(bgImage));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(mainLayout);

        Scene profileScene = new Scene(stackPane, 1470, 830);
        primaryStage.setScene(profileScene);
        primaryStage.setTitle("Profile Page");

        backButton.setOnAction(event -> {
            HomePage homePage = new HomePage();
            homePage.start(primaryStage);
        });

        responseButton.setOnAction(event -> {
            ResponsePage responsePage = new ResponsePage(primaryStage);
            responsePage.showResponsePage();
        });

        ratingsButton.setOnAction(event -> {
            UserRatingsDriver ratingsPage = new UserRatingsDriver(primaryStage);
            ratingsPage.showDriverRatingsPage();
        });

        profileButton.setOnAction(event -> {
            ProfilePage profilePage = new ProfilePage(primaryStage);
            profilePage.showProfilePage();
        });
    }

    private void updateProfilePhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            // Save the selected image file to the user's profile
            String photoPath = selectedFile.toURI().toString();
            photoUrlU = photoPath;

            // Optionally, you can upload the file to a cloud storage and update the
            // Firestore with the URL
            // updateFirestoreWithPhotoUrl(photoPath);

            // Refresh the profile page to show the new photo
            showProfilePage();
        }
    }

    // Optionally, update Firestore with the photo URL
    private void updateFirestoreWithPhotoUrl(String photoUrl) {
        DocumentReference docRef = DataService.db.collection("users").document(emailU);
        docRef.update("photoUrl", photoUrl);
    }
}