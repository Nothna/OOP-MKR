package com.example.carsharing.shared.dataWriter;

import com.example.carsharing.Users.dto.CreateUserDto;
import com.example.carsharing.Users.User;
import com.example.carsharing.shared.Rental;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserData {
    private String usersFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "data" + System.getProperty("file.separator") + "users.json";
    private File usersFile;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private long idCount;
    private final PasswordEncoder passwordEncoder;

    public UserData() throws IOException {
        this.usersFile = new File(usersFilePath);
        this.passwordEncoder = new BCryptPasswordEncoder();

        if (!this.usersFile.exists() || this.usersFile.length() == 0) {
            // Handle empty or non-existent file
            List<User> emptyUsers = new ArrayList<>();
            this.objectMapper.writeValue(this.usersFile, emptyUsers);
            this.idCount = 0;
        } else {
            // File exists and has content, proceed with parsing
            List<User> users = this.objectMapper.readValue(this.usersFile, new TypeReference<List<User>>() {});
            this.idCount = users.size();
        }
    }

    public List<User> get() throws IOException {
        return objectMapper.readValue(this.usersFile, new TypeReference<List<User>>() {});
    }

    public User get(long id) throws IOException {
        List<User> users = get();
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User get(String email) throws IOException {
        List<User> users = get();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public User order(long userId, Rental rental) throws IOException{
        List<User> users = get();
        for (User user : users) {
            if (user.getId() == userId) {
                user.setRental(rental);
                this.objectMapper.writeValue(this.usersFile, users);
                return user;
            }
        }
        return null;

    }

    public User create(CreateUserDto newUser) throws IOException {
        List<User> users = get();
        String email = newUser.getEmail();
        if (get(email) != null) {
            throw new IOException("User with given email already exists");
        }
        String hashedPassword = this.passwordEncoder.encode(newUser.getPassword());

        User user = new User(++idCount, newUser.getNickname(), hashedPassword, newUser.getEmail());
        users.add(user);
        objectMapper.writeValue(this.usersFile, users);
        return user;
    }


    public void delete(long id) throws IOException {
        List<User> users = get();
        users.removeIf(user -> user.getId() == id);
        objectMapper.writeValue(usersFile, users);
    }
}

