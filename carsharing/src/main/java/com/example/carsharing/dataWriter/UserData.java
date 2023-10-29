package com.example.carsharing.dataWriter;

import com.example.carsharing.dto.CreateUserDto;
import com.example.carsharing.userModule.User;
import jakarta.annotation.PostConstruct;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.mindrot.jbcrypt.BCrypt;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class UserData {
    String users_file = "users.json";
    String filepath = System.getProperty("user.dir") + System.getProperty("file.separator") + "data" + System.getProperty("file.separator") + users_file;

    private JSONParser parser = new JSONParser();
    private FileReader reader = new FileReader(filepath);

    private long idCount;


    // class constructor
    public UserData() throws Exception{

        try{
            JSONArray users = get();

            this.idCount = users.size();

        }catch (Exception e){
            throw e;
        }

    }
    @PostConstruct()
    public void init() throws Exception{

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
                if (user.get("id").equals(id)) {
                    return user;
                }
            }
        }
        return null;
    }

    public JSONObject get(String email) throws Exception {
        try (FileReader reader = new FileReader(filepath)) {
            JSONParser parser = new JSONParser();
            JSONArray users = (JSONArray) parser.parse(reader);
            for (Object obj : users) {
                JSONObject user = (JSONObject) obj;
                if (user.get("email").equals(email)) {
                    return user;
                }
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


        user.put("id", idCount);
        user.put("nickname", newUser.getNickname());
        user.put("password", hashedPassword);
        user.put("email", newUser.getEmail());

        users.add(user);
        save(users);
        return new User(idCount, newUser.getNickname(), newUser.getPassword(), newUser.getEmail());
    }


    public void update(long id, JSONObject updatedUser) throws Exception {
        JSONArray users = get();
        Iterator<Object> iterator = users.iterator();
        while (iterator.hasNext()) {
            JSONObject user = (JSONObject) iterator.next();
            if (user.get("id").equals(id)) {
                user.putAll(updatedUser); // оновлюємо інформацію користувача
                save(users);
                return;
            }
        }
    }

    public void delete(long id) throws Exception {
        JSONArray users = get();
        Iterator<Object> iterator = users.iterator();
        while (iterator.hasNext()) {
            JSONObject user = (JSONObject) iterator.next();
            if (user.get("id").equals(id)) {
                iterator.remove(); // видаляємо користувача
                save(users);
                return;
            }
        }
    }

    private void save(JSONArray users) throws IOException {
        try (FileWriter writer = new FileWriter(filepath)) {
            writer.write(users.toJSONString());
            writer.flush();
        }
    }


}
