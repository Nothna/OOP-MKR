package com.example.carsharing.postModule;

import jakarta.annotation.PostConstruct;

public class Post {
    private int id;
    private String authorEmail;
    private String head;
    private String body;
    private String[] photo;

    public Post(int id, String authorEmail, String head, String body, String[] photo){
        this.id = id;
        this.authorEmail = authorEmail;
        this.head = head;
        this.body = body;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public String getBody() {
        return body;
    }

    public String getHead() {
        return head;
    }

    public String[] getPhoto() {
        return photo;
    }
}
