
/**
 * A classe Ambiente é responsável por representar um ambiente no jogo, com descrição,
 * saídas, um possível NPC (Personagem não jogador) e um item associado ao ambiente.
 * 
 * @author José Airton Rios Júnior e Lara Ramos Linhares
 * @version 2023.12.03
 */

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import itens.Item;
import itens.Evidencia;

public class Ambiente {
    private String nome;
    private String descricao;
    private Map<String, Ambiente> saidas;
    private Personagem npc;
    private Item item;

    /**
     * Cria um ambiente com a descrição passada, opcionalmente com um NPC e um item.
     *
     * @param nome O nome do ambiente.
     * @param descricao A descrição do ambiente.
     * @param item O item presente no ambiente.
     * @param nomePersonagem O nome do NPC presente no ambiente (pode ser nulo se não houver NPC).
     * @param descricaoPersonagem A descrição do NPC presente no ambiente.
     * @param falaInicial A fala inicial do NPC.
     * @param falaFinal A fala final do NPC.
     * @param evidenciaQueAfeta A evidência que afeta o NPC.
     * @param assassino Indica se o NPC é um assassino.
     */
    public Ambiente(String nome, String descricao, Item item, String nomePersonagem, String descricaoPersonagem,
            String falaInicial, String falaFinal, Evidencia evidenciaQueAfeta, boolean assassino) {
        saidas = new HashMap<>();
        this.nome = nome;
        this.descricao = descricao;
        if (nomePersonagem == null) {
            this.npc = null;
        } else {
            this.npc = new Personagem(nomePersonagem, descricaoPersonagem, falaInicial, falaFinal, evidenciaQueAfeta,
                    assassino);
        }
        this.item = item;
    }

    /**
     * Define as saídas do ambiente para direções específicas.
     *
     * @param norte O ambiente ao norte.
     * @param leste O ambiente ao leste.
     * @param sul O ambiente ao sul.
     * @param oeste O ambiente ao oeste.
     * @param cima O ambiente acima.
     * @param baixo O ambiente abaixo.
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
     * Retorna o nome do ambiente.
     *
     * @return O nome do ambiente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a descrição breve do ambiente.
     *
     * @return A descrição breve do ambiente.
     */
    public String getPequenaDescricao() {
        return descricao;
    }

    /**
     * Retorna a descrição completa do ambiente, incluindo suas saídas e a presença
     * de NPCs.
     *
     * @return A descrição completa do ambiente.
     */
    public String getLongaDescricao() {
        String retornoDescricao = descricao + ".\n" + getStringSaida() + "\n";
        if (npc != null) {
            retornoDescricao += "Você também vê " + npc.getNome() + " e observa " + npc.getNome()
                    + npc.getDescricao() + ".\n";
        } else {
            retornoDescricao += "Não há ninguém aqui.\n";
        }
        return retornoDescricao;
    }

    /**
     * Retorna a lista de saídas com os destinos correspondentes.
     *
     * @return A lista de saídas com os destinos correspondentes.
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
     * Retorna o personagem presente no ambiente.
     *
     * @return O personagem presente no ambiente.
     */
    public Personagem getPersonagem() {
        return npc;
    }

    /**
     * Retorna o ambiente para o qual uma determinada direção leva.
     *
     * @param direcao A direção desejada.
     * @return O ambiente para o qual a direção leva (ou null se não houver saída).
     */
    public Ambiente getSaida(String direcao) {
        return saidas.get(direcao);
    }

    /**
     * Retorna o nome do personagem presente no ambiente.
     *
     * @return O nome do personagem do ambiente.
     */
    public String getNomePersonagem() {
        if (npc != null) {
            return npc.getNome();
        }
        return "Não há ninguém aqui";
    }

    /**
     * Retorna a descrição do personagem presente no ambiente.
     *
     * @return A descrição do personagem do ambiente.
     */
    public String getDescricaoPersonagem() {
        if (npc != null) {
            return npc.getDescricao();
        }
        return "Não há ninguém aqui";
    }

    /**
     * Retorna a fala inicial do personagem presente no ambiente.
     *
     * @return A fala inicial do personagem do ambiente.
     */
    public String getFalaPersonagem() {
        if (npc != null) {
            return npc.getFalaAtual();
        }
        return "Não há ninguem aqui para conversar";
    }

    /**
     * Atualiza a fala do personagem com base nas evidências fornecidas.
     *
     * @param evidencias Lista de evidências que afetam a fala do personagem.
     */
    public void afetaFalaPersonagem(List<Evidencia> evidencias) {
        if (npc != null) {
            npc.afetaFala(evidencias);
        }
    }

    /**
     * Retorna o item presente no ambiente.
     *
     * @return O item do ambiente.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Remove o item do ambiente.
     * 
     * @return O item removido do ambiente.
     */
    public Item removerItem() {
        Item itemRemovido = item;
        item = null;
        return itemRemovido;
    }
}