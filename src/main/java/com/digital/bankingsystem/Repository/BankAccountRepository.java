package com.digital.bankingsystem.Repository;

import com.digital.bankingsystem.Entity.BankAccount;
import com.digital.bankingsystem.Enum.AccountType;
import com.digital.bankingsystem.Enum.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findByAccountNumber(String senderAccountNumber);

    List<BankAccount> findByAccountTypeAndStatus(AccountType accountType, Status status);
}
