package com.bank.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
      @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info().title("Banking API")
                .description("REST API for Banking Operations"));
    }
}
