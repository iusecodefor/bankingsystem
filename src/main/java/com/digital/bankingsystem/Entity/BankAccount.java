package com.digital.bankingsystem.Entity;

import com.digital.bankingsystem.Enum.AccountType;
import com.digital.bankingsystem.Enum.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal balance;
    private String branchName;
    private String ifscCode;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createdAt;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "bankAccount")
    private List<Transactions> transactions;
}
