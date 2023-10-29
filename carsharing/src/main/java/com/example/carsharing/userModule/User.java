package com.example.carsharing.userModule;

public class User {
    private long id;
    private String email;
    private String nickname;
    private String password;

    public User(long id, String email, String nickname, String password){
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }


}
