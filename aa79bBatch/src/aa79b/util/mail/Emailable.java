/**
 * Fichero: Emailable.java
 * Autor: aresua
 */
package aa79b.util.mail;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Titulo: Emailable
 * Empresa: Eurohelp Consulting
 * @author aresua
 * @version 1.0
 *
 */
public interface Emailable extends Serializable {
    /**
     * Este metodo sirve para obtener el to address
     * @author aresua
     * @return to address
     */
    public String getToAddress();

    /**
     * Este metodo sirve para
     * @author aresua
     * @param toAddress String
     */
    public void setToAddress(String toAddress);

    /**
     * Este metodo sirve para añadir un ccc address
     * @author aresua
     * @param cccAddress El cc address
     */
    public void addCccAddress(String cccAddress);

    /**
     * Este metodo sirve para obetener La lista de ccc address
     * @author aresua
     * @return La lista de ccc address
     */
    public List<String> getCccAddress();

    /**
     * Este metodo sirve para fijar el from addres
     * @author aresua
     * @param fromAddress El from address
     */
    public void setFromAddress(String fromAddress);

    /**
     * Este metodo sirve para obtener el from address
     * @author aresua
     * @return El from address
     */
    public String getFromAddress();

    /**
     * Este metodo sirve para fijar subject
     * @author aresua
     * @param subject El subject
     */
    public void setSubject(String subject);

    /**
     * Este metodo sirve para obtener el subject
     * @author aresua
     * @return El subject
     */
    public String getSubject();

    /**
     * Este metodo sirve para fijar el body
     * @author aresua
     * @param newBody String
     */
    public void setBody(String newBody);

    /**
     * Este metodo sirve para obtener el body
     * @author aresua
     * @return El body
     */
    public String getBody();

    /**
     * Este metodo sirve para fijar el content type
     * @author aresua
     * @return El context type
     */
    public String getContentType();

    /**
     * Este metodo sirve para obtener el content type
     * @author aresua
     * @param contentType El content type
     */
    public void setContentType(String contentType);

    /**
     * Este metodo sirve para fijar un adjunto
     * @author aresua
     * @param file Object
     */
    public void setFile(Object file);

    /**
     * Este metodo sirve para fijar los adjuntos
     * @author aresua
     * @param files Collection
     */
    public void setFiles(Collection<Object> files);

    /**
     * Este metodo sirve para obtener los adjuntos
     * @author aresua
     * @return Los adjuntos
     */
    public Collection<Object> getFiles();
}
