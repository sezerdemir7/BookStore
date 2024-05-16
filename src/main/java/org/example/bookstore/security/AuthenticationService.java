package org.example.bookstore.security;

import lombok.extern.slf4j.Slf4j;

import org.example.bookstore.dto.request.AuthRequest;
import org.example.bookstore.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    public String generateToken(AuthRequest request){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(request.getUsername());
        }

        log.info("invalid username" + request.getUsername());
        throw new UsernameNotFoundException("invalid username {} "+request.getUsername());
    }
}