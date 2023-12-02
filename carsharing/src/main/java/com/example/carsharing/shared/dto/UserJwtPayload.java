package com.example.carsharing.shared.dto;

public class UserJwtPayload {
    long id;
    String email;
    public UserJwtPayload(long id, String email){
        this.id = id;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
