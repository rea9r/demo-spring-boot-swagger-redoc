package com.example.demo_spring_boot_swagger_redoc.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Address details")
public record Address(
        @Schema(description = "Street address", example = "123 Main St")
        String street,
        @Schema(description = "City", example = "Springfield")
        String city,
        @Schema(description = "State", example = "IL")
        String state,
        @Schema(description = "Postal code", example = "62701")
        String postalCode,
        @Schema(description = "Country", example = "USA")
        String country
) {}