package com.example.demo_spring_boot_swagger_redoc.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Invoice details")
public record Invoice(
        @Schema(description = "Unique identifier of the invoice", example = "1001")
        Long id,
        @Schema(description = "Customer ID associated with the invoice", example = "1")
        Long customerId,
        @Schema(description = "Amount of the invoice", example = "199.99")
        Double amount,
        @Schema(description = "Status of the invoice", example = "PAID")
        String status,
        @Schema(description = "Due date of the invoice", example = "2023-12-31")
        String dueDate,
        @Schema(description = "Description of the invoice", example = "Monthly subscription fee")
        String description
) {}