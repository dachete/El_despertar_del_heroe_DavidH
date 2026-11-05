public class Akuma extends Personaje{
    public static final int VIDA_INICIAL = 60;
    public static final int DANO_MIN = 5;
    public static final int DANO_MAX = 15;

    public Akuma(String nombre, int vida) {
        super(nombre, vida);
    }

    @Override
    public int calcularAtaque() {
        return (int) (Math.random() * (DANO_MAX - DANO_MIN + 1)) + DANO_MIN;
    }
}
