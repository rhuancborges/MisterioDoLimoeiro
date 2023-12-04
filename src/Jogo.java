
/**
 * A classe Jogo é a principal da aplicação "Misterio do Limoeiro", um jogo de investigação 
 * baseado em texto. Os jogadores podem caminhar entre cenários, capturar itens e conversar 
 * com personagens. O objetivo é descobrir quem é o assassino da história, deduzindo pistas 
 * ao longo dos cenários e delatar ao delegado. Se o delegado receber o nome correto do assassino, 
 * o jogador ganha o jogo. Caso receba outro nome, o jogo termina, e o jogador perde, pois o 
 * assassino fugiu.
 *
 * Para jogar esse jogo, crie uma instância dessa classe e chame o método "jogar".
 *
 * Essa classe principal cria e inicializa todas as outras: ela cria os ambientes, cria o analisador 
 * e começa o jogo.Ela avalia, executa os comandos que o analisador retorna e também recebe comandos 
 * da interface gráfica.
 *
 * @author João Pedro Nogueira Lucas, Lara Ramos Linhares, José Airton Rios Júnior e Rhuan Campideli Borges
 * @version 2023.12.03
 */
import itens.*;

public class Jogo {
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private Inventario inventario;
    private String assassino;
    private Tela tela;

    /**
     * Construtor da classe Jogo.
     * Inicializa o analisador, inventário, cria os ambientes, define os comandos
     * conhecidos e gera o inventário inicial.
     */
    public Jogo() {
        analisador = new Analisador();
        inventario = new Inventario();
        criarAmbientes();
        gerarComandos();
        gerarInventarioInicial();
        tela = new Tela();
    }

    /**
     * Cria todos os ambientes e liga as saídas deles.
     * Cada ambiente tem até 6 saídas, uma para cada direção cardeal e uma para cima
     * e uma para baixo.
     * Cada ambiente tem um item, que é uma evidência.
     * Cada ambiente tem um personagem, que é um suspeito.
     */
    private void criarAmbientes() {
        Ambiente delegacia, praca, casaDaMonica, casaDaMagali, casaDoCebola, escola, cinema,
                padaria, bosque, galpao, lago, pastelariaJuca;

        Evidencia veneno, bolsa, macarico, faca, pingente, balde, sansao, arma, plano;
        veneno = new Evidencia("veneno", "Um veneno para ratos");
        bolsa = new Evidencia("bolsa", "A bolsa da Carminha FruFru aberta e vazia com a abertura levemente queimada");
        macarico = new Evidencia("macarico", "Um maçarico comum");
        faca = new Evidencia("faca", "Uma faca de cozinha coberta de sangue");
        pingente = new Evidencia("pingente", "Um pingente de ouro com as iniciais 'CF'");
        balde = new Evidencia("balde", "Um balde com água");
        sansao = new Evidencia("sansao",
                "Um Sansão de pelúcia, porém coberto de sangue e com um corte profundo na barriga");
        arma = new Evidencia("arma da amnésia", "uma arma tecnológica capaz de apagar a memória de alguém");
        plano = new Evidencia("plano do cebolinha", "um plano elaborado por Cebolinha num papel levemente queimado");

        this.assassino = "Magali"; // Define o assassino.

        // Cria os ambientes.
        // Completar com itens.
        delegacia = new Ambiente("Delegacia", "Você está na delegacia", null, "Jeremias",
                "Olá, sou o Jeremias, o delegado da cidade. Estou investigando a morte da Carminha FruFru e por isso chamamos você, para que nos ajude com a busca.",
                "", null, false);

        praca = new Ambiente("Praca", "Você está na praça central", plano, "Denise",
                "Obrigada por vir, detetive! Achei o corpo da minha amiga no lago e fiquei desesperadíssima",
                "Isso me lembrou da última vez que vi a Carminha! Beijando um garoto na padaria", faca, false);

        casaDaMagali = new Ambiente("Casa da Magali", "Você está na casa da Magali", null, "Magali",
                "Oi, detetive, não repara nesse tanto de melancia que comi. Estou mal pela morte da minha amiga!",
                "Onde achou isso? Acho que temos que respeitar a privacidade dela!", bolsa, true);

        casaDaMonica = new Ambiente("Casa da Monica", "Você está na casa da Mônica", null, "Mônica",
                "AAAAAA, o Sansão não está aqui! Eu pego aquele peste do Cebolinha!",
                "Sim, eu a usei! Mas eu precisei usá-la por motivo de força maior", arma, false);

        casaDoCebola = new Ambiente("Casa do Cebola", "Você está na casa do Cebola", veneno, "Cascão",
                "O Cebolinha desapareceu, estou preocupado! Claro que me preocupo com a morte da Carminha, mas é diferente",
                "Tá bom, eu falo a verdade! Antes da notícia da morte da Carminha, o Cebolinha estava aflito, me entregou o sansão e fugiu para a fazenda do Chico Bento",
                balde, false);

        escola = new Ambiente("Escola", "Você está na escola", bolsa, "Marina",
                "Quem faria uma coisa dessas? Realmente é um crime terrível",
                "Eu me lembro de tudo! A Mônica sabe quem é o assassino e apagou a minha memória quando eu e o Franjinha quisemos denunciar",
                pingente, false);

        cinema = new Ambiente("Cinema", "Você está no cinema", pingente, "Franjinha",
                "Eu não sei de nada! Não saí do meu laboratório em nenhum momento",
                "A Arma... A arma que fiz estava dentro do Sansão", sansao, false);

        padaria = new Ambiente("Padaria", "Você está na padaria", faca, "Quinzinho",
                "Desculpa a demora em atendê-lo! Estava enfaixando meu machucado",
                "Estou chocado! Não posso acreditar que ela chegou a esse ponto!", plano, false);

        bosque = new Ambiente("Bosque", "Você está no bosque", sansao, "Luiz M.", "Lembre-se: sempre faça a modelagem antes da implementação.", null, null, false);

        galpao = new Ambiente("Galpao", "Você está no galpão", balde, null, null, null, null, false);

        lago = new Ambiente("Lago", "Você está em frente ao lago", arma, null, null, null, null, false);

        pastelariaJuca = new Ambiente("Pastelaria do Juca", "Você está na pastelaria do Juca", macarico, "Juca",
                "Eu tenho que conversar mesmo? Não estou me sentindo muito confortável",
                "Ai meu Pai! A culpa foi toda minha!! Como fui capaz de deixar isso acontecer?", veneno, false);

        // Arrumar saídas.
        // Inicializa as saídas dos ambientes.
        delegacia.setSaidas("leste", praca);

        praca.setSaidas("norte", casaDaMonica);
        praca.setSaidas("leste", pastelariaJuca);
        praca.setSaidas("sul", padaria);
        praca.setSaidas("oeste", delegacia);

        casaDaMagali.setSaidas("leste", casaDoCebola);
        casaDaMagali.setSaidas("sul", pastelariaJuca);
        casaDaMagali.setSaidas("oeste", casaDaMonica);

        casaDaMonica.setSaidas("leste", casaDaMagali);
        casaDaMonica.setSaidas("sul", praca);

        casaDoCebola.setSaidas("sul", lago);
        casaDoCebola.setSaidas("oeste", casaDaMagali);

        escola.setSaidas("oeste", lago);

        cinema.setSaidas("norte", pastelariaJuca);
        cinema.setSaidas("leste", bosque);
        cinema.setSaidas("oeste", padaria);

        padaria.setSaidas("norte", praca);
        padaria.setSaidas("leste", cinema);

        bosque.setSaidas("norte", lago);
        bosque.setSaidas("oeste", cinema);
        bosque.setSaidas("baixo", galpao);

        galpao.setSaidas("cima", bosque);
        
        lago.setSaidas("norte", casaDoCebola);
        lago.setSaidas("leste", escola);
        lago.setSaidas("sul", bosque);
        lago.setSaidas("oeste", pastelariaJuca);

        pastelariaJuca.setSaidas("norte", casaDaMagali);
        pastelariaJuca.setSaidas("leste", lago);
        pastelariaJuca.setSaidas("sul", cinema);
        pastelariaJuca.setSaidas("oeste", praca);
        
        // O jogo comeca na delegacia.
        ambienteAtual = delegacia; // O jogo começa do lado de fora.
    }

