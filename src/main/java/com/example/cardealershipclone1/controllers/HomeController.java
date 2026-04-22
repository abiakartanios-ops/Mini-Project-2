package com.example.cardealershipclone1.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class HomeController {

    @FXML private Button personsMngBtn;
    @FXML private Button ralphMngBtn;
    @FXML private Button clientMngBtn;

    @FXML
    private void openPersonsMng() {
        try {
            Stage stage = (Stage) personsMngBtn.getScene().getWindow();

            URL fxmlLocation = getClass().getResource("/com/example/cardealershipclone1/dashboard.fxml");
            if (fxmlLocation == null) {
                fxmlLocation = getClass().getResource("/dashboard.fxml");
            }

            if (fxmlLocation == null) {
                System.out.println("Critical Error: dashboard.fxml not found!");
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load(), 900, 600);
            stage.setScene(scene);
            stage.setTitle("Car Dealership - Car Management");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void openRalphMng() {
        try {
            Stage stage = (Stage) ralphMngBtn.getScene().getWindow();

            // Load demo1's MainView directly — skipping its login page
            URL fxmlLocation = getClass().getResource("/com/example/demo1/MainView.fxml");
            if (fxmlLocation == null) {
                fxmlLocation = getClass().getResource("/MainView.fxml");
            }

            if (fxmlLocation == null) {
                System.out.println("Critical Error: MainView.fxml not found!");
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load(), 800, 600);
            stage.setScene(scene);
            stage.setTitle("Vehicle Registry - Ralph's Management");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
@FXML
    private void openClientMng() {
        try {
            Stage stage = (Stage) clientMngBtn.getScene().getWindow();

            URL fxmlLocation = getClass().getResource("/com/example/cardealershipclone1/ClientsView.fxml");

            if (fxmlLocation == null) {
                System.out.println("ClientsView not found!");
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load(), 1000, 800);
            stage.setScene(scene);
            stage.setTitle("Client Management");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}