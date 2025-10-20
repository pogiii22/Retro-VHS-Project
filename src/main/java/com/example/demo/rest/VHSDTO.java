package com.example.demo.rest;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VHSDTO {

    @NotBlank
    private String title;

    private String genre;
    private Integer releaseYear;
}
