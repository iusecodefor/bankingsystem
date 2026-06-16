package com.digital.bankingsystem.Service;


import com.digital.bankingsystem.Dto.AdminRequestDTO;
import com.digital.bankingsystem.Dto.AdminResponseDTO;
import com.digital.bankingsystem.Entity.Admin;
import com.digital.bankingsystem.Mapper.AdminMapper;
import com.digital.bankingsystem.Repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminResponseDTO addAdmin(AdminRequestDTO dto){
        log.info("Request received to add new admin with username: {}", dto.getName());
        Admin admin = AdminMapper.toEntity(dto);
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        Admin savedAdmin = adminRepository.save(admin);
        log.info("Admin created successfully with id: {}", savedAdmin.getAdminId());
        return AdminMapper.toResponse(savedAdmin);
    }

    public String deleteAdmin(Long id){
        log.info("Request received to delete admin with id: {}", id);
        Admin admin = adminRepository.findById(id).orElseThrow(()->
        {    log.error("Admin not found with id: {}", id);
           return new RuntimeException("Id Not Founded");
        });
        adminRepository.delete(admin);
        log.info("Admin deleted successfully with id: {}", id);
        return "Admin deleted Successfully";
    }

    public List<Admin> findAllAdmins(){
        log.info("Request received to find all admins");
        return adminRepository.findAll();
    }
}
