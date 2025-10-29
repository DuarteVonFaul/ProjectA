package com.sge.boavista.Utils.Enums;

public enum EndpointsBoavista {
    REMESSA_VENDA("/conciliador/rest/remessa/v9/venda"),
    REMESSA_VENDA_DETALHADA("/conciliador/rest/remessa/v9/vendaDetalhada"),
    RETORNO_VENDA("/conciliador/rest/retorno/v9/venda"),
    RETORNO_VENDA_DETALHADA("/conciliador/rest/retorno/v9/vendaDetalhada"),
    RETORNO_PAGAMENTO("/conciliador/rest/retorno/v9/pagamento"),
    RETORNO_AJUSTE("/conciliador/rest/retorno/v9/ajuste"),
    RETORNO_RECEBIVEL("/conciliador/rest/retorno/v9/recebivel");

    private final String path;

    EndpointsBoavista(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getUrl(Ambiente ambiente) {
        return ambiente.getBaseUrl() + path;
    }
}
