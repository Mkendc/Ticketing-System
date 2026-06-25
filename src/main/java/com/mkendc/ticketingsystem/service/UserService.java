package com.mkendc.ticketingsystem.service;

import com.mkendc.ticketingsystem.entity.User;
import com.mkendc.ticketingsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}