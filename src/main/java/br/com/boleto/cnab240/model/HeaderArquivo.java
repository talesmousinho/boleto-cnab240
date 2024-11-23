package br.com.boleto.cnab240.model;

import br.com.boleto.cnab240.enums.TipoInscricao;
import br.com.boleto.cnab240.enums.TipoOperacao;
import br.com.boleto.cnab240.enums.TipoRegistro;
import br.com.boleto.cnab240.util.CNABUtil;
import br.com.boleto.cnab240.util.Constants;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HeaderArquivo extends RegistroCNAB {
    private String codigoBanco;
    private String numeroLote;
    private TipoInscricao tipoInscricaoEmpresa;
    private String numeroInscricaoEmpresa;
    private String codigoTransmissao;
    private String nomeEmpresa;
    private String nomeBanco;
    private LocalDate dataGeracaoArquivo;
    private String numeroSequencialArquivo;
    private String numeroVersaoLayoutArquivo;
    private TipoOperacao tipoOperacao;

    public HeaderArquivo() {
        super(TipoRegistro.HEADER_ARQUIVO);
        this.codigoBanco = Constants.CODIGO_BANCO;
        this.nomeBanco = Constants.NOME_BANCO;
        this.tipoOperacao = TipoOperacao.REMESSA;
        this.tipoInscricaoEmpresa = TipoInscricao.CNPJ;
        this.numeroLote = "0000";
        this.numeroVersaoLayoutArquivo = "040";
        this.numeroSequencialArquivo = "000001";
    }

    @Override
    protected void appendFields(StringBuilder linha) {
        // Posições 001 a 003 - Código do Banco
        linha.append(CNABUtil.formatNumber(Long.valueOf(codigoBanco), 3));
        // Posições 004 a 007 - Lote de Serviço
        linha.append(CNABUtil.formatNumber(Long.valueOf(numeroLote), 4));
        // Posição 008 - Tipo de Registro
        linha.append(tipoRegistro.getCodigo());
        // Posições 009 a 016 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 8));
        // Posição 017 - Tipo de Inscrição da Empresa
        linha.append(tipoInscricaoEmpresa.getCodigo());
        // Posições 018 a 032 - Número de Inscrição da Empresa
        linha.append(CNABUtil.formatNumber(Long.valueOf(numeroInscricaoEmpresa), 15));
        // Posições 033 a 047 - Código do Transmissão
        linha.append(CNABUtil.formatAlphanumeric(codigoTransmissao, 15));
        // Posições 048 a 072 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 25));
        // Posições 073 a 102 - Nome da Empresa
        linha.append(CNABUtil.formatAlphanumeric(nomeEmpresa, 30));
        // Posições 103 a 132 - Nome do Banco
        linha.append(CNABUtil.formatAlphanumeric(nomeBanco, 30));
        // Posições 133 a 142 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 10));
        // Posições 143 - Código Remessa / Retorno
        linha.append(tipoOperacao.getCodigo());
        // Posições 144 a 151 - Data de Geração do Arquivo
        linha.append(CNABUtil.formatDate(dataGeracaoArquivo));
        // Posições 152 a 157 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 6));
        // Posições 158 a 163 - Número Sequencial do Arquivo
        linha.append(CNABUtil.formatNumber(Long.valueOf(numeroSequencialArquivo), 6));
        // Posições 164 a 166 - Número da Versão do Layout do Arquivo
        linha.append(CNABUtil.formatNumber(Long.valueOf(this.numeroVersaoLayoutArquivo), 3));
        // Posições 167 a 240 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 74));
    }

    @Override
    protected void validateFields() {
        if (numeroInscricaoEmpresa == null) {
            throw new IllegalStateException("Número de Inscrição da Empresa é obrigatório");
        }
        if (codigoTransmissao == null) {
            throw new IllegalStateException("Código de Transmissão é obrigatório");
        }
        if (nomeEmpresa == null) {
            throw new IllegalStateException("Nome da Empresa é obrigatório");
        }
        if (dataGeracaoArquivo == null) {
            throw new IllegalStateException("Data de Geração do Arquivo é obrigatório");
        }
    }
}
