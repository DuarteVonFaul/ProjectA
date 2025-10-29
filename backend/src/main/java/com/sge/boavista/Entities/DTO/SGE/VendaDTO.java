package com.sge.boavista.Entities.DTO.SGE;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VendaDTO{
    private String id;
    private String loja;
    private String caixa;
    private Integer idtr;
    private String nsu;
    private String nsuHost;
    private String Autoricacao;
    private String tipoTransacao;
    private BigDecimal valorTotal;
    private Integer numParcelas;
    private String rede;
    private String bandeira;
    private Integer cancel;

    public VendaDTO(String id, String loja, String caixa, Integer idtr, String nsu, String nsuHost, String tipoTransacao,
                    BigDecimal valorTotal, String rede, String bandeira, Integer cancel, String Autoricacao) {
        this.id = id;
        this.loja = loja;
        this.caixa = caixa;
        this.idtr = idtr;
        this.nsu = nsu;
        this.nsuHost = nsuHost;
        this.tipoTransacao = tipoTransacao;
        this.valorTotal = valorTotal;
        this.numParcelas = 1;
        this.rede = rede;
        this.cancel = cancel;
        this.bandeira = bandeira;
        this.Autoricacao = Autoricacao;
    }

    public VendaDTO(String id, String loja, String caixa, Integer idtr, String nsu, String nsuHost, String tipoTransacao,
                    BigDecimal valorTotal, String rede, String bandeira, Integer cancel, String Autoricacao, Integer numParcelas) {
        this.id = id;
        this.loja = loja;
        this.caixa = caixa;
        this.idtr = idtr;
        this.nsu = nsu;
        this.nsuHost = nsuHost;
        this.tipoTransacao = tipoTransacao;
        this.valorTotal = valorTotal;
        this.numParcelas = numParcelas;
        this.rede = rede;
        this.cancel = cancel;
        this.bandeira = bandeira;
        this.Autoricacao = Autoricacao;
    }
}
