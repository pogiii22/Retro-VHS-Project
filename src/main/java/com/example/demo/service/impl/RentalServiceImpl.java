package com.example.demo.service.impl;

import com.example.demo.dao.RentalRepository;
import com.example.demo.domain.Rental;
import com.example.demo.domain.User;
import com.example.demo.domain.VHS;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.rest.RentalDTO;
import com.example.demo.service.RentalService;
import com.example.demo.service.UserService;
import com.example.demo.service.VHSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepo;
    @Autowired
    private VHSService vhsService;
    @Autowired
    private UserService userService;
    private static final Float fee = 0.5f;

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
        rental.setReturnDate(rentalDTO.getReturnDate());

        calculateDays(rental);

        return rentalRepo.save(rental);
    }

    @Override
    public Rental returnRental(RentalDTO rentalDTO) {
        Rental ret = rentalRepo.findTopByVhs_TitleAndUser_EmailAndUser_NameOrderByRentalDateDesc(
                rentalDTO.getVhsTitle(), rentalDTO.getUserEmail(), rentalDTO.getUserName())
                .orElseThrow(() -> new ResourceNotFoundException("Rental with name " +
                        rentalDTO.getUserName()+", email " + rentalDTO.getUserEmail()
                        + " and title " + rentalDTO.getVhsTitle() + " not found! Please register first!"));

        LocalDate returnDate = LocalDate.now();
        ret.setReturnDate(returnDate);

        calculateDays(ret);
        return ret;
    }

    public static void calculateDays(Rental rental){
        int daysLate = 0;
        if (rental.getDueDate() != null && rental.getReturnDate() != null && rental.getReturnDate().isAfter(rental.getDueDate())) {
            daysLate = Math.toIntExact((ChronoUnit.DAYS.between(rental.getDueDate(), rental.getReturnDate())));
        }
        rental.setDaysLate(daysLate);
        rental.setLateFee(daysLate*fee);
    }
}
