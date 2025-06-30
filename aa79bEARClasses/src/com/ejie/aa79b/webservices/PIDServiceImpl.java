package com.ejie.aa79b.webservices;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.TokenClientHandler;
import com.ejie.aa79b.common.TokenClientHandlerResolver;
import com.ejie.aa79b.common.TokenFactory;
import com.ejie.aa79b.common.exceptions.AppRuntimeException;
import com.ejie.w43df.pid.client.W43DfAttribute;
import com.ejie.w43df.pid.client.W43DfContent;
import com.ejie.w43df.pid.client.W43DfDocument;
import com.ejie.w43df.pid.client.W43DfFormat;
import com.ejie.w43df.pid.client.W43DfGETContent;
import com.ejie.w43df.pid.client.W43DfGETDocument;
import com.ejie.w43df.pid.client.W43DfPIDInterface;
import com.ejie.w43df.pid.client.W43DfPIDService;
import com.ejie.y31.factory.Y31JanoServiceAbstractFactory;
import com.ejie.y31.service.Y31JanoService;
import com.ejie.y31.vo.Y31AttachmentBean;

@Service(value = "aa79bPIDService")
public class PIDServiceImpl implements PIDService {

	@Autowired()
	private Properties appConfiguration;

	private static final String COD_APLICACION_AA79B = Constants.CONSTANTE_APLICACION;
	private final Long segundosExpiracion = new Long(60 * 60);
	private final Long segundosExpiracionAd55b = new Long(1296000);
	private static final Logger LOGGER = LoggerFactory.getLogger(PIDServiceImpl.class);
	private static final String WSDL = "webservice.pid.wsdl";
	private static final String DOCUMENT_TYPE = "documentTypePid_ivap_itzulnet";
	private static final String RUTA_PIF = "rutaPif";
	private static final String OBJECT_NAME = "object_name";

	@Override
	public String subidaPif(String nombreDocumento, byte[] fichero) throws Exception {
		return this.subidaPif(nombreDocumento, new ByteArrayInputStream(fichero),
				this.appConfiguration.getProperty(RUTA_PIF), false);
	}

	@Override
	public String subidaPif(String nombreDocumento, InputStream fichero) throws Exception {
		return this.subidaPif(nombreDocumento, fichero, this.appConfiguration.getProperty(RUTA_PIF), false);
	}

	@Override
	public String subidaPif(String nombreDocumento, byte[] fichero, String propiedadRutaPIF, boolean preserveName) throws Exception {
		return this.subidaPif(nombreDocumento, new ByteArrayInputStream(fichero), propiedadRutaPIF, preserveName);
	}

	private String subidaPif(String nombreDocumento, InputStream fichero, String propiedadRutaPIF, boolean preserveName) throws Exception {
		// paso1: SUBIR EL DOCUMENTO AL EXPLORADOR DE PIF

		Y31JanoService service = Y31JanoServiceAbstractFactory.getInstance();
		Document sesion = TokenFactory
				.fncString2DOM(TokenFactory.getInstance().getToken(Constants.CONSTANTE_APLICACION));

		String to = propiedadRutaPIF + nombreDocumento;
		LOGGER.info("Subir fichero a PIF=" +to);
		if(to.contains("/" + Constants.AD55B.toUpperCase()) || to.contains("/" + Constants.AD55B)) {
			Y31AttachmentBean resul = service.put(sesion, fichero, to, preserveName, this.segundosExpiracionAd55b);
			return resul.getFilePath();
		}else {
			Y31AttachmentBean resul = service.put(sesion, fichero, to, preserveName, this.segundosExpiracion);
			return resul.getFilePath();
		}

	}

