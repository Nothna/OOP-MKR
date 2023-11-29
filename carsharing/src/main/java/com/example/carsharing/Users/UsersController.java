package com.example.carsharing.Users;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.carsharing.shared.dataWriter.UserData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    String usersFilename = "users.json";
    String usersFilepath = System.getProperty("user.dir") + System.getProperty("file.separator") + "data" + System.getProperty("file.separator") + usersFilename;

    private final UserData userData;
    public UsersController() throws Exception {
        this.userData = new UserData();
    }

    @GetMapping()
    public ResponseEntity getUsers(@RequestAttribute("user") DecodedJWT user){
        try{

            User newUser = userData.get(user.getId());
            return ResponseEntity.ok(newUser);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }


    @PostMapping("/order")
    public ResponseEntity getUsers(HttpServletRequest request){
        try{
            /*System.out.println(user);
            System.out.println(user.getId());
            //this.userData.order(user.getId(), body);*/
            return ResponseEntity.ok(request.getAttribute("user"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }







}
