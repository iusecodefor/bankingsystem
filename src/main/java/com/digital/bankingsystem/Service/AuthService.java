package com.digital.bankingsystem.Service;

import com.digital.bankingsystem.Dto.AuthRequest;
import com.digital.bankingsystem.JwtService.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final UserDetailServiceImp userDetailServiceImp;
    private final JwtService jwtService;

    public String login(AuthRequest request){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        if(authentication.isAuthenticated()){
            return  jwtService.generateToken(request.getEmail());
        }
        return "fails";
    }
}
