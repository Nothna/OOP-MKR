package com.example.carsharing.cars;

public class Crossover extends Car{
    private boolean is4WD;
    public Crossover(String manufacturer, String model, int year, boolean is4WD){
        super(manufacturer, model, year);
        this.is4WD = is4WD;
    }
}
