package com.ejie.aa79b.webservices;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.ejie.w43df.pid.client.W43DfDocument;
import com.ejie.y31.vo.Y31AttachmentBean;

/**
 * The type T65bWebServices.
 *
 * @author vdorantes
 *
 */
public interface PIDService {

	/**
	 * The method addDocument.
	 *
	 * @param nombreDocumento
	 *            String
	 * @param fichero
	 *            byte[]
	 * @return String
	 * @throws Exception
	 */
	public String addDocument(String nombreDocumento, byte[] fichero) throws Exception;

	/**
	 * The method getDocument.
	 *
	 * @param oid
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] getDocument(String oid) throws Exception;

	/**
	 * The method modifyDocument.
	 *
	 * @param nombreDocumento
	 *            String
	 * @param rutaPIF
	 *            String
	 * @param oid
	 *            String
	 * @throws Exception
	 */
	public void modifyDocument(String nombreDocumento, String rutaPIF, String oid) throws Exception;

	/**
	 * The method deleteDocument.
	 *
	 * @param oid
	 *            String
	 * @throws Exception
	 */
	public void deleteDocument(String oid) throws Exception;

	/**
	 * The method deleteDocuments.
	 *
	 * @param listaOids
	 *            List<String>
	 * @throws Exception
	 */
	public void deleteDocuments(List<String> listaOids) throws Exception;

	/**
	 * @param oid
	 *            String
	 * @return W43DfDocument
	 * @throws Exception
	 *             e
	 */
	public W43DfDocument getDocumentInfo(String oid) throws Exception;

	/**
	 * @param nombreDocumento
	 *            String
	 * @param fichero
	 *            byte[]
	 * @return String
	 * @throws Exception
	 *             e
	 */
	String subidaPif(String nombreDocumento, byte[] fichero) throws Exception;

	/**
	 * @param rutaPif
	 *            String
	 * @param nombreDocumento
	 *            String
	 * @return String
	 * @throws Exception
	 *             e
	 */
	String addDocument(String nombreDocumento, String rutaPif) throws Exception;

	/**
	 * Obtiene la ruta del PIF asociada al fichero
	 *
	 * @param oid
	 * @return String con la ruta del fichero
	 * @throws Exception
	 */
	public String getRutaPIF(String oid) throws Exception;

	/**
	 *
	 * @param nombreDocumento
	 *            String
	 * @param fichero
	 *            byte[]
	 * @param propiedadRutaPIF
	 *            String
	 * @return String
	 * @throws Exception
	 *             e
	 */
	String subidaPif(String nombreDocumento, byte[] fichero, String propiedadRutaPIF, boolean preserveName) throws Exception;

	/**
	 *
	 * @param nombreDocumento
	 *            String
	 * @param ruta
	 *            String
	 * @return String
	 * @throws Exception
	 *             e
	 */
	public String getOidFromPif(String nombreDocumento, String ruta) throws Exception;

	void modifyDocumentConBytes(String nombreDocumento, byte[] fichero, String oid) throws Exception;

	void getDocument(String oid, OutputStream out) throws Exception;

	InputStream servicioGetDelPifInput(String ruta) throws Exception;

	/**
	 *
	 * @param nombreDocumento
	 *            String
	 * @param ruta
	 *            String
	 * @return String
	 * @throws Exception
	 *             e
	 */
	String moveDocument(String nombreDocumento, String ruta) throws Exception;

	InputStream getDocumentInput(String oid) throws Exception;

	/**
	 *
	 * @param nombreDocumento
	 *            String
	 * @param fichero
	 *            byte[]
	 * @param propiedadRutaPIF
	 *            String
	 * @return String
	 * @throws Exception
	 */
	String subidaPifP43j(String nombreDocumento, byte[] fichero, String propiedadRutaPIF) throws Exception;

	/**
	 *
	 * @param nombre
	 * @param ruta
	 * @return
	 * @throws Exception
	 */
	Y31AttachmentBean prepararDocumentoPid(String nombre, String ruta) throws Exception;

	/**
	 *
	 * @param nombreDocumento
	 * @param fichero
	 * @return
	 * @throws Exception
	 */
	String subidaPif(String nombreDocumento, InputStream fichero) throws Exception;

	/**
	 *
	 * @param nombreDocumento
	 * @param fichero
	 * @return
	 */
	String addDocument(String nombreDocumento, InputStream fichero);

	/**
	 * Descarga un fichero del PID por su OID, y lo deja en la ruta PIF que recibe como parametro
	 * @param oid
	 * @param pathPIFDestino
	 * @return
	 * @throws Exception
	 */
	String getRutaPIF(String oid, String pathPIFDestino) throws Exception;

}
