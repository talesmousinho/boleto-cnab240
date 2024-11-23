package br.com.boleto.cnab240.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosEmpresa {
    private String codigoTransmissao;
    private String cnpj;
    private String nomeEmpresa;
    private String agencia;
    private String digitoAgencia;
    private String conta;
    private String digitoConta;
}
