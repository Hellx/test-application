package com.example.testapplication.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @Bean
    public OpenAPI getOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Sectors API")
                                .description("Sectors API")
                                .version("1.0")
                                .contact(
                                        new io.swagger.v3.oas.models.info.Contact()
                                                .name("Hellx")
                                )
                );
    }

    private String getContextPath() {
        return "default".equals(activeProfile) ? "/" : "/rest/sectors-api";
    }
}

