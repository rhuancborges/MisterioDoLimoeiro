/**
*  Essa eh a classe de interface gráfica da aplicação "Misterio do Limoeiro"
* 
* @author  Rhuan
* @version 1.0
*/

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela 
{
   private Analisador analisador;
   private String textoAtual;
   private Ambiente ambienteAtual;
   private JFrame janela;
   private JTextArea listaItens;
   private JTextArea inventario;
   private JTextArea jogo;
   private JTextArea localAtual;
   private JTextField comandos;
   private JButton enviar;
   private boolean flagRetorno;
       
   /**
    * Cria o jogo e incializa seu mapa interno.
    */
   public Tela() 
   {
        janela = new JFrame();
        montarJanela();
        flagRetorno = false;
       
   }

   public void exibir(Jogo jogo){
        janela.setVisible(true); 
        comandos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(comandos.getText());
                jogo.receberComando(comandos.getText());
                comandos.setText("");
            }
        });
   }

     public void montarJanela(){
          janela.setSize(1280, 720);
          janela.setLayout(new BorderLayout());

          JPanel painelSuperior = new JPanel();
          painelSuperior.setLayout(new FlowLayout());
          localAtual = new JTextArea();
          localAtual.setEditable(false);
          painelSuperior.add(localAtual);
       
          JPanel painelEsquerda = new JPanel();
          painelEsquerda.setLayout(new FlowLayout());
          listaItens = new JTextArea("Lista de evidências: \n\n");
          listaItens.setEditable(false);
          painelEsquerda.add(listaItens);

          painelSuperior.setPreferredSize(new Dimension(500, 150));
          painelSuperior.setLayout(new BoxLayout(painelSuperior, BoxLayout.PAGE_AXIS));
          EmptyBorder borderSuperior = new EmptyBorder(10, 10, 10, 10);
          painelSuperior.setBorder(borderSuperior);

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

          JPanel painelInferior = new JPanel();
          painelInferior.setLayout(new BoxLayout(painelInferior, BoxLayout.Y_AXIS));
          jogo = new JTextArea();
          JPanel interacao = new JPanel();
          interacao.setLayout(new BoxLayout(interacao, BoxLayout.X_AXIS));
          comandos = new JTextField();
          enviar = new JButton("Executar Comando");
          interacao.add(comandos);
          interacao.add(enviar);

          painelInferior.add(jogo);
          painelInferior.add(interacao);

          janela.add(painelSuperior, BorderLayout.NORTH);
          janela.add(painelEsquerda, BorderLayout.WEST);
          janela.add(painelCentro, BorderLayout.CENTER);
          janela.add(painelDireita, BorderLayout.EAST);
          janela.add(painelInferior, BorderLayout.SOUTH);
   }
   
   public void fecharJanela(){
        janela.setVisible(false);
   }

   public void adicionarNaTela(String s){
        jogo.append(s + "\n");
   }
}
