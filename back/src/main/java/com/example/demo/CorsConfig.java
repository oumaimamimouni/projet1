package com.example.demo;

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
                registry.addMapping("/**")  // Permet à toutes les routes de ton API d'accepter les requêtes CORS
                        .allowedOrigins("http://localhost:4200")  // Autoriser ton frontend Angular (à adapter si nécessaire)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Méthodes HTTP autorisées
                        .allowedHeaders("*")  // Accepter tous les en-têtes
                        .allowCredentials(true);  // Permet l'envoi des cookies si nécessaire
            }
        };
    }
}
