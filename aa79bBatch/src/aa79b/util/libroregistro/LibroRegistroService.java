/**
 * Fichero: LibroRegistroService.java
 * Autor: javarona
 */
package aa79b.util.libroregistro;

import aa79b.util.beans.LibroRegistroEntrada;


/**
 * Titulo: LibroRegistroService Empresa: Eurohelp Consulting Copyright: Copyright
 *
 * @author javarona
 * @version 1.0
 *
 */
public final class LibroRegistroService {
	private static final LibroRegistroService INSTANCE = new LibroRegistroService();

	/**
	 * Este metodo sirve para obtener la instacia de la clase
	 *
	 * @author javarona
	 * @return La instancia de la clase
	 */
	public static LibroRegistroService getInstance() {
		return LibroRegistroService.INSTANCE;
	}

	private LibroRegistroDelegate delegate = null;

	/**
	 * Un constructor para LibroRegistroService
	 *
	 * @author javarona
	 */
	private LibroRegistroService() {
		this.delegate = new CoreLibroRegistroDelegate();
	}

	
	/**
	 * Da de alta en el registro presencial
	 * 
	 * @param libroRegistro
	 *            LibroRegistroEntrada
	 * @return LibroRegistroEntrada
	 * @throws Exception
	 *             Exception
	 */
	public LibroRegistroEntrada altaRegistroEntrada(LibroRegistroEntrada libroRegistro) throws Exception {
		return this.delegate.altaRegistroEntrada(libroRegistro);
	}
	
}
