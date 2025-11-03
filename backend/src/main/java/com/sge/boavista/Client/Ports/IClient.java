package com.sge.boavista.Client.Ports;


import com.sge.boavista.Entities.DTO.BoaVista.RemessaResponse;

public interface IClient {

    public RemessaResponse criarRespostaDeErro(String descricaoErro);
}
