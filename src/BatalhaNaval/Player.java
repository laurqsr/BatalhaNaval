package BatalhaNaval;
import java.util.Random;
import java.util.Scanner;

public class Player {

    String nome;
    char[][] map = new char[10][10];
    char[] coluna = {'A','B','C','D','E','F','G','H','I','J'};

    public void setName(String nome){ //define o nome do jogador
        this.nome = nome;
    }

    public void setAgua(){ //define os espacos para a agua
        for (int l = 0; l< map.length;l++){
            for (int c = 0; c< map[0].length;c++){
                if (map[l][c] != 'B'){
                    map[l][c] = '~';
                }
            }
        }
    }

    public boolean setNavio(int linha, int coluna, int posicao, int tipoNavio) { //define navio no mapa
        int colInicio = coluna; //pega o valor da coluna inicial
        int linInicio = linha; //pega o valor da linha inicial
        int atribuidos = 0; // número de barcos posicionados;
        for (int l=0; l<map.length; l++){ // percorre a matriz
            for (int c=0; c<map[0].length;c++){
                if (atribuidos < tipoNavio) { //verifica se ainda deve definir o navio no mapa dependendo do tamanho dele
                    if (l==linha && c==coluna){ //se a linha e coluna definidas pelo usuario coincidem
                        if (posicao == 1){ //se o usuario escolheu horizontal
                            if(colInicio+tipoNavio <= map.length){ //verifica se tem espaco para alocar o navio na matriz
                                for (int i = coluna+1; i < colInicio+tipoNavio; i++){ // checa se os espacos depois da localização atual n possuem barcos atribuidos
                                    if (map[l][i] == 'B') return false; // se tem retorna falso
                                }
                                if (map[l][c] != 'B'){// verifica se possui barco na localizaçao atual
                                    map[l][c] = 'B'; //define o navio na matriz
                                    coluna++; //move uma coluna
                                    atribuidos++; //atribui +1 aos barcos atribuidos
                                } else return false;
                            } else return false;
                        }
                        else if (posicao == 2){ //se o usuario escolheu horizontal
                            if(linInicio+tipoNavio <= map.length){ //verifica se tem espaco para alocar o navio na matriz
                                for (int i = linha+1; i < linInicio+tipoNavio; i++){ // checa se os espacos depois da localização atual n possuem barcos atribuidos
                                    if (map[i][c] == 'B') return false;
                                }
                                if (map[l][c] != 'B'){ // verifica se possui barco na localizaçao atual
                                    map[l][c] = 'B'; //define o navio na matriz
                                    linha++; //move uma coluna
                                    atribuidos++; //atribui +1 aos barcos atribuidos
                                } else return false;
                            } else return false;
                        }
                    }
                }
            }
        } return true;
    }

    public void setMapRandom(){ // preenche aleatoriamente o mapa
        Random aleatorio = new Random();
        int navioGG = 1, navioG = 2, navioM = 3, navioP = 4; //numero de barcos para atribuir
        do {
            if (navioGG!=0){ // se ainda tiver barcos disponiveis
                if (setNavio(aleatorio.nextInt(10), aleatorio.nextInt(10), aleatorio.nextInt(2)+1,4)){ // se a atribuicao do barco funcionar, retorna true
                    navioGG--; // diminui 1 barco dos disponiveis
                }
            }
            if (navioG!=0){
                if (setNavio(aleatorio.nextInt(10), aleatorio.nextInt(10), aleatorio.nextInt(2)+1,3)){
                    navioG--;
                }
            }
            if (navioM!=0){
                if (setNavio(aleatorio.nextInt(10), aleatorio.nextInt(10), aleatorio.nextInt(2)+1,2)){
                    navioM--;
                }
            }
            if (navioP!=0){
                if (setNavio(aleatorio.nextInt(10), aleatorio.nextInt(10), aleatorio.nextInt(2)+1,1)){
                    navioP--;
                }
            }
        }while (navioGG != 0 || navioG != 0 || navioM != 0 || navioP != 0); // enquanto ainda tiver barcos para atribuir
    }

    public void getMap(){ // imprime o mapa atualizado do jogador
        int count= 0; //contador para ver se tem todos os 20 barcos no mapa
        for (int i = 0; i<coluna.length;i++){
            if (i==0) System.out.print("    "+coluna[i]+"   ");
            else System.out.print(coluna[i]+"   ");
        }
        System.out.println();
        for (int l = 0; l<map.length; l++){
            for (int c = 0; c<map[0].length;c++){
                if (c==0){
                    System.out.print(l + " | ");
                    System.out.print(map[l][c]+" | ");
                }
                else System.out.print(map[l][c]+" | ");
                if (map[l][c] == 'B') count++;
            }
            System.out.println();
        }
        System.out.println("Número de barcos: "+count);
    }

