package com.sge.boavista.Client;


import com.sge.boavista.Client.Ports.IClient;
import com.sge.boavista.Client.Ports.IConciliacaoClient;
import com.sge.boavista.Entities.DTO.BoaVista.Erro;
import com.sge.boavista.Entities.DTO.BoaVista.RemessaResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class ConciliacaoClient implements IConciliacaoClient, IClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public RemessaResponse conciliarRemessa(String corpoXml, HttpHeaders headers, String url) {
        HttpEntity<String> requestEntity = new HttpEntity<>(corpoXml, headers);


        try {
            ResponseEntity<RemessaResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    RemessaResponse.class
            );
            return response.getBody();

        } catch (HttpClientErrorException e) {
            System.out.println(headers.toString());
            System.out.println(corpoXml);
            return criarRespostaDeErro("Erro de comunicação: " + e.getStatusCode() + e.getMessage());


        } catch (Exception e) {
            return criarRespostaDeErro("Erro inesperado: " + e.getMessage());
        }
    }

    @Override
    public RemessaResponse criarRespostaDeErro(String descricaoErro) {
        Erro erro = new Erro();
        erro.setStatus("ERRO");
        erro.setRejeitado(true);
        erro.setDescricao(descricaoErro);

        RemessaResponse resposta = new RemessaResponse();
        resposta.setErro(erro);

        return resposta;
    }
}
