public class CartaColorida {
    Color cor;
    Simbol simbolo;

    public enum Color {
        BLUE   { public String toString() { return "BLUE"; }},
        RED    { public String toString() { return "RED"; }},
        GREEN  { public String toString() { return "GREEN"; }},
        YELLOW { public String toString() { return "YELLOW"; }},
        BLACK { public String toString() { return "BLACK"; }},

    }

    public enum Simbol {
        ZERO    { public String toString() { return "0";}},
        ONE     { public String toString() { return "1";}},
        TWO     { public String toString() { return "2";}},
        THREE   { public String toString() { return "3";}},
        FOUR    { public String toString() { return "4";}},
        FIVE    { public String toString() { return "5";}},
        SIX     { public String toString() { return "6";}},
        SEVEN   { public String toString() { return "7";}},
        EIGHT   { public String toString() { return "8";}},
        NINE    { public String toString() { return "9";}},
        SKIP    { public String toString() { return "SKIP";}},
        REVERSE { public String toString() { return "REVERSE";}},
        DRAWTWO { public String toString() { return "+2";}},


        COLORCHANGE { public String toString() { return "COLORCHANGE";}},
        PLUSFOUR    { public String toString() { return "PLUSFOUR";}}


    }


    public CartaColorida(Color color, Simbol simbol){
        this.cor = color;
        this.simbolo = simbol;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public Simbol getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(Simbol simbolo) {
        this.simbolo = simbolo;
    }
}
