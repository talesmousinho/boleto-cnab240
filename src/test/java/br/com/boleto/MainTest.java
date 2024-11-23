package br.com.boleto;

import br.com.boleto.cnab240.model.DadosBoleto;
import br.com.boleto.cnab240.model.DadosEmpresa;
import br.com.boleto.cnab240.model.DadosRemessa;
import br.com.boleto.cnab240.service.RemessaService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void testGeneratedRemessaFileContent() throws IOException {
        DadosEmpresa dadosEmpresa = new DadosEmpresa();
        dadosEmpresa.setCnpj("12345678901234");
        dadosEmpresa.setCodigoTransmissao("123456");
        dadosEmpresa.setNomeEmpresa("Empresa Teste");
        dadosEmpresa.setAgencia("1234");
        dadosEmpresa.setDigitoAgencia("5");
        dadosEmpresa.setConta("12345");
        dadosEmpresa.setDigitoConta("6");

        RemessaService remessaService = new RemessaService(dadosEmpresa);

        List<DadosBoleto> boletos = new ArrayList<>();
        DadosBoleto boleto = new DadosBoleto();
        boleto.setNumeroDocumento("123456");
        boleto.setValor(new BigDecimal("100.00"));
        boleto.setVencimento(LocalDate.now().plusDays(5));
        boleto.setPagadorCpf("12345678901");
        boleto.setPagadorNome("Fulano de Tal");
        boleto.setPagadorEndereco("Rua Tal, 123");
        boleto.setPagadorBairro("Bairro Tal");
        boleto.setPagadorCep("12345");
        boleto.setPagadorCepSufixo("678");
        boleto.setPagadorCidade("São Paulo");
        boleto.setPagadorUf("SP");
        boletos.add(boleto);

        DadosRemessa dadosRemessa = new DadosRemessa();
        dadosRemessa.setNumeroRemessa("1");
        dadosRemessa.setDataGeracaoArquivo(LocalDate.now());

        String generatedFilePath = "remessa.txt";
        remessaService.gerarRemessa(dadosRemessa, boletos, generatedFilePath);

        String generatedContent = new String(Files.readAllBytes(Paths.get(generatedFilePath)));
        String referenceFilePath = getClass().getClassLoader().getResource("remessa_referencia.txt").getPath();
        String referenceContent = new String(Files.readAllBytes(Paths.get(referenceFilePath)));

        assertEquals(referenceContent, generatedContent);
    }
}