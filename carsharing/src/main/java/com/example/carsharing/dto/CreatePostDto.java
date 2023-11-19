package com.example.carsharing.dto;

public class CreatePostDto {
    private String title;
    private String body;
    private String image;
    private Long userId;

    // Constructor
    public CreatePostDto(String title, String body, String image, Long userId) {
        this.title = title;
        this.body = body;
        this.image = image;
        this.userId = userId;

    }

    // Getters for the properties
    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getImage() {
        return image;
    }

    public Long getUserId() {
        return userId;
    }

    // Setters if needed

    public void setTitle(String title) {
        this.title = title;
    }


}
