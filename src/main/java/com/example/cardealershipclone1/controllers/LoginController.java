package com.example.cardealershipclone1.controllers;

import com.example.cardealershipclone1.Users;
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
       String user = usernameField.getText().trim();
       String pass = passwordField.getText().trim();

       if (Users.validate(user, pass)) {
           try {
               Stage stage = (Stage) usernameField.getScene().getWindow();

               URL fxmlLocation = getClass().getResource("/com/example/cardealershipclone1/homeview.fxml");

               if (fxmlLocation == null) {
                   errorLabel.setText("HomeView not found!");
                   return;
               }

               FXMLLoader loader = new FXMLLoader(fxmlLocation);
               Scene scene = new Scene(loader.load(), 650, 500);
               stage.setScene(scene);
               stage.setResizable(true);
               stage.setTitle("Home");

           } catch (IOException e) {
               errorLabel.setText("Error loading Home.");
               e.printStackTrace();
           }
       } else {
           errorLabel.setText("Invalid credentials.");
       }
   }}