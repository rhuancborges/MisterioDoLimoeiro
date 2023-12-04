/**
 * A classe Comando é responsável por armazenar informações sobre um comando
 * inserido pelo usuário, que consiste em uma palavra de comando e,
 * opcionalmente, uma segunda palavra.
 *
 * Se o comando não for reconhecido, a palavra de comando é definida como null.
 * Caso o comando contenha apenas uma palavra, a segunda palavra é também
 * definida como null.
 * 
 * @author João Pedro Nogueira Lucas e Lara Ramos Linhares
 * @version 2023.12.03
 */

public class Comando {
    private String palavraDeComando;
    private String segundaPalavra;

    /**
     * Constrói um objeto Comando.
     *
     * @param primeiraPalavra A primeira palavra do comando. Pode ser null se o comando não foi reconhecido.
     * @param segundaPalavra  A segunda palavra do comando.
     */
    public Comando(String primeiraPalavra, String segundaPalavra) {
        palavraDeComando = primeiraPalavra;
        this.segundaPalavra = segundaPalavra;
    }

    /**
     * Obtém a palavra de comando deste comando.
     *
     * @return A palavra de comando. Pode ser null se o comando não foi reconhecido.
     */
    public String getPalavraDeComando() {
        return palavraDeComando;
    }

    /**
     * Obtém a segunda palavra deste comando.
     *
     * @return A segunda palavra do comando. Pode ser null se não existe segunda
     *         palavra.
     */
    public String getSegundaPalavra() {
        return segundaPalavra;
    }

    /**
     * Verifica se o comando não foi reconhecido.
     *
     * @return true se o comando não foi entendido; false caso contrário.
     */
    public boolean ehDesconhecido() {
        return (palavraDeComando == null);
    }

    /**
     * Verifica se o comando possui uma segunda palavra.
     *
     * @return true se o comando tem uma segunda palavra; false caso contrário.
     */
    public boolean temSegundaPalavra() {
        return (segundaPalavra != null);
    }
}