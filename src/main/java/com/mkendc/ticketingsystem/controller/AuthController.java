package com.mkendc.ticketingsystem.controller;

import com.mkendc.ticketingsystem.dto.LoginRequest;
import com.mkendc.ticketingsystem.dto.LoginResponse;
import com.mkendc.ticketingsystem.enums.Role;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = new LoginResponse(
                "Login successful",
                request.getEmail(),
                Role.ADMIN
        );

        return ResponseEntity.ok(response);
    }
}
