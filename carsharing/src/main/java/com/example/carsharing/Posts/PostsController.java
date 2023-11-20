package com.example.carsharing.Posts;

import com.example.carsharing.dataWriter.PostData;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController("/posts")
public class PostsController {
    private final PostData postData;
    public PostsController() throws Exception{
        this.postData = new PostData();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id){
        try {
            JSONObject post = this.postData.get(id);
            if (post == null) throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Post not found");
            return ResponseEntity.ok().body(post);
        } catch(Exception e){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid request");

        }

    }
}
