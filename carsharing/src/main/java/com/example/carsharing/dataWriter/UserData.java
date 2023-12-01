package com.example.carsharing.dataWriter;

import com.example.carsharing.dto.CreateUserDto;
import com.example.carsharing.userModule.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.mindrot.jbcrypt.BCrypt;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserData {
    private String users_file = "users.json";
    private FileWriter fileWriter;
    private String filepath = System.getProperty("user.dir") + System.getProperty("file.separator") + "data" + System.getProperty("file.separator") + users_file;

    private JSONParser parser = new JSONParser();
    private long idCount;

    // No-argument constructor
    public UserData() throws Exception {
        JSONArray users = get();
        this.idCount = users.size(); // This assumes the ID is the size of the array, which may not be accurate if users can be deleted.
    }

    public JSONArray get() throws Exception {
        try (FileReader reader = new FileReader(filepath)) {
            return (JSONArray) this.parser.parse(reader);
        }
    }

    public JSONObject get(long id) throws Exception {
        try (FileReader reader = new FileReader(filepath)) {
            JSONArray users = (JSONArray) this.parser.parse(reader);
            for (Object obj : users) {
                JSONObject user = (JSONObject) obj;
                if (String.valueOf(id).equals(user.get("id").toString())) {
                    return user;
                }
            }
        }
        return null;
    }

    public JSONObject get(String email) throws Exception {
        JSONArray users = get();
        for (Object obj : users) {
            JSONObject user = (JSONObject) obj;
            if (user.get("email").equals(email)) {
                return user;
            }
        }
        return null;
    }

    public User create(CreateUserDto newUser) throws Exception {
        JSONArray users = get();
        String email = newUser.getEmail();
        if (get(email) != null) {
            throw new IOException("User with given email already exists");
        }
        JSONObject user = new JSONObject();
        String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());

        user.put("id", ++idCount); // Increment the ID count for each new user
        user.put("nickname", newUser.getNickname());
        user.put("password", hashedPassword);
        user.put("email", newUser.getEmail());

        users.add(user);
        save(users);
        return new User(idCount, newUser.getNickname(), newUser.getPassword(), newUser.getEmail());
    }

    public void update(long id, JSONObject updatedUser) throws Exception {
        JSONArray users = get();
        for (Object obj : users) {
            JSONObject user = (JSONObject) obj;
            if (String.valueOf(id).equals(user.get("id").toString())) {
                user.putAll(updatedUser);
                save(users);
                return;
            }
        }
    }

    public void delete(long id) throws Exception {
        JSONArray users = get();
        users.removeIf(obj -> String.valueOf(id).equals(((JSONObject)obj).get("id").toString()));
        save(users);
    }

    public void save(JSONArray users) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filepath)) {
            fileWriter.write(users.toJSONString());
            fileWriter.flush();
        }
    }
}
