package com.example.demo.rest;

import com.example.demo.domain.User;
import com.example.demo.domain.VHS;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    public List<User> listAllUsers(HttpServletRequest request){
        log.info( "[CONTROLLER] GET /api/users - request {}",  request.getRemoteAddr());
        return userService.listAll();
    }

    @PostMapping("adduser")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO, HttpServletRequest request){
        log.info("[CONTROLLER] POST /api/users/adduser -RequestBody: {} request from {}", userDTO, request.getRemoteAddr());
        User saved = userService.createUser(userDTO);
        log.info("[CONTROLLER] Successfully created User {} (HTTP {})",saved, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("pay")
    public ResponseEntity<String> payFee(@Valid @RequestBody UserEmailDTO userEmailDTO, HttpServletRequest request){
        log.info("[CONTROLLER] POST /api/users/pay -RequestBody: {} request from {}", userEmailDTO, request.getRemoteAddr());
        String msg = userService.payFee(userEmailDTO);
        log.info("[CONTROLLER] Successfully payed Fee - {} (HTTP {})",userEmailDTO.getEmail(), HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }
}
