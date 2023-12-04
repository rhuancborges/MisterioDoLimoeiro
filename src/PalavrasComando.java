import java.util.HashMap;
import java.util.Map;

/**
 * A classe PalavrasComando é responsável por manter uma enumeração de todos os
 * comandos conhecidos do jogo e é usada para reconhecer comandos conforme são
 * digitados.
 *
 * @author José Airton Rios Júnior e Lara Ramos Linhares
 * @version 2023.12.03
 */
public class PalavrasComando {
    // Um vetor constante que armazeba todas as palavras de comandos válidas
    private static HashMap<String, String> comandosValidos;

    /**
     * Construtor que inicializa as palavras de comando.
     */
    public PalavrasComando() {
        comandosValidos = new HashMap<String, String>();
    }

    /**
     * Insere um novo comando válido na lista de comandos.
     * 
     * @param comando   Novo comando a ser inserido no HashMap.
     * @param descricao Descrição associada ao comando.
     */
    public void setComando(String comando, String descricao) {
        comandosValidos.put(comando, descricao);
    }

    /**
     * Remove um comando da lista de comandos.
     * 
     * @param comando Comando a ser removido do HashMap.
     */
    public void deletarComando(String comando) {
        comandosValidos.remove(comando);
    }

    /**
     * Verifica se uma determinada String é uma palavra de comando válida.
     * 
     * @param umaString A string a ser verificada.
     * @return true se a string dada é um comando válido, false caso contrário.
     */
    public boolean ehComando(String umaString) {
        for (Map.Entry<String, String> comando : comandosValidos.entrySet()) {
            if (comando.getKey().equals(umaString)) {
                return true;
            }
        }
        // Se chegamos aqui, a string não foi encontrada nos comandos.
        return false;
    }

    /**
     * Retorna uma representação em String de todos os comandos válidos.
     * 
     * @return Uma String contendo todos os comandos válidos e suas descrições.
     */
    public String getComandos() {
        String retorna = "";
        for (Map.Entry<String, String> comando : comandosValidos.entrySet()) {
            retorna += comando.getKey() + " - " + comando.getValue() + "\n";
        }
        return retorna;
    }
}