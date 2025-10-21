package com.example.demo.service.impl;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.rest.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> listAll() {
        return userRepo.findAll();
    }

    @Override
    public User createUser(UserDTO userDTO) {
        boolean alreadyExists = userRepo.existsByNameOrEmail(userDTO.getName(), userDTO.getEmail());
        if (alreadyExists) {
            throw new DuplicateResourceException("User with name "
                    + userDTO.getName() + " or email "
                    + userDTO.getEmail() + " already exists!");
        }

        User user = new User(userDTO.getName(), userDTO.getEmail());
        return userRepo.save(user);
    }

    @Override
    public User findByNameAndEmail(String name, String email) {
        return userRepo.findByNameAndEmail(name, email)
                .orElseThrow(() -> new ResourceNotFoundException("User with name " + name +" and email " + email + " not found! Please register first!"));
    }
}
