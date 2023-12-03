package com.example.carsharing.shared;

public class UserJwtPayload {
    long id;
    String email;

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    public UserJwtPayload(){}
    public UserJwtPayload(long id, String email){
        this.id = id;
        this.email = email;
    }
}
