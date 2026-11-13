package XML;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LeerXML {
    public static void mostrarRanking() throws Exception {
        CrearXML.crearSiNoExiste();

        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        DocumentBuilder constructor = fabrica.newDocumentBuilder();
        Document documento = constructor.parse(CrearXML.ARCHIVO_RANKING);
        documento.getDocumentElement().normalize();

        if (!documento.getDocumentElement().getTagName().equals("ranking")) {
            throw new IllegalStateException("El XML no tiene una raíz <ranking>");
        }

        NodeList nodos = documento.getElementsByTagName("jugador");
        List<Element> jugadores = new ArrayList<>();
        for (int i = 0; i < nodos.getLength(); i++) {
            jugadores.add((Element) nodos.item(i));
        }

        jugadores.sort(Comparator.comparingInt(LeerXML::puntuacion).reversed());
        System.out.println();
        System.out.println("       --------------------------------------------------------------------------------------- Ranking ---------------------------------------" + 
        "----------------------------------------------");
        System.out.println();
        if (jugadores.isEmpty()) {
            System.out.println("Todavía no hay puntuaciones registradas");
            System.out.println();
            return;
        }

        for (int i = 0; i < jugadores.size(); i++) {
            Element jugador = jugadores.get(i);
            System.out.println();
            System.out.println((i + 1) + ". " + jugador.getAttribute("nombre")
                    + " - " + puntuacion(jugador) + " puntos");
        }
    }

    private static int puntuacion(Element jugador) {
        NodeList puntuaciones = jugador.getElementsByTagName("puntuacion");
        if (!jugador.hasAttribute("nombre") || puntuaciones.getLength() != 1) {
            throw new IllegalStateException("Entrada de jugador incompleta en ranking.xml");
        }
        return Integer.parseInt(puntuaciones.item(0).getTextContent());
    }

    public static void main(String[] args) throws Exception {
        mostrarRanking();
    }
}
