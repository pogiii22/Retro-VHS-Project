package com.example.demo.dao;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByName(String name);
    public boolean existsByEmail(String email);
    boolean existsByNameOrEmail(String name, String email);
}
