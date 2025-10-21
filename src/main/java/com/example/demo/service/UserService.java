package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.rest.UserDTO;

import java.util.List;

public interface UserService {
    List<User> listAll();
    User createUser(UserDTO userDTO);
}
