package com.example.demo.rest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RentalDTO {
    @NotBlank(message = "{VHS.name.notBlank}")
    private String vhsTitle;

    @NotBlank(message = "{User.name.NotBlank}")
    private String userName;

    @Email(message = "{Email.invalid}")
    @NotBlank(message = "Email.notBlank")
    private String userEmail;

    @NotNull(message = "Rental date is required")
    private LocalDate rentalDate;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    // Opcionalno ako korisnik vraća odmah
    private LocalDate returnDate;
}

