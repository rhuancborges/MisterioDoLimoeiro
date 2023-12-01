package itens;

public class Usavel extends Item {

    private int usosRestantes;

    public Usavel(String nome, String descricao, int usosRestantes) {
        super(nome, descricao);
        this.usosRestantes = usosRestantes;
    }
}
