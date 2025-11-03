package com.sge.boavista.Client.Ports;


import com.sge.boavista.Entities.DTO.BoaVista.VendaResponse;
import org.springframework.http.HttpHeaders;

import java.time.LocalDate;

public interface ISearchSalesClient {

    public VendaResponse buscarVendasNaoConciliadas(LocalDate dataVenda, String codigoLoja, String url, HttpHeaders headers);

    public VendaResponse criarRespostaDeErro(String descricao);

}
