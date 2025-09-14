package com.pearl.warehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")      // allow all endpoints
                        .allowedOrigins("*")    // allow all origins
                        .allowedMethods("*")    // allow all HTTP methods (GET, POST, etc.)
                        .allowedHeaders("*");   // allow all headers
            }
        };
    }
}
