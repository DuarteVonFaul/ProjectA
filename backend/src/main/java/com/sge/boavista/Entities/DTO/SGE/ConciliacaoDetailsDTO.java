package com.sge.boavista.Entities.DTO.SGE;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ConciliacaoDetailsDTO {

    private Integer id;
    private String detalhe;
    private LocalDate dataConciliada;
    private LocalDateTime dataConciliacao;
    private Integer status;
    private String loja;
    private String caixa;
}
