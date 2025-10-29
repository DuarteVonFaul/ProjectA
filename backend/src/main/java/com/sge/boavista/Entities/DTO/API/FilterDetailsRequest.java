package com.sge.boavista.Entities.DTO.API;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FilterDetailsRequest {

    LocalDate dataConciliada;
    String loja;
    int status;
}
