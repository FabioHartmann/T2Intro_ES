public class Jogar {
    public static void main(String[] args) {
        Uno baralho = new Uno();
        baralho.embaralhar();
        baralho.cardList.forEach(carta -> System.out.println(carta.simbolo + " " + carta.cor));
    }
}
