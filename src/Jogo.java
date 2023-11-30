public class Jogo {
    private Analisador analisador;
    private Ambiente ambienteAtual;

    public Jogo() {
        analisador = new Analisador();
        criarAmbientes();
        gerarComandos();
    }

    private void criarAmbientes() {
        Ambiente delegacia, praca, casaDaMonica, casaDaMagali, casaDoCebola, escola, cinema,
                padaria, bosque, galpao, lago, pastelariaJuca;
        Personagem jeremias, quinzinho, franjinha, monica, magali, cascao, marina, juca, denise;

        // cria os ambientes

        // COMPLETAR COM ITENS

        delegacia = new Ambiente("Delegacia", "Você está na delegacia", null, "Jeremias",
                "Alto, magro, cabelo preto, olhos castanhos, usa óculos",
                "Olá, sou o Jeremias, o delegado da cidade. Estou investigando o desaparecimento do Cebola e por isso chamamos você, para que nos ajude com a busca.",
                "Obrigado por me ajudar a encontrar o Cebola. Você é um ótimo detetive!");
        praca = new Ambiente("Praca", "Você está na praça central", null, "Denise", "a Denise", "Olá, sou a Denise",
                "Tchau");
        casaDaMagali = new Ambiente("Casa da Magali", "Você está na casa da Magali", null, "Magali", "a Magali",
                "Olá, sou a Magali", "Tchau");
        casaDaMonica = new Ambiente("Casa da Monica", "Você está na casa da Mônica", null, "Mônica", "a Mônica",
                "Olá, sou a Mônica", "Tchau");
        casaDoCebola = new Ambiente("Casa do Cebola", "Você está na casa do Cebola", null, "Cascão", "o Cascão",
                "Olá, sou o Cascão", "Tchau");
        escola = new Ambiente("Escola", "Você está na escola", null, "Marina", "a Marina", "Olá, sou a Marina",
                "Tchau");
        cinema = new Ambiente("Cinema", "Você está no cinema", null, "Franjinha", "o franjinha", "Olá, sou o Franjinha",
                "Tchau");
        padaria = new Ambiente("Padaria", "Você está na padaria", null, "Quinzinho", "o quinzinho",
                "Olá, sou o Quinzinho", "Tchau");
        bosque = new Ambiente("Bosque", "Você está no bosque", null, null, null, null, null);
        galpao = new Ambiente("Galpao", "Você está no galpão", null, null, null, null, null);
        lago = new Ambiente("Lago", "Você está em frente ao lago", null, null, null, null, null);
        pastelariaJuca = new Ambiente("Pastelaria do Juca", "Você está na pastelaria do Juca", null, "Juca", "o Juca",
                "Olá, sou o Juca", "Tchau");

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

    private void gerarComandos() {
        analisador.definirComando("ajuda");
        analisador.definirComando("ir");
        analisador.definirComando("observar");
        analisador.definirComando("conversar");
        // analisador.definirComando("olhar com lupa");
        // analisador.definirComando("pegar");
        analisador.definirComando("sair");
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
            System.out.println("Comando desconhecido...01 Por favor insira um comando válido!");
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
        /*
         * else if (palavraDeComando.equals("olhar com a lupa"))
         * olharComALupa();
         * else if (palavraDeComando.equals("pegar"))
         * pegarItem();
         */
        else if (palavraDeComando.equals("sair"))
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
        System.out.println("Suas palavras de comando sao:");
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
        System.out.println(ambienteAtual.getLongaDescricao());
    }

    /**
     * Conversa com o personagem do ambiente atual.
     */

    private void conversarComPersonagem() {
        // Adicionar a lógica do item - Se um dado item estiver no inventário, o
        // personagem tem uma fala diferente
        System.out.println(ambienteAtual.getFalaInicialPersonagem());
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
