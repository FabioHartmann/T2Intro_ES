import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Jogar {
    public static void main(String[] args) throws InterruptedException {
        Uno baralho = new Uno();
        ArrayList<CartaColorida> mesa;
        String nomeJ1 = "", nomeJ2 = "", uno="";
        int player = 1, cartaEscolhida, sums = 0;
        Scanner t = new Scanner(System.in);
        boolean ativePlusFour = false;

        baralho.embaralhar();

        while (nomeJ1.length() == 0){
            System.out.println("Nome jogador 1:");
            nomeJ1 = t.nextLine();
        }

        while ( nomeJ2.length() == 0){
            System.out.println("Nome jogador 2:");
            nomeJ2 = t.nextLine();
        }

        ArrayList<CartaColorida> maoJ1 = baralho.comprarCartas(7);
        MaoDoJogador j1 = new MaoDoJogador(maoJ1, nomeJ1);

        ArrayList<CartaColorida> maoJ2 = baralho.comprarCartas(7);
        MaoDoJogador j2 = new MaoDoJogador(maoJ2, nomeJ2);

        mesa = baralho.comprarCartas(1);

        // O Jogo
        while(j1.getMao().toArray().length > 0 && j2.getMao().toArray().length > 0){
            System.out.println("----------  " + player + "  ---------" );
            CartaColorida ultimaCartaDaMesa = (CartaColorida) mesa.toArray()[mesa.toArray().length - 1];
            System.out.println("Carta da mesa: " +ultimaCartaDaMesa.getCor() + " " + ultimaCartaDaMesa.getSimbolo());
        if (player == 1) {
            System.out.println("Suas cartas:");
            j1.getMao().forEach(carta -> System.out.print(carta.getCor() + " " + carta.getSimbolo() + "  |"));
            ArrayList<CartaColorida> cartasDescartaveis;
            if(ultimaCartaDaMesa.getSimbolo() == CartaColorida.Simbol.DRAWTWO && sums > 0){
               cartasDescartaveis = responderMaisDois(j1.getMao(), ultimaCartaDaMesa);
               if(cartasDescartaveis.toArray().length <= 0) {
                   ArrayList<CartaColorida > cartasCompradas = baralho.comprarCartas(sums);
                   cartasCompradas.forEach(carta -> j1.adicionaCartaNaMao(carta));
                   System.out.println("Suas Cartas são:");
                   j1.getMao().forEach(carta -> System.out.print(carta.getCor() + " " + carta.getSimbolo() + "  |"));
                   sums = 0;
                   player ++;
                   continue;
               }
            }else if(ultimaCartaDaMesa.getSimbolo() == CartaColorida.Simbol.PLUSFOUR && ativePlusFour) {
                ArrayList<CartaColorida > cartasCompradas = baralho.comprarCartas(4);
                cartasCompradas.forEach(carta -> j1.adicionaCartaNaMao(carta));
                System.out.println("Suas Cartas são:");
                j1.getMao().forEach(carta -> System.out.print(carta.getCor() + " " + carta.getSimbolo() + "  |"));
                ativePlusFour = false;
                player ++;
                continue;
            } else{
                cartasDescartaveis = cartasDescartaveis(j1.getMao(), ultimaCartaDaMesa);
            }
            while(cartasDescartaveis.toArray().length == 0 ){
                CartaColorida cartaComprada;
                cartaComprada = baralho.comprarCarta();
                System.out.print(cartaComprada.cor + " " + cartaComprada.simbolo);
                j1.adicionaCartaNaMao(cartaComprada);
                cartasDescartaveis = cartasDescartaveis(j1.getMao(), ultimaCartaDaMesa);
                System.out.println("Cartas Descartaveis após compra" + cartasDescartaveis.toArray().length );
            }
            System.out.println(j1.getNome() + " Digite o número da carta que deseja descartar:(conforme a ordem que aparece) ");
            System.out.println("As cartas descartaveis são:");
            cartasDescartaveis.forEach(carta -> System.out.println(carta.cor + " " + carta.simbolo));

            cartaEscolhida = t.nextInt() - 1;

            CartaColorida cartaDescartada = (CartaColorida) cartasDescartaveis.toArray()[cartaEscolhida];

            j1.descartarCarta(cartaDescartada);

            if(cartaDescartada.getSimbolo() != CartaColorida.Simbol.SKIP){
                if(cartaDescartada.getSimbolo() == CartaColorida.Simbol.DRAWTWO){
                    sums = sums+2;
                }else if(cartaDescartada.getSimbolo() == CartaColorida.Simbol.PLUSFOUR){
                    ativePlusFour = true;
                    String corEscolhida = "";
                    while (corEscolhida.length() <= 0){
                        System.out.println("Selecione a cor? RED / BLUE / YELLOW / GREEN");
                        corEscolhida = t.nextLine().toUpperCase();
                    }
                    cartaDescartada.setCor(CartaColorida.Color.valueOf(corEscolhida) );
                }else if(cartaDescartada.getSimbolo() == CartaColorida.Simbol.COLORCHANGE){
                    String corEscolhida = "";
                    while (corEscolhida.length() <= 0){
                        System.out.println("Selecione a cor? RED / BLUE / YELLOW / GREEN");
                        corEscolhida = t.nextLine().toUpperCase();
                    }
                    cartaDescartada.setCor(CartaColorida.Color.valueOf(corEscolhida));
                }
                player ++;
            }
            mesa.add(cartaDescartada);
            if(j1.getMao().toArray().length == 1){
                System.out.println("Uno?");
                while(uno.length() < 3){
                    uno = t.nextLine();
                    Thread.sleep(10000);
                    continue;
                }
                if(uno.toLowerCase() !="uno"){
                    ArrayList<CartaColorida > cartasCompradas = baralho.comprarCartas(2);
                    cartasCompradas.forEach(carta -> j1.adicionaCartaNaMao(carta));
                }
            }
            cartasDescartaveis.clear();

        }else{
            System.out.println("Suas cartas:");
            j2.getMao().forEach(carta-> System.out.print(carta.getCor() + " " + carta.getSimbolo()  + "  |"));
            ArrayList<CartaColorida> cartasDescartaveis;
            if(ultimaCartaDaMesa.getSimbolo() == CartaColorida.Simbol.DRAWTWO && sums > 0){
                cartasDescartaveis = responderMaisDois(j2.getMao(), ultimaCartaDaMesa);
                if(cartasDescartaveis.toArray().length <= 0) {
                    ArrayList<CartaColorida > cartasCompradas = baralho.comprarCartas(sums);
                    cartasCompradas.forEach(carta -> j2.adicionaCartaNaMao(carta));
                    System.out.println("Suas Cartas são:");
                    j2.getMao().forEach(carta -> System.out.print(carta.getCor() + " " + carta.getSimbolo() + "  |"));
                    sums = 0;
                    player --;
                    continue;
                }
            }else if(ultimaCartaDaMesa.getSimbolo() == CartaColorida.Simbol.PLUSFOUR && ativePlusFour) {
                ArrayList<CartaColorida > cartasCompradas = baralho.comprarCartas(4);
                cartasCompradas.forEach(carta -> j2.adicionaCartaNaMao(carta));
                System.out.println("Suas Cartas são:");
                j2.getMao().forEach(carta -> System.out.print(carta.getCor() + " " + carta.getSimbolo() + "  |"));
                ativePlusFour = false;
                player --;
                continue;
            }
            else{
                cartasDescartaveis = cartasDescartaveis(j2.getMao(), ultimaCartaDaMesa);
            }
            System.out.println("Cartas Descartaveis length " + cartasDescartaveis.toArray().length );
            while(cartasDescartaveis.toArray().length == 0 ){
                CartaColorida cartaComprada;
                cartaComprada = baralho.comprarCarta();
                System.out.print(cartaComprada.cor + " " + cartaComprada.simbolo);
                j2.adicionaCartaNaMao(cartaComprada);
                cartasDescartaveis = cartasDescartaveis(j2.getMao(), ultimaCartaDaMesa);
                System.out.println("Cartas Descartaveis após compra" + cartasDescartaveis.toArray().length );
            }
            System.out.println(j2.getNome() + " Digite o número da carta que deseja descartar:(conforme a ordem que aparece) ");
            System.out.println("As cartas descartaveis são:");
            cartasDescartaveis.forEach(carta -> System.out.println(carta.cor + " " + carta.simbolo));

            cartaEscolhida = t.nextInt() - 1;

            CartaColorida cartaDescartada = (CartaColorida) cartasDescartaveis.toArray()[cartaEscolhida];

            j2.descartarCarta(cartaDescartada);

            if(cartaDescartada.getSimbolo() != CartaColorida.Simbol.SKIP){
                  if(cartaDescartada.getSimbolo() == CartaColorida.Simbol.DRAWTWO){
                    sums = sums + 2;
                }else if(cartaDescartada.getSimbolo() == CartaColorida.Simbol.PLUSFOUR){
                      ativePlusFour = true;
                      String corEscolhida = "";
                      while (corEscolhida.length() <= 0){
                          System.out.println("Selecione a cor? RED / BLUE / YELLOW / GREEN");
                          corEscolhida = t.nextLine().toUpperCase();
                      }
                      cartaDescartada.setCor(CartaColorida.Color.valueOf(corEscolhida));
                  }else if(cartaDescartada.getSimbolo() == CartaColorida.Simbol.COLORCHANGE){
                      String corEscolhida = "";
                      while (corEscolhida.length() <= 0){
                          System.out.println("Selecione a cor? RED / BLUE / YELLOW / GREEN");
                          corEscolhida = t.nextLine().toUpperCase();
                  }
                      cartaDescartada.setCor(CartaColorida.Color.valueOf(corEscolhida));
                }
                player --;
            }
            mesa.add(cartaDescartada);
            if(j2.getMao().toArray().length == 1){
                System.out.println("Uno?");
                while(uno.length() < 3){
                    uno = t.nextLine();
                    Thread.sleep(10000);
                    continue;
                }
                if(uno.toLowerCase() !="uno"){
                    ArrayList<CartaColorida > cartasCompradas = baralho.comprarCartas(2);
                    cartasCompradas.forEach(carta -> j2.adicionaCartaNaMao(carta));
                }
            }
            cartasDescartaveis.clear();
        }

        }

        if((j1.getMao().toArray().length == 0 )){
            System.out.println("Parabéns " + j1.getNome() + " você venceu a partida");
        } else{
            System.out.println("Parabéns " + j2.getNome() + " você venceu a partida");
        }

    }

    public static ArrayList<CartaColorida> cartasDescartaveis(ArrayList<CartaColorida> cartas, CartaColorida cartaMesa){
        ArrayList<CartaColorida>  cartasElegiveis = new ArrayList<CartaColorida>();
           cartas.forEach(carta -> {
               if(carta.getSimbolo() == cartaMesa.getSimbolo() || carta.getCor() == cartaMesa.getCor() || carta.getCor() == CartaColorida.Color.BLACK ) cartasElegiveis.add(carta);
           });
        return cartasElegiveis;
    }

    public static ArrayList<CartaColorida> responderMaisDois(ArrayList<CartaColorida> cartas, CartaColorida cartaMesa){
        ArrayList<CartaColorida>  cartasElegiveis = new ArrayList<CartaColorida>();
        cartas.forEach(carta -> {
            if(carta.getSimbolo() == cartaMesa.getSimbolo()) cartasElegiveis.add(carta);
        });
        return cartasElegiveis;
    }

}
