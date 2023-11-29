package com.example.carsharing.Posts;

import com.example.carsharing.shared.Car;

public class Post {
    private long id;
    private String title;
    private String body;
    private Car car;
    private String image;
    private String price;

    public Post(long id, String title, String body, Car car, String image, String price){
        this.id = id;
        this.title = title;
        this.body = body;
        this.image = image;
        this.car = car;
        this.price = price;
    }
    public Post(){}

    public long getId() {
        return this.id;
    }



    public String getBody() {
        return this.body;
    }

    public String getTitle() {
        return this.title;
    }

    public String getImage() {
        return this.image;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car type) {
        this.car = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
