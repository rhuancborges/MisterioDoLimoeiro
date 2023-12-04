import java.util.List;
import itens.Evidencia;

/**
 * A classe Personagem é responsável por representar um personagem no jogo. Cada
 * personagem possui um nome, fala inicial, fala final (se afetado
 * por evidência), evidência que afeta suas falas e se é ou não o assassino.
 * 
 * @author João Pedro Nogueira Lucas e José Airton Rios Júnior
 * @version 2023.12.03
 */
public class Personagem {
    private String nome;
    private Evidencia evidenciaQueAfeta;
    private String falaFinal;
    private String falaAtual;
    private boolean assassino;

    /**
     * Construtor para a classe Personagem.
     * 
     * @param nome O nome do personagem.
     * @param falaInicial A fala inicial do personagem.
     * @param falaFinal A fala final do personagem (se afetado por evidência).
     * @param evidenciaQueAfeta A evidência que afeta a fala do personagem.
     * @param assassino Indica se o personagem é o assassino.
     */
    public Personagem(String nome, String falaInicial, String falaFinal,
            Evidencia evidenciaQueAfeta, boolean assassino) {
        this.nome = nome;
        if (evidenciaQueAfeta != null) {
            this.evidenciaQueAfeta = evidenciaQueAfeta;
            this.falaFinal = falaFinal;
        }
        falaAtual = falaInicial;
        this.assassino = assassino;
    }

    /**
     * Obtém o nome do personagem.
     * 
     * @return O nome do personagem.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém a fala atual do personagem.
     * 
     * @return A fala atual do personagem.
     */
    public String getFalaAtual() {
        return falaAtual;
    }

    /**
     * Afeta a fala do personagem com base nas evidências fornecidas.
     * 
     * @param evidencias Lista de evidências no inventário do jogador.
     */
    public void afetaFala(List<Evidencia> evidencias) {
        if (evidenciaQueAfeta != null) {
            if (evidencias.contains(evidenciaQueAfeta)) {
                falaAtual = falaFinal;
            }
        }
    }

    /**
     * Verifica se o personagem é o assassino.
     * 
     * @return true se o personagem é o assassino, false caso contrário.
     */
    public boolean ehAssassino() {
        return assassino;
    }
}
