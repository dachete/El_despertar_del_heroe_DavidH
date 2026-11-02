public class Dan_Hibiki extends Personaje {
    public static final int VIDA_INICIAL = 40;
    public static final int DANO_MIN = 5;
    public static final int DANO_MAX = 15;

    public Dan_Hibiki(String nombre, int vida) {
        super(nombre, vida);
    }

    @Override
    public int calcularAtaque(int DANO_MIN, int DANO_MAX) {
        int ataqueRyu = (int) (Math.random() * (DANO_MAX - DANO_MIN + 1)) + DANO_MIN;
        return ataqueRyu;
    }

    @Override
    public String barravida(boolean obtenerDano_Ryu) {
        String[] barra = new String[20];
        int barrasQuitar = 0;
        int barrasAcumular = 0;
        System.out.println(obtenerDano_Ryu);
        if (obtenerDano_Ryu == true) {
            int ataqueRyu = calcularAtaque(DANO_MIN, DANO_MAX);
            System.out.println(ataqueRyu);
            barrasQuitar = ataqueRyu / 2;
            barrasAcumular = ataqueRyu % 2;
        }
        System.out.println(barrasQuitar + " " + barrasAcumular);
        System.out.println("Acumulado: " + barrasAcumular);
        for (int i = 0; i < barra.length - barrasQuitar; i++) {
            barra[i] = "█";
        }
        for (int i = barra.length - barrasQuitar; i < barra.length; i++) {
            barra[i] = "-";
        }
        if (barrasAcumular == 2) {
            barrasQuitar += 1;
        }
        return getNombre() + " " + String.join("", barra) + "  " + getVida();
    }
}
