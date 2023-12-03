package com.example.carsharing.shared;

import com.example.carsharing.Posts.Post;

import java.time.LocalDateTime;

public class Rental {
    private Post post;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    public Rental(Post post, LocalDateTime startTime, LocalDateTime endTime){
        this.post = post;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Rental(){};


    public Post getPost() {
        return post;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
