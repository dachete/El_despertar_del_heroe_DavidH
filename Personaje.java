public class Personaje {
    private String nombre;
    private int vida;
    private final int vidaMaxima;

    public Personaje(String nombre, int vida) {
        this.nombre = nombre;
        this.vida = vida;
        this.vidaMaxima = vida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getvidaMaxima() {
        return vidaMaxima;
    }

    public void recibirDano(int dano) {
        setVida(Math.max(0, getVida() - dano));
    }

    public String barraVida(int VIDA_MAXIMA) {
        int bloques = getVida() * 20 / VIDA_MAXIMA;
        String barra = "";
        for (int i = 0; i < 20; i++) {
            if (i < bloques) {
                barra += "█";
            } else {
                barra += "-";
            }
        }
        return getNombre() + " " + barra + "  " + getVida();
    }

    public int calcularAtaque() {
        int ataque = 0;
        return ataque;
    };

    public boolean curar(int cantidad, int VIDA_MAXIMA) {
        int vidaAnterior = getVida();
        setVida(Math.min(VIDA_MAXIMA, vidaAnterior + cantidad));
        return getVida() > vidaAnterior;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }
}
