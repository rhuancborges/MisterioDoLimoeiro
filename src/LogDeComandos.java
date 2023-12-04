import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * A classe LogDeComandos é responsável por registrar comandos digitados
 * em um arquivo de texto. Cada comando é gravado em uma nova linha no arquivo
 * especificado. O nome do arquivo padrão é "comandos_digitados.txt".
 * 
 * Os comandos são registrados em modo de apêndice, permitindo a continuidade do
 * registro mesmo após múltiplas execuções do programa.
 * 
 * Para utilizar esta classe, é criado uma instância de LogDeComandos e chamado
 * o método registrarComando() passando por parâmetro o comando que deseja
 * registrar.
 * 
 * @author João Pedro Nogueira Lucas
 * @version 2023.12.03
 */
public class LogDeComandos {
    private static final String nomeArquivo = "comandos_digitados.txt";

    /**
     * Metódo que registra um comando no arquivo de texto. O comando é adicionado
     * como uma nova linha no arquivo.
     * 
     * @param comando O comando a ser registrado no arquivo.
     * @throws IOException Se ocorrer uma falha ao salvar no arquivo.
     */
    public void registrarComando(String comando) {
        try {
            FileWriter arquivoEscrita = new FileWriter(nomeArquivo, true);
            BufferedWriter bufferEscrita = new BufferedWriter(arquivoEscrita);
            bufferEscrita.write(comando);
            bufferEscrita.newLine();
            bufferEscrita.close();
        } catch (IOException e) {
            System.out.println("Falha ao salvar no arquivo" + nomeArquivo);
        }
    }
}