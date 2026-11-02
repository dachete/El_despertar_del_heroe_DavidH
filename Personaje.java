public class Personaje {
    private String nombre;
    private int vida;

    public Personaje(String nombre, int vida){
        this.nombre = nombre;
        this.vida = vida;
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

    public String barravida(boolean obtenerDano){
        int numBarras = 20;
        String[] barra = new String[numBarras];
        for(int i=0; i < barra.length; i++){
            barra[i] = "█";
        }
        return nombre + " " + String.join("",barra) + "  " + vida;
    }

    public int calcularAtaque(int DANO_MIN, int DANO_MAX){
        int ataque = (int) (Math.random() * (DANO_MAX - DANO_MIN + 1)) + DANO_MIN;
        return ataque;
    }

    public boolean obtenerDano(){
        boolean obtener = true;
        return obtener;
    }
}
