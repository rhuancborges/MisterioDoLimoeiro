/**
 *  Essa eh a classe principal da aplicacao "World of Zull".
 *  "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *  Usuarios podem caminhar em um cenario. E eh tudo! Ele realmente
 *  precisa ser estendido para fazer algo interessante!
 * 
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e 
 *  executa os comandos que o analisador retorna.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Jogo 
{
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private JFrame janela;
    private JTextArea listaItens;
    private JTextArea inventario;
    private JTextArea jogo;
    private JTextArea localAtual;
    private JTextField comandos;
    private JButton enviar;
        
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() 
    {
        criarAmbientes();
        analisador = new Analisador();
        janela = new JFrame("Misterio do Limoeiro");
        montarJanela();
        acoesJanela();
        exibir();
    }

    public void exibir(){
        janela.setVisible(true);
    }

    public void montarJanela(){
        janela.setSize(1280, 720);
        janela.setLayout(new BorderLayout());

        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new FlowLayout());
        localAtual = new JTextArea();
        localAtual.setEditable(false);
        painelSuperior.add(localAtual);
        
        // O BoxLayout permite que os painéis tenham tamanho preferenciais ou fixos
        painelSuperior.setPreferredSize(new Dimension(500, 150));
        painelSuperior.setLayout(new BoxLayout(painelSuperior, BoxLayout.PAGE_AXIS));
        // Cria uma margem entre os painéiss
        EmptyBorder borderSuperior = new EmptyBorder(10, 10, 10, 10);
        painelSuperior.setBorder(borderSuperior);
  
        JPanel painelEsquerda = new JPanel();
        painelEsquerda.setLayout(new FlowLayout());
        listaItens = new JTextArea("Lista de evidências: \n\n");
        listaItens.setEditable(false);
        painelEsquerda.add(listaItens);

        painelEsquerda.setPreferredSize(new Dimension(300, 520));
        painelEsquerda.setLayout(new BoxLayout(painelEsquerda, BoxLayout.PAGE_AXIS));
        EmptyBorder borderEsquerda = new EmptyBorder(0, 10, 10, 10);
        painelEsquerda.setBorder(borderEsquerda);

        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new FlowLayout());
        ImageIcon imagem = new ImageIcon("./src/mapa.jpg");
        JLabel labelImagem = new JLabel(imagem);
        painelCentro.add(labelImagem);
        painelCentro.setPreferredSize(new Dimension(800, 520));
        
        JPanel painelDireita = new JPanel();
        painelDireita.setLayout(new FlowLayout());
        inventario = new JTextArea("Inventário: \n\n - Mapa\n - Lupa\n - Carteira\n - Distintivo\n - Câmera\n");
        inventario.setEditable(false);
        painelDireita.add(inventario);
        
        painelDireita.setPreferredSize(new Dimension(300, 520));
        painelDireita.setLayout(new BoxLayout(painelDireita, BoxLayout.PAGE_AXIS));
        EmptyBorder borderDireita = new EmptyBorder(0, 10, 10, 10);
        painelDireita.setBorder(borderDireita);

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
    
    public void acoesJanela(){
        enviar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                comandos.getText();
                comandos.setText("");
            }
        });
    }

    /* */
    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarAmbientes()
    {

        Ambiente delegacia, praca, casaDaMonica, casaDaMagali, casaDoCebola, escola, cinema, 
        padaria, bosque, galpao, lago, pastelariaJuca;
      
        // cria os ambientes

        // COMPLETAR PERSONAGENS E ITENS

        delegacia = new Ambiente("na delegacia", "Jeremias", "");
        praca = new Ambiente("na praça central", "", "");
        casaDaMagali = new Ambiente("na casa da Magali", "", "");
        casaDaMonica = new Ambiente("na casa da Mônica", "", "");
        casaDoCebola = new Ambiente("na casa do Cebola", "", "");
        escola = new Ambiente("na escola", "", "");
        cinema = new Ambiente("no cinema", "", "");
        padaria = new Ambiente("na padaria", "", "");
        bosque = new Ambiente("no bosque", "", "");
        galpao = new Ambiente("no galpão", "", "");
        lago = new Ambiente("no lago", "", "");
        pastelariaJuca = new Ambiente("na pastelaria do Juca", "", "");


        // ARRUMAR SAÍDAS
        // inicializa as saidas dos ambientes
        delegacia.setSaidas(null, praca, null, null, null, null);
        praca.setSaidas(casaDaMonica, pastelariaJuca, padaria, delegacia, null, null);
        casaDaMagali.setSaidas(null, casaDoCebola, pastelariaJuca, casaDaMonica,null, null);
        casaDaMonica.setSaidas(null, casaDaMagali, praca, null, null, null);
        casaDoCebola.setSaidas(null, null, lago, casaDaMagali, null, null);
        escola.setSaidas(null, null, null, lago, null, null);
        cinema.setSaidas(padaria, bosque, null, padaria, null, null);
        padaria.setSaidas(praca, cinema, null, null, null, null);
        bosque.setSaidas(lago, null, null, cinema, null, galpao);
        galpao.setSaidas(null, praca, null, null, bosque, null);
        lago.setSaidas(casaDoCebola, escola, bosque, pastelariaJuca, null, null);
        pastelariaJuca.setSaidas(casaDaMagali, lago, cinema, praca, null, null);

        // o jogo comeca na delegacia
        ambienteAtual = delegacia;
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() 
    {            
        imprimirBoasVindas();
        acoesJanela();

        // Não permite que o usuário edite a interface
        jogo.setEditable(false);
        listaItens.setEditable(false);
        inventario.setEditable(false);

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
                
        boolean terminado = false;
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Até mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas()
    {
        
        jogo.append("\nBem-vindo, Detetive!\n");
        jogo.append("Sou o delegado Jeremias, do bairro do Limoeiro. Você está aqui para resolver um crime que está assombrando o nosso querido bairro\n");
        jogo.append("Infelizmente, houve um homicídio há uma semana e o assassino está à solta\n\n");
        jogo.append("Seu objetivo é reunir provas, identificar o suspeito e voltar à delegacia com o nome do assassino\n");
        jogo.append("Para te ajudar nessa jornada, você vai receber um mapa do bairro, uma lupa, uma carteira, um distintivo e uma câmera\n");
        jogo.append("Explore todo o bairro e solucione o caso. Contamos com você, detetive!\n\n");

        jogo.append("Digite 'ajuda' se voce precisar de ajuda\n.");
        
        localAtual.append("Voce está " + ambienteAtual.getLongaDescricao() + "\n\n");
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) 
    {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            System.out.println("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            irParaAmbiente(comando);
        }
        else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        }

        return querSair;
    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de 
     * palavras de comando
     */
    private void imprimirAjuda() 
    {
        System.out.println("Você está investigando um homicício. Você está sozinho. Você caminha");
        System.out.println("pela Cidade do Limoeiro.");
        System.out.println();
        System.out.println("Suas palavras de comando são:");
        System.out.println("   ir sair ajuda");
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no 
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando) 
    {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir pra onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proxAmbiente = ambienteAtual.getSaida(direcao);

        if(proxAmbiente == null){
            System.out.println("Não há nenhum caminho!");
        } else {
            ambienteAtual = proxAmbiente;
            System.out.println(ambienteAtual.getLongaDescricao());
        }
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) 
    {
        if(comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        }
        else {
            return true;  // sinaliza que nos queremos sair
        }
    }
    
}