    /**
     * Define os comandos que são conhecidos pelo analisador.
     */
    private void gerarComandos() { // Adicionar descrição dos itens.
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

    /**
     * Define os itens que o protagonista possui no início do jogo.
     */
    private void gerarInventarioInicial() { // Colocar todas aqui como ferramentas.
        Item lupa, mapa, carteira, distintivo;
        lupa = new Ferramenta("lupa", "Uma lupa de detetive");
        mapa = new Ferramenta("mapa", "Um mapa da cidade");
        carteira = new Ferramenta("carteira", "A carteira do detetive");
        distintivo = new Ferramenta("distintivo", "O distintivo do detetive");
        inventario.adicionarItem(lupa);
        inventario.adicionarItem(mapa);
        inventario.adicionarItem(carteira);
        inventario.adicionarItem(distintivo);
    }

    /**
     * Rotina principal do jogo. Fica em loop até terminar o jogo.
     */
    public void jogar() {
        imprimirBoasVindas();
        // Entra no loop principal. Aqui lê-se e executa-se os comandos repetidamente.
        tela.exibir(this);
    }

    /**
     * Recebe um comando da classe Tela e o repassa para processamento.
     * 
     * @param c Comando recebido da interface gráfica.
     */
    public void receberComando(String c) {
        Comando comando = analisador.pegarComando(c);
        if (processarComando(comando)) {
            tela.fecharJanela();
        }
    }

    /**
     * Imprime a mensagem de boas-vindas ao iniciar o jogo.
     */
    private void imprimirBoasVindas() {
        tela.adicionarNaTela("Detetive, precisamos de ajuda pra resolver o Mistério Do Limoeiro!");
        tela.adicionarNaTela("Podemos contar com você?");
        tela.adicionarNaTela("Digite 'ajuda' se voce precisar de ajuda.");
        tela.adicionarNaTela(" ");
        tela.adicionarNaTela(ambienteAtual.getPequenaDescricao());
    }

    /**
     * Processa o comando fornecido pelo jogador. Se o comando for desconhecido, o
     * jogador é avisado.
     * 
     * @param comando O comando a ser processado.
     * @return true se o comando finaliza o jogo, false caso contrário.
     */
    private boolean processarComando(Comando comando) {
        boolean querSair = false;

        if (comando.ehDesconhecido()) {
            tela.adicionarNaTela("Comando inválido! Digite 'ajuda' para ver os comandos disponíveis.");
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
            tela.adicionarNaTela("Comando desconhecido... Por favor insira um comando válido!");
        return querSair;
    }

    /**
     * Imprime os comandos que o jogador pode usar.
     */
    private void imprimirAjuda() {
        tela.adicionarNaTela(" ");
        tela.adicionarNaTela("Suas palavras de comando sao:");
        tela.adicionarNaTela(analisador.mostrarComandos());
        tela.adicionarNaTela(ambienteAtual.getPequenaDescricao());
    }

    /**
     * Tenta ir para um ambiente. Se existe uma saída, entra no ambiente.
     *
     * @param comando Comando contendo "ir" e a direção para onde ir.
     */
    private void irParaAmbiente(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            // Se não há segunda palavra, não se sabe para onde ir...
            tela.adicionarNaTela("Ir para onde? Informe novamente...");
            return;
        }

        analisador.excluirComando("pegar"); // Reseta o comando "pegar" dos ambientes

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual.
        Ambiente proximoAmbiente = ambienteAtual.getSaida(direcao);

        if (proximoAmbiente == null)
            tela.adicionarNaTela("Nao ha passagem!");
        else {
            ambienteAtual = proximoAmbiente;
            tela.adicionarNaTela(ambienteAtual.getLongaDescricao());
        }
    }

    /**
     * Fornece uma descrição mais detalhada do ambiente atual, com saídas e
     * personagens.
     */
    private void observar() {
        if (inventario.contemItem("mapa")) {
            tela.adicionarNaTela(ambienteAtual.getLongaDescricao());
        } else {
            tela.adicionarNaTela(ambienteAtual.getPequenaDescricao());
        }
    }

    /**
     * Conversa com o personagem do ambiente atual.
     */
    private void conversarComPersonagem() {
        // Adicionar a lógica do item - Se um dado item estiver no inventário, o
        // personagem tem uma fala diferente
        ambienteAtual.afetaFalaPersonagem(inventario.getEvidencias());
        tela.adicionarNaTela(ambienteAtual.getFalaPersonagem());
    }

    /**
     * Olha com a lupa o ambiente atual.
     */
    private void procurarComLupa() {
        if (inventario.contemItem("lupa")) { // Caso venha a ser implentado algo em que o protagonista perde a lupa.
            tela.adicionarNaTela("Você olha com a lupa e vê: ");
            Item item = ambienteAtual.getItem();

            if (item != null) {
                tela.adicionarNaTela(item.getDescricao());
                analisador.definirComando("pegar",
                        "Pega um item do ambiente atual, só funciona quando há itens no local");
            } else {
                tela.adicionarNaTela("Não há nada aqui!");
            }
        } else {
            tela.adicionarNaTela("Você não tem uma lupa para olhar!");
        }
    }

    /**
     * Checa o inventário do protagonista.
     */
    private void checarInventario() {
        tela.adicionarNaTela(inventario.getItens());
    }

    /**
     * Pega um item do ambiente atual.
     */
    private void pegarItem() {
        Item item = ambienteAtual.removerItem();
        if (item != null) {
            inventario.adicionarItem(item);
            tela.adicionarNaTela("Você pegou o item: " + item.getNome());
            tela.adicionarEvidencia(item.getNome());
            analisador.excluirComando("pegar");
        } else {
            tela.adicionarNaTela("Não há nada aqui!");
        }
    }

    /**
     * Acusa o personagem de ser o assassino.
     *
     * @param comando Comando com a palavra acusar e o nome do personagem.
     * @return true se o jogo deve ser encerrado, false caso contrário.
     */
    private boolean acusarPersonagem(Comando comando) {
        if (ambienteAtual.getNome().equals("Delegacia")) {

            if (!comando.temSegundaPalavra()) {
                tela.adicionarNaTela("Acusar quem?");
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
            tela.adicionarNaTela("Você não pode acusar ninguém aqui! Vá para a delegacia!");
            return false;
        }
    }

    /**
     * Finaliza o jogo, se o comando for válido para tal.
     *
     * @param comando Comando com a palavra sair. Caso haja outra palavra além de
     *                'sair', o jogo não é finalizado.
     * @return true se o comando finaliza o jogo, false caso contrário.
     */
    private boolean sair(Comando comando) {
        if (comando.temSegundaPalavra()) {
            tela.adicionarNaTela("Sair o que?");
            return false;
        } else {
            return true; // Sinaliza que quer sair.
        }
    }
}
