package com.example.demo.service.impl;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.rest.UserDTO;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository UserRepo;

    @Override
    public List<User> listAll() {
        return UserRepo.findAll();
    }

    @Override
    public User createUser(UserDTO userDTO) {
        boolean alreadyExists = UserRepo.existsByNameOrEmail(userDTO.getName(), userDTO.getEmail());
        if (alreadyExists) {
            throw new DuplicateResourceException("User with name "
                    + userDTO.getName() + " or email "
                    + userDTO.getEmail() + " already exists!");
        }

        User user = new User(userDTO.getName(), userDTO.getEmail());
        return UserRepo.save(user);
    }
}
