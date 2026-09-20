package com.nexus.commerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI nexusCommerceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("NexusCommerce Enterprise API")
                        .description("Production-Grade E-Commerce Backend Platform built with Spring Boot 3 and Java 21")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Nexus Engineering")
                                .email("dev@nexuscommerce.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://spring.io")));
    }
}
