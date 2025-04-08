package com.sagar.gym.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    // For demonstration purposes, using hard-coded credentials.
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if ("gymuser".equals(loginRequest.getUsername()) && "gympass".equals(loginRequest.getPassword())) {
            List<String> roles = Arrays.asList("USER");
            String token = jwtUtil.generateToken(loginRequest.getUsername(), roles);
            return ResponseEntity.ok(new JwtResponse(token));
        } else if ("admin".equals(loginRequest.getUsername()) && "adminpass".equals(loginRequest.getPassword())) {
            List<String> roles = Arrays.asList("ADMIN", "USER");
            String token = jwtUtil.generateToken(loginRequest.getUsername(), roles);
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Invalid credentials");
        }
    }
}
