/**
 * Fichero: PidService.java
 * Autor: aresua
 */
package aa79b.util.pid;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;


/**
 * Titulo: PidService Empresa: Eurohelp Consulting Copyright: Copyright
 *
 * @author aresua
 * @version 1.0
 *
 */
public final class PidService {
	private static final PidService INSTANCE = new PidService();

	/**
	 * Este metodo sirve para obtener una propiedad del fichero de propiedades
	 * @param propiedad
	 *            La key de la propiedad
	 * @return El valor de la propiedad
	 */
	public static String obtenerProperty(String propiedad) {
		String strResultado = "";
		final InputStream in = PidService.class.getResourceAsStream("/aa79b/aa79b.properties");
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
	public static PidService getInstance() {
		return PidService.INSTANCE;
	}

	private PidDelegate delegate = null;

	/**
	 * Un constructor para PidService
	 *
	 * @author aresua
	 */
	private PidService() {
		// Crea el delegado de e-mails
		this.delegate = new CorePidDelegate();
	}

	/**
	 * Este metodo sirve para subir un documento del Pif al Pid
	 * @author aresua
	 * @param nombreDocumento Nombre del documento
	 * @param rutaPif Ruta del Pif
	 * @return String devuelve el oid
	 */
	public String addDocument(String nombreDocumento, String rutaPif) {
		return this.delegate.addDocument(nombreDocumento, rutaPif);
	}

	/**
	 * Este metodo sirve para modificar un documento del PID
	 * @author aresua
	 * @param nombreDocumento Nombre del documento
	 * @param rutaPif Ruta del Pif
	 * @param oid Oid a modificar
	 */
	public void modifyDocument(String nombreDocumento, String rutaPif, String oid) {
		this.delegate.modifyDocument(nombreDocumento, rutaPif, oid);
	}

	public String copyDocument(String oid) {
		return this.delegate.copyDocument(oid);
	}

	public boolean deleteDocument(String oid) {
		return this.delegate.deleteDocument(oid);
	}

	public void deleteDocuments(List<String> listOids){
		this.delegate.deleteDocuments(listOids);
	}

	/**
	 *
	 * @param oid
	 * @return
	 */
	public String getRutaPIF(String oid) {
		return this.delegate.getRutaPIF(oid);
	}
	public String getRutaPIFX43f(String oid) {
		return this.delegate.getRutaPIFX43f(oid);
	}

}
