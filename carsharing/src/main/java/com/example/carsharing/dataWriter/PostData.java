package com.example.carsharing.dataWriter;

import com.example.carsharing.dto.CreatePostDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PostData {
    private String posts_file = "posts.json";
    private FileWriter fileWriter;
    private String filepath = System.getProperty("user.dir") + System.getProperty("file.separator") + "data" + System.getProperty("file.separator") + posts_file;

    private JSONParser parser;
    private long idCount;

    // No-argument constructor
    public PostData() throws IOException {
        this.fileWriter = new FileWriter(filepath);
        this.parser = new JSONParser();

    // This assumes the ID is the size of the array, which may not be accurate if posts can be deleted.
    }

    public JSONArray get() throws Exception {
        try (FileReader reader = new FileReader(filepath)) {
            return (JSONArray) this.parser.parse(reader);
        }
    }

    public JSONObject get(long id) throws Exception {
        JSONArray posts = get();
        for (Object obj : posts) {
            JSONObject post = (JSONObject) obj;
            if (String.valueOf(id).equals(post.get("id").toString())) {
                return post;
            }
        }
        return null;
    }

    public JSONObject create(CreatePostDto newPost) throws Exception {
        JSONArray posts = get();
        JSONObject post = new JSONObject();

        post.put("id", ++idCount); // Increment the ID count for each new post
        post.put("title", newPost.getTitle());
        post.put("body", newPost.getBody());
        post.put("image", newPost.getImage());
        post.put("userId", newPost.getUserId());

        posts.add(post);
        save(posts);
        return post;
    }

    public void update(long id, JSONObject updatedPost) throws Exception {
        JSONArray posts = get();
        for (Object obj : posts) {
            JSONObject post = (JSONObject) obj;
            if (String.valueOf(id).equals(post.get("id").toString())) {
                post.putAll(updatedPost);
                save(posts);
                return;
            }
        }
    }

    public void delete(long id) throws Exception {
        JSONArray posts = get();
        posts.removeIf(obj -> String.valueOf(id).equals(((JSONObject)obj).get("id").toString()));
        save(posts);
    }

    private void save(JSONArray posts) throws IOException {
        try (FileWriter file = new FileWriter(filepath)) {
            file.write(posts.toJSONString());
            file.flush();
        }
    }
}
