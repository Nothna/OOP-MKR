package com.example.carsharing.postModule;

import jakarta.annotation.PostConstruct;

public class Post {
    private int id;
    private String userId;
    private String head;
    private String body;
    private String photo;

    public Post(int id, String authorEmail, String head, String body, String photo){
        this.id = id;
        this.userId = authorEmail;
        this.head = head;
        this.body = body;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getAuthorEmail() {
        return userId;
    }

    public String getBody() {
        return body;
    }

    public String getHead() {
        return head;
    }

    public String getPhoto() {
        return photo;
    }
}
