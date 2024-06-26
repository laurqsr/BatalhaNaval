package BatalhaNaval;
import java.util.Scanner;

public class Main {

    public static void alocarNavio(int disp, int tipoNavio, Player player) throws InterruptedException { //metodo e param.//pra usar o metodo de dormir
        boolean flagEnd = false;//variavel de sim e nao
        int coluna = 0;
        Scanner ler = new Scanner(System.in);
        do {//montar mapa, pede p posicionar navio enquanto o flagend é falso
            if (disp!=0) { // verifica se ainda tem barcos disponiveis, se tem, joga mais 1
                System.out.println();
                player.getMap();
                System.out.println("Escolha a localização do navio ﹏\uD80C\uDE9D﹏");
                System.out.print("✩ Linha: ");
                int linha = ler.nextInt();
                System.out.print("✮ Coluna: ");
                char colunaChar = ler.next().charAt(0);
                colunaChar = Character.toUpperCase(colunaChar);//maisc e min
                for (int i = 0; i<player.coluna.length;i++){//procura a posição da coluna escolhida
                    if (colunaChar == player.coluna[i]) coluna = i; //atribui o valor correspondente à letra lida na var coluna
                }
                System.out.println("Escolha a posição do navio:");
                System.out.println("Horizontal [1]  \uD80C\uDE9D  Vertical [2]");
                int posicao = ler.nextInt();
                if (player.setNavio(linha, coluna, posicao, tipoNavio)) { // tenta posicionar o navio no mapa do jogador se atribuir corretamente retorna true
                    flagEnd = true; // encerra o loop
                } else { // se houver algum erro retorna false e retoma o loop
                    System.out.println("Localização inválida!!");
                    System.out.println("Posicione novamente!!");
                    Thread.sleep(1000); // pausa o prompt pro usuario ver a msg
                }
            }
            else{
                System.out.println("Você não tem mais navios disponíveis!"); // se n houver barcos disponiveis retoma o loop
                flagEnd = true;
                Thread.sleep(1000); // pausa o prompt
            }
        }while (!flagEnd);
    }

    public static void posicionar(Player player) throws InterruptedException { //metodo la do player e param.//pra usar o metodo de dormir
        Scanner ler = new Scanner(System.in);
        int navioGG = 1; //numero de barcos para atribuir
        int navioG = 2;
        int navioM = 3;
        int navioP = 4;
        System.out.println("\t\uD80C\uDD9F "+player.nome+" \uD80C\uDD9F\t");
        do {//enquanto tiver barcos
            System.out.println();
            System.out.println("Escolha o tipo de navio para :");
            System.out.println("✩ Navio 4 espaços [4] \uD80C\uDE9D " + navioGG + " disponível");//quantos navios ainda tem
            System.out.println("✮ Navio 3 espaços [3] \uD80C\uDE9D " + navioG + " disponível");
            System.out.println("✩ Navio 2 espaços [2] \uD80C\uDE9D " + navioM + " disponível");
            System.out.println("✮ Navio 1 espaços [1] \uD80C\uDE9D " + navioP + " disponível");
            int tipoNavio = ler.nextInt();
            switch (tipoNavio){
                case 4 ->{
                    alocarNavio(navioGG,tipoNavio,player); //chama o método alocarNavio
                    if (navioGG != 0 ) navioGG--; //caso retorne true, diminui um barco para atribuir
                }
                case 3 ->{
                    alocarNavio(navioG,tipoNavio,player);
                    if (navioG != 0 ) navioG--;
                }
                case 2 ->{
                    alocarNavio(navioM,tipoNavio,player);
                    if (navioM != 0 ) navioM--;
                }
                case 1 ->{
                    alocarNavio(navioP,tipoNavio,player);
                    if (navioP != 0 ) navioP--;
                }
                default -> System.out.println("\uD80C\uDD89\uD80C\uDD9D\uD80C\uDDFC\uD80C\uDD9F Opção inválida! \uD80C\uDD89\uD80C\uDD9D\uD80C\uDDFC\uD80C\uDD9F");
            }
        }while (navioGG != 0 || navioG != 0 || navioM != 0 || navioP != 0); // se nao tiver mais barcos disponiveis encerra a atribuicao
    }

