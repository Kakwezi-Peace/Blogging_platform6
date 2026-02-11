package com.example.Blogging_platform2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // allow all endpoints
                .allowedOrigins("*") // allow all origins (frontend apps)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // allowed HTTP methods
                .allowedHeaders("*") // allow all headers
                .maxAge(3600); // cache preflight response for 1 hour
    }
}
