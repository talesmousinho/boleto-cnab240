# Boleto CNAB240

## Project Setup

### Prerequisites

- Java 11 or higher
- Maven 3.6.3 or higher

### Steps

1. Clone the repository:
    ```sh
    git clone https://github.com/talesmousinho/boleto-cnab240.git
    cd boleto-cnab240
    ```

2. Build the project:
    ```sh
    mvn clean install
    ```

## Running Tests

To run the tests using Maven, execute the following command:
```sh
mvn test
```

## Generating Remessa File

To generate the remessa file, you can use the `RemessaService` class. Here is an example of how to use it:

```java
import br.com.boleto.cnab240.model.DadosBoleto;
import br.com.boleto.cnab240.model.DadosEmpresa;
import br.com.boleto.cnab240.model.DadosRemessa;
import br.com.boleto.cnab240.service.RemessaService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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
        try {
            remessaService.gerarRemessa(dadosRemessa, boletos, generatedFilePath);
            System.out.println("Remessa file generated successfully at: " + generatedFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
