package com.hcl.favourite.service;

import com.hcl.favourite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Simple user service for demo purposes
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isValidUser(Long userId) {
        return userRepository.existsById(userId);
    }
}
