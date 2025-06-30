/**
 * Fichero: CorePidDelegate.java
 * Autor: aresua
 * Fecha: 2017
 */
package aa79b.util.pid;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import aa79b.util.common.Logger;
import aa79b.util.mail.EmailService;
import aa79b.util.token.TokenClientHandler;
import aa79b.util.token.TokenClientHandlerResolver;
import aa79b.w43df.pid.client.W43DfAttribute;
import aa79b.w43df.pid.client.W43DfContent;
import aa79b.w43df.pid.client.W43DfDocument;
import aa79b.w43df.pid.client.W43DfFormat;
import aa79b.w43df.pid.client.W43DfGETContent;
import aa79b.w43df.pid.client.W43DfGETDocument;
import aa79b.w43df.pid.client.W43DfPIDInterface;
import aa79b.w43df.pid.client.W43DfPIDService;

/**
 * Titulo: CorePidDelegate.
 * Empresa: Eurohelp Consulting
 * Copyright: Copyright (c) 2017
 * @author aresua
 * @version 1.0
 *
 */
public class CorePidDelegate implements PidDelegate {

	private static final String WSDL = CorePidDelegate.obtenerProperty("webservice.pid.wsdl");
	/**
	 * Un constructor para CorePidDelegate.
	 * @author aresua
	 */
	public CorePidDelegate() {
		/* vacio */
	}

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
	 * Este es un metodo sobreescrito.
	 * @see aa79b.aa79butil.aa79bmail.aa79bPidDelegate#addDocument(String nombreDocumento, String rutaPif)
	 * @author aresua
	 * @param nombreDocumento Nombre del documento
	 * @param rutaPif Ruta del Pif
	 * @return String devuelve el oid
	 */
	@Override
	public String addDocument(String nombreDocumento, String rutaPif) {
		String oid = "";
		try{
			// ESTABLECER CONFIGURACION DEL PID
			Logger.batchlog(Logger.INFO, "PIDServiceImpl configurando PID");
			final W43DfPIDInterface documentsManager = this.configurarPID();

			Logger.batchlog(Logger.INFO, "PIDServiceImpl-W43DfDocument");
			final W43DfDocument document = new W43DfDocument();
			// tipo de documento
			document.setType(CorePidDelegate.obtenerProperty("documentTypePid_ivap_itzulnet"));
			// content
			final W43DfContent content = new W43DfContent();
			// formato
			Logger.batchlog(Logger.INFO, "PIDServiceImpl-W43DfContent. NombreDoc: "+nombreDocumento+"  rutaPif: "+rutaPif);
			final W43DfFormat format = new W43DfFormat();
			final String extension = nombreDocumento.substring(nombreDocumento.lastIndexOf(".") + 1);
			format.setExtension(extension);
			content.setFormat(format);
			content.setPath(rutaPif);
			document.setContent(content);
			final W43DfAttribute attribute = new W43DfAttribute();
			attribute.setKey("object_name");
			attribute.getValues().add(nombreDocumento);
			document.getAttributes().add(attribute);
			Logger.batchlog(Logger.INFO, "PIDServiceImpl subir documento al PID");
			oid = documentsManager.addDocument(document);
		} catch (final Exception e) {
			Logger.batchlog(Logger.INFO, "PIDServiceImpl.addDocument: " + e);
			oid = null;
		}

		Logger.batchlog(Logger.INFO, "PIDServiceImpl documento subido al PID");
		Logger.batchlog(Logger.INFO, "PIDServiceImpl OID: " + oid);
		return oid;
	}

	/**
	 *
	 * @param nombreDocumento
	 * @param rutaPIF
	 * @param oid
	 */
	@Override()
	public void modifyDocument(String nombreDocumento, String rutaPIF, String oid) {

		Logger.batchlog(Logger.INFO,"PIDServiceImpl.modifyDocument");
		try{
			final W43DfPIDInterface documentsManager = this.configurarPID();

			Logger.batchlog(Logger.INFO,"PIDServiceImpl-W43DfDocument");
			final W43DfDocument document = new W43DfDocument();
			document.setId(oid);
			// tipo de documento
			document.setType(CorePidDelegate.obtenerProperty("documentTypePid_ivap_itzulnet"));
			// content
			Logger.batchlog(Logger.INFO,"PIDServiceImpl-W43DfContent");
			final W43DfContent content = new W43DfContent();
			// formato
			final W43DfFormat format = new W43DfFormat();
			final String extension = nombreDocumento.substring(nombreDocumento.lastIndexOf(".") + 1);
			format.setExtension(extension);
			content.setFormat(format);
			content.setPath(rutaPIF);
			document.setContent(content);
			// atributo
			final W43DfAttribute attribute = new W43DfAttribute();
			attribute.setKey("object_name");
			attribute.getValues().add(nombreDocumento);
			document.getAttributes().add(attribute);
			documentsManager.modifyDocument(document);
		} catch (final Exception e) {
			Logger.batchlog(Logger.INFO, "PIDServiceImpl.addDocument: " + e);
		}
	}

