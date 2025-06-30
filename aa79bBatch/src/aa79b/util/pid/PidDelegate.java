/**
 * Fichero: PidDelegate.java
 * Autor: aresua
 */
package aa79b.util.pid;

import java.util.List;

import aa79b.w43df.pid.client.W43DfWSFault;

/**
 * Titulo: PidDelegate
 * Empresa: Eurohelp Consulting
 * @author aresua
 * @version 1.0
 *
 */
public interface PidDelegate {

	/**
	 *
	 * @param nombreDocumento String
	 * @param rutaPif String
	 * @return String
	 */
	public String addDocument(String nombreDocumento, String rutaPif);

	/**
	 *
	 * @param nombreDocumento String
	 * @param rutaPIF String
	 * @param oid String
	 */
	void modifyDocument(String nombreDocumento, String rutaPIF, String oid);

	/**
	 *
	 * @param oid
	 * @return
	 */
	public String copyDocument(String oid);

	/**
	 *
	 * @param oid
	 * @throws W43DfWSFault
	 * @throws Exception
	 */
	boolean deleteDocument(String oid);

	/**
	 *
	 * @param listaOids
	 * @throws Exception
	 */
	void deleteDocuments(List<String> listaOids);

	/**
	 *
	 * @param oid
	 * @return
	 */
	String getRutaPIF(String oid);

	/**
	 *
	 * @param oid
	 * @return
	 */
	String getRutaPIFX43f(String oid);
}
