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
public class HeaderLote extends RegistroCNAB {
    private String codigoBanco;
    private Long numeroLote;
    private TipoOperacao tipoOperacao;
    private String tipoServico;
    private String numeroVersaoLayoutLote;
    private TipoInscricao tipoInscricaoEmpresa;
    private String numeroInscricaoEmpresa;
    private String codigoTransmissao;
    private String nomeBeneficiario;
    private String mensagem1;
    private String mensagem2;
    private String numeroRemessaRetorno;
    private LocalDate dataGravacaoRemessaRetorno;

    public HeaderLote() {
        super(TipoRegistro.HEADER_LOTE);
        this.codigoBanco = Constants.CODIGO_BANCO;
        this.tipoRegistro = TipoRegistro.HEADER_LOTE;
        this.tipoOperacao = TipoOperacao.REMESSA;
        this.tipoInscricaoEmpresa = TipoInscricao.CNPJ;
        this.numeroLote = 1L;
        this.tipoServico = "01"; // 01 - Cobrança
        this.numeroVersaoLayoutLote = "030";
    }

    @Override
    protected void appendFields(StringBuilder linha) {
        // Posições 001 a 003 - Código do Banco na compensação
        linha.append(CNABUtil.formatNumber(Long.valueOf(codigoBanco), 3));
        // Posições 004 a 007 - Número do lote remessa
        linha.append(CNABUtil.formatNumber(numeroLote, 4));
        // Posição 008 - Tipo de registro
        linha.append(tipoRegistro.getCodigo());
        // Posição 009 - Tipo de operação
        linha.append(tipoOperacao.getCodigo());
        // Posições 010 a 011 - Tipo de serviço
        linha.append(CNABUtil.formatNumber(Long.valueOf(tipoServico), 2));
        // Posições 012 a 013 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 2));
        // Posições 014 a 016 - Nº da versão do layout do lote
        linha.append(CNABUtil.formatNumber(Long.valueOf(numeroVersaoLayoutLote), 3));
        // Posição 017 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 1));
        // Posição 018 - Tipo de inscrição da empresa
        linha.append(tipoInscricaoEmpresa.getCodigo());
        // Posições 019 a 033 - Nº de inscrição da empresa
        linha.append(CNABUtil.formatNumber(Long.valueOf(numeroInscricaoEmpresa), 15));
        // Posições 034 a 053 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 20));
        // Posições 054 a 068 - Código de Transmissão
        linha.append(CNABUtil.formatAlphanumeric(codigoTransmissao, 15));
        // Posições 069 a 073 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 5));
        // Posições 074 a 103 - Nome do Beneficiário
        linha.append(CNABUtil.formatAlphanumeric(nomeBeneficiario, 30));
        // Posições 104 a 143 - Mensagem 1
        linha.append(CNABUtil.formatAlphanumeric(mensagem1, 40));
        // Posições 144 a 183 - Mensagem 2
        linha.append(CNABUtil.formatAlphanumeric(mensagem2, 40));
        // Posições 184 a 191 - Número remessa/retorno
        linha.append(CNABUtil.formatNumber(Long.valueOf(numeroRemessaRetorno), 8));
        // Posições 192 a 199 - Data da gravação remessa/retorno
        linha.append(CNABUtil.formatDate(dataGravacaoRemessaRetorno));
        // Posições 200 a 240 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 41));
    }

    @Override
    protected void validateFields() {
        if (codigoBanco == null) {
            throw new IllegalStateException("Código do Banco é obrigatório");
        }
        if (numeroLote == null) {
            throw new IllegalStateException("Número do lote é obrigatório");
        }
        if (tipoOperacao == null) {
            throw new IllegalStateException("Tipo de operação é obrigatório");
        }
        if (tipoServico == null) {
            throw new IllegalStateException("Tipo de serviço é obrigatório");
        }
        if (numeroVersaoLayoutLote == null) {
            throw new IllegalStateException("Número da versão do layout do lote é obrigatório");
        }
        if (numeroInscricaoEmpresa == null) {
            throw new IllegalStateException("Número de inscrição da empresa é obrigatório");
        }
        if (codigoTransmissao == null) {
            throw new IllegalStateException("Código de Transmissão é obrigatório");
        }
        if (nomeBeneficiario == null) {
            throw new IllegalStateException("Nome do Beneficiário é obrigatório");
        }
        if (numeroRemessaRetorno == null) {
            throw new IllegalStateException("Número remessa/retorno é obrigatório");
        }
        if (dataGravacaoRemessaRetorno == null) {
            throw new IllegalStateException("Data da gravação remessa/retorno é obrigatória");
        }
    }
}