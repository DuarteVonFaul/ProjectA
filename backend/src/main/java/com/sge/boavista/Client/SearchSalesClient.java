package com.sge.boavista.Client;


import com.sge.boavista.Client.Ports.ISearchSalesClient;
import com.sge.boavista.Entities.DTO.BoaVista.Erro;
import com.sge.boavista.Entities.DTO.BoaVista.VendaResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchSalesClient implements ISearchSalesClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public VendaResponse buscarVendasNaoConciliadas(LocalDate dataVenda, String codigoLoja, String url, HttpHeaders headers) {
        String dataFormatada = dataVenda.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Monta o corpo XML da requisição com os filtros necessários.
        String corpoXml = String.format(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                        "<Filtro>" +
                        "<dataVendaInicio>%s</dataVendaInicio>" +       // Filtro de data [cite: 363]
                        "<dataVendaFim>%s</dataVendaFim>" +           // Usamos a mesma data para início e fim para buscar um dia específico.
                        "<codigoLoja>%s</codigoLoja>" +                 // Filtro de loja [cite: 365]
                        "<statusConciliacao>2</statusConciliacao>" +    // Filtro para "Não conciliada" conforme Tabela VI [cite: 991]
                        "</Filtro>",
                dataFormatada, dataFormatada, codigoLoja
        );

        HttpEntity<String> requestEntity = new HttpEntity<>(corpoXml, headers);


        try {
            ResponseEntity<VendaResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    VendaResponse.class
            );
            return response.getBody();

        } catch (HttpClientErrorException e) {
            System.err.println("Erro HTTP na busca: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return criarRespostaDeErro("Erro de comunicação com a API: " + e.getStatusCode());
        } catch (Exception e) {
            System.err.println("Erro inesperado na busca: " + e.getMessage());
            return criarRespostaDeErro("Falha inesperada ao buscar vendas: " + e.getMessage());
        }
    }

    @Override
    public VendaResponse criarRespostaDeErro(String descricaoErro) {
        Erro erro = new Erro();
        erro.setStatus("ERRO");
        erro.setRejeitado(true);
        erro.setDescricao(descricaoErro);

        VendaResponse resposta = new VendaResponse();
        resposta.setErro(erro);
        return resposta;
    }
}
