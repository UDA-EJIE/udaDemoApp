package aa79b.util.token;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import aa79b.common.exceptions.AppRuntimeException;
import aa79b.util.common.Logger;
import n38a.exe.N38APISesion;

public final class TokenFactory {
    /**
     * INSTANCE: Aa06aTokenFactory.
     */
    private static final TokenFactory INSTANCE = new TokenFactory();

    /**
     * Este metodo devuelve la instancia de la clase.
     *
     * @return La instancia de la clase
     */
    public static TokenFactory getInstance() {
        return TokenFactory.INSTANCE;
    }

    /**
     * Este metodo devuelve el token de la aplicacion proporcionada.
     *
     * @param aplicacion
     *            La aplicacion
     * @return El token
     */
    public String getToken(String aplicacion) {
        try {
            // Obtenemos la sesion de N38
            final N38APISesion n38Sesion = new N38APISesion();
            // Obtenemos el token de session
            final Document token = n38Sesion.n38APISesionCrearApp(aplicacion);

            final DOMSource domSource = new DOMSource(token);
            final StringWriter writer = new StringWriter();
            final StreamResult result = new StreamResult(writer);

            final TransformerFactory tf = TransformerFactory.newInstance();
            final Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            return writer.toString();
        } catch (Exception e) {
            throw new AppRuntimeException(e);
        }
    }

    /**
     * Devuelve el Document a partir de un String
     *
     * @param string
     *            El documento W3C DOM a parsear en formato string
     * @return El document resultante
     * @throws Exception
     *             Cualquier excepcion
     */
    public static Document fncString2DOM(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(xmlStr)));
        } catch (Exception e) {
        	Logger.batchlog(Logger.INFO,e.getMessage());
            throw new AppRuntimeException(e);
        }
        return doc;
    }
}
