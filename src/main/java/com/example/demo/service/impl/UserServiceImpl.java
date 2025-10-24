package com.example.demo.service.impl;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.rest.UserDTO;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> listAll() {
        log.info("[SERVICE] Listed all users");
        return userRepo.findAll();
    }

    @Override
    public User createUser(UserDTO userDTO) {
        boolean alreadyExists = userRepo.existsByEmail(userDTO.getEmail());
        if (alreadyExists) {
            throw new DuplicateResourceException("User with email "
                    + userDTO.getEmail() + " already exists!");
        }

        User user = new User(userDTO.getName(), userDTO.getEmail());
        log.info("[SERVICE] created new user; name= {} email= {}", user.getName(), user.getEmail());
        return userRepo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        log.info("[SERVICE] searched for user; email= {}", email);
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found! Please register first!"));
    }
}
