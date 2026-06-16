package com.digital.bankingsystem.Controller;


import com.digital.bankingsystem.Entity.Transactions;
import com.digital.bankingsystem.Service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
@Tag(name = "Transaction", description = "APIs for managing transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Add a new transaction")
    @ApiResponse(responseCode = "201", description = "Transaction created successfully")
    @PostMapping
    public ResponseEntity<Transactions> addTransaction(@RequestBody Transactions transaction) {
        log.info("POST /api/transaction - Add Transaction");
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.addTransaction(transaction));
    }

    @Operation(summary = "Delete a transaction by ID")
    @ApiResponse(responseCode = "200", description = "Transaction deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id) {
        log.info("DELETE /api/transaction/{} - Delete Transaction", id);
        return ResponseEntity.ok(transactionService.deleteTransaction(id));
    }

    @Operation(summary = "Get all transactions")
    @ApiResponse(responseCode = "200", description = "Transactions fetched successfully")
    @GetMapping
    public ResponseEntity<List<Transactions>> findAllTransactions() {
        log.info("GET /api/transaction - Find All Transactions");
        return ResponseEntity.ok(transactionService.findAllTransactions());
    }
}