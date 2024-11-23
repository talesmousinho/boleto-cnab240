package br.com.boleto.cnab240.service;

import br.com.boleto.cnab240.builder.RemessaFileBuilder;
import br.com.boleto.cnab240.model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RemessaService {
    private DadosEmpresa dadosEmpresa;

    public RemessaService(DadosEmpresa dadosEmpresa) {
        this.dadosEmpresa = dadosEmpresa;
    }

    public void gerarRemessa(DadosRemessa dadosRemessa, List<DadosBoleto> boletos, String filePath) throws IOException {
        RemessaFileBuilder remessaFileBuilder = new RemessaFileBuilder(dadosEmpresa)
            .withHeader(dadosRemessa);

        boletos.forEach(remessaFileBuilder::addBoleto);

        RemessaFile remessaFile = remessaFileBuilder.build();
        String fileContent = remessaFile.generateFile();
        Files.writeString(Path.of(filePath), fileContent, StandardCharsets.ISO_8859_1);
    }
}
