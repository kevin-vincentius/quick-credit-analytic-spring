package com.bcaf.tugasakhir.QUCA_Spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Allow all endpoints
                .allowedOrigins("http://localhost:4200") // Allow any localhost subdomain with any port
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow these HTTP methods
                .allowedHeaders("Content-Type", "Authorization", "X-Session") // Allow these headers
                .exposedHeaders("X-Session") // ✅ This allows frontend to read it
                .allowCredentials(true); // Allow credentials (cookies, etc.)
    }
}

