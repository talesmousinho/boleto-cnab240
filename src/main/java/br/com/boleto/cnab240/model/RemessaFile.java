package br.com.boleto.cnab240.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RemessaFile {
    private final HeaderArquivo headerArquivo;
    private final HeaderLote headerLote;
    private final List<SegmentoP> segmentosP;
    private final List<SegmentoQ> segmentosQ;
    private final TrailerLote trailerLote;
    private final TrailerArquivo trailerArquivo;

    public RemessaFile() {
        this.headerArquivo = new HeaderArquivo();
        this.headerLote = new HeaderLote();
        this.segmentosP = new ArrayList<>();
        this.segmentosQ = new ArrayList<>();
        this.trailerLote = new TrailerLote();
        this.trailerArquivo = new TrailerArquivo();
    }

    public void addSegmentoP(SegmentoP segmentoP) {
        segmentosP.add(segmentoP);
    }

    public void addSegmentoQ(SegmentoQ segmentoQ) {
        segmentosQ.add(segmentoQ);
    }

    public String generateFile() {
        StringBuilder arquivo = new StringBuilder();
        arquivo.append(headerArquivo.getConteudoRegistro()).append(System.lineSeparator());
        arquivo.append(headerLote.getConteudoRegistro()).append(System.lineSeparator());
        for (int i = 0; i < segmentosP.size(); i++) {
            arquivo.append(segmentosP.get(i).getConteudoRegistro()).append(System.lineSeparator());
            arquivo.append(segmentosQ.get(i).getConteudoRegistro()).append(System.lineSeparator());
        }
        // Quantidade de registros no lote é igual a 2 (header lote e trailer lote) + quantidade de segmentos P e Q
        trailerLote.setQuantidadeRegistrosLote(2 + (segmentosP.size() * 2L));
        arquivo.append(trailerLote.getConteudoRegistro()).append(System.lineSeparator());
        // Quantidade de registros no arquivo é igual a 4 (header arquivo, header lote, trailer lote e trailer arquivo) + quantidade de segmentos P e Q
        trailerArquivo.setQuantidadeRegistrosArquivo(4 + (segmentosP.size() * 2L));
        arquivo.append(trailerArquivo.getConteudoRegistro());
        return arquivo.toString();
    }
}
