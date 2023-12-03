package com.example.carsharing.shared;

public class Car {
    private String manufacturer; //like KIA
    private String model;        //Cee`d
    private long year;           //2017

    private String wheelBase; //4x2

    private long seats; //4


    public Car(String manufacturer, String model, int year, long seats, String wheelBase) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.seats = seats;
        this.wheelBase = wheelBase;
    }

    public Car(){}

    public long getYear() {
        return year;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public long getSeats() {
        return seats;
    }

    public String getWheelBase() {
        return wheelBase;
    }
}

