package com.example.demo1;

import javafx.beans.property.*;

public class Vehicle {
    private final StringProperty make;
    private final StringProperty model;
    private final IntegerProperty year;

    public Vehicle(String make, String model, int year) {
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.year = new SimpleIntegerProperty(year);
    }

    public StringProperty makeProperty() { return make; }
    public StringProperty modelProperty() { return model; }
    public IntegerProperty yearProperty() { return year; }
}