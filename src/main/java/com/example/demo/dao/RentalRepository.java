package com.example.demo.dao;

import com.example.demo.domain.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    Optional<Rental> findTopByVhs_TitleAndUser_EmailAndReturnDateIsNullOrderByRentalDateDesc(
            String vhsTitle, String userEmail);
    List<Rental> findByUser_EmailAndReturnDateIsNull(String email);
}
