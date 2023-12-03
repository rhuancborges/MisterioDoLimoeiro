
/**
 *  Essa eh a classe principal da aplicação "Misterio do Limoeiro"
 *  "Misterio do Limoeiro" eh um jogo de investigacao baseado em texto.
 *  Usuarios podem caminhar entre cenarios, capturar itens e conversar com personagens. 
 *  A ideia eh o jogador descobrir quem eh o assassino da historia, deduzindo pistas ao longo
 * dos cenarios e delatar ao delegado.
 *  Se o delegado receber o nome correto do assassino, o jogador ganha o jogo. Caso receba outro
 * nome, o jogo termina e o jogador perde, pois o assassino fugiu.
 * 
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar". 
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, cria o analisador e comeca o jogo. Ela avalia,
 *  executa os comandos que o analisador retorna e tambem funciona como interface grafica
 * 
 * @author  Rhuan, Lara
 * @version 1.1
 */

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import itens.*;

public class Jogo {
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private Inventario inventario;
    private String assassino;

    public Jogo() {
        analisador = new Analisador();
        inventario = new Inventario();
        criarAmbientes();
        gerarComandos();
        gerarInventarioInicial();
    }

    /*
     * Cria todos os ambientes e liga as saidas deles.
     * Cada ambiente tem até 6 saídas, uma para cada direção cardeal e uma para cima
     * e uma para baixo.
     * Cada ambiente tem um item, que é uma evidência.
     * Cada ambiente tem um personagem, que é um suspeito.
     */

    private void criarAmbientes() {
        Ambiente delegacia, praca, casaDaMonica, casaDaMagali, casaDoCebola, escola, cinema,
                padaria, bosque, galpao, lago, pastelariaJuca;

        Evidencia veneno, bolsa, macarico, faca, pingente, balde, sansao;
        veneno = new Evidencia("veneno", "Um veneno para ratos");
        bolsa = new Evidencia("bolsa", "A bolsa da Carminha FruFru");
        macarico = new Evidencia("macarico", "Um maçarico comum");
        faca = new Evidencia("faca", "Uma faca de cozinha coberta de sangue");
        pingente = new Evidencia("pingente", "Um pingente de ouro com as iniciais 'CF'");
        balde = new Evidencia("balde", "Um balde com água");
        sansao = new Evidencia("sansao", "Um Sansão de pelúcia, porém coberto de sangue");

        this.assassino = "Magali"; // define o assassino

        // cria os ambientes

        // COMPLETAR COM ITENS

        delegacia = new Ambiente("Delegacia", "Você está na delegacia", null, "Jeremias",
                "Alto, magro, cabelo preto, olhos castanhos, usa óculos",
                "Olá, sou o Jeremias, o delegado da cidade. Estou investigando o desaparecimento do Cebola e por isso chamamos você, para que nos ajude com a busca.",
                "Obrigado por me ajudar a encontrar o Cebola. Você é um ótimo detetive!", null, false);

        praca = new Ambiente("Praca", "Você está na praça central", null, "Denise", " a Denise",
                "Obrigada por vir, detetive! Achei o corpo da minha amiga no lago e fiquei desesperadíssima",
                "Isso me lembrou da última vez que vi a Carminha! Beijando um garoto na padaria", faca, false);

        casaDaMagali = new Ambiente("Casa da Magali", "Você está na casa da Magali", null, "Magali", " a Magali",
                "Oi, detetive, não repara nesse tanto de melancia que comi. Estou mal pela morte da minha amiga!",
                "Onde achou isso? Acho que temos que respeitar a privacidade dela!", bolsa, true);

        casaDaMonica = new Ambiente("Casa da Monica", "Você está na casa da Mônica", null, "Mônica", " a Mônica",
                "AAAAAA, o Sansão não está aqui! Eu pego aquele peste do Cebolinha!",
                "Sim, eu a usei! Mas eu precisei usá-la", sansao, false);

        casaDoCebola = new Ambiente("Casa do Cebola", "Você está na casa do Cebola", veneno, "Cascão", " o Cascão",
                "O Cebolinha desapareceu, estou preocupado! Claro que me preocupo com a morte da Carminha, mas é diferente",
                "Tá bom, eu falo a verdade! Antes da notícia da morte da Carminha, o Cebolinha estava aflito, me entregou o sansão e fugiu para a fazenda do Chico Bento",
                balde, false);

        escola = new Ambiente("Escola", "Você está na escola", bolsa, "Marina", " a Marina",
                "Quem faria uma coisa dessas? Realmente é um crime terrível",
                "Eu me lembro de tudo! A Mônica sabe quem é o assassino e apagou a minha memória quando eu e o Franjinha quisemos denunciar",
                pingente, false);

        cinema = new Ambiente("Cinema", "Você está no cinema", pingente, "Franjinha", " o franjinha",
                "Eu não sei de nada! Não saí do meu laboratório em nenhum momento",
                "A Arma... A arma que fiz está aí", sansao, false);

        padaria = new Ambiente("Padaria", "Você está na padaria", faca, "Quinzinho", " o quinzinho",
                "Desculpa a demora em atendê-lo! Estava enfaixando meu machucado",
                "Estou chocado! Não posso acreditar que ela chegou a esse ponto!", bolsa, false);

        bosque = new Ambiente("Bosque", "Você está no bosque", sansao, null, null, null, null, null, false);

        galpao = new Ambiente("Galpao", "Você está no galpão", balde, null, null, null, null, null, false);

        lago = new Ambiente("Lago", "Você está em frente ao lago", null, null, null, null, null, null, false);

        pastelariaJuca = new Ambiente("Pastelaria do Juca", "Você está na pastelaria do Juca", macarico, "Juca",
                "o Juca",
                "Eu tenho que conversar mesmo? Não estou me sentindo muito confortável",
                "Ai meu Pai! A culpa foi toda minha!! Como fui capaz de deixar isso acontecer?", veneno, false);

        // ARRUMAR SAÍDAS
        // inicializa as saidas dos ambientes
        delegacia.setSaidas(null, praca, null, null, null, null);
        praca.setSaidas(casaDaMonica, pastelariaJuca, padaria, delegacia, null, null);
        casaDaMagali.setSaidas(null, casaDoCebola, pastelariaJuca, casaDaMonica, null, null);
        casaDaMonica.setSaidas(null, casaDaMagali, praca, null, null, null);
        casaDoCebola.setSaidas(null, null, lago, casaDaMagali, null, null);
        escola.setSaidas(null, null, null, lago, null, null);
        cinema.setSaidas(pastelariaJuca, bosque, null, padaria, null, null);
        padaria.setSaidas(praca, cinema, null, null, null, null);
        bosque.setSaidas(lago, null, null, cinema, null, galpao);
        galpao.setSaidas(null, null, null, null, bosque, null);
        lago.setSaidas(casaDoCebola, escola, bosque, pastelariaJuca, null, null);
        pastelariaJuca.setSaidas(casaDaMagali, lago, cinema, praca, null, null);

        // o jogo comeca na delegacia
        ambienteAtual = delegacia; // o jogo começa do lado de fora
    }

