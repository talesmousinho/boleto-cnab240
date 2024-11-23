package br.com.boleto.cnab240.model;

import br.com.boleto.cnab240.enums.CodigoMovimento;
import br.com.boleto.cnab240.enums.TipoInscricao;
import br.com.boleto.cnab240.enums.TipoRegistro;
import br.com.boleto.cnab240.util.CNABUtil;
import br.com.boleto.cnab240.util.Constants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SegmentoQ extends RegistroCNAB {
    private String codigoBanco;
    private Long numeroLote;
    private Long numeroSequencialRegistro;
    private String codigoSegmento;
    private CodigoMovimento codigoMovimento;
    private TipoInscricao pagadorTipoInscricao;
    private String pagadorCpf;
    private String pagadorNome;
    private String pagadorEndereco;
    private String pagadorBairro;
    private String pagadorCep;
    private String pagadorCepSufixo;
    private String pagadorCidade;
    private String pagadorUf;
    private TipoInscricao beneficiarioTipoInscricao;
    private String beneficiarioCnpj;
    private String beneficiarioNome;

    public SegmentoQ() {
        super(TipoRegistro.DETALHE_SEGMENTO_Q);
        this.codigoBanco = Constants.CODIGO_BANCO;
        this.numeroLote = 1L;
        this.codigoSegmento = "Q";
        this.codigoMovimento = CodigoMovimento.ENTRADA_BOLETO;
        this.pagadorTipoInscricao = TipoInscricao.CPF;
        this.beneficiarioTipoInscricao = TipoInscricao.CNPJ;
    }

    @Override
    protected void appendFields(StringBuilder linha) {
        // Posições 001 a 003 - Código do Banco
        linha.append(CNABUtil.formatNumber(Long.valueOf(codigoBanco), 3));
        // Posições 004 a 007 - Número do lote remessa
        linha.append(CNABUtil.formatNumber(numeroLote, 4));
        // Posição 008 - Tipo de registro
        linha.append(tipoRegistro.getCodigo());
        // Posição 009 - 013 - Número sequencial do registro no lote
        linha.append(CNABUtil.formatNumber(numeroSequencialRegistro, 5));
        // Posição 014 - 014 - Código Segmento do Registro Detalhe
        linha.append(CNABUtil.formatAlphanumeric(codigoSegmento, 1));
        // Posição 015 - 015 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 1));
        // Posição 016 - 017 - Código de Movimento Remessa
        linha.append(CNABUtil.formatNumber(Long.valueOf(codigoMovimento.getCodigo()), 2));
        // Posição 018 - 018 - Tipo de inscrição do Pagador
        linha.append(CNABUtil.formatNumber(Long.valueOf(pagadorTipoInscricao.getCodigo()), 1));
        // Posição 019 - 033 - Número de inscrição do Pagador
        linha.append(CNABUtil.formatNumber(Long.valueOf(pagadorCpf), 15));
        // Posição 034 - 073 - Nome do Pagador
        linha.append(CNABUtil.formatAlphanumeric(pagadorNome, 40));
        // Posição 074 - 113 - Endereço do Pagador
        linha.append(CNABUtil.formatAlphanumeric(pagadorEndereco, 40));
        // Posição 114 - 128 - Bairro do Pagador
        linha.append(CNABUtil.formatAlphanumeric(pagadorBairro, 15));
        // Posição 129 - 133 - CEP do Pagador
        linha.append(CNABUtil.formatNumber(Long.valueOf(pagadorCep), 5));
        // Posição 134 - 136 - Sufixo do CEP do Pagador
        linha.append(CNABUtil.formatNumber(Long.valueOf(pagadorCepSufixo), 3));
        // Posição 137 - 151 - Cidade do Pagador
        linha.append(CNABUtil.formatAlphanumeric(pagadorCidade, 15));
        // Posição 152 - 153 - Unidade da Federação do Pagador
        linha.append(CNABUtil.formatAlphanumeric(pagadorUf, 2));
        // Posição 154 - 154 - Tipo de inscrição do Beneficiário
        linha.append(CNABUtil.formatNumber(Long.valueOf(beneficiarioTipoInscricao.getCodigo()), 1));
        // Posição 155 - 169 - Número de inscrição do Beneficiário
        linha.append(CNABUtil.formatNumber(Long.valueOf(beneficiarioCnpj), 15));
        // Posição 170 - 209 - Nome do Beneficiário
        linha.append(CNABUtil.formatAlphanumeric(beneficiarioNome, 40));
        // Posição 210 - 240 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 31));
    }

    @Override
    protected void validateFields() {
        if (numeroSequencialRegistro == null) {
            throw new IllegalArgumentException("Número sequencial do registro no lote é obrigatório");
        }
        if (pagadorCpf == null) {
            throw new IllegalArgumentException("CPF do pagador é obrigatório");
        }
        if (pagadorNome == null) {
            throw new IllegalArgumentException("Nome do pagador é obrigatório");
        }
        if (pagadorEndereco == null) {
            throw new IllegalArgumentException("Endereço do pagador é obrigatório");
        }
        if (pagadorBairro == null) {
            throw new IllegalArgumentException("Bairro do pagador é obrigatório");
        }
        if (pagadorCep == null) {
            throw new IllegalArgumentException("CEP do pagador é obrigatório");
        }
        if (pagadorCepSufixo == null) {
            throw new IllegalArgumentException("Sufixo do CEP do pagador é obrigatório");
        }
        if (pagadorCidade == null) {
            throw new IllegalArgumentException("Cidade do pagador é obrigatório");
        }
        if (pagadorUf == null) {
            throw new IllegalArgumentException("UF do pagador é obrigatório");
        }
        if (beneficiarioCnpj == null) {
            throw new IllegalArgumentException("CNPJ do beneficiário é obrigatório");
        }
        if (beneficiarioNome == null) {
            throw new IllegalArgumentException("Nome do beneficiário é obrigatório");
        }
    }
}
