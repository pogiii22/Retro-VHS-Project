package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class VHS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String genre;
    private Integer releaseYear;
    private Boolean rented = false;
}
