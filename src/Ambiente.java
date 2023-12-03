/**
 * Classe Ambiente - representa um ambiente no jogo de detetive "Mistério do Limoeiro".
 *
 * Um "Ambiente" representa uma localização no cenário do jogo, conectado a outros ambientes por meio de saídas.
 * Cada saída é nomeada como norte, sul, leste, oeste, cima e baixo. Para cada direção, o ambiente guarda uma referência
 * para o ambiente vizinho ou null se não houver saída naquela direção.
 *
 * @author José Airton Rios, Lara Ramos Linhares
 * @version 1.0
 */

import java.util.HashMap;
import java.util.Map;

import itens.Item;

public class Ambiente {
    private String nome;
    private String descricao;
    private Map<String, Ambiente> saidas;
    private Personagem npc;
    private Item item;

    /**
     * Cria um ambiente com o nome, descrição, item, nome do personagem, descrição do personagem, fala inicial e final fornecidos.
     *
     * @param nome O nome do ambiente.
     * @param descricao A descrição do ambiente.
     * @param item O item presente no ambiente.
     * @param nomePersonagem O nome do personagem no ambiente.
     * @param descricaoPersonagem A descrição do personagem no ambiente.
     * @param falaInicial A fala inicial do personagem no ambiente.
     * @param falaFinal A fala final do personagem no ambiente.
     */
    public Ambiente(String nome, String descricao, Item item, String nomePersonagem, String descricaoPersonagem,
            String falaInicial, String falaFinal) {
        saidas = new HashMap<>();

        this.nome = nome;
        this.descricao = descricao;
        if (nomePersonagem == null) {
            this.npc = null;
        } else {
            this.npc = new Personagem(nomePersonagem, descricaoPersonagem, falaInicial, falaFinal);
        }
        this.item = item;
    }

    /**
     * Define as saídas do ambiente para diferentes direções.
     *
     * @param norte A saída para o norte.
     * @param leste A saída para o leste.
     * @param sul A saída para o sul.
     * @param oeste A saída para o oeste.
     * @param cima A saída para cima.
     * @param baixo A saída para baixo.
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
     * @return A descrição do ambiente.
     */
    public String getPequenaDescricao() {
        return descricao;
    }

    /**
     * @return A descrição completa do ambiente, incluindo suas saídas e a presença de personagens.
     */
    public String getLongaDescricao() {
        String retornoDescricao = "Você está " + descricao + ".\n" + getStringSaida() + "\n";
        if (npc != null) {
            retornoDescricao += "Você também vê " + npc.getNome() + " e observa " + npc.getNome()
                    + npc.getDescricao() + ".\n";
        } else {
            retornoDescricao += "Não há ninguém aqui.\n";
        }
        return retornoDescricao;
    }

    /**
     * @return Uma representação textual das saídas disponíveis no ambiente.
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
     * @return O personagem presente no ambiente.
     */
    public Personagem getPersonagem() {
        return npc;
    }

    /**
     * @param direcao A direção desejada.
     * @return O ambiente para o qual a direção leva (ou null se não houver saída nessa direção).
     */
    public Ambiente getSaida(String direcao) {
        return saidas.get(direcao);
    }

    // ======================================PERSONAGENS======================================

    /**
     * @return O nome do personagem presente no ambiente.
     */
    public String getNomePersonagem() {
        if (npc != null) {
            return npc.getNome();
        }
        return "Não há ninguém aqui";
    }

    /**
     * @return A descrição do personagem presente no ambiente.
     */
    public String getDescricaoPersonagem() {
        if (npc != null) {
            return npc.getDescricao();
        }
        return "Não há ninguém aqui";
    }

    /**
     * @return A fala inicial do personagem presente no ambiente.
     */
    public String getFalaInicialPersonagem() {
        if (npc != null) {
            return npc.getFalaInicial();
        }
        return "Não há ninguém aqui para conversar";
    }

    /**
     * @return A fala final do personagem presente no ambiente.
     */
    public String getFalaFinalPersonagem() {
        if (npc != null) {
            return npc.getFalaFinal();
        }
        return "Não há ninguém aqui para conversar";
    }

    // ======================================ITENS======================================

    /**
     * @return O item presente no ambiente.
     */
    public Item getItem() {
        return item;
    }
}

