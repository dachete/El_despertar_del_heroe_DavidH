
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Ryu ryu = new Ryu("Ryu", Ryu.VIDA_INICIAL);
        Dan_Hibiki dan = new Dan_Hibiki("Dan Hibiki", Dan_Hibiki.VIDA_INICIAL);
        Akuma akuma = new Akuma("Akuma", Akuma.VIDA_INICIAL);
        Baison baison = new Baison("M. Baison", Baison.VIDA_INICIAL);
        Personaje oponente = dan;
        String decision;
        System.out.println(Escenas.imagenmain());
        System.out.println("**Para una mejor experiencia maximiza el tamaño de la terminal**");
        enter(1);
        do {
            System.out.print("Decisión: ");
            decision = teclado.nextLine();
            if (decision.equalsIgnoreCase("Salir")) {
                break;
            } else if (decision.equalsIgnoreCase("Ranking")) {
                System.out.println("Ranking");
                System.out.println("Pulsa Enter para volver al menú principal");
                continuar(teclado);
            } else if (decision.equalsIgnoreCase("Ayuda")) {
                enter(2);
                Ayuda.ayuda();
                System.out.println("Pulsa Enter para volver al menú principal");
                continuar(teclado);
            } else if (decision.equalsIgnoreCase("Nueva partida")) {
                nombreusuario(teclado);
                intro(teclado, tutorial());
                combate(teclado, ryu, dan, akuma, baison, oponente);
            } else if (decision.isBlank()) {
                System.out.println("ERROR: Entrada nula no válida");
                enter(1);
            } else {
                System.out.println("ERROR: Entrada de texto no válida");
                enter(1);
            }

        } while (!decision.equalsIgnoreCase("Nueva partida") && !decision.equalsIgnoreCase("Salir"));
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
                System.out.println("ERROR: Entrada de texto no válida");
                enter(1);
            }
        } while (error);
    }

    static String tutorial() {
        String mostrartutorial = """
                       --------------------------------------------------------------------------------------- Tutorial ----------------------------------------------------------------------------------

                Una vez iniciado el combate aparecerán los dos combatientes, jugarás como Ryu, el personaje de la izquierda. Abajo de cada uno se encuentra su barra de salud junto con su nombre a la izquierda
                y el nivel actual a la derecha. Para atacar al oponente se escribe Hit en la terminal, el daño causado tanto al oponente como a ti se calcula aleatoriamente dentro de un rango. Después de
                atacar al oponente se debe presionar la tecla Enter para que pueda atacarte a ti.

                En tu turno, aparte de atacar, puedes abandonar (Abandonar), consultar el inventario (Inventario) o recuperar 25 puntos de vida (Curar). Dispones de 3 recuperaciones de 25 puntos de vida cada
                una, al utilizarlas se consume el turno. Si las consumes todas en un combate no podrás recuperar vida y al intentarlo no se consumirá el turno, igual que si tienes la vida entera.

                """;

        return mostrartutorial;
    }

    static void mostrarBarra(Ryu ryu, Personaje oponente) {
        System.out.println("                          " + ryu.barraVida(Ryu.VIDA_MAXIMA)
                + "                                                                            "
                + oponente.barraVida(oponente.getVidaMaxima()));
    }

    static void ronda1(Ryu ryu, Personaje oponente) {
        System.out.println(Escenas.escena1());
        mostrarBarra(ryu, oponente);
    }

    static void ronda2(Ryu ryu, Personaje oponente) {
        System.out.println(Escenas.escena2());
        mostrarBarra(ryu, oponente);
    }

    static void ronda3(Ryu ryu, Personaje oponente) {
        System.out.println(Escenas.escena3());
        mostrarBarra(ryu, oponente);
    }    

    static void mostrarRonda(int ronda, Ryu ryu, Personaje oponente) {
        if (ronda == 0) {
            ronda1(ryu, oponente);
        } else if (ronda == 1) {
            ronda2(ryu, oponente);
        } else {
            ronda3(ryu, oponente);
        }
    }

    static void mostrarInventario(int[] inventario, int disponibles) {
        for (int i = 0; i < disponibles; i++) {
            System.out.println((i + 1) + ": Recuperación " + Ryu.VIDA_CURACIONES + " HP");
        }
    }

    static void combate(Scanner teclado, Ryu ryu, Dan_Hibiki dan, Akuma akuma, Baison baison, Personaje oponente) {
        int ronda;
        String escenaCombate1 = Escenas.escena1CombateRyu();
        String escenaCombate2 = Escenas.escena1CombateDan();
        for (ronda = 0; ronda < 3; ronda++) {
            ryu.setVida(100);
            if (ronda == 1) {
                oponente = akuma;
                escenaCombate1 = Escenas.escena2CombateRyu();
                escenaCombate2 = Escenas.escena2CombateAkuma();
                oponente.setVida(Akuma.VIDA_INICIAL);
                System.out.println(Escenas.fight2());
                continuar(teclado);
            } else if (ronda == 2) {
                oponente = baison;
                escenaCombate1 = Escenas.escena3CombateRyu();
                escenaCombate2 = Escenas.escena3CombateBaison();
                oponente.setVida(Baison.VIDA_INICIAL);
                System.out.println(Escenas.fight3());
                continuar(teclado);
            } else{
                System.out.println(Escenas.fight1());
                continuar(teclado);
            }
            mostrarRonda(ronda, ryu, oponente);

            int[] inventario = { Ryu.VIDA_CURACIONES, Ryu.VIDA_CURACIONES, Ryu.VIDA_CURACIONES };
            int disponibles = inventario.length;

            while (ryu.getVida() > 0 && oponente.getVida() > 0) {
                String accion = teclado.nextLine();
                if (accion.equalsIgnoreCase("Abandonar")) {
                    return;
                }
                if (accion.equalsIgnoreCase("Inventario")) {
                    mostrarInventario(inventario, disponibles);
                    continue;
                } else if (accion.equalsIgnoreCase("Hit")) {
                    int danoRyu = ryu.calcularAtaque();
                    oponente.recibirDano(danoRyu);
                    System.out.println(escenaCombate1);
                    mostrarBarra(ryu, oponente);
                } else if (accion.equalsIgnoreCase("Curar")) {
                    if (disponibles == 0) {
                        mostrarRonda(ronda, ryu, oponente);
                        System.out.println("Has gastado todas tus recuperaciones de vida");
                        continue;
                    } else if (!ryu.curar(inventario[0], Ryu.VIDA_MAXIMA)) {
                        mostrarRonda(ronda, ryu, oponente);
                        System.out.println("No puedes recuperarte, tienes la vida completa");
                        continue;
                    } else {
                        for (int i = 0; i < disponibles - 1; i++) {
                            inventario[i] = inventario[i + 1];
                        }
                        disponibles--;
                        inventario[disponibles] = 0;
                        mostrarRonda(ronda, ryu, oponente);
                    }
                } else {
                    mostrarRonda(ronda, ryu, oponente);
                    continue;
                }
                if (oponente.getVida() == 0) {
                    enter(2);
                    System.out.println(Escenas.win());
                    enter(2);
                    continuar(teclado);
                    break;
                }
                continuar(teclado);
                int danoOponente = oponente.calcularAtaque();
                ryu.recibirDano(danoOponente);
                System.out.println(escenaCombate2);
                mostrarBarra(ryu, oponente);
                if (ryu.getVida() == 0) {
                    enter(2);
                    System.out.println(Escenas.gameover());
                    enter(2);
                    continuar(teclado);
                    return;
                }
            }
        }
    }
}