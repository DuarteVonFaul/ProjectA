package com.sge.boavista.Entities.DTO.BoaVista;

import jakarta.xml.bind.annotation.XmlElement;

public class Erro {
    private String descricao;
    private boolean rejeitado;
    private String status;

    @XmlElement(name = "descricao")
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @XmlElement(name = "rejeitado")
    public boolean isRejeitado() { return rejeitado; }
    public void setRejeitado(boolean rejeitado) { this.rejeitado = rejeitado; }

    @XmlElement(name = "status")
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
