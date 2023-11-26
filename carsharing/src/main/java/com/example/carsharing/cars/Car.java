package com.example.carsharing.cars;

public class Car {
    private String manufacturer; //like KIA
    private String model;        //Cee`d
    private long year;           //2017


    public Car(String manufacturer, String model, int year) {
        this.manufacturer = manufacturer;
        this.model = manufacturer;
        this.year = year;
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

}

