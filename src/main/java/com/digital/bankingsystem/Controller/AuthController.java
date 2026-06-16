package com.digital.bankingsystem.Controller;

import com.digital.bankingsystem.Dto.AuthRequest;
import com.digital.bankingsystem.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest){
        return service.login(authRequest);
    }
}
