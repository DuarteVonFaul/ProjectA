package com.sge.boavista.Entities.DTO.API;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DbConnectionDetails {
    private String url;
    private String username;
    private String password;
    private String codEstab;
    private LocalDate data_inicio;

    // Getters e Setters
}