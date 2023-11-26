package com.example.carsharing.Auth;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.carsharing.dataWriter.UserData;
import jakarta.annotation.PostConstruct;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;

public class AuthService{

    String secret = "Somesecretkeyherenothingweryinterestingthough";
    Algorithm algorithm;
    JSONParser parser;
    UserData userData;

    JWTVerifier verifier;
    public AuthService() throws Exception{
        this.parser = new JSONParser();
        this.userData = new UserData();
        this.algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algorithm).build();
    }




    public UserJwtPayload verifyUser(String token) throws Exception {
        DecodedJWT decoded = verifier.verify(token);
        Long id = decoded.getClaim("id").asLong();
        String email = decoded.getClaim("email").asString();



        if (id != null && email != null) {
            UserJwtPayload userJwtPayload = new UserJwtPayload(id, email);
            return userJwtPayload;
        }
        throw new Exception("User not found");
    }
}