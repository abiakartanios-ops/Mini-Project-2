package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    private void handleLogin() {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if (Users.validate(user, pass)) {
            try {
                Stage stage = (Stage) usernameField.getScene().getWindow();

                // FINAL FAIL-SAFE PATH LOGIC
                URL fxmlLocation = getClass().getResource("/com/example/demo1/MainView.fxml");
                if (fxmlLocation == null) {
                    fxmlLocation = getClass().getResource("/MainView.fxml");
                }

                if (fxmlLocation == null) {
                    errorLabel.setText("Critical Error: MainView.fxml is missing from resources!");
                    return;
                }

                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                Scene scene = new Scene(loader.load(), 800, 600);
                stage.setScene(scene);
                stage.setTitle("Vehicle Registry - Logged in as: " + user);

            } catch (IOException e) {
                errorLabel.setText("Error loading the next screen.");
                e.printStackTrace();
            }
        } else {
            errorLabel.setText("Invalid credentials. Try Ralph / 123456");
        }
    }
}