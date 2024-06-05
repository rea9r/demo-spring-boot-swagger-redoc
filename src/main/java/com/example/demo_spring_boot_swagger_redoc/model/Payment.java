package com.example.demo_spring_boot_swagger_redoc.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payment details")
public record Payment(
        @Schema(description = "Unique identifier of the payment", example = "5001")
        Long id,
        @Schema(description = "Customer ID associated with the payment", example = "1")
        Long customerId,
        @Schema(description = "Invoice ID associated with the payment", example = "1001")
        Long invoiceId,
        @Schema(description = "Amount of the payment", example = "199.99")
        Double amount,
        @Schema(description = "Payment method", example = "Credit Card")
        String method,
        @Schema(description = "Payment status", example = "COMPLETED")
        String status,
        @Schema(description = "Payment date", example = "2023-12-01")
        String date
) {}