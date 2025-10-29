package com.sge.boavista.Utils;



import com.sge.boavista.Utils.Enums.Operadora;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class ConvertXmlUtil {



    private String criarTag(String nomeTag, Object valor) {
        // Se o valor for nulo, usa uma string vazia; senão, usa a representação em string do valor.
        String valorSeguro = (valor == null) ? "" : valor.toString();
        return String.format("        <%s>%s</%s>\n", nomeTag, valorSeguro, nomeTag);
    }

    private String criarTagValor(String nomeTag, BigDecimal valor) {
        String valorFormatado = (valor == null) ? "0.00" : String.format("%.2f", valor);
        return String.format("        <%s>%s</%s>\n", nomeTag, valorFormatado.replace(',','.'), nomeTag);
    }

    public String gerarXmlRemessaVendaSimples(List<VendaDTO> vendas, LocalDate dataVenda) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
        xmlBuilder.append("<VendasERP>\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataFormatada = dataVenda.format(formatter);

        for (VendaDTO venda : vendas) {
            xmlBuilder.append("    <venda>\n");

            // Usando a função auxiliar para criar as tags de forma segura
            xmlBuilder.append(criarTag("id", venda.getId()));
//            if (venda.getBandeira() != null && !venda.getBandeira().isEmpty()) {
//                Optional<String> optionalBandeira = service.getNomeBandeira(venda.getBandeira(), venda.getTipoTransacao());
//                optionalBandeira.ifPresent(nomeBandeira -> {
//                    System.out.println("SGE: " + venda.getBandeira() + " | Transação: " + venda.getTipoTransacao() + " | Search: " + nomeBandeira);
//                    xmlBuilder.append(criarTag("bandeira", Bandeira.getCodigoPeloNome(nomeBandeira)));
//                });
//            }

            xmlBuilder.append(criarTag("bandeira", venda.getBandeira()));

            xmlBuilder.append(criarTag("dataVenda", dataFormatada));
            xmlBuilder.append(criarTag("nsu", venda.getNsu()));
            xmlBuilder.append(criarTag("autorizacao", venda.getAutoricacao()));
            xmlBuilder.append(criarTag("nsuHost", venda.getNsuHost()));
            xmlBuilder.append(criarTag("plano",  venda.getNumParcelas() <= 0 ? 1 :  venda.getNumParcelas()));
            xmlBuilder.append(criarTag("areaCliente", venda.getCaixa()));
            xmlBuilder.append(criarTag("rede", Operadora.getCodigoPeloNome(venda.getRede().trim())));
            xmlBuilder.append(criarTagValor("valor", venda.getValorTotal()));
            xmlBuilder.append(criarTag("codigoLoja", venda.getLoja()));
            xmlBuilder.append(criarTag("status", venda.getCancel()));

            xmlBuilder.append("    </venda>\n");
        }

        xmlBuilder.append("</VendasERP>");
        return xmlBuilder.toString();
    }

    public String gerarXmlRemessaVendaParcelada(List<VendaParceladaDTO> vendas, LocalDate dataVenda) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
        xmlBuilder.append("<VendasERP>\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataFormatada = dataVenda.format(formatter);

        for (VendaParceladaDTO venda : vendas) {
            xmlBuilder.append("    <venda>\n");

            // Usando a função auxiliar para criar as tags de forma segura
            xmlBuilder.append(criarTag("id", venda.getId()));
//            if (venda.getBandeira() != null && !venda.getBandeira().isEmpty()) {
//                Optional<String> optionalBandeira = service.getNomeBandeira(venda.getBandeira(), venda.getTipoTransacao());
//                optionalBandeira.ifPresent(nomeBandeira -> {
//                    System.out.println("SGE: " + venda.getBandeira() + " | Transação: " + venda.getTipoTransacao() + " | Search: " + nomeBandeira + "| Boa Vista: " + Bandeira.getCodigoPeloNome(nomeBandeira));
//                    xmlBuilder.append(criarTag("bandeira", Bandeira.getCodigoPeloNome(nomeBandeira)));
//                });
//            }
            xmlBuilder.append(criarTag("dataVenda", dataFormatada));
            xmlBuilder.append(criarTag("nsu", venda.getNsu()));
            xmlBuilder.append(criarTag("nsuHost", venda.getNsuHost()));
            xmlBuilder.append(criarTag("plano", venda.getNumParcelas()));
            xmlBuilder.append(criarTag("parcela", venda.getNumParcela()));
            xmlBuilder.append(criarTag("areaCliente", venda.getCaixa()));
            xmlBuilder.append(criarTag("rede", Operadora.getCodigoPeloNome(venda.getRede().trim())));
            xmlBuilder.append(criarTagValor("valor", venda.getValorTotal()));
            xmlBuilder.append(criarTag("codigoLoja", venda.getLoja()));
            xmlBuilder.append(criarTag("status", venda.getCancel()));

            xmlBuilder.append("    </venda>\n");
        }

        xmlBuilder.append("</VendasERP>");
        return xmlBuilder.toString();
    }

    private String mapTipoTransacao(String tipoTransacao) {
        if (tipoTransacao != null) {
            if (tipoTransacao.toLowerCase().contains("credito")) {
                return "C"; //
            }
            if (tipoTransacao.toLowerCase().contains("debito")) {
                return "D"; //
            }
        }
        return null; // Retorna vazio se não conseguir mapear
    }

}
