package com.example.demo_spring_boot_swagger_redoc.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Customer details")
public record Customer(
        @Schema(description = "Unique identifier of the customer", example = "1")
        Long id,
        @Schema(description = "Name of the customer", example = "John Doe")
        String name,
        @Schema(description = "Email of the customer", example = "john.doe@example.com")
        String email,
        @Schema(description = "Customer's phone number", example = "+1234567890")
        String phone,
        @Schema(description = "List of addresses associated with the customer")
        List<Address> addresses
) {}