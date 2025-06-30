/**
 * Fichero: FirmaService.java
 * Autor: aresua
 */
package aa79b.util.firma;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Titulo: FirmaService Empresa: Eurohelp Consulting Copyright: Copyright
 *
 * @author javarona
 * @version 1.0
 *
 */
public final class FirmaService {
	private static final FirmaService INSTANCE = new FirmaService();

	/**
	 * Este metodo sirve para obtener una propiedad del fichero de propiedades
	 * @param propiedad
	 *            La key de la propiedad
	 * @return El valor de la propiedad
	 */
	public static String obtenerProperty(String propiedad) {
		String strResultado = "";
		final InputStream in = FirmaService.class.getResourceAsStream("/aa79b/aa79b.properties");
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
	public static FirmaService getInstance() {
		return FirmaService.INSTANCE;
	}

	private FirmaDelegate delegate = null;

	/**
	 * Un constructor para FirmaService
	 *
	 * @author aresua
	 */
	private FirmaService() {
		// Crea el delegado de firma
		this.delegate = new CoreFirmaDelegate();
	}


	public String getServerSignatureLocationDetached(String document) throws Exception{
		return this.delegate.getServerSignatureLocationDetached( document);
	}



}
