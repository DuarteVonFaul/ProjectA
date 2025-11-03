package com.sge.boavista.Entities.DTO.BoaVista;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "RemessaResponse")
public class RemessaResponse {

    private Erro erro;
    private List<Venda> conciliadas;
    private List<VendaDivergente> divergentes;
    private List<VendaComErro> erros;
    private String status;

    @XmlElement(name = "erro")
    public Erro getErro() {
        return erro;
    }

    public void setErro(Erro erro) {
        this.erro = erro;
    }

    /**
     * Mapeia a tag <conciliadas> que envolve a lista de vendas.
     * Cada item da lista é uma tag <venda>[cite: 162, 163].
     */
    @XmlElementWrapper(name = "conciliadas")
    @XmlElement(name = "venda")
    public List<Venda> getConciliadas() {
        return conciliadas != null ? conciliadas : new ArrayList<>();
    }

    public void setConciliadas(List<Venda> conciliadas) {
        this.conciliadas = conciliadas;
    }

    /**
     * Mapeia a tag <divergentes> que envolve a lista de vendas.
     * Cada item da lista é uma tag <venda>[cite: 183, 184].
     */
    @XmlElementWrapper(name = "divergentes")
    @XmlElement(name = "venda")
    public List<VendaDivergente> getDivergentes() {
        return divergentes != null ? divergentes : new ArrayList<>();
    }

    public void setDivergentes(List<VendaDivergente> divergentes) {
        this.divergentes = divergentes;
    }

    /**
     * Mapeia a tag <erros> que envolve a lista de vendas.
     * Cada item da lista é uma tag <venda>[cite: 204, 205].
     */
    @XmlElementWrapper(name = "erros")
    @XmlElement(name = "venda")
    public List<VendaComErro> getErros() {
        return erros != null ? erros : new ArrayList<>();
    }

    public void setErros(List<VendaComErro> erros) {
        this.erros = erros;
    }

    @XmlElement(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
