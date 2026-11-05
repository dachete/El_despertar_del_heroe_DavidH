public class Ryu extends Personaje {
    public static final int VIDA_INICIAL = 100;
    public static final int VIDA_MAXIMA = 100;
    public static final int CURACIONES = 3;
    public static final int VIDA_CURACIONES = 25;
    public static final int DANO_MIN = 10;
    public static final int DANO_MAX = 20;

    public Ryu(String nombre, int vida) {
        super(nombre, vida);
    }

    @Override
    public int calcularAtaque() {
        return (int) (Math.random() * (DANO_MAX - DANO_MIN + 1)) + DANO_MIN;
    }

}
