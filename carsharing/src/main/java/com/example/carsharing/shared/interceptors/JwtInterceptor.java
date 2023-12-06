package com.example.carsharing.shared.interceptors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.carsharing.shared.UserJwtPayload;
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

        try{
            System.out.println(request.getMethod());
            if(request.getMethod().equals("OPTIONS")) return true;
            System.out.println(request.getHeader("Authorization"));
            String token = request.getHeader("Authorization");

            // Extract the token without "Bearer "
            String accessToken = token.split(" ")[1];

            DecodedJWT payload = JWT.decode(accessToken);
            long userId = payload.getClaim("id").asLong();
            String email = payload.getClaim("email").asString();
            UserJwtPayload userJwtPayload = new UserJwtPayload(userId, email);

            request.setAttribute("user", userJwtPayload);
        } catch(Exception e){
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Jwt token is not valid");
        }




        return true;
    }
}
