package br.com.boleto.cnab240.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DadosBoleto {
    private String numeroDocumento;
    private BigDecimal valor;
    private LocalDate vencimento;
    private String pagadorCpf;
    private String pagadorNome;
    private String pagadorEndereco;
    private String pagadorBairro;
    private String pagadorCep;
    private String pagadorCepSufixo;
    private String pagadorCidade;
    private String pagadorUf;
}
