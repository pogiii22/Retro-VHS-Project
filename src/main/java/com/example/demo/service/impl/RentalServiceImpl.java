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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    private static final Logger log = LoggerFactory.getLogger(RentalServiceImpl.class);
    private static final Float fee = 0.5f;

    @Autowired
    private RentalRepository rentalRepo;
    @Autowired
    private VHSService vhsService;
    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = true)
    public List<Rental> listAll() {
        log.info("[SERVICE] searched for all Rentals");
        return rentalRepo.findAll();
    }

    @Override
    @Transactional
    public Rental createRental(RentalDTO rentalDTO) {
        User user = userService.findByEmail(rentalDTO.getUserEmail());
        VHS notRented = vhsService.findFirstByTitleAndRentedFalse(rentalDTO.getVhsTitle());
        notRented.setRented(true);
        vhsService.saveVHS(notRented);

        LocalDate rentalDate = LocalDate.now();
        LocalDate dueDate = rentalDate.plusMonths(1);
        Rental rental = new Rental(user, notRented, rentalDate, dueDate);

        log.info("[SERVICE] Rental saved to database: title= {}, name= {}, email= {}, rentalDate= {}", rental.getVhs().getTitle(),
                rental.getUser().getName(), rental.getUser().getEmail(), rental.getRentalDate());
        return rentalRepo.save(rental);
    }

    @Override
    @Transactional
    public Rental returnRental(RentalDTO rentalDTO) {
        Rental ret = rentalRepo.findTopByVhs_TitleAndUser_EmailAndReturnDateIsNullOrderByRentalDateDesc(
                rentalDTO.getVhsTitle(),
                rentalDTO.getUserEmail()
        ).orElseThrow(() -> new ResourceNotFoundException(
                "No active rental found for user with email " + rentalDTO.getUserEmail() + " and VHS title " + rentalDTO.getVhsTitle()));

        LocalDate returnDate = LocalDate.now();
        ret.setReturnDate(returnDate);
        ret.getVhs().setRented(false);
        vhsService.saveVHS(ret.getVhs());
        calculateDays(ret);

        log.info("[SERVICE] Rental returned: title= {}, name= {}, email= {}, returnedDate= {}, toPay= {}e",
                ret.getVhs().getTitle(), ret.getUser().getName(),ret.getUser().getEmail(), ret.getReturnDate(), ret.getLateFee());

        Float toPay = ret.getLateFee() + ret.getUser().getToPay();
        ret.getUser().setToPay(toPay);
        userService.saveUser(ret.getUser());
        return ret;
    }

    @Override
    public List<Rental> getActiveRentals(String email) {
        log.info("[SERVICE] User {} searched for active Rentals", email);
        User user = userService.findByEmail(email); //throws error if does not exists
       return rentalRepo.findByUser_EmailAndReturnDateIsNull(email);
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