	/**
	 *
	 * @return W43DfPIDInterface
	 * @throws MalformedURLException
	 */
	@SuppressWarnings(value = "rawtypes")
	private W43DfPIDInterface configurarPID() throws MalformedURLException{
		final W43DfPIDService pidService = new W43DfPIDService(new URL(WSDL),
				new QName(CorePidDelegate.obtenerProperty("qname.pid.namespaceURI"),
						CorePidDelegate.obtenerProperty("qname.pid.localpart")));

		final TokenClientHandlerResolver resolver = new TokenClientHandlerResolver();
		final List<Handler> listaHandlers = new ArrayList<Handler>();
		listaHandlers.add(new TokenClientHandler(CorePidDelegate.obtenerProperty("ruta.WSToken")));
		resolver.setHandlerChain(listaHandlers);

		pidService.setHandlerResolver(resolver);

		final W43DfPIDInterface documentsManager = pidService.getW43DfPIDPort();
		// Establecimiento del endpoint real. Si no, por defecto se
		// utilizara el
		// que marque el port del WSDL
		((BindingProvider) documentsManager).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, WSDL);

		return documentsManager;
	}

	@Override
	public String copyDocument(String oid)  {
		String nuevoOid ="";
		try{
			W43DfPIDInterface documentsManager = this.configurarPID();
			W43DfDocument document = new W43DfDocument();
			document.setId(oid);
			// tipo de documento
			document.setType(CorePidDelegate.obtenerProperty("documentTypePid_ivap_itzulnet"));
			nuevoOid = documentsManager.copyDocument(document);
		} catch (final Exception e) {
			Logger.batchlog(Logger.INFO, "CorePidDelegate.copyDocument: " + e);
			nuevoOid = null;
		}
		Logger.batchlog(Logger.INFO, "CorePidDelegate.copyDocument finalizado. Nuevo OID: " + nuevoOid);
		return nuevoOid;

	}

	@Override
	public boolean deleteDocument(String oid) {
		try{
			W43DfPIDInterface documentsManager =  this.configurarPID();
			documentsManager.deleteDocument(oid);
			Logger.batchlog(Logger.INFO, "CorePidDelegate.deleteDocument finalizado. Eliminado el OID: " + oid);
			return true;
		} catch (final Exception e) {
			Logger.batchlog(Logger.INFO, "ERROR. CorePidDelegate.deleteDocument: " + oid+ " ..... "+ e);
			return false;
		}
	}

	@Override
	public void deleteDocuments(List<String> listaOids) {
		try{
			W43DfPIDInterface documentsManager =  this.configurarPID();
			documentsManager.deleteDocuments(listaOids);
			Logger.batchlog(Logger.INFO, "CorePidDelegate.deleteDocuments finalizado. Eliminados los OIDs: " + listaOids.toString());
		} catch (final Exception e) {
			Logger.batchlog(Logger.INFO, "ERROR. CorePidDelegate.deleteDocuments: " + listaOids.toString()+ " ..... "+ e);
		}

	}



	/* MÉTODOS AÑADIDOS PARA LA FIRMA DE DOCUMENTOS */


	@Override()
	public String getRutaPIF(String oid) {
		String rutaVuelta = new String("");
		try{
			Logger.batchlog(Logger.INFO, "CorePidDelegate.getRutaPIF: " + oid);
			W43DfPIDInterface documentsManager = this.configurarPID();
			// OBTENER DOCUMENTO
			W43DfGETDocument document = new W43DfGETDocument();
			document.setId(oid);
			W43DfGETContent content = new W43DfGETContent();
			Logger.batchlog(Logger.INFO, "CorePidDelegate.getRutaPIF: antes del setPath: "+CorePidDelegate.obtenerProperty("aplicacion.path"));
			content.setPath(CorePidDelegate.obtenerProperty("aplicacion.path"));
			document.setContent(content);
			W43DfDocument w43DfDocument = documentsManager.getDocument(document);

			rutaVuelta =  w43DfDocument.getContent().getPath();
			Logger.batchlog(Logger.INFO, "CorePidDelegate.getRutaPIF * Finaliza bien* vuelta: " + rutaVuelta);
		} catch (final Exception e) {
			Logger.batchlog(Logger.INFO, "ERROR. CorePidDelegate.getRutaPIF: " + oid+ " ..... "+ e);
		}
		return rutaVuelta;
	}
	@Override()
	public String getRutaPIFX43f(String oid) {
		String rutaVuelta = new String("");
		try{
			Logger.batchlog(Logger.INFO, "CorePidDelegate.getRutaPIFX43f: " + oid);
			W43DfPIDInterface documentsManager = this.configurarPID();
			// OBTENER DOCUMENTO
			W43DfGETDocument document = new W43DfGETDocument();
			document.setId(oid);
			W43DfGETContent content = new W43DfGETContent();
			Logger.batchlog(Logger.INFO, "CorePidDelegate.getRutaPIFX43f: antes del setPath: "+CorePidDelegate.obtenerProperty("x43f.aplicacion.path"));
			content.setPath(CorePidDelegate.obtenerProperty("x43f.aplicacion.path"));
			document.setContent(content);
			W43DfDocument w43DfDocument = documentsManager.getDocument(document);

			rutaVuelta =  w43DfDocument.getContent().getPath();
			Logger.batchlog(Logger.INFO, "CorePidDelegate.getRutaPIFX43f * Finaliza bien* vuelta: " + rutaVuelta);
		} catch (final Exception e) {
			Logger.batchlog(Logger.INFO, "ERROR. CorePidDelegate.getRutaPIFX43f: " + oid+ " ..... "+ e);
		}
		return rutaVuelta;
	}

}
