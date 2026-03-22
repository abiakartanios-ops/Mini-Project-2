package com.example.cardealershipclone1.controllers;

import com.example.cardealershipclone1.models.Car;
import com.example.cardealershipclone1.models.CarDataStore;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DashboardController {

    private final CarDataStore dataStore = CarDataStore.getInstance();
    private boolean isFormView = true;


    @FXML private Label headerLabel;
    @FXML private Label countLabel;
    @FXML private Button toggleSceneButton;

    @FXML private StackPane contentPane;
    @FXML private VBox formScene;
    @FXML private VBox tableScene;


    @FXML private TextField makeField;
    @FXML private TextField modelField;
    @FXML private Spinner<Integer> yearSpinner;
    @FXML private TextField priceField;
    @FXML private ComboBox<String> colorCombo;
    @FXML private TextField mileageField;
    @FXML private TextField vinField;
    @FXML private ComboBox<String> fuelCombo;
    @FXML private CheckBox isNewCheckBox;
    @FXML private Label statusLabel;


    @FXML private TableView<Car> tableView;
    @FXML private Button deleteBtn;
    @FXML private Label tableStatusLabel;

    @FXML
    public void initialize() {
        // Initialize Year Spinner
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1990, 2026, 2025);
        yearSpinner.setValueFactory(valueFactory);

        // Initialize Color ComboBox
        colorCombo.getItems().addAll(
                "White", "Black", "Silver", "Gray", "Red",
                "Blue", "Green", "Yellow", "Orange", "Brown"
        );

        // Initialize Fuel ComboBox
        fuelCombo.getItems().addAll("Diesel", "Electric", "Hybrid");

        // Setup TableView
        setupTableView();

        // Setup delete button listener
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> deleteBtn.setDisable(newVal == null)
        );

        // Listen to data store changes
        dataStore.getCars().addListener((javafx.collections.ListChangeListener<Car>) change -> {
            updateCountLabel();
        });

        updateCountLabel();
    }

    @SuppressWarnings("unchecked")
    private void setupTableView() {
        tableView.setItems(dataStore.getCars());
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Make Column
        TableColumn<Car, String> makeCol = new TableColumn<>("Make");
        makeCol.setCellValueFactory(cellData -> cellData.getValue().makeProperty());
        makeCol.setMinWidth(90);

        // Model Column
        TableColumn<Car, String> modelCol = new TableColumn<>("Model");
        modelCol.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        modelCol.setMinWidth(90);

        // Year Column
        TableColumn<Car, Integer> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
        yearCol.setMinWidth(60);

        // Price Column
        TableColumn<Car, Double> priceCol = new TableColumn<>("Price ($)");
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        priceCol.setMinWidth(90);
        priceCol.setCellFactory(col -> new TableCell<Car, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    setText(String.format("$%,.2f", price));
                }
            }
        });

        // Color Column
        TableColumn<Car, String> colorCol = new TableColumn<>("Color");
        colorCol.setCellValueFactory(cellData -> cellData.getValue().colorProperty());
        colorCol.setMinWidth(70);

        // Mileage Column
        TableColumn<Car, Integer> mileageCol = new TableColumn<>("Mileage");
        mileageCol.setCellValueFactory(cellData -> cellData.getValue().mileageProperty().asObject());
        mileageCol.setMinWidth(80);
        mileageCol.setCellFactory(col -> new TableCell<Car, Integer>() {
            @Override
            protected void updateItem(Integer mileage, boolean empty) {
                super.updateItem(mileage, empty);
                if (empty || mileage == null) {
                    setText(null);
                } else {
                    setText(String.format("%,d mi", mileage));
                }
            }
        });

        // VIN Column
        TableColumn<Car, String> vinCol = new TableColumn<>("VIN");
        vinCol.setCellValueFactory(cellData -> cellData.getValue().vinProperty());
        vinCol.setMinWidth(140);

        // Fuel Type Column
        TableColumn<Car, String> fuelCol = new TableColumn<>("Fuel Type");
        fuelCol.setCellValueFactory(cellData -> cellData.getValue().fuelTypeProperty());
        fuelCol.setMinWidth(80);

        // Condition Column
        TableColumn<Car, Boolean> conditionCol = new TableColumn<>("Condition");
        conditionCol.setCellValueFactory(cellData -> cellData.getValue().isNewProperty().asObject());
        conditionCol.setMinWidth(80);
        conditionCol.setCellFactory(col -> new TableCell<Car, Boolean>() {
            @Override
            protected void updateItem(Boolean isNew, boolean empty) {
                super.updateItem(isNew, empty);
                if (empty || isNew == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(isNew ? "New" : "Used");
                    setStyle(isNew
                            ? "-fx-text-fill: green; -fx-font-weight: bold;"
                            : "-fx-text-fill: orange; -fx-font-weight: bold;");
                }
            }
        });

        tableView.getColumns().addAll(
                makeCol, modelCol, yearCol, priceCol, colorCol,
                mileageCol, vinCol, fuelCol, conditionCol
        );

        // Placeholder
        Label placeholder = new Label("No cars in inventory.\nClick '➕ Add New Car' to add one.");
        placeholder.setStyle("-fx-font-family: Arial; -fx-font-size: 14; -fx-text-fill: gray;");
        tableView.setPlaceholder(placeholder);
    }

    @FXML
    private void handleSubmit() {
        String make = makeField.getText().trim();
        String model = modelField.getText().trim();
        int year = yearSpinner.getValue();
        String priceText = priceField.getText().trim();
        String color = colorCombo.getValue();
        String mileageText = mileageField.getText().trim();
        String vin = vinField.getText().trim();
        String fuelType = fuelCombo.getValue();
        boolean isNew = isNewCheckBox.isSelected();

        // Validation
        if (make.isEmpty() || model.isEmpty() || priceText.isEmpty() ||
                color == null || mileageText.isEmpty() || vin.isEmpty() || fuelType == null) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("⚠ Please fill in all fields.");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
            if (price < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("⚠ Price must be a valid positive number.");
            return;
        }

        int mileage;
        try {
            mileage = Integer.parseInt(mileageText);
            if (mileage < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("⚠ Mileage must be a valid positive integer.");
            return;
        }

        if (vin.length() > 7) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("⚠ VIN must be maximum 7 characters.");
            return;
        }

        // Add car
        Car car = new Car(make, model, year, price, color, mileage, vin, fuelType, isNew);
        dataStore.addCar(car);

        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("✅ Car added successfully! Total cars: " + dataStore.getCars().size());

        // Clear form
        clearForm();
    }

    @FXML
    private void handleClear() {
        clearForm();
        statusLabel.setText("");
    }

    private void clearForm() {
        makeField.clear();
        modelField.clear();
        yearSpinner.getValueFactory().setValue(2025);
        priceField.clear();
        colorCombo.setValue(null);
        mileageField.clear();
        vinField.clear();
        fuelCombo.setValue(null);
        isNewCheckBox.setSelected(false);
    }

    @FXML
    private void handleToggleScene() {
        isFormView = !isFormView;

        if (isFormView) {
            // Show form
            formScene.setVisible(true);
            tableScene.setVisible(false);
            headerLabel.setText("🚗 Add New Car");
            toggleSceneButton.setText("📋 View Inventory");
            countLabel.setText("");
        } else {
            // Show table
            formScene.setVisible(false);
            tableScene.setVisible(true);
            headerLabel.setText("📋 Car Inventory");
            toggleSceneButton.setText("➕ Add New Car");
            updateCountLabel();
        }
    }

    @FXML
    private void handleDelete() {
        Car selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Delete Car");
            alert.setContentText("Are you sure you want to delete:\n" + selected.toString() + "?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    dataStore.removeCar(selected);
                    updateCountLabel();
                    tableStatusLabel.setTextFill(Color.GREEN);
                    tableStatusLabel.setText("✅ Car deleted successfully.");
                }
            });
        }
    }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cardealershipclone1/login.fxml"));
            Scene loginScene = new Scene(loader.load(), 420, 480);

            Stage loginStage = new Stage();
            loginStage.setTitle("Car Dealership - Sign In");
            loginStage.setResizable(false);
            loginStage.setScene(loginScene);
            loginStage.show();

            // Close dashboard
            Stage dashboardStage = (Stage) headerLabel.getScene().getWindow();
            dashboardStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCountLabel() {
        countLabel.setText("Total Cars: " + dataStore.getCars().size());
    }
}