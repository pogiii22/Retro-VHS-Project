package com.example.demo.rest;

import com.example.demo.domain.Rental;
import com.example.demo.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
