package com.example.cardealershipclone1.models;

import javafx.beans.property.*;

//Model class representing a Car entity in the dealership.

public class Car {

    private final StringProperty make;
    private final StringProperty model;
    private final IntegerProperty year;
    private final DoubleProperty price;
    private final StringProperty color;
    private final IntegerProperty mileage;
    private final StringProperty vin;
    private final StringProperty fuelType;
    private final BooleanProperty isNew;


    public Car(String make, String model, int year, double price, String color,
               int mileage, String vin, String fuelType, boolean isNew) {
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.year = new SimpleIntegerProperty(year);
        this.price = new SimpleDoubleProperty(price);
        this.color = new SimpleStringProperty(color);
        this.mileage = new SimpleIntegerProperty(mileage);
        this.vin = new SimpleStringProperty(vin);
        this.fuelType = new SimpleStringProperty(fuelType);
        this.isNew = new SimpleBooleanProperty(isNew);
    }

    // --- Make ---
    public String getMake() { return make.get(); }
    public void setMake(String make) { this.make.set(make); }
    public StringProperty makeProperty() { return make; }

    // --- Model ---
    public String getModel() { return model.get(); }
    public void setModel(String model) { this.model.set(model); }
    public StringProperty modelProperty() { return model; }

    // --- Year ---
    public int getYear() { return year.get(); }
    public void setYear(int year) { this.year.set(year); }
    public IntegerProperty yearProperty() { return year; }

    // --- Price ---
    public double getPrice() { return price.get(); }
    public void setPrice(double price) { this.price.set(price); }
    public DoubleProperty priceProperty() { return price; }

    // --- Color ---
    public String getColor() { return color.get(); }
    public void setColor(String color) { this.color.set(color); }
    public StringProperty colorProperty() { return color; }

    // --- Mileage ---
    public int getMileage() { return mileage.get(); }
    public void setMileage(int mileage) { this.mileage.set(mileage); }
    public IntegerProperty mileageProperty() { return mileage; }

    // --- VIN ---
    public String getVin() { return vin.get(); }
    public void setVin(String vin) { this.vin.set(vin); }
    public StringProperty vinProperty() { return vin; }

    // --- Fuel Type ---
    public String getFuelType() { return fuelType.get(); }
    public void setFuelType(String fuelType) { this.fuelType.set(fuelType); }
    public StringProperty fuelTypeProperty() { return fuelType; }

    // --- Is New ---
    public boolean getIsNew() { return isNew.get(); }
    public void setIsNew(boolean isNew) { this.isNew.set(isNew); }
    public BooleanProperty isNewProperty() { return isNew; }

    @Override
    public String toString() {
        return year.get() + " " + make.get() + " " + model.get() + " - $" + String.format("%.2f", price.get());
    }
}