/**
*  Essa eh a classe de interface gráfica da aplicação "Misterio do Limoeiro"
* 
* @author  João Pedro Nogueira Lucas, Rhuan Campideli Borges.
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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela {
     private JFrame janela;
     private JTextArea listaItens;
     private JTextArea inventario;
     private JTextArea jogo;
     private JTextField comandos;
     private LogDeComandos logDeComandos;

     /**
      * Cria o jogo e incializa seu mapa interno.
      */
     public Tela() {
          janela = new JFrame();
          montarJanela();
          logDeComandos = new LogDeComandos();
     }

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

     public void montarJanela() {
          janela.setSize(1280, 720);
          janela.setLayout(new BorderLayout());
       
          JPanel painelEsquerda = new JPanel();
          painelEsquerda.setLayout(new FlowLayout());
          listaItens = new JTextArea("Lista de evidências: \n\n");
          listaItens.setEditable(false);
          painelEsquerda.add(listaItens);


       JPanel painelCentro = new JPanel();
       painelCentro.setLayout(new FlowLayout());
       ImageIcon imagem = new ImageIcon("./mapa.jpg");
       JLabel labelImagem = new JLabel(imagem);
       painelCentro.add(labelImagem);
       
       JPanel painelDireita = new JPanel();
       painelDireita.setLayout(new FlowLayout());
       inventario = new JTextArea("Inventário: \n\n - Mapa\n - Lupa\n - Carteira\n - Distintivo\n - Câmera\n");
       inventario.setEditable(false);
       painelDireita.add(inventario);

          /*
           * Embaixo, fica uma area de texto com scrollbar mostrando os textos que o jogo retorna ao jogador. Tambem fica um campo de texto para que o jogador digite os comandos
           */
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

       janela.add(painelEsquerda, BorderLayout.WEST);
       janela.add(painelCentro, BorderLayout.CENTER);
       janela.add(painelDireita, BorderLayout.EAST);
       janela.add(painelInferior, BorderLayout.SOUTH);
   }
   
   public void fecharJanela(){
        janela.setVisible(false);
   }

      /*
       * Metodo para adicionar uma string na area de texto que e mostrada ao usuario
       */
     public void adicionarNaTela(String s){
          jogo.append(s + "\n");
     }

     /*
       * Metodo para adicionar uma string na area de texto que mostra as evidencias
       */
     public void adicionarEvidencia(String s){
          listaItens.append(s + "\n");
     }
}
