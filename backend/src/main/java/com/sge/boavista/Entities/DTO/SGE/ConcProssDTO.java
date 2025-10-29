package com.sge.boavista.Entities.DTO.SGE;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class ConcProssDTO {

    private LocalDate date;
    private String loja;
}
