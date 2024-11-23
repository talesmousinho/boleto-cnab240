package br.com.boleto.cnab240.enums;

public enum TipoOperacao {
    REMESSA("1", "Remessa"),
    RETORNO("2", "Retorno");

    private final String codigo;
    private final String descricao;

    TipoOperacao(String codigo, String descricao) {
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
