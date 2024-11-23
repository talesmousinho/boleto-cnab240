package br.com.boleto.cnab240.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DadosRemessa {
    private String numeroRemessa;
    private LocalDate dataGeracaoArquivo;
}
