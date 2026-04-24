package com.company.franchise.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.*;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Franchise API",
        version = "1.0.0",
        description = """
            API for managing franchises, branches, and products.
            
            This API follows Clean Architecture principles:
            - Domain-driven design
            - Hexagonal architecture (Ports & Adapters)
            - Separation of concerns
            
            Features:
            - Create franchises
            - Manage branches
            - Manage products and stock
            - Retrieve top stock products per franchise
            """
    )
)
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class OpenApiConfig {
}