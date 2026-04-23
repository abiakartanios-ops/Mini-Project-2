package com.example.cardealershipclone1.controllers;

import com.example.cardealershipclone1.models.Client;
import com.example.cardealershipclone1.models.ClientsStore;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;

public class ClientsController {

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField addressField;

    @FXML private TableView<Client> clientsTable;
    @FXML private TableColumn<Client, String> idColumn;
    @FXML private TableColumn<Client, String> nameColumn;
    @FXML private TableColumn<Client, String> phoneColumn;
    @FXML private TableColumn<Client, String> emailColumn;
    @FXML private TableColumn<Client, String> addressColumn;

    private ObservableList<Client> clientsList = FXCollections.observableArrayList();

    private ClientsStore store = new ClientsStore();

    @FXML
    private void initialize() {

        clientsTable.setItems(clientsList);

        idColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        phoneColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhone()));
        emailColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        addressColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress()));
    }

    @FXML
    private void addClient() {

        Client client = new Client(
                idField.getText(),
                nameField.getText(),
                phoneField.getText(),
                emailField.getText(),
                addressField.getText()
        );

        store.addClient(client);      // 🔥 يحفظ بالـ DB
        clientsList.add(client);      // 🔥 يظهر بالـ Table

        clearFields();
    }

    @FXML
    private void deleteClient() {
        Client selected = clientsTable.getSelectionModel().getSelectedItem();

        if (selected != null) {
            clientsList.remove(selected);
           store.deleteClient(selected.getId());
        }
    }

    @FXML
    private void updateClient() {
        Client selected = clientsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (!nameField.getText().isEmpty())
                selected.setName(nameField.getText());

            if (!phoneField.getText().isEmpty())
                selected.setPhone(phoneField.getText());

            if (!emailField.getText().isEmpty())
                selected.setEmail(emailField.getText());

            if (!addressField.getText().isEmpty())
                selected.setAddress(addressField.getText());

            store.updateClient(selected);
            clientsTable.refresh();
        }
    }

    @FXML
    private void clearFields() {
        idField.clear();
        nameField.clear();
        phoneField.clear();
        emailField.clear();
        addressField.clear();
    }
}
// test