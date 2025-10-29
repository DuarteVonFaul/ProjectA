package com.sge.boavista.Utils.Enums;

public enum Ambiente {
    HOMOLOGACAO("https://bv-homolog.eextrato.com.br"),
    PRODUCAO("https://integracao.eextrato.com.br");

    private final String baseUrl;

    Ambiente(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
