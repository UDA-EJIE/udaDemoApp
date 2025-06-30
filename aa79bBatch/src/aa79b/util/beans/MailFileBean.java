/**
 * Fichero: MailFileBean.java
 * Autor: aresua
 */
package aa79b.util.beans;

import java.io.Serializable;

/**
 * Titulo: MailFileBean.
 * Empresa: Eurohelp Consulting
 * @author aresua
 * @version 1.0
 *
 */
public class MailFileBean implements Serializable {

    /**
     * serialVersionUID: long.
     */
    private static final long serialVersionUID = 1L;

    /**
     * nombre: String.
     * Este campo sirve para almacenar el valor del nombre.
     */
    private String nombre = "";

    /**
     * bytes: new byte[0].
     * Este campo sirve para almacenar el valor de bytes.
     */
    private byte[] bytes = new byte[0];

    /**
     * contentType: String.
     * Este campo sirve para almacenar el valor del contentType.
     */
    private String contentType = "";

    /**
     * Este metodo sirve para obetener el valor de la propiedad nombre.
     * @author aresua
     * @return El valor de nombre
     */
    public final String getNombre() {
        return this.nombre;
    }
    /**
     * Este metodo sirve para fijar el valor de la propiedad nombre.
     * @author aresua
     * @param nombre El nuevo valor de nombre
     */
    public final void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Este metodo sirve para obetener el valor de la propiedad bytes.
     * @author aresua
     * @return El valor de bytes
     */
    public final byte[] getBytes() {
        return this.bytes;
    }
    /**
     * Este metodo sirve para fijar el valor de la propiedad bytes.
     * @author aresua
     * @param bytes El nuevo valor de bytes
     */
    public final void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
    /**
     * Este metodo sirve para obetener el valor de la propiedad contentType.
     * @author aresua
     * @return El valor de contentType
     */
    public final String getContentType() {
        return this.contentType;
    }
    /**
     * Este metodo sirve para fijar el valor de la propiedad contentType.
     * @author aresua
     * @param contentType El nuevo valor de contentType
     */
    public final void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
