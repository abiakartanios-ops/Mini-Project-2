package com.example.cardealershipclone1.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    private static final String VALID_USERNAME = "tanios";
    private static final String VALID_PASSWORD = "tanios123";

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button loginButton;

    @FXML
    public void initialize() {
        // Set up Enter key action
        passwordField.setOnAction(e -> handleLogin());
        usernameField.setOnAction(e -> handleLogin());
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please enter both username and password.");
            return;
        }

        if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
            try {
                // Load home page
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/example/cardealershipclone1/homeview.fxml")
                );
                Scene homeScene = new Scene(loader.load(), 650, 500);

                Stage homeStage = new Stage();
                homeStage.setTitle("Company Management System");
                homeStage.setScene(homeScene);
                homeStage.setResizable(false);
                homeStage.show();

                // Close login stage
                Stage loginStage = (Stage) loginButton.getScene().getWindow();
                loginStage.close();

            } catch (Exception e) {
                e.printStackTrace();
                errorLabel.setText("Error loading dashboard.");
            }
        } else {
            errorLabel.setText("Invalid username or password. Please try again.");
            passwordField.clear();
        }
    }
}