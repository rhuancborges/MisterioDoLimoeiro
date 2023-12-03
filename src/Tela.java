/**
*  Essa eh a classe de interface gráfica da aplicação "Misterio do Limoeiro"
* 
* @author  Rhuan
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
      * Inicializa a janela (Jframe) e monta-a
     */
     public Tela() {
          janela = new JFrame();
          montarJanela();
          
     }

     /*
     * Metodo para exibir a janela, tornando-a visivel e adicionando um ActionListener no JTextField comandos para enviar a classe Jogo o que foi digitado e apagar o conteudo
     @param: jogo, um objeto do tipo Jogo representando a instancia de Jogo criada. Serve apenas para acessar a instancia de Jogo e enviar o comando digitado na Tela. 
     */
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
     /*
      * Metodo para montar a janela com seus componentes.
      */
     public void montarJanela(){
          janela.setSize(1280, 720);
          janela.setLayout(new BorderLayout());
          
          /*
           * Na esquerda, fica uma area de texto mostrando os itens recolhidos pelo mapa
           */
          JPanel painelEsquerda = new JPanel();
          painelEsquerda.setLayout(new FlowLayout());
          listaItens = new JTextArea("Lista de evidências: \n\n");
          listaItens.setEditable(false);
          painelEsquerda.add(listaItens);

          /*
          * Este trecho de código configura o painelEsquerda.
          * 
          * painelEsquerda é um componente de contêiner JPanel que 
          * está sendo configurado com um tamanho preferencial de 300x150 pixels, 
          * um layout BoxLayout (caixa) com orientação PAGE_AXIS (vertical), e uma
          * borda vazia com margens de 10 pixels em todos os lados.
          */
          painelEsquerda.setPreferredSize(new Dimension(300, 150));
          painelEsquerda.setLayout(new BoxLayout(painelEsquerda, BoxLayout.PAGE_AXIS));
          EmptyBorder borderLateral = new EmptyBorder(10, 10, 10, 10);
          painelEsquerda.setBorder(borderLateral);

          /*
           * No centro, fica uma imagem mostrando o mapa do jogo
           */
          JPanel painelCentro = new JPanel();
          painelCentro.setLayout(new FlowLayout());
          ImageIcon imagem = new ImageIcon("./src/mapa.jpg");
          JLabel labelImagem = new JLabel(imagem);
          painelCentro.add(labelImagem);
          
          /*
           * Na direira, fica uma area de texto mostrando o inventario do detetive
           */
          JPanel painelDireita = new JPanel();
          painelDireita.setLayout(new FlowLayout());
          inventario = new JTextArea("Inventário: \n\n - Mapa\n - Lupa\n - Carteira\n - Distintivo\n - Câmera\n");
          inventario.setEditable(false);
          painelDireita.add(inventario);

          /*
          * Este trecho de código configura o painelDireita.
          * 
          * painelDireita é um componente de contêiner JPanel que 
          * está sendo configurado com um tamanho preferencial de 300x150 pixels, 
          * um layout BoxLayout (caixa) com orientação PAGE_AXIS (vertical), e uma
          * borda vazia com margens de 10 pixels em todos os lados.
          */
          painelDireita.setPreferredSize(new Dimension(300, 150));
          painelDireita.setLayout(new BoxLayout(painelDireita, BoxLayout.PAGE_AXIS));
          painelDireita.setBorder(borderLateral);

          /*
           * Embaixo, fica uma area de texto com scrollbar mostrando os textos que o jogo retorna ao jogador. Tambem fica um campo de texto para que o jogador digite os comandos
           */
          JPanel painelInferior = new JPanel();
          painelInferior.setSize(1280, 100);;
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
     /*
      * Metodo para fechar a janela gerada e encerrar a execucao do programa
      */
     public void fecharJanela(){
          janela.setVisible(false);
          System.exit(0);
     }

      /*
       * Metodo para adicionar uma string na area de texto que e mostrada ao usuario
       */
     public void adicionarNaTela(String s){
          jogo.append(s + "\n");
     }
     }
