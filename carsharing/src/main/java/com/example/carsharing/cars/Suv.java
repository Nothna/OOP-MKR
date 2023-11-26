package com.example.carsharing.cars;

class Suv extends Car {
    private boolean is4WD;

    public Suv(String manufacturer, String model, int year, boolean is4WD) {
        super(manufacturer, model, year);
        this.is4WD = is4WD;
    }

    public boolean is4WD() {
        return this.is4WD;
    }
}