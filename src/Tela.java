/**
 * Classe Tela - representa a interface gráfica para o jogo de detetive "Mistério do Limoeiro".
 * Esta classe é responsável por criar e gerenciar a interface gráfica do jogo, exibindo informações
 * sobre evidências, inventário e interações com o jogador.
 *
 * @author João Pedro Nogueira Lucas, Lara Ramos Linhares, Rhuan Campideli Borges.
 * @version 1.0
 */
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class Tela {
    private JFrame janela;
    private JTextArea listaItens;
    private JTextArea inventario;
    private JTextArea jogo;
    private JTextField comandos;

    /**
     * Inicializa a janela (Jframe) e a monta.
     */
    public Tela() {
        janela = new JFrame();
        montarJanela();
    }

    /**
     * Exibe a janela, tornando-a visível, e adiciona um ActionListener no JTextField comandos para enviar o comando
     * digitado à classe Jogo e apagar o conteúdo.
     *
     * @param jogo Um objeto do tipo Jogo representando a instância de Jogo criada.
     *             Serve apenas para acessar a instância de Jogo e enviar o comando digitado na Tela.
     */
    public void exibir(Jogo jogo) {
        janela.setVisible(true);
        comandos.addActionListener(new ActionListener() {
            /**
             * Executa a ação de enviar o comando digitado para a classe Jogo.
             *
             * @param e O evento de ação.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(comandos.getText());
                jogo.receberComando(comandos.getText());
                comandos.setText("");
            }
        });
    }

    /**
     * Monta a janela com seus componentes.
     */
    public void montarJanela() {
        janela.setSize(1280, 720);
        janela.setLayout(new BorderLayout());

        /*
         * À esquerda, há uma área de texto mostrando os itens recolhidos pelo mapa.
         */
        JPanel painelEsquerda = new JPanel();
        painelEsquerda.setLayout(new FlowLayout());
        listaItens = new JTextArea("Lista de evidências: \n\n");
        listaItens.setEditable(false);
        painelEsquerda.add(listaItens);

        /*
         * Configuração do painelEsquerda:
         * - Tamanho preferencial de 300x150 pixels.
         * - Layout BoxLayout (caixa) com orientação PAGE_AXIS (vertical).
         * - Borda vazia com margens de 10 pixels em todos os lados.
         */
        painelEsquerda.setPreferredSize(new Dimension(300, 150));
        painelEsquerda.setLayout(new BoxLayout(painelEsquerda, BoxLayout.PAGE_AXIS));
        EmptyBorder borderLateral = new EmptyBorder(10, 10, 10, 10);
        painelEsquerda.setBorder(borderLateral);

        /*
         * No centro, há uma imagem mostrando o mapa do jogo.
         */
        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new FlowLayout());
        ImageIcon imagem = new ImageIcon("./src/mapa.jpg");
        JLabel labelImagem = new JLabel(imagem);
        painelCentro.add(labelImagem);

        /*
         * À direita, há uma área de texto mostrando o inventário do detetive.
         */
        JPanel painelDireita = new JPanel();
        painelDireita.setLayout(new FlowLayout());
        inventario = new JTextArea("Inventário: \n\n - Mapa\n - Lupa\n - Carteira\n - Distintivo\n - Câmera\n");
        inventario.setEditable(false);
        painelDireita.add(inventario);

        /*
         * Configuração do painelDireita:
         * - Tamanho preferencial de 300x150 pixels.
         * - Layout BoxLayout (caixa) com orientação PAGE_AXIS (vertical).
         * - Borda vazia com margens de 10 pixels em todos os lados.
         */
        painelDireita.setPreferredSize(new Dimension(300, 150));
        painelDireita.setLayout(new BoxLayout(painelDireita, BoxLayout.PAGE_AXIS));
        painelDireita.setBorder(borderLateral);

        /*
         * Embaixo, há uma área de texto com barra de rolagem mostrando os textos que o jogo retorna ao jogador.
         * Também há um campo de texto para que o jogador digite os comandos.
         */
        JPanel painelInferior = new JPanel();
        painelInferior.setSize(1280, 100);
        painelInferior.setLayout(new BoxLayout(painelInferior, BoxLayout.Y_AXIS));
        jogo = new JTextArea();
        JScrollPane scroll = new JScrollPane(jogo);
        JScrollBar barra = new JScrollBar(JScrollBar.VERTICAL);
        scroll.setVerticalScrollBar(barra);
        jogo.setEditable(false);
        JPanel interacao = new JPanel();
        interacao.setLayout(new BoxLayout(interacao, BoxLayout.X_AXIS));
        comandos = new JTextField();
        interacao.add(comandos);

        painelInferior.add(scroll);
        painelInferior.add(interacao);

        /*
         * Adiciona os painéis à janela usando BorderLayout:
         * - painelEsquerda à posição WEST (esquerda).
         * - painelCentro à posição CENTER (centro).
         * - painelDireita à posição EAST (direita).
         * - painelInferior à posição SOUTH (inferior).
         */
        janela.add(painelEsquerda, BorderLayout.WEST);
        janela.add(painelCentro, BorderLayout.CENTER);
        janela.add(painelDireita, BorderLayout.EAST);
        janela.add(painelInferior, BorderLayout.SOUTH);
    }

    /**
     * Fecha a janela gerada e encerra a execução do programa.
     */
    public void fecharJanela() {
        janela.setVisible(false);
        System.exit(0);
    }

    /**
     * Adiciona uma string na área de texto que é mostrada ao usuário.
     *
     * @param s A string a ser adicionada.
     */
    public void adicionarNaTela(String s) {
        jogo.append(s + "\n");
    }
}