    /*
     * Define os comandos que são conhecidos.
     */

    private void gerarComandos() { // ADICIONAR DESCRIÇÃO DOS COMANDOS
        analisador.definirComando("ajuda", "Mostra os comandos disponíveis");
        analisador.definirComando("ir", "Vai para um ambiente, digite 'ir' e a direção");
        analisador.definirComando("observar", "Observa o ambiente atual e dá as informações sobre ele");
        analisador.definirComando("conversar", "Conversa com o personagem do ambiente atual");
        analisador.definirComando("procurar", "Procura com a lupa o ambiente atual");
        analisador.definirComando("inventario", "Checa o seu inventário");
        analisador.definirComando("acusar",
                "Acusa quem é o assassino(escreva acusar nomeDoSuspeito), se for o correto, você vence o jogo");
        analisador.definirComando("sair", "Sai do jogo");
    }

    /*
     * Define os itens que o protagonista possui no inicio do jogo.
     */

    private void gerarInventarioInicial() { // COLOCAR TODAS AQUI COMO FERRAMENTAS
        Item lupa, mapa, carteira, distintivo;
        lupa = new Ferramenta("lupa", "Uma lupa de detetive");
        mapa = new Ferramenta("mapa", "Um mapa da cidade");
        carteira = new Consumivel("carteira", "A carteira do detetive", 2);
        distintivo = new Ferramenta("distintivo", "O distintivo do detetive");
        inventario.adicionarItem(lupa);
        inventario.adicionarItem(mapa);
        inventario.adicionarItem(carteira);
        inventario.adicionarItem(distintivo);
    }

    /*
     * Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */

