package com.example.carsharing.Auth;

public class UserJwtPayload {
    public long id;
    public String email;

    UserJwtPayload(long id, String email){
        this.id = id;
        this.email = email;
    }
}