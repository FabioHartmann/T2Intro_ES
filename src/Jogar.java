import java.util.ArrayList;
import java.util.Scanner;

public class Jogar {
    public static void main(String[] args) {
        Uno baralho = new Uno();
        ArrayList<CartaColorida> mesa;
        String nomeJ1 = "", nomeJ2 = "";
        int player = 1, cartaEscolhida;
        Scanner t = new Scanner(System.in);

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
            CartaColorida ultimaCartaDaMesa = (CartaColorida) mesa.toArray()[mesa.toArray().length - 1];
            System.out.println("Carta da mesa: " +ultimaCartaDaMesa.getCor() + " " + ultimaCartaDaMesa.getSimbolo());
        if (player == 1) {
            System.out.println("Suas cartas:");
            j1.getMao().forEach(carta-> System.out.print(carta.getCor() + " " + carta.getSimbolo() + "  |"));
            System.out.println(j1.getNome() + " Digite o número da carta que deseja descartar:(conforme a ordem que aparece) ");
            ArrayList<CartaColorida> cartasDescartaveis;
            cartasDescartaveis = cartasDescartaveis(j1.getMao(), ultimaCartaDaMesa);
//            System.out.println("Cartas Descartaveis length " + cartasDescartaveis.toArray().length );
            while(cartasDescartaveis.toArray().length == 0 ){
                System.out.println("No while");
                CartaColorida cartaComprada;
                cartaComprada = baralho.comprarCarta();
                System.out.print(cartaComprada.cor + " " + cartaComprada.simbolo);
                j1.adicionaCartaNaMao(cartaComprada);
                cartasDescartaveis = cartasDescartaveis(j1.getMao(), ultimaCartaDaMesa);
                System.out.println("Cartas Descartaveis após compra" + cartasDescartaveis.toArray().length );
//                if(cartasDescartaveis.toArray().length == 0 ) break;
            }


            System.out.println("As cartas descartaveis são:");
            cartasDescartaveis.forEach(carta -> System.out.println(carta.cor + " " + carta.simbolo));

            cartaEscolhida = t.nextInt() - 1;

            CartaColorida cartaDescartada = (CartaColorida) cartasDescartaveis.toArray()[cartaEscolhida];

            mesa.add(cartaDescartada);
            j1.descartarCarta(cartaDescartada);

            if(cartaDescartada.getSimbolo() != CartaColorida.Simbol.SKIP){
                player ++;
            }
            cartasDescartaveis.clear();

        }else{
            System.out.println("Suas cartas:");
            j2.getMao().forEach(carta-> System.out.print(carta.getCor() + " " + carta.getSimbolo()  + "  |"));
            System.out.println(j2.getNome() + " Digite o número da carta que deseja descartar:(conforme a ordem que aparece) ");
            ArrayList<CartaColorida> cartasDescartaveis;
            cartasDescartaveis = cartasDescartaveis(j2.getMao(), ultimaCartaDaMesa);
            System.out.println("Cartas Descartaveis length " + cartasDescartaveis.toArray().length );
            while(cartasDescartaveis.toArray().length == 0 ){
                System.out.println("No while");
                CartaColorida cartaComprada;
                cartaComprada = baralho.comprarCarta();
                System.out.print(cartaComprada.cor + " " + cartaComprada.simbolo);
                j2.adicionaCartaNaMao(cartaComprada);
                cartasDescartaveis = cartasDescartaveis(j2.getMao(), ultimaCartaDaMesa);
                System.out.println("Cartas Descartaveis após compra" + cartasDescartaveis.toArray().length );
            }

            System.out.println("As cartas descartaveis são:");
            cartasDescartaveis.forEach(carta -> System.out.println(carta.cor + " " + carta.simbolo));

            cartaEscolhida = t.nextInt() - 1;

            CartaColorida cartaDescartada = (CartaColorida) cartasDescartaveis.toArray()[cartaEscolhida];

            mesa.add(cartaDescartada);
            j2.descartarCarta(cartaDescartada);

            if(cartaDescartada.getSimbolo() != CartaColorida.Simbol.SKIP){
                player --;
            }
            cartasDescartaveis.clear();
        }

        }

        if((j1.getMao().toArray().length == 0 )){
            System.out.println("Parabéns " + j1.getNome() + "você venceu a partida");
        } else{
            System.out.println("Parabéns " + j2.getNome() + "você venceu a partida");
        }

    }

    public static ArrayList<CartaColorida> cartasDescartaveis(ArrayList<CartaColorida> cartas, CartaColorida cartaMesa){
        ArrayList<CartaColorida>  cartasElegiveis = new ArrayList<CartaColorida>();
           cartas.forEach(carta -> {
               if(carta.getSimbolo() == cartaMesa.getSimbolo() || carta.getCor() == cartaMesa.getCor() || carta.getCor() == CartaColorida.Color.BLACK ) cartasElegiveis.add(carta);
           });
        return cartasElegiveis;
    }

}
