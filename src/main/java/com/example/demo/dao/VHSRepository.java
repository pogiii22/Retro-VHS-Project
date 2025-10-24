package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.domain.VHS;

import java.util.List;
import java.util.Optional;

public interface VHSRepository extends JpaRepository<VHS, Long> {
    public boolean existsByTitle(String title);
    List<VHS> findByTitle(String title);
    Optional<VHS> findFirstByTitleAndRentedFalse(String title);
}
