package com.sge.boavista.Entities.DTO.BoaVista;

import jakarta.xml.bind.annotation.XmlElement;

public class Venda {
    private String id;
    private String autorizacao;
    private String nsu;
    private String valor;

    @XmlElement(name = "id")
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @XmlElement(name = "autorizacao")
    public String getAutorizacao() { return autorizacao; }
    public void setAutorizacao(String autorizacao) { this.autorizacao = autorizacao; }

    @XmlElement(name = "nsu")
    public String getNsu() { return nsu; }
    public void setNsu(String nsu) { this.nsu = nsu; }

    @XmlElement(name = "valor")
    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }
}
