package itens;

/**
 * A classe Ferramenta representa um tipo de item no jogo. Ferramentas são
 * objetos genéricos que podem ter diversas utilidades no contexto do jogo. Esta
 * classe é uma subclasse de Item.
 * 
 * @author José Airton Rios Júnior
 * @version 2023.12.03
 */
public class Ferramenta extends Item {

    /**
     * Construtor da classe Ferramenta.
     * 
     * @param nome O nome da ferramenta.
     * @param descricao A descrição da ferramenta.
     */
    public Ferramenta(String nome, String descricao) {
        super(nome, descricao);
    }
}
