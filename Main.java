
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Personaje Ryu = new Ryu("Ryu", 100);
        Personaje Dan_Hibiki = new Dan_Hibiki("Dan Hibiki", 40);
        Boolean obtenerDano_Oponente = false;
        Boolean obtenerDano_Ryu = false;
        String decision;
        Escenas.imagenmain();
        System.out.println("**Para una mejor experiencia maximiza el tamaño de la terminal**");
        enter(1);
        do {
            System.out.print("Decisión: ");
            decision = teclado.nextLine();
            if (decision.equalsIgnoreCase("Salir")) {
                break;
            } else if (decision.equalsIgnoreCase("Ranking")) {
                System.out.println("Ranking");
            } else if (decision.equalsIgnoreCase("Ayuda")) {
                enter(2);
                Ayuda.ayuda();
            } else if (decision.equalsIgnoreCase("Nueva partida")) {
                nombreusuario(teclado);
                intro(teclado, tutorial());
                combate(teclado, Ryu, Dan_Hibiki, obtenerDano_Oponente, obtenerDano_Ryu);
            } else if (decision.isBlank()) {
                System.out.println("ERROR: Entrada nula no válida");
                enter(1);
            } else {
                System.out.println("ERROR: Entrada de texto no válida");
                enter(1);
            }

        } while (!decision.equalsIgnoreCase("Nueva partida") && !decision.equalsIgnoreCase("Ranking")
                && !decision.equalsIgnoreCase("Ayuda")
                && !decision.equalsIgnoreCase("Salir"));
        teclado.close();
    }

    static void enter(int saltos) {
        for (int i = 0; i < saltos; i++) {
            System.out.println();
        }
    }

    static void continuar(Scanner teclado) {
        System.out.println("");
        teclado.nextLine();
    }

    static void nombreusuario(Scanner teclado) {
        enter(1);
        String usuario;
        boolean error;
        do {
            System.out.print("Introduce nombre de usuario: ");
            usuario = teclado.nextLine();
            enter(1);
            if (usuario.isBlank()) {
                error = true;
                System.out.println("ERROR: Entrada nula no válida");
                enter(1);
            } else {
                error = false;
            }
        } while (error);
    }

    static void intro(Scanner teclado, String mostrartutorial) {
        enter(1);
        System.out.println("Introducción: ");
        enter(1);
        System.out.println("Te encuentras en las rondas clasificatorias de un torneo clandestino de artes marciales " +
                "organizado por la misteriosa corporación Shadaloo. Para hacerte con el título de Campeón Mundial, " +
                "deberás \nenfrentarte en tres combates consecutivos contra rivales cada vez más poderosos.");
        enter(1);
        System.out.println("Tu nivel de salud inicial es de 100 HP y cuentas con 3 curaciones que " +
                "recuperan 25 HP cada una. ¡Demuestra tus habilidades y alcanza la gloria en el ranking!");
        continuar(teclado);
        System.out.println("**Para abandonar el combate, escribir Abandonar en la terminal**");
        enter(1);
        boolean error;
        do {
            System.out.print("¿Mostrar el tutorial? True o False: ");
            String tutorial = teclado.nextLine();
            if (tutorial.isBlank()) {
                error = true;
                System.out.println("ERROR: Entrada nula no válida");
                enter(1);
            } else if (tutorial.equalsIgnoreCase("True")) {
                error = false;
                enter(1);
                System.out.println(mostrartutorial);
                enter(2);
                continuar(teclado);
            } else if (tutorial.equalsIgnoreCase("False")) {
                error = false;
                enter(2);
            } else {
                error = true;
                System.out.println("ERROR: Entrada de texto no váida");
                enter(1);
            }
        } while (error);
        Escenas.fight();
        continuar(teclado);
    }

    static String tutorial() {
        String mostrartutorial = """
                       --------------------------------------------------------------------------------------- Tutorial ----------------------------------------------------------------------------------

                Una vez iniciado el combate aparecerán los dos combatientes, jugarás como Ryu, el personaje de la izquierda. Abajo de cada uno se encuentra su barra de salud junto con su nombre a la izquierda 
                y el nivel actual a la derecha. Para atacar al oponente se escribe Hit en la terminal, el daño causado tanto al oponente como a ti se calcula aleatoriamente dentro de un rango. 

                """;

        return mostrartutorial;
    }

    static void mostrarBarra(Personaje Ryu, Personaje Dan_Hibiki, boolean obtenerDano_Oponente, boolean obtenerDano_Ryu) {
        System.out.println("                          " + Ryu.barravida(obtenerDano_Oponente)
                + "                                                                            "
                + Dan_Hibiki.barravida(obtenerDano_Ryu));
    }

    static void ronda1(Scanner teclado, Personaje Ryu, Personaje Dan_Hibiki, boolean obtenerDano_Oponente, boolean obtenerDano_Ryu) {
        Escenas.escena1();
        mostrarBarra(Ryu, Dan_Hibiki, obtenerDano_Oponente, obtenerDano_Ryu);
        
    }

    static void combate(Scanner teclado, Personaje Ryu, Personaje Dan_Hibiki, boolean obtenerDano_Oponente, boolean obtenerDano_Ryu) {
        boolean error;
        for (int ronda = 1; ronda <= 3; ronda++) {
            if(ronda == 1){
                ronda1(teclado, Ryu, Dan_Hibiki, obtenerDano_Oponente, obtenerDano_Ryu);  
            }
            do {
                System.out.print("");
                String continuar = teclado.nextLine();
                if (continuar.isBlank()) {
                    error = true;
                    ronda1(teclado, Ryu, Dan_Hibiki, obtenerDano_Oponente, obtenerDano_Ryu);
                } else if (continuar.equalsIgnoreCase("Hit")) {
                    obtenerDano_Oponente = true;
                    error = false;
                    Escenas.escena1CombateRyu();
                    mostrarBarra(Ryu, Dan_Hibiki, obtenerDano_Oponente, obtenerDano_Ryu);
                    continuar(teclado);
                } else if (continuar.equalsIgnoreCase("Abandonar")) {
                    return;
                } else {
                    error = true;
                    ronda1(teclado, Ryu, Dan_Hibiki, obtenerDano_Oponente, obtenerDano_Ryu);
                }
            } while (error);
        }
    }
}
