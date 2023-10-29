package com.example.carsharing.dto;

public class CreateUserDto {
    String nickname;
    String email;
    String password;

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }
}
