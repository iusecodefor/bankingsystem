package com.digital.bankingsystem.Controller;

import com.digital.bankingsystem.Entity.Customer;
import com.digital.bankingsystem.Service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Tag(name = "Customer", description = "APIs for managing customers")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Add a new customer")
    @ApiResponse(responseCode = "201", description = "Customer created successfully")
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        log.info("POST /api/customer - Add Customer");
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customer));
    }


    @Operation(summary = "Delete a customer by ID")
    @ApiResponse(responseCode = "200", description = "Customer deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        log.info("DELETE /api/customer/{} - Delete Customer", id);
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }

    @Operation(summary = "Get all customers")
    @ApiResponse(responseCode = "200", description = "Customers fetched successfully")
    @GetMapping
    public ResponseEntity<List<Customer>> findAllCustomers() {
        log.info("GET /api/customer - Find All Customers");
        return ResponseEntity.ok(customerService.FindAllCustomers());
    }

    @Operation(summary = "Get all customers by pages")
    @ApiResponse(responseCode = "200", description = "Customers fetched by pages wise successfully")
    @GetMapping("/page")
    public Page<Customer> findByPage(@RequestParam Integer page, @RequestParam Integer size){
        log.info("Get /api/customer/page - Find all Customers page wise ");
        return customerService.findByCustomersPage(page, size);
    }
}
