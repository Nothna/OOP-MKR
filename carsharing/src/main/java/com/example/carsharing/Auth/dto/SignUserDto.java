package com.example.carsharing.Auth.dto;

public class SignUserDto {
    String email;
    String password;
    public SignUserDto(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
