package com.example.carsharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CarsharingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarsharingApplication.class, args);
    }

}
