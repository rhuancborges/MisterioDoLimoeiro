import java.util.Scanner;

/**
 * Analisador - responsável por interpretar os comandos fornecidos pelo usuário
 * no contexto do jogo "Mistério do Limoeiro".
 * 
 * Este analisador lê a entrada do usuário e tenta interpretá-la como um
 * comando de aventura. Cada vez que é chamado, ele lê uma linha do terminal
 * e tenta interpretar a linha como um comando de duas palavras. Ele retorna
 * o comando como um objeto da classe Comando.
 *
 * O analisador possui um conjunto de palavras de comando conhecidas. Ele compara
 * a entrada do usuário com os comandos conhecidos, e se a entrada não é um
 * dos comandos conhecidos, ele retorna um objeto comando marcado como
 * um comando desconhecido.
 * 
 * @author José Airton Rios, Lara Ramos Linhares
 * @version 1.0
 */
public class Analisador {
    private PalavrasComando palavrasDeComando; 

    /**
     * Cria um analisador para ler do terminal.
     */
    public Analisador() {
        palavrasDeComando = new PalavrasComando();
    }

    /**
     * Lê a linha fornecida e retorna o próximo comando do usuário.
     * 
     * @param linha A linha de entrada fornecida pelo usuário.
     * @return O próximo comando do usuário.
     */
    public Comando pegarComando(String linha) {
        String palavra1 = null;
        String palavra2 = null;

        Scanner tokenizer = new Scanner(linha);
        if (tokenizer.hasNext()) {
            palavra1 = tokenizer.next(); 
            if (tokenizer.hasNext()) {
                palavra2 = tokenizer.next(); 
                if (palavra2.equals("com")) {
                    palavra2 = tokenizer.next();
                }
            }
        }

        if (palavrasDeComando.ehComando(palavra1)) {
            return new Comando(palavra1, palavra2);
        } else {
            return new Comando(null, palavra2);
        }
    }

    /**
     * Retorna uma representação em string dos comandos disponíveis no jogo.
     * 
     * @return Os comandos disponíveis no jogo.
     */
    public String mostrarComandos() {
        return palavrasDeComando.getComandos();
    }

    /**
     * Define um novo comando para ser reconhecido pelo analisador.
     * 
     * @param comando O novo comando a ser adicionado.
     */
    public void definirComando(String comando) {
        palavrasDeComando.setComando(comando);
    }
}

