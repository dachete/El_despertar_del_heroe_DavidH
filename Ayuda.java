public class Ayuda {
    static void ayuda(){
        System.out.println("       --------------------------------------------------------------------------------------- Ayuda ---------------------------------------" + 
        "----------------------------------------------");
        Main.enter(1);
        String reglas = """
        Reglas:

        - El jugador debe acabar los tres encuentros para clasificarse en el Ranking
        - En cada encuentro se reiniciar la vida del jugador
        - La vida del héroe comenzará con 100 puntos
        - El ataque del héroe estará entre 10 y 20 de daño
        - El ataque de los oponentes estará entre 5 y 15 de daño
        - El héroes comienza con 3 curaciones y se reiniciarán en cada encuentro
        - La vida de los oponentes será de 40, 60 y 80 puntos de vida

        """;
        System.out.println(reglas);

        String acciones = """
        Acciones: 
        
        En el menú principal:

        - Nueva partida: iniciar una partida nueva
        - Ranking: consultar el ranking de los jugadores y sus puntuaciones
        - Ayuda: abrir el panel de ayuda
        - Salir: salir del juego

        Durante el combate:

        - Abandonar: salir del juego
        - Hit: atacar al oponente
        - Curar: utilizar una recuperación de vida
        - Inventario: consultar el inventario
        
        """;

        System.out.println(acciones);

        String puntuacion = """
        Puntuación:

        - Se otogran 100 puntos por cada enemigo derrotado bajo tu nombre de usuario
        - Solo en caso de derrotar a los tres enemigos seguidamente se suma 50 puntos de bonificación
        
        """;

        System.out.println(puntuacion);
    }
}
