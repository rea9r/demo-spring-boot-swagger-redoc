package com.example.demo_spring_boot_swagger_redoc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_spring_boot_swagger_redoc.model.Payment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payments", description = "Payment management API")
public class PaymentController {

    private List<Payment> payments = new ArrayList<>();

    @Operation(
            summary = "Get all payments",
            description = """
                    Returns a list of all payments.

                    Sample curl command:
                    ```sh
                    curl -X GET "http://localhost:8080/api/payments" \\
                         -H "accept: application/json"
                    ```
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Payment.class))
                    }),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @GetMapping
    public List<Payment> getAllPayments() {
        return payments;
    }

    @Operation(
            summary = "Add a new payment",
            description = """
                    Creates a new payment.

                    Sample curl command:
                    ```sh
                    curl -X POST "http://localhost:8080/api/payments" \\
                         -H "accept: application/json" \\
                         -H "Content-Type: application/json" \\
                         -d '{
                             "customerId":1,
                             "invoiceId":1001,
                             "amount":199.99,
                             "method":"Credit Card",
                             "status":"COMPLETED",
                             "date":"2023-12-01"
                         }'
                    ```
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Payment.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "409", description = "Payment already exists", content = @Content)
    })
    @PostMapping
    public Payment addPayment(
            @RequestBody(description = "Payment to be added", required = true,
                    content = @Content(schema = @Schema(implementation = Payment.class)))
            @org.springframework.web.bind.annotation.RequestBody Payment payment) {
        payments.add(payment);
        return payment;
    }

    @Operation(
            summary = "Get payments by customer ID",
            description = """
                    Returns a list of all payments associated with a specific customer.

                    Sample curl command:
                    ```sh
                    curl -X GET "http://localhost:8080/api/customers/1/payments" \\
                         -H "accept: application/json"
                    ```
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Payment.class))
                    }),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @GetMapping("/customers/{customerId}")
    public List<Payment> getPaymentsByCustomerId(
            @Schema(description = "ID of the customer to retrieve payments for", example = "1")
            @PathVariable Long customerId) {
        List<Payment> result = new ArrayList<>();
        for (Payment payment : payments) {
            if (payment.customerId().equals(customerId)) {
                result.add(payment);
            }
        }
        return result;
    }
}

