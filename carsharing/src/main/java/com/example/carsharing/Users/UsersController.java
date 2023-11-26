package com.example.carsharing.Users;
import com.example.carsharing.dataWriter.UserData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    String usersFilename = "users.json";
    String usersFilepath = System.getProperty("user.dir") + System.getProperty("file.separator") + "data" + System.getProperty("file.separator") + usersFilename;

    private final UserData userData;
    public UsersController() throws Exception {
        this.userData = new UserData();
    }

    @GetMapping("/")
    public ResponseEntity getUsers(){
        try{

            List<User> users = userData.get();
            return ResponseEntity.ok(users);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getUsers(@PathVariable("id") long id){
        try{

            User user = userData.get(id);
            System.out.println(id);
            System.out.println(user);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity getUsers(@PathVariable("email") String email){
        try{
            User user = userData.get(email);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }







}
