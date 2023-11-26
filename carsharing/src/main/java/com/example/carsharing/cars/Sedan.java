package com.example.carsharing.cars;

class Sedan extends Car {
    private int numOfDoors;

    public Sedan(String manufacturer, String model, int year, int numOfDoors) {
        super(manufacturer, model, year);
        this.numOfDoors = numOfDoors;
    }

    public int getNumOfDoors() {
        return numOfDoors;
    }
}

