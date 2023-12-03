package itens;

public class Consumivel extends Item {

    private int usosRestantes;

    public Consumivel(String nome, String descricao, int usosRestantes) {
        super(nome, descricao);
        this.usosRestantes = usosRestantes;
    }

    public int getUsosRestantes() {
        return this.usosRestantes;
    }

    public void usar() {
        if (usosRestantes > 0) {
            this.usosRestantes--;
        }
    }
}
