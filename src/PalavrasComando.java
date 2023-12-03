import java.util.HashMap;
import java.util.Map;

/**
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 * 
 * Essa classe guarda uma enumeracao de todos os comandos conhecidos do
 * jogo. Ela eh usada no reconhecimento de comandos como eles sao digitados.
 *
 * @author Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

public class PalavrasComando {
    // um vetor constante que guarda todas as palavras de comandos validas
    private static HashMap<String, String> comandosValidos;

    /**
     * Construtor - inicializa as palavras de comando.
     */
    public PalavrasComando() {
        comandosValidos = new HashMap<String, String>();
    }

    /**
     * Insere os comandos validos na lista de comandos.
     * 
     * @param comando Novo comando a ser inserido no arraylist.
     */
    public void setComando(String comando, String descricao) {
        comandosValidos.put(comando, descricao);
    }

    /**
     * Remove um comando da lista de comandos.
     * 
     * @param comando Comando a ser removido do arraylist.
     */
    public void deletarComando(String comando) {
        comandosValidos.remove(comando);
    }

    /**
     * Verifica se uma dada String eh uma palavra de comando valida.
     * 
     * @return true se a string dada eh um comando valido,
     *         false se nao eh.
     */
    // for (Map.Entry<String, Ambiente> item : saidas.entrySet()) {
    public boolean ehComando(String umaString) {
        for (Map.Entry<String, String> comando : comandosValidos.entrySet()) {
            if (comando.getKey().equals(umaString)) {
                return true;
            }
        }
        // se chegamos aqui, a string nao foi encontrada nos comandos.
        return false;
    }

<<<<<<< HEAD
    public String getComandos() {
        String retorna = "";
        for (String comando : comandosValidos) {
            retorna += comando + "\n";
=======
    public void getComandos() {
        for (Map.Entry<String, String> comando : comandosValidos.entrySet()) {
            System.out.println(comando.getKey() + " - " + comando.getValue() + "\n");
>>>>>>> 671c63605e9b82fb8df79f1e911d736b382c615b
        }
        return retorna;
    }
}
