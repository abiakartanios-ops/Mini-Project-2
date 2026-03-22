package com.example.cardealershipclone1.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private Button personsMngBtn;

    @FXML
    private void openPersonsMng() {
        try {
            // Load the Dashboard (Persons Management) view
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/cardealershipclone1/dashboard.fxml")
            );
            Scene dashboardScene = new Scene(loader.load(), 950, 650);

            // Get current stage and switch scene
            Stage currentStage = (Stage) personsMngBtn.getScene().getWindow();
            currentStage.setScene(dashboardScene);
            currentStage.setTitle("Car Management - Dashboard");
            currentStage.setResizable(true); // Allow resizing for dashboard
            currentStage.centerOnScreen();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading Persons Management view: " + e.getMessage());
        }
    }
}

