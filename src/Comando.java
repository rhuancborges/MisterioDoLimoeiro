/**
 * Classe Comando - representa um comando fornecido pelo usuário no contexto
 * do jogo "Mistério do Limoeiro".
 * 
 * Esta classe guarda informações sobre um comando que foi digitado pelo 
 * usuário. Um comando atualmente consiste em duas strings: uma palavra
 * de comando e uma segunda palavra (por exemplo, se o campo for "pegar
 * mapa", então as duas strings obviamente serão "pegar" e "mapa").
 * 
 * Isso é usado assim: comandos já estão validados como comandos válidos.
 * Se o usuário entrou um comando inválido (uma palavra que não é
 * conhecida), então a palavra de comando é <null>.
 *
 * Se o comando tem só uma palavra, a segunda palavra é <null>.
 * 
 * @author Lara Ramos Linhares
 * @version 1.0
 */
public class Comando {
    private String palavraDeComando;
    private String segundaPalavra;

    /**
     * Cria um objeto comando. A primeira e segunda palavra devem ser 
     * fornecidas, mas qualquer uma (ou ambas) podem ser null.
     * 
     * @param primeiraPalavra A primeira palavra do comando. Null se
     *                        o comando não foi reconhecido.
     * @param segundaPalavra A segunda palavra do comando.
     */
    public Comando(String primeiraPalavra, String segundaPalavra) {
        palavraDeComando = primeiraPalavra;
        this.segundaPalavra = segundaPalavra;
    }

    /**
     * Retorna a palavra de comando (a primeira palavra) deste comando.
     * Se o comando não foi entendido, o resultado é null.
     * 
     * @return A palavra de comando.
     */
    public String getPalavraDeComando() {
        return palavraDeComando;
    }

    /**
     * Retorna a segunda palavra deste comando. Retorna null se 
     * não existe segunda palavra.
     * 
     * @return A segunda palavra do comando.
     */
    public String getSegundaPalavra() {
        return segundaPalavra;
    }

    /**
     * Verifica se o comando não foi entendido.
     * 
     * @return true se o comando não foi entendido.
     */
    public boolean ehDesconhecido() {
        return (palavraDeComando == null);
    }

    /**
     * Verifica se o comando tem uma segunda palavra.
     * 
     * @return true se o comando tem uma segunda palavra.
     */
    public boolean temSegundaPalavra() {
        return (segundaPalavra != null);
    }
}

