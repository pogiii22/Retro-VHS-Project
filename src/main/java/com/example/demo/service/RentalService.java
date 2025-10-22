package com.example.demo.service;

import com.example.demo.domain.Rental;
import com.example.demo.rest.RentalDTO;

import java.util.List;

public interface RentalService {
    List<Rental> listAll();
    Rental createRental(RentalDTO rentalDTO);
    Rental returnRental(RentalDTO rentalDTO);
}
