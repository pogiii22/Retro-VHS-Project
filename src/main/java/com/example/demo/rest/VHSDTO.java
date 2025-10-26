package com.example.demo.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VHSDTO {

    @NotBlank (message = "{VHS.name.notBlank}")
    private String title;
    @NotBlank (message = "{VHS.genre.notBlank}")
    private String genre;
    @NotNull(message = "{VHS.releaseYear.notNull}")
    private Integer releaseYear;
}
