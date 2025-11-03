package com.sge.boavista.Client;


import com.sge.boavista.Client.Ports.IBodyValidationClient;
import com.sge.boavista.Client.Ports.IClient;
import com.sge.boavista.Entities.DTO.BoaVista.Erro;
import com.sge.boavista.Entities.DTO.BoaVista.RemessaResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class BodyValidationClient implements IBodyValidationClient, IClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public RemessaResponse validarRemessa(String corpoXml, HttpHeaders headers) {
        final String url = "https://integracao.eextrato.com.br/conciliador/rest/remessa/venda/valida";

        HttpEntity<String> requestEntity = new HttpEntity<>(corpoXml, headers);


        try {
            // A mudança principal está aqui: pedimos a resposta já convertida.
            ResponseEntity<RemessaResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    RemessaResponse.class // O tipo de retorno esperado agora é o nosso objeto
            );
            System.out.println(response.getBody().getStatus());
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
