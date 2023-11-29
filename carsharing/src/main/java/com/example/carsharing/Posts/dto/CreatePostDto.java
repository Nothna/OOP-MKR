package com.example.carsharing.Posts.dto;

import com.example.carsharing.shared.Car;

public class CreatePostDto {
    private String title;
    private String body;
    private String price;
    private Car car;

    // Constructor
    public CreatePostDto(String title, String body, Car car, String price) {
        this.title = title;
        this.body = body;
        this.car = car;
        this.price = price;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public Car getCar() {
        return car;
    }

    public String getPrice() {
        return price;
    }
}
