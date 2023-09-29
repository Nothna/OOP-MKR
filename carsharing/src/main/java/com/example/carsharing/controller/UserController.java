package com.example.carsharing.controller;


import com.example.carsharing.dataWriter.DataWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController  {
    String usersFilename = "users.json";
    String usersFilepath = System.getProperty("user.dir") + System.getProperty("file.separator") + "data" + System.getProperty("file.separator") + usersFilename;

    @GetMapping("/")
    public ResponseEntity getUsers(){
        try{
            DataWriter dataWriter = new DataWriter(usersFilepath);
            JSONArray users = dataWriter.get();
            return ResponseEntity.ok(users);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getUsers(@PathVariable("id") long id){
        try{
            DataWriter dataWriter = new DataWriter(usersFilepath);
            JSONObject user = dataWriter.get(id);
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
            DataWriter dataWriter = new DataWriter(usersFilepath);
            JSONObject user = dataWriter.get(email);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }






}
