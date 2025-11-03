package com.sge.boavista.Client.Ports;


import com.sge.boavista.Entities.DTO.BoaVista.RemessaResponse;
import org.springframework.http.HttpHeaders;

public interface IConciliacaoClient {

    public RemessaResponse conciliarRemessa(String corpoXml, HttpHeaders headers, String url);
}
