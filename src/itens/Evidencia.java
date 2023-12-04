package itens;

/**
 * A classe Evidencia representa um tipo específico de item no jogo. Evidências
 * são objetos importantes para o desenvolvimento do enredo e podem ser
 * coletadas pelo jogador. Esta classe é uma subclasse de Item.
 * 
 * @author José Airton Rios Júnior
 * @version 2023.12.03
 */
public class Evidencia extends Item {

    /**
     * Construtor da classe Evidencia.
     * 
     * @param nome O nome da evidência.
     * @param descricao A descrição da evidência.
     */
    public Evidencia(String nome, String descricao) {
        super(nome, descricao);
    }

}