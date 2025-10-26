package com.example.demo.rest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserEmailDTO {
    @Email(message = "{Email.invalid}")
    @NotBlank(message = "{Email.notBlank}")
    private String email;

    public UserEmailDTO() {
    }

    public UserEmailDTO(String email) {
        this.email = email;
    }
}

