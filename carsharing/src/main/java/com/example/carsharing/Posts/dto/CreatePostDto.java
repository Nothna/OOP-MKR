package com.example.carsharing.Posts.dto;

import com.example.carsharing.cars.Car;

public class CreatePostDto {
    private String title;
    private String body;
    private Car type; // Using Car as the type instead of a generic T

    // Constructor
    public CreatePostDto(String title, String body, Car type) {
        this.title = title;
        this.body = body;
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public Car getType() {
        return type;
    }
}
