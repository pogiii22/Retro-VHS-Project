package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.domain.VHS;

public interface VHSRepository extends JpaRepository<VHS, Long> {
    public boolean existsByTitle(String title);
    public VHS findByTitle(String title);
}
