package com.example.carsharing.Users;

import com.example.carsharing.shared.Rental;

public class User {
    public User(Long id, String nickname, String password,  String email){
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
    public User(){}
    private Long id;
    private Rental rental;
    private String email;
    private String password;
    private String nickname;

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