    public static void main(String[] args) throws InterruptedException {//inicia, p usar o sleep
        Scanner ler = new Scanner(System.in);
        System.out.println("☼ Lalas e Vitz apresentam:\n");
        System.out.println("⊹ ࣪ ﹏\uD80C\uDE9D﹏\uD80C\uDC81﹏⊹ ࣪ ˖ BATALHA NAVAL ⊹ ࣪ ﹏\uD80C\uDE9D﹏\uD80C\uDC81﹏⊹ ࣪ ˖ \n");
        System.out.println("Selecione seu modo de jogo:");
        System.out.println("Multiplayer [M] \uD80C\uDE9D Singleplayer [S]");
        char modo = ler.next().charAt(0);
        switch (modo){//modo de jjogar single ou multi
            case 's','S' -> {
                System.out.println("\t\uD80C\uDD9F SINGLEPLAYER \uD80C\uDD9F \n");
                Player player1 = new Player(); //cria o jogador
                Player bot = new Player(); // cria o bot
                bot.setName("Bot");
                bot.setAgua(); // preenche os espaco de agua do bot
                bot.setMapRandom(); // preenche aleatorimante o mapa do bot
                System.out.print("Informe seu nome: ");
                String nome = ler.next();
                player1.setName(nome);
                player1.setAgua(); // preenche os espaco de agua do jogador
                System.out.println("Como você deseja criar seu mapa:");
                System.out.println("Manual [M] \uD80C\uDE9D Automático [A]");
                char opcao = ler.next().charAt(0);
                switch (opcao) {
                    case 'm', 'M' -> posicionar(player1); // manualmente
                    case 'a', 'A' -> player1.setMapRandom(); //define aleatoriamente o mapa do jogador
                    default -> System.out.println("\uD80C\uDD89\uD80C\uDD9D\uD80C\uDDFC\uD80C\uDD9F Caractere inválido! \uD80C\uDD89\uD80C\uDD9D\uD80C\uDDFC\uD80C\uDD9F");// ele vai aparecer esse cod embaixo mas sem barco no map
                }

                System.out.println("\t\uD80C\uDD9D \uD80C\uDD9F \uD80C\uDD9E \uD80C\uDD9D \uD80C\uDD9F \uD80C\uDD9E");
                System.out.println("\tHORA DE ATACAR");
                System.out.println("\t\uD80C\uDD9D \uD80C\uDD9F \uD80C\uDD9E \uD80C\uDD9D \uD80C\uDD9F \uD80C\uDD9E");
                int winPlayer1 = 0; //flag barcos atingidos, contar as jogadas
                int winBot = 0;
                boolean player1Hit = false; //pra ver se acerta
                boolean botHit = false;
                do {
                    do{
                        if (player1.atacar(bot)) {//atacar no outro arq.
                            winPlayer1++;//se jogador atingir um navio, conta os barcos atingidos
                            player1Hit = true;
                        }
                        else player1Hit = false;
                    }while(player1Hit);//repete o ataque se ele acertar um navio
                    do{
                        if (bot.atacarRandom(player1)) {
                            winBot++;//se bot atingir um navio, conta os barcos atingidos pelo bot
                            botHit = true;
                        }
                        else botHit = false;
                    }while(botHit);//repete o ataque do bot
                    if (winPlayer1==20) {//se jogador atingir os 20 barcos
                        System.out.println("\t\uD80C\uDD9F "+player1.nome.toUpperCase()+" GANHOU O JOGO!\t\uD80C\uDD9F");
                    }
                    else
                    {
                        System.out.println("\t\uD80C\uDD9F "+player1.nome.toUpperCase()+" PERDEU O JOGO!\t\uD80C\uDD9F");
                    }
                    if (winBot==20) {//se bot atingir os 20 barcos
                        System.out.println("\t\uD80C\uDD9F "+bot.nome.toUpperCase()+" GANHOU O JOGO\t\uD80C\uDD9F");
                    }
                    else
                    {
                        System.out.println("\t\uD80C\uDD9F "+bot.nome.toUpperCase()+" PERDEU O JOGO\t\uD80C\uDD9F");
                    }
                }while(winPlayer1!=20 && winBot!= 20); //funciona enquanto ngm afundar os 20 barcos
            }
            case 'm','M' -> {
                System.out.println("\uD80C\uDD9F MULTIPLAYER \uD80C\uDD9F \n");
                Player player1 = new Player(); //cria o jogador 1
                Player player2 = new Player(); // cria o jogador 2
                System.out.print("Informe o nome do jogador 1: ");
                String nome1 = ler.next();
                player1.setName(nome1);
                player1.setAgua(); // preenche os espaco de agua
                System.out.print("Informe o nome do jogador 2: ");
                String nome2 = ler.next();
                player2.setName(nome2);
                player2.setAgua(); // preenche os espaco de agua
                System.out.println("Como você deseja criar seu mapa:");
                System.out.println("Manual [M] \uD80C\uDE9D Automático [A]");
                char opcao = ler.next().charAt(0);
                switch (opcao){
                    case 'm','M' ->{//manual
                        posicionar(player1);//metodos de posicionar navios la em cima
                        posicionar(player2);
                    }
                    case 'a','A' ->{
                        player1.setMapRandom();//metodo de gerar aleadorio outro arq.
                        player2.setMapRandom();
                    }
                    default -> System.out.println("\uD80C\uDD89\uD80C\uDD9D\uD80C\uDDFC\uD80C\uDD9F Caractere inválido! \uD80C\uDD89\uD80C\uDD9D\uD80C\uDDFC\uD80C\uDD9F");
                }

                System.out.println("\t\uD80C\uDD9D \uD80C\uDD9F \uD80C\uDD9E \uD80C\uDD9D \uD80C\uDD9F");
                System.out.println("\tHORA DE ATACAR");
                System.out.println("\t\uD80C\uDD9D \uD80C\uDD9F \uD80C\uDD9E \uD80C\uDD9D \uD80C\uDD9F");
                int winPlayer1 = 0;
                int winPlayer2 = 0;
                boolean player1Hit = false;
                boolean player2Hit = false;
                do{//MESMA LOGICA
                    do{
                        if (player1.atacar(player2)) {
                            winPlayer1++;
                            player1Hit = true;
                        }
                        else player1Hit = false;
                    }while(player1Hit);
                    do{
                        if (player2.atacar(player1)) {
                            winPlayer2++;
                            player2Hit = true;
                        }
                        else player2Hit = false;
                    }while(player2Hit);
                    if (winPlayer1==20)
                    {
                        System.out.println("\t\uD80C\uDD9F "+player1.nome.toUpperCase()+" GANHOU O JOGO!\t\uD80C\uDD9F");
                    }
                    else
                    {
                        System.out.println("\t\uD80C\uDD9F "+player1.nome.toUpperCase()+" PERDEU O JOGO!\t\uD80C\uDD9F");
                    }
                    if (winPlayer2==20) {
                        System.out.println("\t\uD80C\uDD9F "+player2.nome.toUpperCase()+" GANHOU O JOGO\t\uD80C\uDD9F");
                    }
                    else
                    {
                        System.out.println("\t\uD80C\uDD9F "+player2.nome.toUpperCase()+" PERDEU O JOGO\t\uD80C\uDD9F");
                    }
                }while(winPlayer1!=20 && winPlayer2!= 20);
            }
            default -> System.out.println("\uD80C\uDD89\uD80C\uDD9D\uD80C\uDDFC\uD80C\uDD9F Modo Inválido! \uD80C\uDD89\uD80C\uDD9D\uD80C\uDDFC\uD80C\uDD9F");
        }
    }
}