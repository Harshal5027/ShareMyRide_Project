package com.ride_share.dashboards;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AboutUsPage extends Application {

        AboutUsPage() {

        }

        @Override
        public void start(Stage primaryStage) {
                showAboutUsPage(primaryStage);
        }

        public void showAboutUsPage(Stage primaryStage) {
                // public void start(Stage primaryStage) {
                // Create the labels

                Button backButton = new Button("Back");

                Label titleLabel = new Label("\nShare my ride");
                titleLabel.setTextFill(Color.YELLOW);
                titleLabel.setFont(new Font("Arial", 24));

                Label aboutUsLabel = new Label("about us");
                aboutUsLabel.setTextFill(Color.YELLOW);
                aboutUsLabel.setFont(new Font("Arial", 24));

                Label eliteLabel = new Label("ELITE\nSuper-X-3.0");
                eliteLabel.setTextFill(Color.YELLOW);
                eliteLabel.setFont(new Font("Arial", 40));

                Label thankYouLabel = new Label("Thank you,\nCore2Web\nfor this\nopportunity");
                thankYouLabel.setTextFill(Color.YELLOW);
                thankYouLabel.setPadding(new Insets(0, 0, 0, 30));
                thankYouLabel.setFont(new Font("Arial", 40));

                // Create photo placeholders
                ImageView rahulPhoto = new ImageView(
                                new Image("file:src/main/java/com/ride_share/Assets/rahul.jpg"));
                rahulPhoto.setFitWidth(150);
                rahulPhoto.setFitHeight(150);
                rahulPhoto.setPreserveRatio(true);

                ImageView adityaPhoto = new ImageView(
                                new Image("file:src/main/java/com/ride_share/Assets/aditya about .jpg"));
                adityaPhoto.setFitWidth(150);
                adityaPhoto.setFitHeight(150);
                adityaPhoto.setPreserveRatio(true);

                ImageView prasadPhoto = new ImageView(new Image("file:src/main/java/com/ride_share/Assets/prsad.jpg"));
                prasadPhoto.setFitWidth(150);
                prasadPhoto.setFitHeight(150);
                prasadPhoto.setPreserveRatio(true);

                ImageView harshalPhoto = new ImageView(
                                new Image("file:src/main/java/com/ride_share/Assets/harshal about.jpg"));
                harshalPhoto.setFitWidth(150);
                harshalPhoto.setFitHeight(150);
                harshalPhoto.setPreserveRatio(true);

                ImageView abhiPhoto = new ImageView(
                                new Image("file:src/main/java/com/ride_share/Assets/abhi for aboutpage.jpeg"));
                abhiPhoto.setFitWidth(150);
                abhiPhoto.setFitHeight(150);
                abhiPhoto.setPreserveRatio(true);

                ImageView shashiPhoto = new ImageView(
                                new Image(
                                                "file:/Users/abhishekdere/java/share_my_ride Final/src/main/java/com/ride_share/Assets/shashi sir.jpg"));
                shashiPhoto.setFitWidth(270);
                shashiPhoto.setFitHeight(274);
                shashiPhoto.setPreserveRatio(true);

                // Create labels for names and degrees
                Label mentor = new Label("Mentor\nRahul Sir");
                mentor.setTextFill(Color.YELLOW);
                mentor.setFont(new Font("Arial", 27));
                mentor.setAlignment(Pos.CENTER);

                Label name1 = new Label("Zeal\nBE-CS");
                name1.setTextFill(Color.YELLOW);
                name1.setFont(new Font("Arial", 27));
                name1.setAlignment(Pos.CENTER);

                Label name2 = new Label("TSSM\nBE-E&TC");
                name2.setTextFill(Color.YELLOW);
                name2.setFont(new Font("Arial", 27));
                name2.setAlignment(Pos.CENTER);

                Label name3 = new Label("Zeal\nBE IT");
                name3.setTextFill(Color.YELLOW);
                name3.setFont(new Font("Arial", 27));
                name3.setAlignment(Pos.CENTER);

                Label name4 = new Label("TSSM\nBE-CS");
                name4.setTextFill(Color.YELLOW);
                name4.setFont(new Font("Arial", 27));
                name4.setAlignment(Pos.CENTER);

                Label shashi1 = new Label("Shashi Sir");
                shashi1.setTextFill(Color.YELLOW);
                // name4.setStyle(STYLESHEET_CASPIAN);
                shashi1.setFont(new Font("Arial", 39));
                shashi1.setAlignment(Pos.CENTER);

                VBox Upper1 = new VBox(30, titleLabel);
                Upper1.setAlignment(Pos.CENTER);

                HBox Upper = new HBox(1100, Upper1, backButton);
                Upper.setAlignment(Pos.CENTER);

                VBox shashinav = new VBox(10, shashiPhoto, shashi1);
                shashinav.setAlignment(Pos.CENTER);

                HBox bigelite = new HBox(200, eliteLabel, shashinav, thankYouLabel);
                bigelite.setAlignment(Pos.CENTER);

                HBox allphotoes = new HBox(180, rahulPhoto, adityaPhoto, prasadPhoto, abhiPhoto, harshalPhoto);
                allphotoes.setPadding(new Insets(10, 0, 0, 0));
                allphotoes.setAlignment(Pos.CENTER);

                HBox collage = new HBox(200, mentor, name1, name2, name3, name4);
                collage.setPadding(new Insets(10));
                collage.setAlignment(Pos.CENTER);

                VBox finalBox = new VBox(35, Upper, bigelite, allphotoes, collage);

                BorderPane mainLayout = new BorderPane(finalBox);
                // mainLayout.setCenter(allphotoes);
                // mainLayout.setBottom(collage);

                // Create a StackPane to hold the grid and set the background
                StackPane stackPane = new StackPane(finalBox);
                stackPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, null)));
                // stackPane.getChildren().add(mainLayout);
                // stackPane.setBottom(collage);

                // Create the scene
                Scene scene = new Scene(stackPane, 1470, 830);

                // Set the stage
                primaryStage.setTitle("About Us - Share my ride");
                primaryStage.setScene(scene);
                primaryStage.show();
        }

        public static void main(String[] args) {
                launch(args);
        }
}