	@Override
	public String addDocument(String nombreDocumento, String rutaPif) throws Exception {
		String oid = "";

		W43DfPIDInterface documentsManager = this.getPidServiceConf();

		W43DfDocument document = new W43DfDocument();
		// tipo de documento
		document.setType(this.appConfiguration.getProperty(DOCUMENT_TYPE));
		// content
		W43DfContent content = new W43DfContent();
		// formato
		W43DfFormat format = new W43DfFormat();
		String extension = nombreDocumento.substring(nombreDocumento.lastIndexOf(".") + 1);
		format.setExtension(extension);
		content.setFormat(format);
		content.setPath(rutaPif);
		document.setContent(content);
		W43DfAttribute attribute = new W43DfAttribute();
		attribute.setKey(OBJECT_NAME);
		attribute.getValues().add(nombreDocumento);
		document.getAttributes().add(attribute);
		oid = documentsManager.addDocument(document);
		return oid;
	}

	@Override
	public String addDocument(String nombreDocumento, byte[] fichero) throws Exception {
		String rutaPif = this.subidaPif(nombreDocumento, fichero);
		return this.addDocument(nombreDocumento, rutaPif);
	}

	@Override
	public String addDocument(String nombreDocumento, InputStream fichero) {
		try {
			String rutaPif = this.subidaPif(nombreDocumento, fichero);
			return this.addDocument(nombreDocumento, rutaPif);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public byte[] getDocument(String oid) throws Exception {

		String ruta = this.getRutaPIF(oid);

		// INVOCAR AL SERVICIO GET DEL PIF
		ByteArrayOutputStream baos = this.servicioGetDelPif(ruta);
		baos.flush();
		return baos.toByteArray();
	}

	@Override
	public void getDocument(String oid, OutputStream out) throws Exception {
		String ruta = this.getRutaPIF(oid);

		// INVOCAR AL SERVICIO GET DEL PIF
		InputStream targetStream = this.servicioGetDelPifInput(ruta);

		byte[] outputByte = new byte[4096];
		while (targetStream.read(outputByte, 0, 4096) != -1) {
			out.write(outputByte, 0, 4096);
		}
		targetStream.close();
		out.close();
	}

	@Override
	public InputStream getDocumentInput(String oid) throws Exception {
		String ruta = this.getRutaPIF(oid);

		// INVOCAR AL SERVICIO GET DEL PIF
		return this.servicioGetDelPifInput(ruta);
	}

	@Override
	public void modifyDocumentConBytes(String nombreDocumento, byte[] fichero, String oid) throws Exception {
		Y31JanoService service = Y31JanoServiceAbstractFactory.getInstance();
		Document sesion = TokenFactory
				.fncString2DOM(TokenFactory.getInstance().getToken(Constants.CONSTANTE_APLICACION));

		String to = this.appConfiguration.getProperty(RUTA_PIF) + nombreDocumento;

		Y31AttachmentBean resul = service.put(sesion, new ByteArrayInputStream(fichero), to, false,
				this.segundosExpiracion);

		W43DfPIDInterface documentsManager = this.getPidServiceConf();

		W43DfDocument document = new W43DfDocument();
		document.setId(oid);
		// tipo de documento
		document.setType(this.appConfiguration.getProperty(DOCUMENT_TYPE));
		// content
		W43DfContent content = new W43DfContent();
		// formato
		W43DfFormat format = new W43DfFormat();
		String extension = nombreDocumento.substring(nombreDocumento.lastIndexOf(".") + 1);
		format.setExtension(extension);
		content.setFormat(format);
		content.setPath(resul.getFilePath());
		document.setContent(content);
		// atributo
		W43DfAttribute attribute = new W43DfAttribute();
		attribute.setKey(OBJECT_NAME);
		attribute.getValues().add(nombreDocumento);
		document.getAttributes().add(attribute);
		documentsManager.modifyDocument(document);
	}

	@Override
	public void modifyDocument(String nombreDocumento, String rutaPIF, String oid) throws Exception {
		W43DfPIDInterface documentsManager = this.getPidServiceConf();
		W43DfDocument document = new W43DfDocument();
		document.setId(oid);
		// tipo de documento
		document.setType(this.appConfiguration.getProperty(DOCUMENT_TYPE));
		// content
		W43DfContent content = new W43DfContent();
		// formato
		W43DfFormat format = new W43DfFormat();
		String extension = nombreDocumento.substring(nombreDocumento.lastIndexOf(".") + 1);
		format.setExtension(extension);
		content.setFormat(format);
		content.setPath(rutaPIF);
		document.setContent(content);
		// atributo
		W43DfAttribute attribute = new W43DfAttribute();
		attribute.setKey(OBJECT_NAME);
		attribute.getValues().add(nombreDocumento);
		document.getAttributes().add(attribute);
		documentsManager.modifyDocument(document);
	}

	@Override
	public void deleteDocument(String oid) throws Exception {
		W43DfPIDInterface documentsManager = this.getPidServiceConf();

		documentsManager.deleteDocument(oid);

	}

	@Override
	public void deleteDocuments(List<String> listaOids) throws Exception {
		W43DfPIDInterface documentsManager = this.getPidServiceConf();

		documentsManager.deleteDocuments(listaOids);

	}

	@Override
	public W43DfDocument getDocumentInfo(String oid) {
		try {
			W43DfPIDInterface documentsManager = this.getPidServiceConf();
			// OBTENER DOCUMENTO
			W43DfGETDocument document = new W43DfGETDocument();
			document.setId(oid);
			W43DfGETContent content = new W43DfGETContent();
			content.setPath(this.appConfiguration.getProperty("aplicacion.path"));
			document.setContent(content);
			return documentsManager.getDocument(document);
		} catch (Exception e) {
			throw new AppRuntimeException(e);
		}
	}

	@Override()
	public String getRutaPIF(String oid) throws Exception {
		W43DfPIDInterface documentsManager = this.getPidServiceConf();
		// OBTENER DOCUMENTO
		W43DfGETDocument document = new W43DfGETDocument();
		document.setId(oid);
		W43DfGETContent content = new W43DfGETContent();
		content.setPath(this.appConfiguration.getProperty("aplicacion.path"));
		document.setContent(content);
		W43DfDocument w43DfDocument = documentsManager.getDocument(document);

		return w43DfDocument.getContent().getPath();
	}

	@Override()
	public String getRutaPIF(String oid, String pathPIFDestino) throws Exception {
		W43DfPIDInterface documentsManager = this.getPidServiceConf();
		// OBTENER DOCUMENTO
		W43DfGETDocument document = new W43DfGETDocument();
		document.setId(oid);
		W43DfGETContent content = new W43DfGETContent();
		content.setPath(pathPIFDestino);
		document.setContent(content);
		W43DfDocument w43DfDocument = documentsManager.getDocument(document);

		return w43DfDocument.getContent().getPath();
	}

	/**
	 *
	 * @return W43DfPIDService
	 * @throws Exception e
	 */
	@SuppressWarnings(value = "rawtypes")
	private W43DfPIDInterface getPidServiceConf() throws Exception {
		// ESTABLECER CONFIGURACION DEL PID
		PIDServiceImpl.LOGGER.info("PIDServiceImpl configurando PID");
		W43DfPIDService pidService = new W43DfPIDService(new URL(this.appConfiguration.getProperty(WSDL)),
				new QName(this.appConfiguration.getProperty("qname.pid.namespaceURI"),
						this.appConfiguration.getProperty("qname.pid.localpart")));

		TokenClientHandlerResolver resolver = new TokenClientHandlerResolver();
		List<Handler> listaHandlers = new ArrayList<Handler>();
		listaHandlers.add(new TokenClientHandler());
		resolver.setHandlerChain(listaHandlers);

		pidService.setHandlerResolver(resolver);

		String urlPid = this.appConfiguration.getProperty(WSDL);
		W43DfPIDInterface documentsManager = pidService.getW43DfPIDPort();
		// Establecimiento del endpoint real. Si no, por defecto se
		// utilizara el
		// que marque el port del WSDL
		((BindingProvider) documentsManager).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, urlPid);
		((BindingProvider) documentsManager).getRequestContext().put("codApp", COD_APLICACION_AA79B);

		return documentsManager;
	}

	/**
	 *
	 * @param nombreDocumento String
	 * @param ruta            String
	 * @return String
	 * @throws Exception e
	 */
	@Override
	public String getOidFromPif(String nombreDocumento, String ruta) throws Exception {
		// INVOCAR AL SERVICIO GET DEL PIF
		ByteArrayOutputStream baos = this.servicioGetDelPif(ruta);

		final String rutaPif = this.subidaPif(nombreDocumento, baos.toByteArray());

		return this.addDocument(nombreDocumento, rutaPif);
	}

	/**
	 *
	 * @param nombreDocumento String
	 * @param ruta            String
	 * @return String
	 * @throws Exception e
	 */
	@Override()
	public String moveDocument(String nombreDocumento, String ruta) throws Exception {
		// paso1: SUBIR EL DOCUMENTO AL EXPLORADOR DE PIF
		Y31JanoService service = Y31JanoServiceAbstractFactory.getInstance();
		Document sesion = TokenFactory
				.fncString2DOM(TokenFactory.getInstance().getToken(Constants.CONSTANTE_APLICACION));

		String to = this.appConfiguration.getProperty(RUTA_PIF) + nombreDocumento;
		Y31AttachmentBean resul = service.move(sesion, ruta, to, false);

		return resul.getFilePath();
	}

	public ByteArrayOutputStream servicioGetDelPif(String ruta) throws Exception {
		// INVOCAR AL SERVICIO GET DEL PIF
		// OBTENER EL NOMBRE DEL FICHERO Y LA EXTENSION
		Y31JanoService service = Y31JanoServiceAbstractFactory.getInstance();
		Document sesion = TokenFactory
				.fncString2DOM(TokenFactory.getInstance().getToken(Constants.CONSTANTE_APLICACION));
		// INVOCAR SERVICE GET DEL PIF
		InputStream is = service.get(sesion, ruta);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = is.read();
		while (reads != -1) {
			baos.write(reads);
			reads = is.read();
		}
		return baos;
	}

	@Override()
	public InputStream servicioGetDelPifInput(String ruta) throws Exception {
		// INVOCAR AL SERVICIO GET DEL PIF
		// OBTENER EL NOMBRE DEL FICHERO Y LA EXTENSION
		Y31JanoService service = Y31JanoServiceAbstractFactory.getInstance();
		Document sesion = TokenFactory
				.fncString2DOM(TokenFactory.getInstance().getToken(Constants.CONSTANTE_APLICACION));
		// INVOCAR SERVICE GET DEL PIF
		return service.get(sesion, ruta);
	}

	@Override()
	public String subidaPifP43j(String nombreDocumento, byte[] fichero, String propiedadRutaPIF) throws Exception {
		// paso1: SUBIR EL DOCUMENTO AL EXPLORADOR DE PIF
		String sResul = "";
		try {
			Y31JanoService service = Y31JanoServiceAbstractFactory.getInstance();
			Document sesion = TokenFactory
					.fncString2DOM(TokenFactory.getInstance().getToken(Constants.CONSTANTE_APLICACION));

			String to = propiedadRutaPIF + nombreDocumento;

			Y31AttachmentBean resul = service.put(sesion, new ByteArrayInputStream(fichero), to, false,
					this.segundosExpiracion);
			sResul = resul.getFilePath();

		} catch (Exception e) {
			PIDServiceImpl.LOGGER.info("subidaPifP43j ERROR: " + e);
		}

		return sResul;
	}

	@Override()
	public Y31AttachmentBean prepararDocumentoPid(String nombre, String ruta) throws Exception {
		Y31JanoService service = Y31JanoServiceAbstractFactory.getInstance();
		Document sesion = TokenFactory
				.fncString2DOM(TokenFactory.getInstance().getToken(Constants.CONSTANTE_APLICACION));
		String rutaPif = this.appConfiguration.getProperty(RUTA_PIF);
		StringBuilder to = new StringBuilder();
		to.append(rutaPif).append(nombre);
		return service.copy(sesion, ruta, to.toString());
	}

}