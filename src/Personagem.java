public class Personagem {
    private String nome;
    private String descricao;
    private String falaInicial;
    private String falaFinal;

    public Personagem(String nome, String descricao, String falaInicial, String falaFinal) {
        this.nome = nome;
        this.descricao = descricao;
        this.falaInicial = falaInicial;
        this.falaFinal = falaFinal;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getFalaInicial() {
        return falaInicial;
    }

    public String getFalaFinal() {
        return falaFinal;
    }
}
