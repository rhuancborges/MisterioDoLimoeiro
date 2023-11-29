
/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

import java.util.HashMap;
import java.util.Map;

public class Ambiente {
    private String nome;
    private String descricao;
    private Map<String, Ambiente> saidas;
    private Personagem npc;
    private Item item;

    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "um jardim aberto".
     * 
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String nome, String descricao, Personagem personagem, Item item) {
        saidas = new HashMap<>();

        this.nome = nome;
        this.descricao = descricao;
        this.npc = personagem;
        this.item = item; // IMPLEMENTARR!!!!!!!!
    }

    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * 
     * @param norte A saida norte.
     * @param leste A saida leste.
     * @param sul   A saida sul.
     * @param oeste A saida oeste.
     */
    public void setSaidas(Ambiente norte, Ambiente leste, Ambiente sul, Ambiente oeste, Ambiente cima, Ambiente baixo) {
        saidas.put("norte", norte);
        saidas.put("leste", leste);
        saidas.put("sul", sul);
        saidas.put("oeste", oeste);
        saidas.put("cima", cima);
        saidas.put("baixo", baixo);
    }

    /**
     * @return O nome do ambiente.
     */

    public String getNome() {
        return nome;
    }

    /**
     * @return A descricao do ambiente.
     */
    public String getPequenaDescricao() {
        return descricao;
    }

    /**
     * @return A descricao completa do ambiente e suas saidas.
     */

    public String getLongaDescricao() {
        String retornoDescricao = "Você está " + descricao + ".\n" + getStringSaida() + "\n";
        if (npc != null) {
            retornoDescricao += "Você também vê " + npc.getNome() + " e observa que " + npc.getNome() + " é "
                    + npc.getDescricao() + ".\n";
        } else {
            retornoDescricao += "Não há ninguém aqui.\n";
        }
        return retornoDescricao;
    }

    /**
     * @return A lista de saídas com o lugar que levam. Ex.: oeste: cozinha leste:
     *         Não há saída
     */

    private String getStringSaida() {
        String returnString = "Saídas: ";
        for (Map.Entry<String, Ambiente> item : saidas.entrySet()) {
            if (item.getValue() != null) {
                returnString += "\n " + item.getKey() + ": " + item.getValue().getNome();
            } else {
                returnString += "\n " + item.getKey() + ": " + "Não há saída";
            }
        }
        return returnString;
    }

    /**
     * @return O personagem do ambiente.
     */

    public Personagem getPersonagem() {
        return npc;
    }

    /**
     * @return O Ambiente que uma direção leva (ou null se não houver saída nessa
     *         direção).
     */

    public Ambiente getSaida(String direcao) {
        return saidas.get(direcao);
    }

}
