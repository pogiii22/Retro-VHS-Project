package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private VHS vhs;

    private LocalDate rentalDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Integer daysLate;
    private Float lateFee;

    public Rental() {}

    public Rental(User user, VHS vhs,LocalDate rentalDate,LocalDate dueDate){
        this.user = user;
        this.vhs = vhs;
        this.rentalDate = rentalDate;
        this.dueDate = dueDate;
    }
}
