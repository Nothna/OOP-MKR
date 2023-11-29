/*
package com.example.carsharing.configs;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.POST,"/auth/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST,"/posts/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST,"/users/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/users/**").hasRole("USER")
                        .anyRequest().authenticated()

                );

        return http.build();

    }
}
*/
