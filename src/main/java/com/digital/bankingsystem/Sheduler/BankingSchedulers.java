package com.digital.bankingsystem.Sheduler;

import com.digital.bankingsystem.Entity.BankAccount;
import com.digital.bankingsystem.Enum.AccountType;
import com.digital.bankingsystem.Enum.Status;
import com.digital.bankingsystem.Repository.BankAccountRepository;
import com.digital.bankingsystem.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BankingSchedulers {

    private final BankAccountRepository bankAccountRepository;

    @Scheduled(initialDelay = 7000, cron = "0 0 0 1 */3 *")
    public void applyMonthlyInterest(){
        log.info("Scheduler: Applying montly interest to SAVINGS accounts");
        List<BankAccount> savingAccounts = bankAccountRepository
                .findByAccountTypeAndStatus(AccountType.SAVING, Status.ACTIVE);

        for(BankAccount account: savingAccounts){
            BigDecimal interest = account.getBalance()
                    .multiply(BigDecimal.valueOf(0.02));
            account.setBalance(account.getBalance().add(interest));
            bankAccountRepository.save(account);
            log.info("Interest applied to account: {} | New Balance: {}",
                    account.getAccountNumber(), account.getBalance());
        }
        log.info("Scheduler: Quarterly interest applied to {} accounts", savingAccounts.size());
    }
}
