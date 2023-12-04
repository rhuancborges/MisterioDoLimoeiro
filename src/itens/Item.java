package itens;

/**
 * A classe Item é uma classe abstrata que representa um item no jogo "Misterio
 * do Limoeiro". Itens são objetos que podem ser coletados ou utilizados pelos
 * jogadores.
 * 
 * @author José Airton Rios Júnior
 * @version 2023.12.03
 */
public abstract class Item {
    private String nome;
    private String descricao;

    /**
     * Construtor da classe Item.
     * 
     * @param nome      O nome do item.
     * @param descricao A descrição do item.
     */
    public Item(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    /**
     * Obtém o nome do item.
     * 
     * @return O nome do item.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Obtém a descrição do item.
     * 
     * @return A descrição do item.
     */
    public String getDescricao() {
        return this.descricao;
    }

}
