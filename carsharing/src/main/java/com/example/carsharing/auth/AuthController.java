package com.example.carsharing.auth;

import com.example.carsharing.dataWriter.UserData;
import com.example.carsharing.dto.CreateUserDto;
import com.example.carsharing.userModule.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@RestController
@RequestMapping("/auth")
public class AuthController {
    String secret = "Somesecretkeyherenothingweryinterestingthough";
    Algorithm algorithm;

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC256(secret);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody() CreateUserDto user){
        try{
            UserData userData = new UserData();
            User createdUser = userData.create(user);
            String token = JWT.create()
                    .withClaim("id", createdUser.getId())
                    .withClaim("email", createdUser.getEmail())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 12*60*60*1000)) // 12 hour expiration
                    .sign(algorithm);
            return ResponseEntity.ok().body(token);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: Incorrect register credentials");
        }
    }

}