package com.mkendc.ticketingsystem.controller;

import com.mkendc.ticketingsystem.dto.LoginRequest;
import com.mkendc.ticketingsystem.dto.LoginResponse;
import com.mkendc.ticketingsystem.entity.User;
import com.mkendc.ticketingsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        User user = userService.findByEmail(request.getEmail());

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            return ResponseEntity.status(401).build();
        }

        LoginResponse response = new LoginResponse(
                "Login successful",
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(response);
    }
}