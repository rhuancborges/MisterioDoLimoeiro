import java.util.List;

import itens.Evidencia;

public class Personagem {
    private String nome;
    private String descricao;
    private Evidencia evidenciaQueAfeta;
    private String falaFinal;
    private String falaAtual;
    private boolean assassino;

    public Personagem(String nome, String descricao, String falaInicial, String falaFinal,
            Evidencia evidenciaQueAfeta, boolean assassino) {
        this.nome = nome;
        this.descricao = descricao;
        if (evidenciaQueAfeta != null) {
            this.evidenciaQueAfeta = evidenciaQueAfeta;
            this.falaFinal = falaFinal;
        }
        falaAtual = falaInicial;
        this.assassino = assassino;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getFalaAtual() {
        return falaAtual;
    }

    public void afetaFala(List<Evidencia> evidencias) {
        if (evidenciaQueAfeta != null) {
            if (evidencias.contains(evidenciaQueAfeta)) {
                falaAtual = falaFinal;
            }
        }
    }

    public boolean ehAssassino() {
        return assassino;
    }
}
