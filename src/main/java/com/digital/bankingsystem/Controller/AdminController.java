package com.digital.bankingsystem.Controller;

import com.digital.bankingsystem.Dto.AdminRequestDTO;
import com.digital.bankingsystem.Dto.AdminResponseDTO;
import com.digital.bankingsystem.Entity.Admin;
import com.digital.bankingsystem.Service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "APIs for managing admins")
public class AdminController {

    private final AdminService adminService;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Admin created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AdminResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request body",
                    content = @Content(schema = @Schema(hidden = true))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied",
                    content = @Content(schema = @Schema(hidden = true))
            )
    })
    @Operation(
            summary = "Add a new admin",
            description = "Creates a new admin and returns the created admin details"
    )
    @PostMapping
    public ResponseEntity<AdminResponseDTO> addAdmin(@RequestBody AdminRequestDTO dto){
        log.info("POST /api/admin - Adding new admin");
        AdminResponseDTO adminResponseDTO = adminService.addAdmin(dto);
        log.info("POST /api/admin - Admin added successfully with id: {}", adminResponseDTO.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(adminResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an admin", description = "Deletes an admin by ID")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        log.info("DELETE /api/admins/{} - Deleting admin", id);
        String message = adminService.deleteAdmin(id);
        log.info("DELETE /api/admins/{} - Admin deleted successfully", id);
        return ResponseEntity.ok(message);
    }

    @GetMapping
    @Operation(summary = "Get All Admins", description = "They Returned List of Admins")
    public List<Admin> findAllAdmins(){
        log.info("Get /api/admin/ - Get All Admins");
        return adminService.findAllAdmins();
    }








}
