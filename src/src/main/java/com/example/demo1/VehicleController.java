package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VehicleController {

    @FXML private TextField makeInput, modelInput, yearInput;
    @FXML private TableView<Vehicle> vehicleTable;
    @FXML private TableColumn<Vehicle, String> makeCol, modelCol;
    @FXML private TableColumn<Vehicle, Integer> yearCol;

    private final ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // This links the table columns to the Vehicle class properties
        makeCol.setCellValueFactory(cellData -> cellData.getValue().makeProperty());
        modelCol.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        yearCol.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());

        vehicleTable.setItems(vehicleList);
    }

    @FXML
    private void handleAddVehicle() {
        try {
            String make = makeInput.getText();
            String model = modelInput.getText();
            int year = Integer.parseInt(yearInput.getText());

            if (!make.isEmpty() && !model.isEmpty()) {
                vehicleList.add(new Vehicle(make, model, year));
                makeInput.clear();
                modelInput.clear();
                yearInput.clear();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Year Input");
        }
    }
}