    public void jogar() {
        imprimirBoasVindas();

        // Entra no loop principal. Aqui nos repetidamente lemos comandos e
        // os executamos ate o jogo terminar.

        boolean terminado = false;
        while (!terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar.  Ate mais!");
    }

    private void imprimirBoasVindas() {
        System.out.println();
        System.out.println("Bem-vindo ao Mundo de Mônica Jovem!");
        System.out.println("Mônica Jovem é um jogo de aventura, incrivelmente chato.");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();
        System.out.println(ambienteAtual.getPequenaDescricao());

    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o) Se o comando for
     * desconhecido, o jogador eh avisado.
     * 
     * @param comando O comando a ser processado.
     * 
     * @return true se o comando finaliza o jogo, false caso contrario.
     */
    private boolean processarComando(Comando comando) {
        boolean querSair = false;

        if (comando.ehDesconhecido()) {
            System.out.println("Comando inválido! Digite 'ajuda' para ver os comandos disponíveis.");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda"))
            imprimirAjuda();
        else if (palavraDeComando.equals("ir"))
            irParaAmbiente(comando);
        else if (palavraDeComando.equals("observar"))
            observar();
        else if (palavraDeComando.equals("conversar"))
            conversarComPersonagem();
        else if (palavraDeComando.equals("procurar"))
            procurarComLupa();
        else if (palavraDeComando.equals("inventario"))
            checarInventario();
        else if (palavraDeComando.equals("pegar"))
            pegarItem();
        else if (palavraDeComando.equals("acusar")) {
            querSair = acusarPersonagem(comando);
        } else if (palavraDeComando.equals("sair"))
            querSair = sair(comando);
        else
            System.out.println("Comando desconhecido... Por favor insira um comando válido!");

        return querSair;
    }

    /*
     * Imprime os comandos que o jogador pode usar.
     */

    private void imprimirAjuda() {
        System.out.println();
        System.out.println("Suas palavras de comando sao:\n");
        analisador.mostrarComandos();
        System.out.println(ambienteAtual.getPequenaDescricao());
    }

    /**
     * Tenta ir para um ambiente. Se existe uma saida entra no ambiente
     * 
     * @param comando Comando contendo "ir" e a direcao para onde ir.
     */

    private void irParaAmbiente(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir para onde? Informe novamente...");
            return;
        }

        analisador.excluirComando("pegar"); // Reseta o pegar dos ambientes

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual.
        Ambiente proximoAmbiente = ambienteAtual.getSaida(direcao);

        if (proximoAmbiente == null)
            System.out.println("Nao ha passagem!");
        else {
            ambienteAtual = proximoAmbiente;
            ambienteAtual.getLongaDescricao();
        }
    }

    /**
     * Dá uma descrição mais detalhada do ambiente atual, com saídas e personagens.
     */

    private void observar() {
        if (inventario.contemItem("mapa")) {
            System.out.println(ambienteAtual.getLongaDescricao());
        } else {
            System.out.println(ambienteAtual.getPequenaDescricao());
        }
    }

    /**
     * Conversa com o personagem do ambiente atual.
     */

    private void conversarComPersonagem() {
        // Adicionar a lógica do item - Se um dado item estiver no inventário, o
        // personagem tem uma fala diferente
        ambienteAtual.afetaFalaPersonagem(inventario.getEvidencias());
        System.out.println(ambienteAtual.getFalaPersonagem());
    }

    /**
     * Olha com a lupa o ambiente atual.
     */

    private void procurarComLupa() {
        if (inventario.contemItem("lupa")) { // Caso venha a ser implentado algo em que o protagonista perde a lupa
            System.out.println("Você olha com a lupa e vê: ");
            Item item = ambienteAtual.getItem();

            if (item != null) {
                System.out.println(item.getDescricao());
                analisador.definirComando("pegar",
                        "Pega um item do ambiente atual, só funciona quando há itens no local");
            } else {
                System.out.println("Não há nada aqui!");
            }
        } else {
            System.out.println("Você não tem uma lupa para olhar!");
        }
    }

    /**
     * Checa o inventário do protagonista.
     */

    private void checarInventario() {
        System.out.println(inventario.getItens());
    }

    /**
     * Pega um item do ambiente atual.
     */

    private void pegarItem() {
        Item item = ambienteAtual.removerItem();
        if (item != null) {
            inventario.adicionarItem(item);
            System.out.println("Você pegou o item: " + item.getNome());
            analisador.excluirComando("pegar");
        } else {
            System.out.println("Não há nada aqui!");
        }
    }

    /**
     * Acusa o personagem de ser o assassino.
     * 
     * @param comando Comando com a palavra acusar e o nome do personagem.
     */

    private boolean acusarPersonagem(Comando comando) {
        if (ambienteAtual.getNome().equals("Delegacia")) {

            if (!comando.temSegundaPalavra()) {
                System.out.println("Acusar quem?");
                return false;
            }

            String personagem = comando.getSegundaPalavra();
            if (personagem.equals(assassino)) {
                System.out.println("Parabéns, você acertou! Você venceu o jogo!");
                System.out.println("Obrigado por jogar.");
                System.out.println(
                        "Desenvolvido por: João Pedro Nogueira, José Airton Rios, Lara Linhares e Rhuan Campideli");
            } else {
                System.out.println("Você errou! O assassino era " + assassino);
                System.out.println("Obrigado por jogar.");
                System.out.println(
                        "Desenvolvido por: João Pedro Nogueira, José Airton Rios, Lara Linhares e Rhuan Campideli");
            }
            return true;

        } else {
            System.out.println("Você não pode acusar ninguém aqui! Vá para a delegacia!");
            return false;
        }
    }

    /**
     * @param comando Comando com a palavra sair para sair do jogo, caso haja outra
     *                palavra além de 'sair' o jogo não é finalizado.
     * @return true se o comando finaliza o jogo, false caso contrario.
     */

    private boolean sair(Comando comando) {
        if (comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        } else
            return true; // sinaliza que quer sair
    }
}
