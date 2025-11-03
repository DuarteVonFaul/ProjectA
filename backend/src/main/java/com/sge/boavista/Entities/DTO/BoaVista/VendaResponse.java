package com.sge.boavista.Entities.DTO.BoaVista;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "RetornoResponse")
public class VendaResponse {

    private List<VendaRetornada> vendas;
    private int paginaAtual;
    private int totalPaginas;
    private Erro erro;

    @XmlElementWrapper(name = "vendas")
    @XmlElement(name = "venda")
    public List<VendaRetornada> getVendas() {
        return vendas != null ? vendas : new ArrayList<>();
    }

    public void setVendas(List<VendaRetornada> vendas) {
        this.vendas = vendas;
    }

    @XmlElement(name = "paginaAtual")
    public int getPaginaAtual() { return paginaAtual; }
    public void setPaginaAtual(int paginaAtual) { this.paginaAtual = paginaAtual; }

    @XmlElement(name = "totalPaginas")
    public int getTotalPaginas() { return totalPaginas; }
    public void setTotalPaginas(int totalPaginas) { this.totalPaginas = totalPaginas; }

    @XmlElement(name = "erro")
    public Erro getErro() { return erro; }
    public void setErro(Erro erro) { this.erro = erro; }
}
