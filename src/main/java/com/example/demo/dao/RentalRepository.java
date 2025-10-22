package com.example.demo.dao;

import com.example.demo.domain.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    Optional<Rental> findTopByVhs_TitleAndUser_EmailAndUser_NameOrderByRentalDateDesc(
            String vhsTitle, String userEmail, String userName);
}
