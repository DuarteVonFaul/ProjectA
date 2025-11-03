package com.sge.boavista.Entities.DTO.BoaVista;


import jakarta.xml.bind.annotation.XmlElement;

import java.math.BigDecimal;

public class VendaRetornada {

    private String dataVenda;
    private String nsu;
    private String autorizacao;
    private int plano;
    private String rede;
    private String bandeira;
    private String produto;
    private String codigoLojaErp;
    private String codigoEstabelecimento;
    private int statusConciliacao;
    private String dataCredito;
    private BigDecimal valorVenda;

    // Getters e Setters com anotações JAXB

    @XmlElement
    public String getDataVenda() { return dataVenda; }
    public void setDataVenda(String dataVenda) { this.dataVenda = dataVenda; }

    @XmlElement
    public String getNsu() { return nsu; }
    public void setNsu(String nsu) { this.nsu = nsu; }

    @XmlElement
    public String getAutorizacao() { return autorizacao; }
    public void setAutorizacao(String autorizacao) { this.autorizacao = autorizacao; }

    @XmlElement
    public int getPlano() { return plano; }
    public void setPlano(int plano) { this.plano = plano; }

    @XmlElement
    public String getRede() { return rede; }
    public void setRede(String rede) { this.rede = rede; }

    @XmlElement
    public String getBandeira() { return bandeira; }
    public void setBandeira(String bandeira) { this.bandeira = bandeira; }

    @XmlElement
    public String getProduto() { return produto; }
    public void setProduto(String produto) { this.produto = produto; }

    @XmlElement
    public String getCodigoLojaErp() { return codigoLojaErp; }
    public void setCodigoLojaErp(String codigoLojaErp) { this.codigoLojaErp = codigoLojaErp; }

    @XmlElement
    public String getCodigoEstabelecimento() { return codigoEstabelecimento; }
    public void setCodigoEstabelecimento(String codigoEstabelecimento) { this.codigoEstabelecimento = codigoEstabelecimento; }

    @XmlElement
    public int getStatusConciliacao() { return statusConciliacao; }
    public void setStatusConciliacao(int statusConciliacao) { this.statusConciliacao = statusConciliacao; }

    @XmlElement
    public String getDataCredito() { return dataCredito; }
    public void setDataCredito(String dataCredito) { this.dataCredito = dataCredito; }

    @XmlElement
    public BigDecimal getValorVenda() { return valorVenda; }
    public void setValorVenda(BigDecimal valorVenda) { this.valorVenda = valorVenda; }
}
