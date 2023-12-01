package com.example.carsharing.configs;

import com.example.carsharing.shared.interceptors.JwtInterceptor;
import com.example.carsharing.shared.interceptors.PostImageInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/users/**");
        registry.addInterceptor(new PostImageInterceptor())
                .addPathPatterns("/posts/create");
    }
}
