package br.com.boleto.cnab240.enums;

public enum TipoInscricao {
    CPF("1", "CPF"),
    CNPJ("2", "CNPJ");

    private final String codigo;
    private final String descricao;

    TipoInscricao(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return codigo + " - " + descricao;
    }
}
