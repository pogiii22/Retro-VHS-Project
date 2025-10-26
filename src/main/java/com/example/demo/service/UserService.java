package com.example.demo.service;

import com.example.demo.domain.Rental;
import com.example.demo.domain.User;
import com.example.demo.rest.UserDTO;
import com.example.demo.rest.UserEmailDTO;

import java.util.List;

public interface UserService {
    List<User> listAll();
    User createUser(UserDTO userDTO);
    User findByEmail(String email);
    void saveUser(User user);
    String payFee(UserEmailDTO userDTO);
}
