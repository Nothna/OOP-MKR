package com.example.carsharing.Users;

public class User {
    public User(Long id, String email, String password, String nickname){
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
    private Long id;
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


}
