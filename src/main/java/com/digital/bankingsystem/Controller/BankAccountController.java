package com.digital.bankingsystem.Controller;

import com.digital.bankingsystem.Entity.BankAccount;
import com.digital.bankingsystem.Service.BankAccountService;
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
@RequestMapping("/api/bankaccount")
@RequiredArgsConstructor
@Tag(name = "BankAccount", description = "APIs for managing bank accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @Operation(summary = "Add a new bank account")
    @ApiResponse(responseCode = "201", description = "BankAccount created successfully")
    @PostMapping
    public ResponseEntity<BankAccount> addBankAccount(@RequestBody BankAccount bankAccount) {
        log.info("POST /api/bankaccount - Add BankAccount");
        return ResponseEntity.status(HttpStatus.CREATED).body(bankAccountService.addBankAccount(bankAccount));
    }




    @Operation(summary = "Delete a bank account by ID")
    @ApiResponse(responseCode = "200", description = "BankAccount deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBankAccount(@PathVariable Long id) {
        log.info("DELETE /api/bankaccount/{} - Delete BankAccount", id);
        return ResponseEntity.ok(bankAccountService.deleteBankAccount(id));
    }

    @Operation(summary = "Get all bank accounts")
    @ApiResponse(responseCode = "200", description = "BankAccounts fetched successfully")
    @GetMapping
    public ResponseEntity<List<BankAccount>> findAllBankAccounts() {
        log.info("GET /api/bankaccount - Find All BankAccounts");
        return ResponseEntity.ok(bankAccountService.findAllBankAccounts());
    }
}
