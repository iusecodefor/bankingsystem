package com.digital.bankingsystem.Service;

import com.digital.bankingsystem.Entity.Customer;
import com.digital.bankingsystem.Enum.Role;
import com.digital.bankingsystem.Enum.Status;
import com.digital.bankingsystem.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public Customer addCustomer(Customer customer){
        log.info("Request received to save customer - Add Customer");
        customer.setRole(Role.CUSTOMER);
        customer.setStatus(Status.ACTIVE);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setCreatedAt(LocalDateTime.now());
        log.info("Customer saved successfully with id: {}", customer.getCustomerId());
        return customerRepository.save(customer);
    }

    public String deleteCustomer(Long id){
        log.info("Request received to delete customer - Delete Customer");
        Customer customer = customerRepository.findById(id).orElseThrow(()->
                new RuntimeException("Customer Not Found"+id));
         customerRepository.delete(customer);
         log.info("Customer deleted successfully with id: {}", id);
         return "Customer Deleted Successfully";
    }

    @Cacheable(value = "customers",  unless = "#result.isEmpty()")
    public List<Customer> FindAllCustomers(){
        log.info("Request received to find all customers");
        return customerRepository.findAll();
    }

    public Page<Customer> findByCustomersPage(int page, int size){
        Pageable pages = PageRequest.of(page, size);
        return customerRepository.findAll(pages);
    }
}
