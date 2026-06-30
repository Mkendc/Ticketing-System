package com.mkendc.ticketingsystem.dto;

import com.mkendc.ticketingsystem.enums.Role;

public class LoginResponse {

    private String message;
    private String email;
    private Role role;

    public LoginResponse(String message, String email, Role role) {
        this.message = message;
        this.email = email;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}