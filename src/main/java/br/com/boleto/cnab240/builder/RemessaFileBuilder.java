package br.com.boleto.cnab240.builder;

import br.com.boleto.cnab240.model.*;

import java.time.LocalDate;

public class RemessaFileBuilder {
    private final RemessaFile remessaFile;
    private final DadosEmpresa dadosEmpresa;

    public RemessaFileBuilder(DadosEmpresa dadosEmpresa) {
        this.remessaFile = new RemessaFile();
        this.dadosEmpresa = dadosEmpresa;
    }

    public RemessaFileBuilder withHeader(DadosRemessa dadosRemessa) {
        remessaFile.getHeaderArquivo().setNumeroInscricaoEmpresa(dadosEmpresa.getCnpj());
        remessaFile.getHeaderArquivo().setCodigoTransmissao(dadosEmpresa.getCodigoTransmissao());
        remessaFile.getHeaderArquivo().setNomeEmpresa(dadosEmpresa.getNomeEmpresa());
        remessaFile.getHeaderArquivo().setDataGeracaoArquivo(dadosRemessa.getDataGeracaoArquivo());
        remessaFile.getHeaderLote().setNumeroInscricaoEmpresa(dadosEmpresa.getCnpj());
        remessaFile.getHeaderLote().setCodigoTransmissao(dadosEmpresa.getCodigoTransmissao());
        remessaFile.getHeaderLote().setNomeBeneficiario(dadosEmpresa.getNomeEmpresa());
        remessaFile.getHeaderLote().setNumeroRemessaRetorno(dadosRemessa.getNumeroRemessa());
        remessaFile.getHeaderLote().setDataGravacaoRemessaRetorno(dadosRemessa.getDataGeracaoArquivo());
        return this;
    }

    public RemessaFileBuilder addBoleto(DadosBoleto boleto) {
        SegmentoP segmentoP = new SegmentoP();
        segmentoP.setNumeroSequencialRegistro(remessaFile.getSegmentosP().size() + 1L);
        segmentoP.setAgencia(dadosEmpresa.getAgencia());
        segmentoP.setDigitoAgencia(dadosEmpresa.getDigitoAgencia());
        segmentoP.setConta(dadosEmpresa.getConta());
        segmentoP.setDigitoConta(dadosEmpresa.getDigitoConta());
        segmentoP.setNumeroDocumento(boleto.getNumeroDocumento());
        segmentoP.setDataEmissao(LocalDate.now());
        segmentoP.setDataVencimento(boleto.getVencimento());
        segmentoP.setValorTitulo(boleto.getValor());
        remessaFile.addSegmentoP(segmentoP);

        SegmentoQ segmentoQ = new SegmentoQ();
        segmentoQ.setNumeroSequencialRegistro(remessaFile.getSegmentosQ().size() + 1L);
        segmentoQ.setPagadorNome(boleto.getPagadorNome());
        segmentoQ.setPagadorCpf(boleto.getPagadorCpf());
        segmentoQ.setPagadorEndereco(boleto.getPagadorEndereco());
        segmentoQ.setPagadorBairro(boleto.getPagadorBairro());
        segmentoQ.setPagadorCep(boleto.getPagadorCep());
        segmentoQ.setPagadorCepSufixo(boleto.getPagadorCepSufixo());
        segmentoQ.setPagadorCidade(boleto.getPagadorCidade());
        segmentoQ.setPagadorUf(boleto.getPagadorUf());
        segmentoQ.setBeneficiarioCnpj(dadosEmpresa.getCnpj());
        segmentoQ.setBeneficiarioNome(dadosEmpresa.getNomeEmpresa());
        remessaFile.addSegmentoQ(segmentoQ);

        return this;
    }

    public RemessaFile build() {
        return remessaFile;
    }
}
