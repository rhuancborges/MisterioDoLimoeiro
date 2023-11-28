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

public class Jogo 
{
    private Analisador analisador;
    private Ambiente ambienteAtual;
        
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() 
    {
        criarAmbientes();
        analisador = new Analisador();
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
        
        System.out.println("\nBem-vindo, Detetive!\n");
        System.out.println("Sou o delegado Jeremias, do bairro do Limoeiro. Você está aqui para resolver um crime que está assombrando o nosso querido bairro\n");
        System.out.println("Infelizmente, houve um homicídio há uma semana e o assassino está à solta\n\n");
        System.out.println("Seu objetivo é reunir provas, identificar o suspeito e voltar à delegacia com o nome do assassino\n");
        System.out.println("Para te ajudar nessa jornada, você vai receber um mapa do bairro, uma lupa, uma carteira, um distintivo e uma câmera\n");
        System.out.println("Explore todo o bairro e solucione o caso. Contamos com você, detetive!\n\n");

        System.out.println("Digite 'ajuda' se voce precisar de ajuda\n.");
        
        System.out.println("Você está " + ambienteAtual.getLongaDescricao() + "\n\n");
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
    private void irParaAmbiente(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            // se não há segunda palavra, não sabemos para onde ir...
            System.out.println("Ir para onde?\n");
            return;
        }
    
        String direcao = comando.getSegundaPalavra();
    
        // Tenta sair do ambiente atual
        Ambiente proxAmbiente = ambienteAtual.getSaida(direcao);
    
        if (proxAmbiente == null) {
            System.out.println("Não há nenhum caminho!\n");
        } else {
            ambienteAtual = proxAmbiente;
            System.out.println("Você está " + ambienteAtual.getLongaDescricao() + "\n\n");
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

