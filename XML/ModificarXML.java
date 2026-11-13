package XML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ModificarXML {
    public static void actualizarPuntuacion(String nombre, int puntuacion) throws Exception {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }
        if (puntuacion < 0) {
            throw new IllegalArgumentException("La puntuación no puede ser negativa");
        }

        CrearXML.crearSiNoExiste();
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        DocumentBuilder constructor = fabrica.newDocumentBuilder();
        Document documento = constructor.parse(CrearXML.ARCHIVO_RANKING);
        documento.getDocumentElement().normalize();

        if (!documento.getDocumentElement().getTagName().equals("ranking")) {
            throw new IllegalStateException("El XML no tiene una raíz <ranking>");
        }

        NodeList jugadores = documento.getElementsByTagName("jugador");
        for (int i = 0; i < jugadores.getLength(); i++) {
            Element jugador = (Element) jugadores.item(i);
            if (jugador.getAttribute("nombre").equals(nombre)) {
                NodeList puntuaciones = jugador.getElementsByTagName("puntuacion");
                if (puntuaciones.getLength() > 1) {
                    throw new IllegalStateException("El jugador tiene varias puntuaciones en ranking.xml");
                }
                Element puntuacionActual = puntuaciones.getLength() == 0
                        ? null : (Element) puntuaciones.item(0);
                if (puntuacionActual == null) {
                    puntuacionActual = documento.createElement("puntuacion");
                    jugador.appendChild(puntuacionActual);
                }
                int puntuacionTotal = Math.addExact(
                        Integer.parseInt(puntuacionActual.getTextContent()), puntuacion);
                puntuacionActual.setTextContent(Integer.toString(puntuacionTotal));
                CrearXML.guardar(documento);
                return;
            }
        }

        Element jugador = documento.createElement("jugador");
        jugador.setAttribute("nombre", nombre);
        Element elementoPuntuacion = documento.createElement("puntuacion");
        elementoPuntuacion.setTextContent(Integer.toString(puntuacion));
        jugador.appendChild(elementoPuntuacion);
        documento.getDocumentElement().appendChild(jugador);
        CrearXML.guardar(documento);
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Uso: ModificarXML <nombre> <puntuación>");
            return;
        }
        actualizarPuntuacion(args[0], Integer.parseInt(args[1]));
        System.out.println("Puntuación guardada correctamente");
    }
}
