package com.example.carsharing.Users;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.carsharing.Posts.Post;
import com.example.carsharing.Users.dto.OrderCarDto;
import com.example.carsharing.shared.Rental;
import com.example.carsharing.shared.UserJwtPayload;
import com.example.carsharing.shared.dataWriter.PostData;
import com.example.carsharing.shared.dataWriter.UserData;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
public class UsersController {
    String usersFilename = "users.json";
    String usersFilepath = System.getProperty("user.dir") + System.getProperty("file.separator") + "data" + System.getProperty("file.separator") + usersFilename;

    private final UserData userData;
    private final PostData postData;
    public UsersController() throws Exception {
        this.userData = new UserData();
        this.postData = new PostData();
    }

    @GetMapping()
    public ResponseEntity getUsers(@RequestAttribute("user") UserJwtPayload user){
        try{

            User newUser = userData.get(user.getId());
            return ResponseEntity.ok(newUser);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }


    @PostMapping("/order")
    public ResponseEntity getUsers(@RequestAttribute("user") UserJwtPayload user,
                                   @RequestBody() OrderCarDto dto){
        try{
            Post post = this.postData.get(dto.getPostId());
            LocalDateTime start = LocalDateTime.now();
            LocalDateTime end = start.plusHours(dto.getHours());
            Rental rental = new Rental(post, start, end);
            User updatedUser = this.userData.order(user.getId(), rental);
            return ResponseEntity.ok(updatedUser);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }







}