    public void hideMap(){ //imprime mapa escondido
        for (int i = 0; i<coluna.length;i++){
            if (i==0) System.out.print("    "+coluna[i]+"   ");
            else System.out.print(coluna[i]+"   ");
        }
        System.out.println();
        for (int l = 0; l<map.length; l++){
            for (int c = 0; c<map[0].length;c++){
                if (c==0){
                    System.out.print(l + " | ");
                }
                switch (map[l][c]) {
                    case 'X' -> System.out.print(map[l][c]+" | ");
                    case '*' -> System.out.print(map[l][c]+" | ");
                    default -> System.out.print("  | ");
                }
            }
            System.out.println();
        }
    }

    public boolean atacar(Player enemy) throws InterruptedException{ //modo atacar manualmente
        boolean flagAcertou = false; //flag para verificar se acertou o barco
        int colunaSelec = 0; // valor da coluna passado para int
        Scanner ler = new Scanner(System.in);
        System.out.println();
        System.out.println("\t\uD80C\uDD9F MAPA DE "+nome.toUpperCase()+ " \uD80C\uDD9F");
        getMap();
        System.out.println();
        System.out.println("\t\uD80C\uDD9F MAPA DE "+enemy.nome.toUpperCase()+" \uD80C\uDD9F");
        enemy.hideMap();
        System.out.println();
        System.out.println("HORA DE "+nome.toUpperCase()+" ATACAR");
        System.out.print("✩ Linha: "); // le as coordenadas
        int linha = ler.nextInt();
        System.out.print("✮ Coluna: "); // le as coordenadas
        char colunaChar = ler.next().charAt(0);
        colunaChar = Character.toUpperCase(colunaChar);
        for (int i = 0; i<enemy.coluna.length;i++){
            if (colunaChar == enemy.coluna[i]) colunaSelec = i; //atribui o valor correspondente à letra lida
        }
        for(int l = 0; l<enemy.map.length;l++){
            for(int c = 0; c<enemy.map[0].length;c++){
                if(l == linha && c == colunaSelec){ // se linha e coluna bate
                    if(enemy.map[l][c] == '~'){ // se for agua
                        enemy.map[l][c] = 'X';
                        System.out.println("XXX "+nome.toUpperCase()+" acertou a água XXX");
                        Thread.sleep(1000);
                    }
                    else if(enemy.map[l][c] == 'B'){ // se for barco
                        enemy.map[l][c] = '*';
                        System.out.println("*** "+nome.toUpperCase()+" acertou um barco ***");
                        Thread.sleep(1000);
                        flagAcertou = true;
                    }
                    else{ //caso seja repetido ou invalido
                        System.out.println("XXX "+nome.toUpperCase()+" já atacou nesse local XXX");
                        System.out.println("Ataque novamente");
                        Thread.sleep(1000);
                        if (atacar(enemy)) flagAcertou = true; //chama o método novamente, se dentro dele acertar um barco envia como true para o main
                    }
                }
            }
        } return flagAcertou;
    }

    public boolean atacarRandom (Player enemy) throws InterruptedException{ //modo atacar auto
        boolean flagAcertou = false;
        Random aleatorio = new Random();
        int linha = aleatorio.nextInt(10); //recebe linha random
        int colunaSelec = aleatorio.nextInt(10); //recebe coluna random
        for(int l = 0; l<enemy.map.length;l++){
            for(int c = 0; c<enemy.map[0].length;c++){
                if(l == linha && c == colunaSelec){
                    if(enemy.map[l][c] == '~'){
                        enemy.map[l][c] = 'X';
                        System.out.println("XXX "+nome.toUpperCase()+" acertou a água XXX");
                        Thread.sleep(1000);
                    }
                    else if(enemy.map[l][c] == 'B'){
                        enemy.map[l][c] = '*';
                        System.out.println("*** "+nome.toUpperCase()+" acertou um barco ***");
                        Thread.sleep(1000);
                        flagAcertou = true;
                    }
                    else {
                        System.out.println("XXX "+nome.toUpperCase()+" já atacou nesse local XXX");
                        Thread.sleep(1000);
                        if (atacarRandom(enemy)) flagAcertou = true;
                    }
                }
            }
        } return flagAcertou;
    }
}
