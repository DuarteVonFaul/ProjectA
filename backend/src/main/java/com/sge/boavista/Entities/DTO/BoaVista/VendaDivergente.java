package com.sge.boavista.Entities.DTO.BoaVista;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "venda")
public class VendaDivergente extends Venda {

    private String tipoDivergencia;

    @XmlElement(name = "tipoDivergencia")
    public String getTipoDivergencia() {
        return tipoDivergencia;
    }

    public void setTipoDivergencia(String tipoDivergencia) {
        this.tipoDivergencia = tipoDivergencia;
    }
}
