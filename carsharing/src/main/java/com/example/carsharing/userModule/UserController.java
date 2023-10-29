package com.example.carsharing.userModule;


import com.example.carsharing.auth.AuthService;
import com.example.carsharing.auth.UserJwtPayload;
import com.example.carsharing.dataWriter.UserData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController  {
    private final AuthService authService;
    private final UserData userData;

    public UserController() throws Exception {
        this.authService = new AuthService();
        this.userData = new UserData();
    }


    @GetMapping("/")
    public ResponseEntity getUsers(){
        try{

            JSONArray users = userData.get();
            return ResponseEntity.ok(users);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping(":id")
    public ResponseEntity getUsers(@RequestParam("id") long id){
        try{
            JSONObject user = userData.get(id);
            System.out.println(id);
            System.out.println(user);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping(":email")
    public ResponseEntity getUsers(@RequestParam("email") String email){
        try{
            JSONObject user = userData.get(email);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity getProfile(@RequestHeader("Authorization") String token){
        try{
            UserJwtPayload payload = this.authService.verifyUser(token);
            long id = payload.id;
            JSONObject user = this.userData.get(id);
            user.remove("password");            //removing password from the object we're returning on client
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: invalid token provided");
        }

    }






}
