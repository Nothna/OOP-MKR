package com.example.carsharing.Posts;

import com.example.carsharing.Posts.dto.CreatePostDto;
import com.example.carsharing.cars.Car;
import com.example.carsharing.dataWriter.PostData;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController()
@RequestMapping("/posts")
public class PostsController {
    private final PostData postData;
    public PostsController() throws Exception{
        this.postData = new PostData();
    }

    @GetMapping()
    public ResponseEntity getById(@RequestParam("id") Long id){
        try {

            Post post = this.postData.get(id);
            if (post == null) throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Post not found");
            return ResponseEntity.ok().body(post);
        } catch(Exception e){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid request");

        }

    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody() CreatePostDto createPostDto){
        try {
            //todo: use interceptors for file processing

            System.out.println(createPostDto.getType());
            Post post = this.postData.create(createPostDto, "g");
            return ResponseEntity.ok().body(post);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid data provided");
        }


    }
}
