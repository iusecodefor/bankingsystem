package com.digital.bankingsystem.Mapper;

import com.digital.bankingsystem.Dto.AdminRequestDTO;
import com.digital.bankingsystem.Dto.AdminResponseDTO;
import com.digital.bankingsystem.Entity.Admin;
import com.digital.bankingsystem.Enum.Role;

import java.time.LocalDateTime;

public class AdminMapper {

    public static Admin toEntity(AdminRequestDTO dto){
        Admin admin = new Admin();
        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        admin.setRole(Role.ADMIN);
        admin.setCreatedAt(LocalDateTime.now());
        return admin;
    }

    public static AdminResponseDTO toResponse(Admin admin){
        AdminResponseDTO dto = new AdminResponseDTO();
        dto.setId(admin.getAdminId());
        dto.setName(admin.getName());
        dto.setEmail(admin.getEmail());
        dto.setRole(admin.getRole());
        dto.setCreatedAt(admin.getCreatedAt());
        return dto;
    }
}
