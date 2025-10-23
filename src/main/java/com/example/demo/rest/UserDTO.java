package com.example.demo.rest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "{User.name.NotBlank}")
    private String name;

    @Email(message = "{Email.invalid}")
    @NotBlank(message = "{Email.notBlank}")
    private String email;

    public UserDTO() {
    }

    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
