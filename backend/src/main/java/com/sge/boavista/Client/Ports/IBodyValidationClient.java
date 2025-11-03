package com.sge.boavista.Client.Ports;


import com.sge.boavista.Entities.DTO.BoaVista.RemessaResponse;
import org.springframework.http.HttpHeaders;

public interface IBodyValidationClient {

    public RemessaResponse validarRemessa(String corpoXml, HttpHeaders headers);
}
