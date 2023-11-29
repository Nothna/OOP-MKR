package com.example.carsharing.Users.dto;

public class SignInUserDto {
    private long id;
    private String email;

    public SignInUserDto(long id, String email){
        this.id = id;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }
}
