package com.sge.boavista.Utils.Enums;

import java.util.Arrays;

public enum Divergencia {
    PRODUTO("Tipo de Transacao (Debito/Credito)"),
    VALOR("Valor"),
    REDE("Rede"),
    BANDEIRA("Bandeira"),
    STATUS("Status (Cancelado/Autorizado)"),
    PLANO("Numero de Parcelas"),
    VENDA("Transacao"),
    DEFAULT_VALUE(null);

    private final String codigo;

    Divergencia(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static Divergencia getByCodigo(String codigo) {
        return Arrays.stream(values())
                .filter(bandeira -> bandeira.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    public static String getCodigoPeloNome(String nome) {
        return Arrays.stream(values())
                .filter(bandeira -> bandeira.name().equalsIgnoreCase(nome))
                .findFirst()
                .map(Divergencia::getCodigo)
                .orElseThrow(() -> new IllegalArgumentException("Nenhuma bandeira encontrada com o nome: " + nome));
    }
}
