package itens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A classe Inventario representa o inventário do jogador no jogo "Misterio do
 * Limoeiro". O inventário armazena os itens coletados pelo jogador durante a
 * partida.
 * 
 * @author José Airton Rios Júnior
 * @version 2023.12.03
 */
public class Inventario {
    private ArrayList<Item> itens;

    /**
     * Construtor da classe Inventario.
     * Inicializa a lista de itens do inventário.
     */
    public Inventario() {
        itens = new ArrayList<Item>();
    }

    /**
     * Adiciona um item ao inventário.
     * 
     * @param item O item a ser adicionado.
     */
    public void adicionarItem(Item item) {
        itens.add(item);
    }

    /**
     * Remove um item do inventário.
     * 
     * @param item O item a ser removido.
     */
    public void removerItem(Item item) {
        itens.remove(item);
    }

    /**
     * Remove um item do inventário.
     * 
     * @param item O item a ser removido.
     */
    public String getItens() {
        String returnString = "Itens no inventário:";
        for (Item item : itens) {
            returnString += "\n " + item.getNome() + " - " + item.getDescricao();
        }
        returnString += "\n";
        return returnString;
    }

    /**
     * Verifica se o inventário contém um item específico.
     * 
     * @param nomeItem O nome do item a ser verificado.
     * @return true se o inventário contém o item, false caso contrário.
     */
    public boolean contemItem(String nomeItem) {
        for (Item item : itens) {
            if (item.getNome().equals(nomeItem)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se o inventário contém um item específico.
     * 
     * @param nomeItem O nome do item a ser verificado.
     * @return true se o inventário contém o item, false caso contrário.
     */
    public Item getItem(String nomeItem) {
        for (Item item : itens) {
            if (item.getNome().equals(nomeItem)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Obtém uma lista imutável de evidências presentes no inventário.
     * 
     * @return Uma lista de evidências.
     */
    public List<Evidencia> getEvidencias() {
        ArrayList<Evidencia> evidencias = new ArrayList<Evidencia>();
        for (Item item : itens) {
            if (item instanceof Evidencia) {
                evidencias.add((Evidencia) item);
            }
        }
        return Collections.unmodifiableList(evidencias);
    }
}