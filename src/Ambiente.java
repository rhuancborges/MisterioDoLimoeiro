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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Ambiente 
{
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
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String descricao, String nomePersonagem, String nomeItem) {
        this.descricao = descricao;
        saidas = new HashMap<>();
        if (!nomePersonagem.equals("")){
            npc = new Personagem(nomePersonagem);
        }
        if (!nomeItem.equals("")){
            item = new Item();
        }
    }

    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * @param norte A saida norte.
     * @param leste A saida leste.
     * @param sul A saida sul.
     * @param oeste A saida oeste.
     */
    public void ajustarSaidas(Ambiente norte, Ambiente leste, Ambiente sul, Ambiente oeste) 
    {
        ajustarSaida("norte", norte);
        ajustarSaida("sul", sul);
        ajustarSaida("leste", leste);
        ajustarSaida("oeste", oeste);
    }

    private void ajustarSaida(String chave, Ambiente valor){
        if(valor != null){
            saidas.put(chave, valor);
        }
    }

    /**
     * @return A descricao do ambiente.
     */
    public String getPequenaDescricao(){
        return descricao;
    }

    public String getLongaDescricao(){
        return descricao + ".\n" + getStringSaida();
    }

    private String getStringSaida(){
        String returnString = "Saídas: ";
        Set<String> chaves = saidas.keySet();
        for(String saida : chaves){
            returnString += " " + saida;
        }
        return returnString;
    }

    public Ambiente getSaida(String direcao){
        return saidas.get(direcao);
    }

}
