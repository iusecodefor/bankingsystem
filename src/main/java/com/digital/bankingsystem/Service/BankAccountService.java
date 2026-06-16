package com.digital.bankingsystem.Service;

import com.digital.bankingsystem.Entity.BankAccount;
import com.digital.bankingsystem.Entity.Customer;
import com.digital.bankingsystem.Enum.Status;
import com.digital.bankingsystem.Repository.BankAccountRepository;
import com.digital.bankingsystem.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequestMapping("")
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;

    public BankAccount addBankAccount(BankAccount bankAccount) {
        log.info("Adding bank account for customerId: {}", bankAccount.getCustomer().getCustomerId());
        Customer customer = customerRepository.findById(bankAccount.getCustomer().getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        bankAccount.setCustomer(customer);
        bankAccount.setStatus(Status.ACTIVE);
        bankAccount.setCreatedAt(LocalDateTime.now());
        BankAccount savedAccount = bankAccountRepository.save(bankAccount);
        log.info("BankAccount saved successfully with id: {}", savedAccount.getAccountId());
        return savedAccount;
    }

    public String deleteBankAccount(Long id) {
        log.info("Deleting bank account with id: {}", id);
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BankAccount not found with id: " + id));
        bankAccountRepository.delete(bankAccount);
        log.info("BankAccount deleted successfully with id: {}", id);
        return "BankAccount deleted successfully";
    }

    public List<BankAccount> findAllBankAccounts() {
        log.info("Fetching all bank accounts");
        return bankAccountRepository.findAll();
    }
}
