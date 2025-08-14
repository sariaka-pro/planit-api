package com.descodeuses.planit.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.descodeuses.planit.dto.AuthRequest;
import com.descodeuses.planit.dto.AuthResponse;
import com.descodeuses.planit.dto.SignupRequest;
import com.descodeuses.planit.entity.DCUser;
import com.descodeuses.planit.repository.UserRepository;
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

    @Autowired
    private LogDocumentService logDocumentService;  

     @Autowired
    private UserRepository userRepository; 

    @Autowired
    private PasswordEncoder passwordEncoder; 

    /// l'équivalent du constructor
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

    @PostMapping("/sign")
    public ResponseEntity<AuthResponse> sign(@RequestBody SignupRequest request) {
    // 1. Vérifier que le username n’existe pas déjà
    if (userRepository.findByUsername(request.getUsername()).isPresent()) {
        return ResponseEntity.badRequest().build();
    }

    // 2. Créer et sauvegarder l'utilisateur
    DCUser user = new DCUser(
        request.getLastname(),
        request.getFirstname(),
        request.getGenre(),
        request.getUsername(),
        passwordEncoder.encode(request.getPassword())
    );

    userRepository.save(user);

    // 3. Générer le token JWT
    String token = jwtUtil.generateToken(request.getUsername());

    // 4. Logger
    LocalDateTime now = LocalDateTime.now();
    this.logDocumentService.addLog("Sign up success: " + request.getUsername(), now);

    return ResponseEntity.ok(new AuthResponse(token));
}
}
