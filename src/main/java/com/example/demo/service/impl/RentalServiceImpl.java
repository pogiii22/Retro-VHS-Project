package com.example.demo.service.impl;

import com.example.demo.dao.RentalRepository;
import com.example.demo.domain.Rental;
import com.example.demo.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository RentalRepo;

    @Override
    public List<Rental> listAll() {
        return RentalRepo.findAll();
    }
}
