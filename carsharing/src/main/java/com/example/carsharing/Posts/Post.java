package com.example.carsharing.Posts;

import com.example.carsharing.cars.Car;

public class Post {
    private long id;
    private String title;
    private String body;
    private Car type;
    private String image;

    public Post(long id, String title, String body, Car type, String image){
        this.id = id;
        this.title = title;
        this.body = body;
        this.image = image;
        this.type = type;
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

    public Car getType() {
        return type;
    }

    public void setType(Car type) {
        this.type = type;
    }
}
