/**
 * Fichero: EmailService.java
 * Autor: aresua
 */
package aa79b.util.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Titulo: EmailService Empresa: Eurohelp Consulting Copyright: Copyright
 *
 * @author aresua
 * @version 1.0
 *
 */
public final class EmailService {
    private static final String SERVIDOR_SMTP = EmailService.obtenerProperty("mail.smptHost");
    		
    private static final EmailService INSTANCE = new EmailService();

    /**
     * Este metodo sirve para obtener una propiedad del fichero de propiedades
     * @param propiedad
     *            La key de la propiedad
     * @return El valor de la propiedad
     */
    public static String obtenerProperty(String propiedad) {
        String strResultado = "";
        final InputStream in = EmailService.class.getResourceAsStream("/aa79b/aa79b.properties");
        if (in != null) {
            final Properties prop = new Properties();
            try {
                prop.load(in);
            } catch (final IOException e) {
                throw new RuntimeException(
                        "obtenerProperty. El fichero /aa79b/aa79b.properties ha dado error.",
                        e);
            }
            strResultado = prop.getProperty(propiedad);
        } else {
            throw new RuntimeException(
                    "obtenerProperty. El fichero /aa79b/aa79b.properties no esta en el classpath o no existe.");
        }
        return strResultado;
    }

    /**
     * Este metodo sirve para obtener la instacia de la clase
     *
     * @author aresua
     * @return La instancia de la clase
     */
    public static EmailService getInstance() {
        return EmailService.INSTANCE;
    }

    private EmailDelegate delegate = null;

    /**
     * Un constructor para EmailService
     *
     * @author aresua
     */
    private EmailService() {
        // Crea el delegado de e-mails
        this.delegate = new CoreEmailDelegate(EmailService.SERVIDOR_SMTP);
    }

    /**
     * Este metodo sirve para enviar un e-mail
     * @author aresua
     * @param email Los datos del email
     */
    public void sendEmail(Emailable email) {
        this.delegate.sendEmail(email);
    }
}
