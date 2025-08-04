package com.descodeuses.planit.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.descodeuses.planit.dto.AuthRequest;
import com.descodeuses.planit.dto.AuthResponse;
import com.descodeuses.planit.security.JwtUtil;
import com.descodeuses.planit.service.LogDocumentService;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    private LogDocumentService logDocumentService;  

    /// l'quivalent du constructor
    public AuthController(
        AuthenticationManager authenticationManager,
        JwtUtil jwtUtil,
        LogDocumentService logDocumentService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.logDocumentService = logDocumentService; 
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
                
        String token = jwtUtil.generateToken(request.getUsername());
        LocalDateTime now = LocalDateTime.now();

        this.logDocumentService.addLog("login called: " + request.getUsername(), now);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
