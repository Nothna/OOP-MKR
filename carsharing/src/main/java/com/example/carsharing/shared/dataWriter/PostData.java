package com.example.carsharing.shared.dataWriter;

import com.example.carsharing.Posts.Post;
import com.example.carsharing.Posts.dto.CreatePostDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostData {
    private String posts_file = "posts.json";
    private String filepath = System.getProperty("user.dir") + File.separator + "data" + File.separator + posts_file;
    private File file;
    private long idCount;
    private final ObjectMapper mapper;
    private final ObjectWriter writer;

    public PostData() throws IOException {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.file = new File(this.filepath);
        this.writer = this.mapper.writerFor(new TypeReference<List<Post>>() {});

        File file = new File(filepath);
        if (!file.exists() || file.length() == 0) {
            List<Post> emptyPosts = new ArrayList<>();
            save(emptyPosts);
            this.idCount = 0;
        } else {
            List<Post> posts = get();
            this.idCount = posts.size();
        }
    }

    public List<Post> get() throws IOException {
        return this.mapper.readValue(this.file, new TypeReference<List<Post>>() {});
    }

    public Post get(long id) throws IOException {
        List<Post> posts = get();
        return posts.stream().filter(post -> post.getId() == id).findFirst().orElse(null);
    }

    public Post create(CreatePostDto newPost, String imageUrl) throws IOException {
        List<Post> posts = get();
        Post post = new Post(++idCount, newPost.getTitle(), newPost.getBody(), newPost.getCar(), imageUrl, newPost.getPrice());
        posts.add(post);
        save(posts);
        return post;
    }

    public void update(long id, Post updatedPost) throws IOException {
        List<Post> posts = get();
        for (Post post : posts) {
            if (post.getId() == id) {
                post.setTitle(updatedPost.getTitle());
                post.setBody(updatedPost.getBody());
                post.setImage(updatedPost.getImage());
                post.setCar(updatedPost.getCar());
                save(posts);
                return;
            }
        }
    }

    public void delete(long id) throws IOException {
        List<Post> posts = get();
        posts.removeIf(post -> post.getId() == id);
        save(posts);
    }

    private void save(List<Post> posts) throws IOException {
        writer.writeValue(new File(filepath), posts);
    }
}
