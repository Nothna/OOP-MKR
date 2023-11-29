package com.example.carsharing.shared;

import java.time.LocalDateTime;

public class Rental {
    private Car car;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    public Rental(Car car, LocalDateTime startTime, LocalDateTime endTime){
        this.car = car;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public Car getCar() {
        return car;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
