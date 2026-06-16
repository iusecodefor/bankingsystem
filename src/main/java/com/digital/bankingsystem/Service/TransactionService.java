package com.digital.bankingsystem.Service;

import com.digital.bankingsystem.Entity.BankAccount;
import com.digital.bankingsystem.Entity.Transactions;
import com.digital.bankingsystem.Enum.Status;
import com.digital.bankingsystem.Repository.BankAccountRepository;
import com.digital.bankingsystem.Repository.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;

    @Transactional
    public Transactions addTransaction(Transactions transaction) {
        log.info("Adding transaction");

        if (transaction.getBankAccount() == null || transaction.getBankAccount().getAccountId() == null) {
            throw new RuntimeException("BankAccount or AccountId must not be null");
        }
        BankAccount bankAccount = bankAccountRepository.findById(transaction.getBankAccount().getAccountId())
                .orElseThrow(() -> new RuntimeException("BankAccount not found"));

        BankAccount senderAccount = bankAccountRepository.findByAccountNumber(transaction.getSenderAccountNumber())
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        BankAccount receiverAccount = bankAccountRepository.findByAccountNumber(transaction.getReceiverAccountNumber())
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        senderAccount.setBalance(senderAccount.getBalance().subtract(transaction.getAmount()));
        bankAccountRepository.save(senderAccount);

        receiverAccount.setBalance(receiverAccount.getBalance().add(transaction.getAmount()));
        bankAccountRepository.save(receiverAccount);

        if (senderAccount.getBalance().compareTo(transaction.getAmount()) < 0) {
            transaction.setStatus(Status.FAILED);
            transaction.setTransactionReference(UUID.randomUUID().toString());
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setBankAccount(bankAccount);
            transactionRepository.save(transaction);
            throw new RuntimeException("Insufficient balance");
        }

        transaction.setTransactionReference(UUID.randomUUID().toString());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus(Status.SUCCESS);
        transaction.setBankAccount(bankAccount);

        Transactions savedTransaction = transactionRepository.save(transaction);
        log.info("Transaction saved successfully with id: {}", savedTransaction.getTransactionId());
        return savedTransaction;
    }

    @Transactional
    public String deleteTransaction(Long id) {
        log.info("Deleting transaction with id: {}", id);
        Transactions transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
        transactionRepository.delete(transaction);
        log.info("Transaction deleted successfully with id: {}", id);
        return "Transaction deleted successfully";
    }


    public List<Transactions> findAllTransactions() {
        log.info("Fetching all transactions");
        return transactionRepository.findAll();
    }
}
