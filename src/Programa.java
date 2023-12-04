/**
 * A classe Programa é responsável por executar todo o programa do jogo. Ela
 * contém o método main, que instancia um objeto da classe Jogo e chama o método
 * jogar() para iniciar o jogo.
 * 
 * @author Rhuan Campideli Borges
 * @version 2023.12.03
 */
public class Programa {

	/**
     * Método main que instancia um objeto da classe Jogo e chama o método jogar() para iniciar o jogo.
     */
	public static void main(String[] args) {
		Jogo jogo;
		jogo = Jogo.getInstance();

		jogo.jogar();

	}
}