package com.sge.boavista.Entities.DTO.SGE;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FilterDetailsDTO {
    LocalDate dataConciliada;
    LocalDate dataConciliacao;
    String loja;
    int status;
}
