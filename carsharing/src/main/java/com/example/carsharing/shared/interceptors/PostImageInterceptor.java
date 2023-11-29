package com.example.carsharing.shared.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class PostImageInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uploadDirectory = System.getProperty("user.dir") + System.getProperty("file.separator") + "static" + System.getProperty("file.separator") + "image";

        try{
            if (request instanceof MultipartHttpServletRequest) {
                MultipartFile file = ((MultipartHttpServletRequest) request).getFile("image");

                if (file != null && !file.isEmpty()) {

                    File directory = new File(uploadDirectory);
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                    // Save the file to the directory
                    String extension = file.getOriginalFilename().split("\\.")[1];
                    if (!extension.equals("jpg") && !extension.equals("png") && !extension.equals("jpeg")){
                        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Incorrect file extension");
                    }
                    String fileName = UUID.randomUUID() + "." + extension;
                    Path filePath = Paths.get(uploadDirectory, fileName);

                    Files.write(filePath, file.getBytes());
                    request.setAttribute("image", fileName);
                    return true;
                }
            }
        } catch (Exception e){
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

        }


        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "No image provided");

    }
}
