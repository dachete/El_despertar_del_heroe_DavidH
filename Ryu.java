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
    public int calcularAtaque(int DANO_MIN, int DANO_MAX) {
        int ataqueOponente = (int) (Math.random() * (DANO_MAX - DANO_MIN + 1)) + DANO_MIN;
        return ataqueOponente;
    }

    @Override
    public String barravida(boolean obtenerDano_Oponente) {
        String[] barra = new String[20];
        int barrasQuitar = 0;
        int barrasAcumular = 0;
        System.out.println(obtenerDano_Oponente);
        if (obtenerDano_Oponente == true) {
            int ataqueOponente = calcularAtaque(DANO_MIN, DANO_MAX);
            System.out.println(ataqueOponente);
            barrasQuitar = ataqueOponente / 5;
            barrasAcumular = ataqueOponente % 5;
        }
        System.out.println(barrasQuitar + " " + barrasAcumular);
        System.out.println("Acumulado: " + barrasAcumular);
        for (int i = 0; i < barra.length - barrasQuitar; i++) {
            barra[i] = "█";
        }
        for (int i = barra.length - barrasQuitar; i < barra.length; i++) {
            barra[i] = "-";
        }
        if (barrasAcumular == 5) {
            barrasQuitar += 1;
        }
        return getNombre() + " " + String.join("", barra) + "  " + getVida();
    }
}
