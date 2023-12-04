import java.util.Scanner;

/**
 * A classe Analisador é responsável por analisar os comandos inseridos pelo
 * usuário. Cada vez que é chamada, lê uma linha do terminal e tenta
 * interpretá-la como um comando de duas palavras. O resultado é retornado como
 * um objeto da classe Comando.
 *
 * O analisador possui um conjunto de palavras de comando conhecidas. Ele
 * compara a entrada do usuário com os comandos conhecidos e, se a entrada não
 * corresponder a um comando reconhecido, retorna um objeto de Comando marcado
 * como desconhecido.
 * 
 * @author José Airton Rios Júnior e Lara Ramos Linhares
 * @version 2023.12.03
 */
public class Analisador {
    private PalavrasComando palavrasDeComando; // Guarda todas as palavras de comando válidas.

    /**
     * Cria um analisador para ler do terminal.
     */
    public Analisador() {
        palavrasDeComando = new PalavrasComando();
    }

    /**
     * Obtém o próximo comando fornecido pelo usuário a partir da linha de entrada.
     *
     * @param linha A linha de entrada do usuário.
     * @return O próximo comando do usuário.
     */
    public Comando pegarComando(String linha) {
        String palavra1 = null;
        String palavra2 = null;

        Scanner tokenizer = new Scanner(linha);
        if (tokenizer.hasNext()) {
            palavra1 = tokenizer.next(); // Pega a primeira palavra.
            if (tokenizer.hasNext()) {
                palavra2 = tokenizer.next(); // Pega a segunda palavra.
            }
        }

        tokenizer.close();

        // Agora verifica se esta palavra é conhecida. Se for, cria um comando
        // com ela. Se não, cria um comando "null" (para comando desconhecido).
        if (palavrasDeComando.ehComando(palavra1)) {
            return new Comando(palavra1, palavra2);
        } else {
            return new Comando(null, palavra2);
        }
    }

    /**
     * Retorna uma string contendo a lista de comandos disponíveis.
     *
     * @return Lista de comandos disponíveis.
     */
    public String mostrarComandos() {
        return palavrasDeComando.getComandos();
    }

    /**
     * Define um novo comando com a respectiva descrição.
     *
     * @param comando O comando a ser definido.
     * @param descricao A descrição associada ao comando.
     */
    public void definirComando(String comando, String descricao) {
        palavrasDeComando.setComando(comando, descricao);
    }

    /**
     * Exclui um comando existente.
     *
     * @param comando O comando a ser excluído.
     */
    public void excluirComando(String comando) {
        palavrasDeComando.deletarComando(comando);
    }
}