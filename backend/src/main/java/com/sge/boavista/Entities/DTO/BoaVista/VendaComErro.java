package com.sge.boavista.Entities.DTO.BoaVista;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "venda")
public class VendaComErro extends Venda {

    private String mensagem;

    @XmlElement(name = "mensagem")
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

