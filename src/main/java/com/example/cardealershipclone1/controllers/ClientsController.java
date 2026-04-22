package com.example.cardealershipclone1.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import com.example.cardealershipclone1.models.Client;

public class ClientsController {

    // 🔹 Fields
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField addressField;

    // 🔹 Table
    @FXML private TableView<Client> clientsTable;
    @FXML private TableColumn<Client, String> idColumn;
    @FXML private TableColumn<Client, String> nameColumn;
    @FXML private TableColumn<Client, String> phoneColumn;
    @FXML private TableColumn<Client, String> emailColumn;
    @FXML private TableColumn<Client, String> addressColumn;

    // 🔹 List
    private ObservableList<Client> clientsList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        System.out.println("Clients View Loaded ✅");

        clientsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // 🔥 ربط الجدول بالـ list
        clientsTable.setItems(clientsList);

        // 🔥 ربط الأعمدة
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        phoneColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhone()));
        emailColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        addressColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress()));
    }

    // 🔹 Add
    @FXML
    private void addClient() {
        Client client = new Client(
                idField.getText(),
                nameField.getText(),
                phoneField.getText(),
                emailField.getText(),
                addressField.getText()
        );

        clientsList.add(client);
        clearFields();
    }

    // 🔹 Delete
    @FXML
    private void deleteClient() {
        Client selected = clientsTable.getSelectionModel().getSelectedItem();

        if (selected != null) {
            clientsList.remove(selected);
        }
    }

    // 🔹 Update (بسيطة)
    @FXML
    private void updateClient() {
        Client selected = clientsTable.getSelectionModel().getSelectedItem();

        if (selected != null) {
            selected.setId(Integer.parseInt(idField.getText()));
            selected.setName(nameField.getText());
            selected.setPhone(phoneField.getText());
            selected.setEmail(emailField.getText());
            selected.setAddress(addressField.getText());

            clientsTable.refresh();
        }
    }

    // 🔹 Clear
    @FXML
    private void clearFields() {
        idField.clear();
        nameField.clear();
        phoneField.clear();
        emailField.clear();
        addressField.clear();
    }
}