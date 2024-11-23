package br.com.boleto.cnab240.model;

import br.com.boleto.cnab240.enums.CodigoMovimento;
import br.com.boleto.cnab240.enums.TipoRegistro;
import br.com.boleto.cnab240.util.CNABUtil;
import br.com.boleto.cnab240.util.Constants;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class SegmentoP extends RegistroCNAB {
    private String codigoBanco;
    private Long numeroLote;
    private Long numeroSequencialRegistro;
    private String tipoCobranca;
    private CodigoMovimento codigoMovimento;
    private String formaCadastramento;
    private String codigoSegmento;
    private String agencia;
    private String digitoAgencia;
    private String conta;
    private String digitoConta;
    private String nossoNumero;
    private String carteira;
    private LocalDate dataVencimento;
    private BigDecimal valorTitulo;
    private String especieTitulo;
    private LocalDate dataEmissao;
    private BigDecimal jurosTaxaMensal;
    private String numeroDocumento;

    public SegmentoP() {
        super(TipoRegistro.DETALHE_SEGMENTO_P);
        this.codigoBanco = Constants.CODIGO_BANCO;
        this.nossoNumero = "0";
        this.numeroLote = 1L;
        this.codigoSegmento = "P";
        this.codigoMovimento = CodigoMovimento.ENTRADA_BOLETO;
        this.tipoCobranca = "1"; // 1 - Cobrança Simples (Eletrônica e com registro)
        this.formaCadastramento = "1"; // 1 - Cobrança Registrada
        this.especieTitulo = "02"; // 02 - Duplicata Mercantil
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
        linha.append(CNABUtil.formatAlphanumeric(codigoMovimento.getCodigo(), 2));
        // Posição 018 - 021 - Agência do Destinatária
        linha.append(CNABUtil.formatNumber(Long.valueOf(agencia), 4));
        // Posição 022 - 022 - Dígito da Agência do Destinatária
        linha.append(CNABUtil.formatNumber(Long.valueOf(digitoAgencia), 1));
        // Posição 023 - 031 - Conta Corrente do Destinatária
        linha.append(CNABUtil.formatNumber(Long.valueOf(conta), 9));
        // Posição 032 - 032 - Dígito da Conta Corrente do Destinatária
        linha.append(CNABUtil.formatNumber(Long.valueOf(digitoConta), 1));
        // Posição 033 - 041 - Conta cobrança Destinatária FIDC (zeros)
        linha.append(CNABUtil.formatNumber(null, 9));
        // Posição 042 - 042 - Dígito da Conta Cobrança Destinatária FIDC (zeros)
        linha.append(CNABUtil.formatNumber(null, 1));
        // Posição 043 - 044 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 2));
        // Posição 045 - 057 - Nosso Número
        linha.append(CNABUtil.formatNumber(Long.valueOf(nossoNumero), 13));
        // Posição 058 - 058 - Tipo de Cobrança
        linha.append(CNABUtil.formatNumber(Long.valueOf(tipoCobranca), 1));
        // Posição 059 - 059 - Forma de Cadastramento
        linha.append(CNABUtil.formatNumber(Long.valueOf(formaCadastramento), 1));
        // Posição 060 - 060 - Tipo de Documento
        linha.append(CNABUtil.formatNumber(1L, 1));
        // Posição 061 - 062 - Reserva (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 2));
        // Posição 063 - 077 - Número do Documento
        linha.append(CNABUtil.formatNumber(Long.valueOf(numeroDocumento), 15));
        // Posição 078 - 085 - Data de Vencimento do Título
        linha.append(CNABUtil.formatDate(dataVencimento));
        // Posição 086 - 100 - Valor Nominal do Título
        linha.append(CNABUtil.formatDecimal(valorTitulo, 15));
        // Posição 101 - 104 - Agência Encarregada da Cobrança FIDC
        linha.append(CNABUtil.formatNumber(null, 4));
        // Posição 105 - 105 - Dígito da Agência Encarregada da Cobrança FIDC
        linha.append(CNABUtil.formatNumber(null, 1));
        // Posição 106 - 106 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 1));
        // Posição 107 - 108 - Espécie do Título
        linha.append(CNABUtil.formatNumber(Long.valueOf(especieTitulo), 2));
        // Posição 109 - 109 - Identificação
        linha.append(CNABUtil.formatAlphanumeric("N", 1));
        // Posição 110 - 117 - Data de Emissão do Título
        linha.append(CNABUtil.formatDate(dataEmissao));
        // Posição 118 - 118 - Código do Juros de Mora
        linha.append(CNABUtil.formatNumber(2L, 1)); // 2 - Taxa Mensal
        // Posição 119 - 126 - Data do Juros de Mora
        linha.append(CNABUtil.formatDate(dataVencimento));
        // Posição 127 - 141 - Juros de Mora por Dia/Taxa Mensal
        linha.append(CNABUtil.formatDecimal(jurosTaxaMensal, 15));
        // Posição 142 - 142 - Código do Desconto 1
        linha.append(CNABUtil.formatNumber(null, 1));
        // Posição 143 - 150 - Data do Desconto 1
        linha.append(CNABUtil.formatDate(null));
        // Posição 151 - 165 - Valor/Percentual a ser Concedido
        linha.append(CNABUtil.formatDecimal(null, 15));
        // Posição 166 - 180 - Valor do IOF a ser Recolhido
        linha.append(CNABUtil.formatDecimal(null, 15));
        // Posição 181 - 195 - Valor do Abatimento
        linha.append(CNABUtil.formatDecimal(null, 15));
        // Posição 196 - 220 - Identificação do Título na Empresa
        linha.append(CNABUtil.formatAlphanumeric(null, 25));
        // Posição 221 - 221 - Código para Protesto
        linha.append(CNABUtil.formatNumber(0L, 1)); // 0 - Não Protestar
        // Posição 222 - 223 - Número de Dias para Protesto
        linha.append(CNABUtil.formatNumber(null, 2));
        // Posição 224 - 224 - Código para Baixa/Devolução
        linha.append(CNABUtil.formatNumber(1L, 1)); // 1 - Baixar
        // Posição 225 - 225 - Reservado (uso Banco)
        linha.append(CNABUtil.formatNumber(null, 1));
        // Posição 226 - 227 - Número de Dias para Baixa/Devolução
        linha.append(CNABUtil.formatNumber(null, 2));
        // Posição 228 - 229 - Código da Moeda
        linha.append(CNABUtil.formatNumber(0L, 2)); // 00 - Real
        // Posição 230 - 240 - Reservado (uso Banco)
        linha.append(CNABUtil.formatAlphanumeric(null, 11));
    }

    @Override
    protected void validateFields() {
        if (numeroSequencialRegistro == null) {
            throw new IllegalArgumentException("Número sequencial do registro é obrigatório");
        }
        if (agencia == null) {
            throw new IllegalArgumentException("Agência é obrigatória");
        }
        if (digitoAgencia == null) {
            throw new IllegalArgumentException("Dígito da Agência é obrigatório");
        }
        if (conta == null) {
            throw new IllegalArgumentException("Conta é obrigatória");
        }
        if (digitoConta == null) {
            throw new IllegalArgumentException("Dígito da Conta é obrigatório");
        }
        if (numeroDocumento == null) {
            throw new IllegalArgumentException("Número do Documento é obrigatório");
        }
        if (dataVencimento == null) {
            throw new IllegalArgumentException("Data de Vencimento é obrigatória");
        }
        if (dataEmissao == null) {
            throw new IllegalArgumentException("Data de Emissão é obrigatória");
        }
        if (valorTitulo == null) {
            throw new IllegalArgumentException("Valor do Título é obrigatório");
        }
    }
}
