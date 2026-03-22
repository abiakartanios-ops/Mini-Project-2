package com.example.cardealershipclone1.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CarDataStore {

    private static CarDataStore instance;
    private final ObservableList<Car> cars;

    private CarDataStore() {
        cars = FXCollections.observableArrayList();
    }


    public static CarDataStore getInstance() {
        if (instance == null) {
            instance = new CarDataStore();
        }
        return instance;
    }


    // Returns the observable list of cars.

    public ObservableList<Car> getCars() {
        return cars;
    }


    // Adds a car to the data store.

    public void addCar(Car car) {
        cars.add(car);
    }


    //Removes a car from the data store.

    public void removeCar(Car car) {
        cars.remove(car);
    }
}