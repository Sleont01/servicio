/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.SLeonProgramacionNCapas.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author digis
 */
@Configuration
public class CorsConfig {
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permite todas las rutas
                        .allowedOrigins("http://localhost:8081") // Orígenes permitidos
                        .allowedMethods("GET", "POST", "PUT","PATCH", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                        .allowedHeaders("*") // Permite todos los encabezados
                        .allowCredentials(true); // Permite el uso de credenciales
            }
        };
    }
    
//    @Bean // terminar una configuración personalizada
//    public CorsFilter corsFilter(){
//        
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration  corsConfig = new CorsConfiguration();
//        
//        corsConfig.addAllowedOrigin("*");
//        
//        corsConfig.addAllowedMethod("GET");
//        corsConfig.addAllowedMethod("POST");
//        corsConfig.addAllowedMethod("PUT");
//        corsConfig.addAllowedMethod("DELETE");
//        corsConfig.addAllowedMethod("PATCH");
//        
//        corsConfig.addAllowedHeader("*");
//        
//        source.registerCorsConfiguration("/**", corsConfig);
//        
//        return new CorsFilter(source);
//    }
    
    
    
}
