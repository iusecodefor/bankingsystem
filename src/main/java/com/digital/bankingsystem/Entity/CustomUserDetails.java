package com.digital.bankingsystem.Entity;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String email;
    private String password;
    private String role;

    public CustomUserDetails(String email, String password, String role){
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static CustomUserDetails fromCustomer(Customer customer){
        return new CustomUserDetails(customer.getEmail(),
                customer.getPassword(),
                customer.getRole().name());
    }
    public static CustomUserDetails fromAdmin(Admin admin){
        return new CustomUserDetails(
                admin.getEmail(),
                admin.getPassword(),
                admin.getRole().name()
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role));
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
