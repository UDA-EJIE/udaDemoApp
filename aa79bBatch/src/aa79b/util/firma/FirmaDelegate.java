/**
 * Fichero: FirmaDelegate.java
 * Autor: aresua
 */
package aa79b.util.firma;

/**
 * Titulo: FirmaDelegate
 * Empresa: Eurohelp Consulting
 * @author javarona
 * @version 1.0
 *
 */
public interface FirmaDelegate {

	/**
	 *
	 * @param rutaArchivoPIF
	 * @return
	 * @throws Exception
	 */
	public String getServerSignatureLocationDetached(String rutaArchivoPIF) throws Exception;


}
