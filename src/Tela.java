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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A classe Tela representa a interface gráfica da aplicação "Misterio do
 * Limoeiro". Ela exibe informações sobre o jogo, como lista de evidências,
 * inventário e interações do jogador. A classe utiliza a biblioteca Swing para
 * construir a interface gráfica.
 * 
 * @author João Pedro Nogueira Lucas e Rhuan Campideli Borges
 * @version 2023.12.03
 */
public class Tela {
     private JFrame janela;
     private JTextArea listaItens;
     private JTextArea inventario;
     private JTextArea jogo;
     private JTextField comandos;
     private LogDeComandos logDeComandos;

     /**
      * Construtor da classe Tela que inicializa a interface gráfica.
      */
     public Tela() {
          janela = new JFrame();
          montarJanela();
          logDeComandos = new LogDeComandos();
     }

     /**
      * Exibe a janela do jogo e configura o tratamento de eventos para os comandos
      * do jogador.
      * 
      * @param jogo Instância da classe Jogo associada à interface gráfica.
      */
     public void exibir(Jogo jogo) {
          janela.setVisible(true);
          comandos.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                    String comandoDigitado = comandos.getText();
                    jogo.receberComando(comandoDigitado);
                    logDeComandos.registrarComando(comandoDigitado);
                    comandos.setText("");
               }
          });
     }

     /**
      * Configura a estrutura da janela do jogo, dividindo-a em áreas para itens,
      * mapa, inventário e interação.
      */
     public void montarJanela() {
          janela.setSize(1280, 720);
          janela.setLayout(new BorderLayout());

          JPanel painelEsquerda = new JPanel();
          painelEsquerda.setLayout(new FlowLayout());
          listaItens = new JTextArea("Lista de evidências: \n\n");
          listaItens.setEditable(false);
          painelEsquerda.add(listaItens);

          // Este trecho de código configura o painelEsquerda.
          // painelEsquerda é um componente de contêiner JPanel que
          // está sendo configurado com um tamanho preferencial de 300x150 pixels,
          // um layout BoxLayout (caixa) com orientação PAGE_AXIS (vertical), e uma
          // borda vazia com margens de 10 pixels em todos os lados.
          painelEsquerda.setPreferredSize(new Dimension(300, 150));
          painelEsquerda.setLayout(new BoxLayout(painelEsquerda, BoxLayout.PAGE_AXIS));
          EmptyBorder borderLateral = new EmptyBorder(10, 10, 10, 10);
          painelEsquerda.setBorder(borderLateral);

          // No centro, fica uma imagem mostrando o mapa do jogo.

          JPanel painelCentro = new JPanel();
          painelCentro.setLayout(new FlowLayout());
          ImageIcon imagem = new ImageIcon("./src/mapa.jpg");
          JLabel labelImagem = new JLabel(imagem);
          painelCentro.add(labelImagem);

          // Na direita, há uma área de texto mostrando o invetário do detetive.

          JPanel painelDireita = new JPanel();
          painelDireita.setLayout(new FlowLayout());
          inventario = new JTextArea("Inventário: \n\n - Mapa\n - Lupa\n - Carteira\n - Distintivo\n - Câmera\n");
          inventario.setEditable(false);
          painelDireita.add(inventario);

          // Este trecho de código configura o painelDireita.
          // painelDireita é um componente de contêiner JPanel que
          // está sendo configurado com um tamanho preferencial de 300x150 pixels,
          // um layout BoxLayout (caixa) com orientação PAGE_AXIS (vertical), e uma
          // borda vazia com margens de 10 pixels em todos os lados.
          painelDireita.setPreferredSize(new Dimension(300, 150));
          painelDireita.setLayout(new BoxLayout(painelDireita, BoxLayout.PAGE_AXIS));
          painelDireita.setBorder(borderLateral);

          // Embaixo, fica uma area de texto com scrollbar mostrando os textos que o jogo
          // retorna ao jogador. Tambem fica um campo de texto para que o jogador digite
          // os comandos
          JPanel painelInferior = new JPanel();
          painelInferior.setLayout(new BoxLayout(painelInferior, BoxLayout.Y_AXIS));
          jogo = new JTextArea();
          JScrollPane scroll = new JScrollPane(jogo);
          JScrollBar barra = new JScrollBar(JScrollBar.VERTICAL);
          scroll.setVerticalScrollBar(barra);
          jogo.setEditable(false);
          scroll.setPreferredSize(new Dimension(1280, 170));
          JPanel interacao = new JPanel();
          interacao.setLayout(new BoxLayout(interacao, BoxLayout.X_AXIS));
          comandos = new JTextField();
          interacao.add(comandos);
          interacao.setPreferredSize(new Dimension(1280, 30));

          painelInferior.add(scroll);
          painelInferior.add(interacao);

          // Adiciona os painéis à janela usando BorderLayout.
          janela.add(painelEsquerda, BorderLayout.WEST);
          janela.add(painelCentro, BorderLayout.CENTER);
          janela.add(painelDireita, BorderLayout.EAST);
          janela.add(painelInferior, BorderLayout.SOUTH);
     }

     /**
      * Fecha a janela do jogo e encerra a execução do programa.
      */
     public void fecharJanela() {
          janela.setVisible(false);
          System.exit(0);
     }

     /**
      * Adiciona uma string à área de texto que é exibida ao usuário.
      * 
      * @param s String a ser adicionada à área de texto principal.
      */
     public void adicionarNaTela(String s) {
          jogo.append(s + "\n");
     }

     /**
      * Adiciona uma string à área de texto que é exibida ao usuário.
      * 
      * @param s String a ser adicionada à área de texto principal.
      */
     public void adicionarEvidencia(String s) {
          listaItens.append(s + "\n");
     }
}
