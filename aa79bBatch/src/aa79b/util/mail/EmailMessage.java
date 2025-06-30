/**
 * Fichero: EmailMessage.java
 * Autor: aresua
 * Fecha: 23/03/2017
 */
package aa79b.util.mail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Titulo: EmailMessage Empresa: Eurohelp Consulting Copyright: Copyright
 * (c) 2017.
 *
 * @author aresua
 * @version 1.0
 *
 */
public final class EmailMessage implements Emailable, Serializable {
    /**
     * serialVersionUID: long.
     */
    private static final long serialVersionUID = 1L;

    /**
     * toAddress: String.
     */
    private String toAddress = "";
    /**
     * cccAddress: List.
     */
    private List<String> cccAddress = new ArrayList<String>();
    /**
     * fromAddress: String.
     */
    private String fromAddress = "";
    /**
     * subject: String.
     */
    private String subject = "";
    /**
     * body: String.
     */
    private String body = "";
    /**
     * files: List.
     */
    private List<Object> files = new ArrayList<Object>();
    /**
     * contentType: String.
     */
    private String contentType = "text/plain";

    /**
     * certDNIErr: List
     */
    private List<String> certDNIErr = new ArrayList<String>();

    /**
     * Este metodo sirve para obetener el valor de la propiedad toAddress.
     *
     * @author aresua
     * @return El valor de toAddress
     */
    @Override
    public String getToAddress() {
        return this.toAddress;
    }

    /**
     * Este metodo sirve para fijar el valor de la propiedad toAddress.
     *
     * @author aresua
     * @param toAddress
     *            El nuevo valor de toAddress
     */
    @Override
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    /**
     * Este es un metodo sobreescrito.
     *
     * @see aa79b.aa79butil.aa79bmail.aa79bEmailable#addCccAddress(java.lang.String)
     * @author aresua
     * @param cccAddress String
     */
    @Override
    public void addCccAddress(String cccAddress) {
        this.cccAddress.add(cccAddress);
    }

    /**
     * Este metodo sirve para obetener el valor de la propiedad cccAddress.
     *
     * @author aresua
     * @return El valor de cccAddress
     */
    @Override
    public List<String> getCccAddress() {
        return this.cccAddress;
    }

    /**
     * Este metodo sirve para fijar el valor de la propiedad cccAddress.
     *
     * @author aresua
     * @param cccAddress
     *            El nuevo valor de cccAddress
     */
    public void setCccAddress(List<String> cccAddress) {
        this.cccAddress = cccAddress;
    }

    /**
     * Este metodo sirve para obetener el valor de la propiedad fromAddress.
     *
     * @author aresua
     * @return El valor de fromAddress
     */
    @Override
    public String getFromAddress() {
        return this.fromAddress;
    }

    /**
     * Este metodo sirve para fijar el valor de la propiedad fromAddress.
     *
     * @author aresua
     * @param fromAddress
     *            El nuevo valor de fromAddress
     */
    @Override
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * Este metodo sirve para obetener el valor de la propiedad subject.
     *
     * @author aresua
     * @return El valor de subject
     */
    @Override
    public String getSubject() {
        return this.subject;
    }

    /**
     * Este metodo sirve para fijar el valor de la propiedad subject.
     *
     * @author aresua
     * @param subject
     *            El nuevo valor de subject
     */
    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Este metodo sirve para obetener el valor de la propiedad body.
     *
     * @author aresua
     * @return El valor de body
     */
    @Override
    public String getBody() {
        return this.body;
    }

    /**
     * Este metodo sirve para fijar el valor de la propiedad body.
     *
     * @author aresua
     * @param body
     *            El nuevo valor de body
     */
    @Override
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Este es un metodo sobreescrito.
     * @see aa79b.aa79butil.aa79bmail.aa79bEmailable#setFile(java.lang.Object)
     * @author aresua
     * @param file Object
     */
    @Override
    public void setFile(Object file) {
        this.files.add(file);
    }

    /**
     * Este metodo sirve para obetener el valor de la propiedad files.
     *
     * @author aresua
     * @return El valor de files
     */
    @Override
    public Collection<Object> getFiles() {
        return this.files;
    }

    /**
     * Este metodo sirve para fijar el valor de la propiedad files.
     *
     * @author aresua
     * @param files
     *            El nuevo valor de files
     */
    @Override
    public void setFiles(Collection<Object> files) {
        this.files = new ArrayList<Object>(files);
    }

    /**
     * Este metodo sirve para obetener el valor de la propiedad contentType.
     *
     * @author aresua
     * @return El valor de contentType
     */
    @Override
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Este metodo sirve para fijar el valor de la propiedad contentType.
     *
     * @author aresua
     * @param contentType
     *            El nuevo valor de contentType
     */
    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return the certDNIErr
     */
    public List<String> getCertDNIErr() {
        return this.certDNIErr;
    }

    /**
     * @param certDNIErr the certDNIErr to set
     */
    public void setCertDNIErr(List<String> certDNIErr) {
        this.certDNIErr = certDNIErr;
    }

    /**
     *
     * @param certDNIErr String
     */
    public void addCertDNIErr(String certDNIErr) {
        this.certDNIErr.add(certDNIErr);
    }


}
