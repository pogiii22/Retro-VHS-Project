package com.example.demo.rest;

import com.example.demo.domain.Rental;
import com.example.demo.service.RentalService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private static Logger log = LoggerFactory.getLogger(RentalController.class);
    @Autowired
    RentalService rentalService;

    @GetMapping("")
    public List<Rental> listRentals(HttpServletRequest request){
        log.info( "[CONTROLLER] GET /api/rentals - request {}",  request.getRemoteAddr());
        return rentalService.listAll();
    }

    @PostMapping("makerent")
    public ResponseEntity<Rental> makeRental(@Valid @RequestBody RentalDTO rentalDTO, HttpServletRequest request){
        log.info("[CONTROLLER] POST /api/rentals/makeretn - request from {}", rentalDTO, request.getRemoteAddr());
        Rental saved = rentalService.createRental(rentalDTO);
        log.info("[CONTROLLER] Successfully created Rental {} (HTTP {})",saved, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("return")
    public ResponseEntity<Rental> returnRental(@Valid @RequestBody RentalDTO rentalDTO, HttpServletRequest request) {
        log.info("[CONTROLLER] POST /api/rentals/return - RequestBody: {} request from {}", rentalDTO, request.getRemoteAddr());
        Rental ret = rentalService.returnRental(rentalDTO);
        log.info("[CONTROLLER] Successfully returned Rental {} (HTTP {})",ret, HttpStatus.CREATED.value());
        return ResponseEntity.ok(ret);
    }

    @GetMapping("active")
    public ResponseEntity<List<Rental>> returnRental(@RequestParam String email, HttpServletRequest request){
        log.info("[CONTROLLER] POST /api/rentals/active - RequestBody: {} request from {}", email, request.getRemoteAddr());
        List<Rental> rentals = rentalService.getActiveRentals(email);
        log.info("[CONTROLLER] Successfully returned active  Rentals (HTTP {})", HttpStatus.OK.value());
        return ResponseEntity.ok(rentals);


    }




}
