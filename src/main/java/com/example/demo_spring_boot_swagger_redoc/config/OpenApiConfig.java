package com.example.demo_spring_boot_swagger_redoc.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                              .title("Billing Management API")
                              .version("1.0")
                              .description(
                                      "This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
                              .termsOfService("https://example.com/terms/")
                              .contact(new Contact()
                                               .name("API Support")
                                               .url("https://example.com/support")
                                               .email("support@example.com"))
                              .license(new License()
                                               .name("Apache 2.0")
                                               .url("https://springdoc.org")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                             .group("public")
                             .pathsToMatch("/api/**")
                             .build();
    }
}
