 
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String decision;
        Escenas.imagenmain();
        System.out.println("**Para una mejor experiencia maximiza el tamaño de la terminal**");
        enter(1);
        do{
            System.out.print("Decisión: ");
            decision = teclado.nextLine();     
            if (decision.equalsIgnoreCase("Salir")) {
                break;
            } else if(decision.equalsIgnoreCase("Ranking")){
                System.out.println("Ranking");
            } else if(decision.equalsIgnoreCase("Ayuda")){
                enter(2);
                Ayuda.ayuda();
            } else if(decision.equalsIgnoreCase("Nueva partida")){
                nombreusuario(teclado);
                intro(teclado);
            } else{
                System.out.println("ERROR: Entrada no válida. Introduce una opción");
                enter(1);
            }
            
        } while(!decision.equalsIgnoreCase("Nueva partida") && !decision.equalsIgnoreCase("Ranking") && !decision.equalsIgnoreCase("Ayuda")
        && !decision.equalsIgnoreCase("Salir"));
        teclado.close();
    }

    static void enter(int saltos){
        for(int i=0; i<saltos; i++){
            System.out.println();
        }        
    }

    static void continuar(Scanner teclado){
        System.out.println("");
        teclado.nextLine();     
    }

    static void nombreusuario(Scanner teclado){
        enter(1);
        String usuario;
        boolean error;
        do{
            System.out.print("Introduce nombre de usuario: ");
            usuario = teclado.nextLine();
            enter(1);
            if(usuario.isBlank()){
                error = true;
                System.out.println("ERROR: Nombre en blanco no válido.");
                enter(1);
            } else{
                error = false;
            }
        } while(error);
    }

    static void intro(Scanner teclado){         
        enter(1);
        System.out.println("Introducción: ");
        enter(1);
        System.out.println("Te encuentras en las rondas clasificatorias de un torneo clandestino de artes marciales " + 
        "organizado por la misteriosa corporación Shadaloo. Para hacerte con el título de Campeón " + 
        "Mundial, deberás enfrentarte en tres combates consecutivos contra rivales cada vez más poderosos.");
        enter(1);
        System.out.println("Tu nivel de salud inicial es de 100 HP y cuentas con recuèraciones de vida que " + 
        "recuperan 25 HP cada una. ¡Demuestra tus habilidades y alcanza la gloria en el ranking!");
        continuar(teclado);
    }

    static void ronda1(){
        Escenas.escena3();
    }
}
