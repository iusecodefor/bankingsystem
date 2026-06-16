package com.digital.bankingsystem.Service;

import com.digital.bankingsystem.Entity.Admin;
import com.digital.bankingsystem.Entity.CustomUserDetails;
import com.digital.bankingsystem.Entity.Customer;
import com.digital.bankingsystem.Repository.AdminRepository;
import com.digital.bankingsystem.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImp implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private  final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if(customer.isPresent()){
            return CustomUserDetails.fromCustomer(customer.get());
        }

        Optional<Admin> admin = adminRepository.findByEmail(email);
        if(admin.isPresent()){
            System.out.println(admin.get().toString());
            return CustomUserDetails.fromAdmin(admin.get());
        }

       throw new UsernameNotFoundException("User not found with this email: "+email);
    }
}
