package com.example.carsharing.shared.interceptors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.HandlerInterceptor;



@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("gjgjgjgj");

        try{
            String token = request.getHeader("Authorization");
            System.out.println(token);


            // Extract the token without "Bearer "
            String accessToken = token.split(" ")[1];

            DecodedJWT payload = JWT.decode(accessToken);

            request.setAttribute("user", payload);
        } catch(Exception e){
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Jwt token is not valid");
        }




        return true;
    }
}
