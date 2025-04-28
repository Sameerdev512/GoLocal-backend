package com.company.backend.service;

import com.company.backend.config.JwtUtil;
import com.company.backend.dto.AuthRequest;
import com.company.backend.dto.RegisterRequest;
import com.company.backend.entity.User;
import com.company.backend.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public String register(RegisterRequest request) {


        if(userRepository.findByUsername(request.getUsername()).isPresent())
        {
            return "User Already Exists with Email!";
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        userRepository.save(user);

        //passing registration success message to frontend
        return "User registered successfully!";
    }

    public ResponseEntity<Map<String,String>> authenticate(AuthRequest request) {
        try {
            //authenticating the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            System.out.println("Authentication done");


        }catch(Exception e)
        {
            System.out.println(e.getMessage());

            //Passing Bad Credentials message
            Map<String,String> response = new HashMap<>();
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        //fetching user fromDB
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return jwtUtil.generateToken(user);
    }
}
