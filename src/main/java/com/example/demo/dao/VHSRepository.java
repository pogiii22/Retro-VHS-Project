package com.example.demo.dao;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.domain.VHS;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface VHSRepository extends JpaRepository<VHS, Long> {
    List<VHS> findByTitle(String title);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<VHS> findFirstByTitleAndRentedFalse(String title);
}
