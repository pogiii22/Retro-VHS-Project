package com.example.demo.rest;

import com.example.demo.domain.Rental;
import com.example.demo.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Autowired
    RentalService rentalService;

    @GetMapping("")
    public List<Rental> listRentals(){
        return rentalService.listAll();
    }

    @PostMapping("makerent")
    public ResponseEntity<Rental> makeRental(@Valid @RequestBody RentalDTO rentalDTO){
        Rental saved = rentalService.createRental(rentalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
