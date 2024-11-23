package br.com.boleto.cnab240.model;

import br.com.boleto.cnab240.enums.TipoRegistro;
import br.com.boleto.cnab240.util.CNABUtil;
import br.com.boleto.cnab240.util.Constants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrailerLote extends RegistroCNAB {
    private String codigoBanco;
    private Long numeroLote;
    private Long quantidadeRegistrosLote;

    public TrailerLote() {
        super(TipoRegistro.TRAILER_LOTE);
        this.codigoBanco = Constants.CODIGO_BANCO;
        this.numeroLote = 1L;
    }

    @Override
    protected void appendFields(StringBuilder linha) {
        // Posições 001 a 003 - Código do Banco
        linha.append(CNABUtil.formatNumber(Long.valueOf(codigoBanco), 3));
        // Posições 004 a 007 - Número do lote remessa
        linha.append(CNABUtil.formatNumber(numeroLote, 4));
        // Posição 008 - Tipo de registro
        linha.append(tipoRegistro.getCodigo());
        // Posições 009 a 017 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 9));
        // Posições 018 a 023 - Quantidade de registros no lote
        linha.append(CNABUtil.formatNumber(quantidadeRegistrosLote, 6));
        // Posições 024 a 240 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 217));
    }

    @Override
    protected void validateFields() {
        if (quantidadeRegistrosLote == null) {
            throw new IllegalArgumentException("Quantidade de registros do lote é obrigatória");
        }
    }
}
