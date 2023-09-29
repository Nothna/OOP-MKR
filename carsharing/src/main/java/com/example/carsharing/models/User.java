package com.example.carsharing.models;

public class User {
    private long id;
    private String email;
    private String nickname;
    private String password;
    private String[] profilePhoto;
    public User(int id, String email, String nickname, String password, String[] profilePhoto ){
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.profilePhoto = profilePhoto;
    }
    public User(int id, String email, String nickname, String password){
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

    public String[] getProfilePhoto() {
        return profilePhoto;
    }
}
