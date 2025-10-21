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
    @NotBlank(message = "{Email.notBlank}")
    private String userEmail;

    private LocalDate rentalDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
}

