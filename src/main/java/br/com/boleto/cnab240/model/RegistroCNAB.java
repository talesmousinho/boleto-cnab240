package br.com.boleto.cnab240.model;

import br.com.boleto.cnab240.enums.TipoRegistro;

public abstract class RegistroCNAB {
    protected TipoRegistro tipoRegistro;

    public RegistroCNAB(TipoRegistro tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getConteudoRegistro() {
        StringBuilder linha = new StringBuilder();
        validateFields();
        appendFields(linha);
        validateLength(linha.toString());
        return linha.toString();
    }

    protected abstract void appendFields(StringBuilder linha);

    protected abstract void validateFields();

    protected void validateLength(String content) {
        if (content.length() != 240) {
            throw new IllegalStateException(
                    "Invalid record length. Expected 240, got " + content.length());
        }
    }
}
