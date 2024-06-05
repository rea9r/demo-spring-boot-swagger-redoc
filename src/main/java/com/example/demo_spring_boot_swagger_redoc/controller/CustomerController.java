package com.example.demo_spring_boot_swagger_redoc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_spring_boot_swagger_redoc.model.Customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customers", description = "Customer management API")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    @Operation(
            summary = "Get all customers",
            description = """
                    Returns a list of all registered customers.

                    Sample curl command:
                    ```sh
                    curl -X GET "http://localhost:8080/api/customers" \\
                         -H "accept: application/json"
                    ```
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Customer.class))
                    }),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @Operation(
            summary = "Add a new customer",
            description = """
                    Creates a new customer.

                    Sample curl command:
                    ```sh
                    curl -X POST "http://localhost:8080/api/customers" \\
                         -H "accept: application/json" \\
                         -H "Content-Type: application/json" \\
                         -d '{
                             "name":"Taro Yamada",
                             "email":"taro.yamada@example.com",
                             "phone":"+819012345678",
                             "addresses":[
                                 {
                                     "street":"123 Main St",
                                     "city":"Tokyo",
                                     "state":"Tokyo",
                                     "postalCode":"100-0001",
                                     "country":"Japan"
                                 }
                             ]
                         }'
                    ```
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Customer.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "409", description = "Customer already exists", content = @Content)
    })
    @PostMapping
    public Customer addCustomer(
            @RequestBody(description = "Customer to be added", required = true,
                    content = @Content(schema = @Schema(implementation = Customer.class)))
            @org.springframework.web.bind.annotation.RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }
}

