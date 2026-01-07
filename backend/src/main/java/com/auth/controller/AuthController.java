package com.auth.controller;



import com.auth.dto.AuthResponse;
import com.auth.dto.LoginRequest;
import com.auth.security.JwtUtil;
import com.user.entity.User;
import com.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    
    @Autowired
    private UserRepository userRepository;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(), request.getPassword()
                        )
                );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        String token = jwtUtil.generateToken(
                request.getEmail(),
                authentication.getAuthorities().iterator().next().getAuthority()
        );

        return new AuthResponse(
        	    token,
        	    user.getEmail(),
        	    user.getRole().name(),
        	    user.getId()
        	);
    }
}

