package XML;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;


public class CrearXML {
    public static final File ARCHIVO_RANKING = new File("XML", "ranking.xml");

    public static void crearSiNoExiste() throws Exception {
        if (ARCHIVO_RANKING.exists()) {
            return;
        }

        File carpeta = ARCHIVO_RANKING.getParentFile();
        if (!carpeta.exists() && !carpeta.mkdirs()) {
            throw new IllegalStateException("No se pudo crear la carpeta XML");
        }

        DocumentBuilder constructor = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document documento = constructor.newDocument();
        documento.appendChild(documento.createElement("ranking"));
        guardar(documento);
    }

    static void guardar(Document documento) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.transform(new DOMSource(documento), new StreamResult(ARCHIVO_RANKING));
    }

    public static void main(String[] args) throws Exception {
        crearSiNoExiste();
        System.out.println("Fichero ranking.xml preparado correctamente");
    }
}
