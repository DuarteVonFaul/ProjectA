package com.sge.boavista.Utils.Enums;

import java.util.Arrays;

public enum Operadora {
    AMEX(1),
    CIELO(2),
    CREDISHOP(3),
    FORTBRASIL(4),
    MAISFACIL(5),
    REDE(6),
    GETNET(7),
    GOODCARD(8),
    LIBERCARD(9),
    HAGANA(10),
    BANESECARD(11),
    TICKET(12),
    FIRSTDATA(13),
    TRIBANCO(15),
    CETELEM(16),
    SODEXO(17),
    VR_BENEFICIOS(18),
    VERO(20),
    LOSANGO(21),
    UP_BRASIL(23),
    VALECARD(24),
    COMPROCARD(25),
    FANCARD(26),
    ECXCARD(27),
    BANESCARD_28(28),
    CABAL(29),
    USECRED(30),
    FITCARD(31),
    PLANVALE(32),
    ABRAPETITE(33),
    SENFF(34),
    STONE(35),
    USACARD(36),
    BNB(37),
    REDETREL(38),
    BENVISAVALE(39),
    MURY(40),
    BANRICARD(41),
    GREENCARD(42),
    CDCCARD(43),
    CALCARD(44),
    CONNECTYPAY(45),
    VALECON(46),
    PAYPAL(47),
    COOPERCARD(48),
    MERCADO_PAGO(49),
    SOROCRED(50),
    NETSHOES(51),
    VOLUS(58),
    PERSONALCARD(59),
    VALESHOP(60),
    SESIMAX(61),
    REDEUZE(62),
    NUTRICASH(63),
    VEROCHEQUE(65),
    BIGCARD(66),
    BANPARA(67),
    MERCADO_LIVRE(69),
    MGCARD(70),
    COOPELIFE(71),
    BRASILCARD(72),
    CREDSYSTEM(73),
    BOA_ALIMENTACAO(74),
    GLOBAL_PAYMENTS(75),
    DIGIMODAS(76),
    VALEMAIS(77),
    DMCARD(78),
    PAGUE_LOGO(79),
    MAXXCARD(81),
    PAGSEGURO(84),
    LINXPAY(87),
    CREDNOSSO(88),
    LECARD(95),
    VERDECARD(100),
    CNOVA(101),
    B2W(102),
    MAXI_CARTAO(103),
    ONECARD(104),
    SAFRA_PAY(105),
    FAMILLYCARD(106),
    SEICON(107),
    REDETENDENCIA(108),
    SIPAG(110),
    VEGASCARD(112),
    PAGOLIVRE(117),
    GOLDENFARMA(118),
    MAXISCARD(119),
    GRANDCARD(127),
    ER_CARD(128),
    CREDZ(129),
    SAFECARD(130),
    PAGSIMPLES(131),
    SOMA_CONTA_DIGITAL(132),
    BRASILCONVENIOS(133),
    SYSPROCARD(136),
    SICRED(137),
    UNICA(138),
    CONVCARD(139),
    HAGANA_UPLOAD(143),
    PICPAY(149),
    AMAZON(154),
    TESTE(155),
    TA_PAGO(156),
    ACCREDITO(159),
    SINDPLUS(173),
    ITI(178),
    ADYEN(193),
    REDEMED(194),
    PIX_ITAU(195),
    AME(219),
    PAGARME(226),
    LAGOACRED(227),
    CARDS_008(228),
    PITCARD(229),
    ALIMENTARE(230),
    SEM_PARAR(231),
    IFOOD(233),
    WEBCARD(234),
    FACECARD(235),
    IBI_CARTOES(236),
    ROMCARD(237),
    NUTRICARD(238),
    EUCARD(239),
    BIQ_BENEFICIOS(240),
    TRUCKPAD(242),
    TRIOCARD(244),
    CROSCARD(245),
    CARTAO_PRE_DATADO(246),
    NEUSFESTECARD(247),
    USE_BLUE(249),
    RAPPI(250),
    WISEPAY(251),
    HELLOTICKET(252),
    PAYMEE(253),
    UBER_EATS(254),
    BONUSCRED(255),
    AVANCARD(256),
    TRUCKPAG(257),
    DAFITI(260),
    WIZEO(261),
    C6(262),
    ALELO(263),
    SHELLBOX(264),
    CABOS_E_SOLDADOS(265),
    SERTAOCARD(266),
    PIX_SANTANDER(267),
    PIX_BRADESCO(268),
    FIX_PAY(269),
    PIX_BB(270),
    FOOD_99(271),
    CONVENIOSCARD(272),
    MEGAVALE(273),
    DESCONHECIDO(326),
    PEDEPRONTO(752),
    BMGCARD(760),
    RAPPI_FARMACIA(761),
    RVCARDS(764),
    BKBANK(765),
    SERVIPA(767),
    PAGAI(768),
    GRANITO(769),
    DEFAULT_VALUE(0);

    private final int codigo;

    Operadora(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static Operadora getByCodigo(int codigo) {
        return Arrays.stream(values())
                .filter(operadora -> operadora.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    /**
     * Busca o código de uma operadora pelo seu nome.
     * A busca não diferencia maiúsculas de minúsculas.
     *
     * @param nome O nome da operadora a ser buscada.
     * @return O código da operadora.
     * @throws IllegalArgumentException se nenhuma operadora com o nome especificado for encontrada.
     */
    public static int getCodigoPeloNome(String nome) {
        try{
            return Arrays.stream(values())
                    .filter(operadora -> operadora.name().equalsIgnoreCase(nome))
                    .findFirst()
                    .map(Operadora::getCodigo)
                    .orElseThrow(() -> new IllegalArgumentException("Nenhuma operadora encontrada com o nome: " + nome));
        }catch (Exception e) {
            return 0;
        }

    }
}
