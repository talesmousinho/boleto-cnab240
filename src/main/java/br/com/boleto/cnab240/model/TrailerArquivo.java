package br.com.boleto.cnab240.model;

import br.com.boleto.cnab240.enums.TipoRegistro;
import br.com.boleto.cnab240.util.CNABUtil;
import br.com.boleto.cnab240.util.Constants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrailerArquivo extends RegistroCNAB {
    private String codigoBanco;
    private String numeroLote;
    private Long quantidadeLotesArquivo;
    private Long quantidadeRegistrosArquivo;

    public TrailerArquivo() {
        super(TipoRegistro.TRAILER_ARQUIVO);
        this.codigoBanco = Constants.CODIGO_BANCO;
        this.numeroLote = "9999";
        this.quantidadeLotesArquivo = 1L;
    }

    @Override
    protected void appendFields(StringBuilder linha) {
        // Posições 001 a 003 - Código do Banco
        linha.append(CNABUtil.formatNumber(Long.valueOf(codigoBanco), 3));
        // Posições 004 a 007 - Número do lote remessa
        linha.append(CNABUtil.formatNumber(Long.valueOf(numeroLote), 4));
        // Posição 008 - Tipo de registro
        linha.append(tipoRegistro.getCodigo());
        // Posições 009 a 017 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 9));
        // Posições 018 a 023 - Quantidade de lotes do arquivo
        linha.append(CNABUtil.formatNumber(quantidadeLotesArquivo, 6));
        // Posições 024 a 029 - Quantidade de registros do arquivo
        linha.append(CNABUtil.formatNumber(quantidadeRegistrosArquivo, 6));
        // Posições 030 a 240 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 211));
    }

    @Override
    protected void validateFields() {
        if (quantidadeRegistrosArquivo == null) {
            throw new IllegalArgumentException("Quantidade de registros do arquivo é obrigatória");
        }
    }
}
