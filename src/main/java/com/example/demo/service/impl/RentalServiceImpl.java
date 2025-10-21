package com.example.demo.service.impl;

import com.example.demo.dao.RentalRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dao.VHSRepository;
import com.example.demo.domain.Rental;
import com.example.demo.domain.User;
import com.example.demo.domain.VHS;
import com.example.demo.rest.RentalDTO;
import com.example.demo.rest.UserDTO;
import com.example.demo.service.RentalService;
import com.example.demo.service.UserService;
import com.example.demo.service.VHSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepo;
    @Autowired
    private VHSService vhsService;
    @Autowired
    private UserService userService;

    @Override
    public List<Rental> listAll() {
        return rentalRepo.findAll();
    }

    @Override
    public Rental createRental(RentalDTO rentalDTO) {
        User user = userService.findByNameAndEmail(rentalDTO.getUserName(), rentalDTO.getUserEmail());
        VHS vhs = vhsService.findByTitle(rentalDTO.getVhsTitle());

        LocalDate rentalDate = LocalDate.now();
        LocalDate dueDate = rentalDate.plusMonths(1);

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setVhs(vhs);
        rental.setRentalDate(rentalDate);
        rental.setDueDate(dueDate);

        return rentalRepo.save(rental);
    }
}
