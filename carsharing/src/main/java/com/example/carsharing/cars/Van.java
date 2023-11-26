package com.example.carsharing.cars;

public class Van extends Car {
    private boolean isMiniVan;

    public Van(String manufacturer, String model, int year, boolean isMiniVan){
        super(manufacturer, model, year);
        this.isMiniVan = isMiniVan;
    }

    public boolean IsMiniVan() {
        return isMiniVan;
    }
}
