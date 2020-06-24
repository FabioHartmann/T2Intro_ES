import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Uno {
    ArrayList<CartaColorida> cardList = new ArrayList();
    Random aleatorizar = new Random();

    public Uno() {
        for (CartaColorida.Color c : CartaColorida.Color.values()) {
            if (c != CartaColorida.Color.BLACK) {
                for (int j = 0; j < 2; j++) {
                    for (CartaColorida.Simbol v : CartaColorida.Simbol.values()) {
                        if(v != CartaColorida.Simbol.PLUSFOUR && v != CartaColorida.Simbol.COLORCHANGE){
                        if (j == 1 && v.equals(CartaColorida.Simbol.ZERO))
                            continue;
                        else {
                            cardList.add(new CartaColorida(c, v));
                        }
                        }
                    }
                }
            }
        }
            for (int j = 0; j < 4; j++) {
                cardList.add(new CartaColorida(CartaColorida.Color.BLACK, CartaColorida.Simbol.COLORCHANGE));
                cardList.add(new CartaColorida(CartaColorida.Color.BLACK, CartaColorida.Simbol.PLUSFOUR));
            }
    }

    public void embaralhar() {
        for(int i = cardList.size() - 1; i > 0; --i){
            int index = aleatorizar.nextInt(i);
            CartaColorida c = cardList.get(index);
            cardList.set(index,cardList.get(i));
            cardList.set(i,c);
        }
    }

    public CartaColorida comprarCarta() {
        CartaColorida cartaComprada = (CartaColorida) this.cardList.toArray()[0];
        this.cardList.remove(0);
        return cartaComprada;
    }

    public ArrayList<CartaColorida> comprarCartas(int quantidade){
        List<CartaColorida> cartas = this.cardList.subList(0, quantidade);
        ArrayList cast = new ArrayList(cartas);

        for(int index = quantidade - 1; index > 0; index--) {
            this.cardList.remove(index);
        }
    return cast;
    }


}
