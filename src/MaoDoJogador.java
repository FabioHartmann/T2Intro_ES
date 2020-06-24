
import java.util.ArrayList;

public class MaoDoJogador {
    String nome;
    ArrayList<CartaColorida> mao;

    public MaoDoJogador(ArrayList<CartaColorida> cartas, String nome) {
        this.nome = nome;
        this.mao = cartas;
    }

    public void descartarCarta(CartaColorida carta) {
        this.mao.remove(carta);
    }

    public void adicionaCartaNaMao(CartaColorida carta) {
       this.mao.add(carta);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<CartaColorida> getMao() {
        return mao;
    }

    public void setMao(ArrayList<CartaColorida> mao) {
        this.mao = mao;
    }
}
