package com.digital.bankingsystem.Entity;

import com.digital.bankingsystem.Enum.Role;
import com.digital.bankingsystem.Enum.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String address;
    private String aadharNumber;
    private String panNumber;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;//Customer
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<BankAccount> bankAccounts;
}
