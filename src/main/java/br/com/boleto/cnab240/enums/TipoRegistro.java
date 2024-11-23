package br.com.boleto.cnab240.enums;

import lombok.Getter;

@Getter
public enum TipoRegistro {
    HEADER_ARQUIVO("0", "Header do Arquivo"),
    HEADER_LOTE("1", "Header do Lote"),
    DETALHE_SEGMENTO_P("3", "Detalhe - Segmento P"),
    DETALHE_SEGMENTO_Q("3", "Detalhe - Segmento Q"),
    TRAILER_LOTE("5", "Trailer do Lote"),
    TRAILER_ARQUIVO("9", "Trailer do Arquivo");

    private final String codigo;
    private final String descricao;

    TipoRegistro(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return codigo + " - " + descricao;
    }
}
