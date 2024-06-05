package com.example.demo_spring_boot_swagger_redoc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_spring_boot_swagger_redoc.model.Invoice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/invoices")
@Tag(name = "Invoices", description = "Invoice management API")
public class InvoiceController {

    private List<Invoice> invoices = new ArrayList<>();

    @Operation(
            summary = "Get all invoices",
            description = """
                    Returns a list of all invoices.

                    Sample curl command:
                    ```sh
                    curl -X GET "http://localhost:8080/api/invoices" \\
                         -H "accept: application/json"
                    ```
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Invoice.class))
                    }),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoices;
    }

    @Operation(
            summary = "Add a new invoice",
            description = """
                    Creates a new invoice.

                    Sample curl command:
                    ```sh
                    curl -X POST "http://localhost:8080/api/invoices" \\
                         -H "accept: application/json" \\
                         -H "Content-Type: application/json" \\
                         -d '{
                             "customerId":1,
                             "amount":199.99,
                             "status":"PAID",
                             "dueDate":"2023-12-31",
                             "description":"Monthly subscription fee"
                         }'
                    ```
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Invoice created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Invoice.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "409", description = "Invoice already exists", content = @Content)
    })
    @PostMapping
    public Invoice addInvoice(
            @RequestBody(description = "Invoice to be added", required = true,
                    content = @Content(schema = @Schema(implementation = Invoice.class)))
            @org.springframework.web.bind.annotation.RequestBody Invoice invoice) {
        invoices.add(invoice);
        return invoice;
    }

    @Operation(
            summary = "Get invoices by customer ID",
            description = """
                    Returns a list of all invoices associated with a specific customer.

                    Sample curl command:
                    ```sh
                    curl -X GET "http://localhost:8080/api/customers/1/invoices" \\
                         -H "accept: application/json"
                    ```
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Invoice.class))
                    }),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @GetMapping("/customers/{customerId}")
    public List<Invoice> getInvoicesByCustomerId(
            @Schema(description = "ID of the customer to retrieve invoices for", example = "1")
            @PathVariable Long customerId) {
        List<Invoice> result = new ArrayList<>();
        for (Invoice invoice : invoices) {
            if (invoice.customerId().equals(customerId)) {
                result.add(invoice);
            }
        }
        return result;
    }
}

