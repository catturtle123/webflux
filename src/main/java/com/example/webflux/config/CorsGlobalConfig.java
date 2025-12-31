package com.example.webflux.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

// Global CORS configuration for WebFlux application
@Configuration
public class CorsGlobalConfig implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3334")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
