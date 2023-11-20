package com.example.carsharing.dataWriter;

import com.example.carsharing.postModule.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.UUID;

public class PostData {
    private final String postsFile = "posts.json";
    private final String filepath = System.getProperty("user.dir") + System.getProperty("file.separator") + "data" + System.getProperty("file.separator") + postsFile;

    private final JSONParser parser = new JSONParser();

    // Constructor
    public PostData() {
        // Initialize if needed
    }


    public JSONArray get() throws IOException, ParseException {
        try (FileReader reader = new FileReader(filepath)) {
            return (JSONArray) parser.parse(reader);
        }
    }

    public JSONObject get(UUID id) throws IOException, ParseException {
        JSONArray posts = get();
        for (Object obj : posts) {
            JSONObject post = (JSONObject) obj;
            UUID postId = UUID.fromString((String) post.get("id"));
            if (postId.equals(id)) {
                return post;
            }
        }
        return null;
    }

    public Post create(String userId, String head, String body, String photo) throws IOException, ParseException {
        JSONArray posts = get();

        //String photoFileName = savePhoto(photoInputStream, originalPhotoName);
        UUID postId = UUID.randomUUID();

        JSONObject post = new JSONObject();
        post.put("id", postId.toString());
        post.put("userId", userId);
        post.put("head", head);
        post.put("body", body);
        post.put("photo", photo);

        posts.add(post);
        save(posts);

        return new Post(postId.hashCode(), userId, head, body, photo);
    }

    public void updatePost(JSONObject updatedPost) throws IOException, ParseException {
        // Extract the id from the updatedPost object
        Long updateId = (Long) updatedPost.get("id");

        JSONArray posts = get();
        for (int i = 0; i < posts.size(); i++) {
            JSONObject post = (JSONObject) posts.get(i);
            Long postId = (Long) post.get("id");

            // Compare the postId from the array with the updateId of the updatedPost
            if (postId.equals(updateId)) {
                posts.set(i, updatedPost); // Update the post with new details
                save(posts);
                return;
            }
        }
    }


    public void delete(Long id) throws IOException, ParseException {
        JSONArray posts = get();
        posts.removeIf(obj -> {
            JSONObject post = (JSONObject) obj;
            return UUID.fromString((String) post.get("id")).equals(id);
        });
        save(posts);
    }

    private void save(JSONArray posts) throws IOException {
        try (FileWriter writer = new FileWriter(filepath)) {
            writer.write(posts.toJSONString());
            writer.flush();
        }
    }




}
