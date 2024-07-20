package com.ride_share.initialize;

import com.ride_share.controller.LoginController;

import javafx.application.Application;
import javafx.stage.Stage;

public class InitializeApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        LoginController loginController = new LoginController(primaryStage);

        primaryStage.setScene(loginController.getLoginScene());

        primaryStage.setTitle("Login");

        primaryStage.show();
    }
}