package br.com.boleto.cnab240.enums;

import lombok.Getter;

@Getter
public enum CodigoMovimento {
    ENTRADA_BOLETO("01", "Entrada de boleto"),
    PEDIDO_BAIXA("02", "Pedido de baixa"),
    CONCESSAO_ABATIMENTO("04", "Concessão de abatimento"),
    CANCELAMENTO_ABATIMENTO("05", "Cancelamento de abatimento"),
    ALTERACAO_VENCIMENTO("06", "Alteração de vencimento"),
    ALTERACAO_IDENTIFICACAO_BOLETO("07", "Alteração da identificação do boleto na empresa (Controle Participante)"),
    ALTERACAO_SEU_NUMERO("08", "Alteração seu número"),
    PEDIDO_PROTESTO("09", "Pedido de Protesto"),
    PEDIDO_SUSTACAO_PROTESTO("18", "Pedido de Sustação de Protesto"),
    CONCESSAO_DESCONTO("10", "Concessão de Desconto"),
    CANCELAMENTO_DESCONTO("11", "Cancelamento de desconto"),
    TRANSFERENCIA_TITULARIDADE_AUTOMATICA("12", "Transferência de Titularidade automática"),
    TRANSFERENCIA_CARTEIRA_SIMPLES_CESSAO("15", "Transferência da carteira Simples para a carteira Cessão"),
    BAIXA_CESSAO_DESCARACTERIZACAO("16", "Baixa de Cessão por Descaracterização"),
    BAIXA_CESSAO_PAGAMENTO("17", "Baixa de Cessão por Pagamento"),
    ALTERACAO_OUTROS_DADOS("31", "Alteração de outros dados"),
    ALTERACAO_VALOR_NOMINAL_BOLETO("47", "Alteração do Valor Nominal do boleto"),
    ALTERACAO_VALOR_MINIMO_PERCENTUAL_MINIMO("48", "Alteração do Valor Mínimo ou Percentual Mínimo"),
    ALTERACAO_VALOR_MAXIMO_PERCENTUAL_MAXIMO("49", "Alteração do Valor Máximo ou Percentual Máximo"),
    NAO_PROTESTAR("98", "Não Protestar (Antes de iniciar o ciclo de protesto)");

    private final String codigo;
    private final String descricao;

    CodigoMovimento(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return codigo + " - " + descricao;
    }
}