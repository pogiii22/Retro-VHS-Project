package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
