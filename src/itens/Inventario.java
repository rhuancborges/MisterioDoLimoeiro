package itens;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> itens;

    public Inventario() {
        itens = new ArrayList<Item>();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public void removerItem(Item item) {
        itens.remove(item);
    }

    public String getItens() {
        String returnString = "Itens no inventário:";
        for (Item item : itens) {
            returnString += "\n " + item.getNome() + " - " + item.getDescricao();
        }
        returnString += "\n";
        return returnString;
    }

    public boolean contemItem(String nomeItem) {
        for (Item item : itens) {
            if (item.getNome().equals(nomeItem)) {
                return true;
            }
        }
        return false;
    }

    public Item getItem(String nomeItem) {
        for (Item item : itens) {
            if (item.getNome().equals(nomeItem)) {
                return item;
            }
        }
        return null;
    }
}
