package com.mkendc.ticketingsystem.config;

import com.mkendc.ticketingsystem.entity.User;
import com.mkendc.ticketingsystem.enums.Role;
import com.mkendc.ticketingsystem.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;

    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {

        if (userService.findByEmail("admin@test.com") == null) {

            User admin = new User();

            admin.setFullName("System Administrator");
            admin.setEmail("admin@test.com");
            admin.setPassword("admin123");
            admin.setRole(Role.ADMIN);

            userService.saveUser(admin);

            System.out.println("Admin user created!");

        } else {

            System.out.println("Admin user already exists!");

        }
    }
}