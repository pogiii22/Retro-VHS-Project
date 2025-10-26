package com.example.demo.rest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentalDTO {
    @NotBlank(message = "{VHS.name.notBlank}")
    private String vhsTitle;

    @Email(message = "{Email.invalid}")
    @NotBlank(message = "{Email.notBlank}")
    private String userEmail;
}

