public class Dan_Hibiki extends Personaje {
    public static final int VIDA_INICIAL = 40;
    public static final int DANO_MIN = 5;
    public static final int DANO_MAX = 15;

    public Dan_Hibiki(String nombre, int vida) {
        super(nombre, vida);
    }

    @Override
    public int calcularAtaque() {
        return (int) (Math.random() * (DANO_MAX - DANO_MIN + 1)) + DANO_MIN;
    }
}
