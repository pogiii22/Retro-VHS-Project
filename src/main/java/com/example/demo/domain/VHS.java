package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class VHS {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String genre;
    private int releaseYear;
}
