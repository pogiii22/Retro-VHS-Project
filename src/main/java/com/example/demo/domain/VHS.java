package com.example.demo.domain;

import jakarta.persistence.Column;
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

    @Column(nullable = false)
    private String title;

    private String genre;
    private Integer releaseYear;
    private Boolean rented = false;
}
