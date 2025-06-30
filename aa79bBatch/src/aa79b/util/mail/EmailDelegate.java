/**
 * Fichero: EmailDelegate.java
 * Autor: aresua
 */
package aa79b.util.mail;

/**
 * Titulo: EmailDelegate
 * Empresa: Eurohelp Consulting
 * @author aresua
 * @version 1.0
 *
 */
public interface EmailDelegate {
    /**
     * Este metodo sirve para enviar un email
     * @author aresua
     * @param k81bEmailMessage El mensaje a enviar
     */
    public void sendEmail(Emailable k81bEmailMessage);
}
