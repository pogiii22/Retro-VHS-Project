package com.example.demo.rest;

import com.example.demo.domain.User;
import com.example.demo.domain.VHS;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> listAllUsers(){
        return userService.listAll();
    }

    @PostMapping("adduser")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO){
        User saved = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
