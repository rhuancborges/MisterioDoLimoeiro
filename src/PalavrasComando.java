import java.util.ArrayList;

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
    private static ArrayList<String> comandosValidos;

    /**
     * Construtor - inicializa as palavras de comando.
     */
    public PalavrasComando() {
        comandosValidos = new ArrayList<String>();
    }

    /**
     * Insere os comandos validos no ArrayList.
     * 
     * @param comando Novo comando a ser inserido no arraylist.
     */
    public void setComando(String comando) {
        comandosValidos.add(comando);
    }

    /**
     * Verifica se uma dada String eh uma palavra de comando valida.
     * 
     * @return true se a string dada eh um comando valido,
     *         false se nao eh.
     */
    public boolean ehComando(String umaString) {
        for (String comando : comandosValidos) {
            if (comando.equals(umaString))
                return true;
        }
        // se chegamos aqui, a string nao foi encontrada nos comandos.
        return false;
    }

    public String getComandos() {
        String retorna = "";
        for (String comando : comandosValidos) {
            retorna += comando + "\n";
        }
        return retorna;
    }
}
