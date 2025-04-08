package com.sagar.member.security;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Login attempt:");
        System.out.println("Username: " + loginRequest.getUsername());
        System.out.println("Password: " + loginRequest.getPassword());

        if ("memberuser".equals(loginRequest.getUsername()) && "memberpass".equals(loginRequest.getPassword())) {
            List<String> roles = Arrays.asList("USER");
            String token = jwtUtil.generateToken(loginRequest.getUsername(), roles);
            System.out.println("Token generated: " + token);
            return ResponseEntity.ok(new JwtResponse(token));
        } else if ("admin".equals(loginRequest.getUsername()) && "adminpass".equals(loginRequest.getPassword())) {
            List<String> roles = Arrays.asList("ADMIN", "USER");
            String token = jwtUtil.generateToken(loginRequest.getUsername(), roles);
            System.out.println("Token generated: " + token);
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            System.out.println("Invalid credentials.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
