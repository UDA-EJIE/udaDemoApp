/**
 * Fichero: CoreFirmaDelegate.java
 * Autor: aresua
 * Fecha: 2017
 */
package aa79b.util.firma;

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
import aa79b.util.token.TokenClientHandler;
import aa79b.util.token.TokenClientHandlerResolver;
import aa79b.webservices.x43f.InputOptions;
import aa79b.webservices.x43f.PlacementType;
import aa79b.webservices.x43f.ProfileType;
import aa79b.webservices.x43f.X43FNSHF;
import aa79b.webservices.x43f.X43FNSHF_Service;




/**
 * Titulo: CoreFirmaDelegate.
 * Empresa: Eurohelp Consulting
 * Copyright: Copyright (c) 2017
 * @author javarona
 * @version 1.0
 *
 */
public class CoreFirmaDelegate implements FirmaDelegate {

	private static final String RUTA_FIRMA_NSHF = "rutaNshf";
	private static final String CERTIFICATE_ID = "certificateID";
	public static final String CONSTANTE_APLICACION = "AA79B";

	/**
	 * Un constructor para CoreFirmaDelegate.
	 * @author javarona
	 */
	public CoreFirmaDelegate() {
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
		final InputStream in = CoreFirmaDelegate.class.getResourceAsStream("/aa79b/aa79b.properties");
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

	@Override()
	public String getServerSignatureLocationDetached(String rutaArchivoPIF) throws Exception {
		// Configurar NSHF
		final X43FNSHF documentsManager = this.getNshfConfiguration();
		// Firmar
		InputOptions inputOptions = new InputOptions();
		inputOptions.setCertificateId(CoreFirmaDelegate.obtenerProperty(CERTIFICATE_ID));
		inputOptions.setProfile(ProfileType.XADES);
		inputOptions.setPlacement(PlacementType.DETACHED);
		return  documentsManager.createSignatureLocation(inputOptions, rutaArchivoPIF, CONSTANTE_APLICACION.toLowerCase());

	}

	/**
	 *
	 * @return X43FNSHF_Service
	 * @throws Exception
	 *             e
	 */
	@SuppressWarnings(value = "rawtypes")
	private X43FNSHF getNshfConfiguration() throws MalformedURLException {
		X43FNSHF_Service x43fService = new X43FNSHF_Service(
				new URL(CoreFirmaDelegate.obtenerProperty(RUTA_FIRMA_NSHF)),
				new QName(CoreFirmaDelegate.obtenerProperty("qname.nshf.namespaceURI"),
						CoreFirmaDelegate.obtenerProperty("qname.nshf.localpart")));

//		X43FNSHF_Service x43fService = new X43FNSHF_Service()

		TokenClientHandlerResolver resolver = new TokenClientHandlerResolver();
		List<Handler> listaHandlers = new ArrayList<Handler>();
		listaHandlers.add(new TokenClientHandler(CoreFirmaDelegate.obtenerProperty("ruta.WSToken")));
		resolver.setHandlerChain(listaHandlers);

		x43fService.setHandlerResolver(resolver);

		String urlPid = CoreFirmaDelegate.obtenerProperty(RUTA_FIRMA_NSHF);
		X43FNSHF documentsManager = x43fService.getX43FNSHFSOAP();

		((BindingProvider) documentsManager).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, urlPid);
		((BindingProvider) documentsManager).getRequestContext().put("codApp", CONSTANTE_APLICACION);

		Logger.batchlog(Logger.INFO, "CoreFirmaDelegate.getNshfConfiguration: fin ok");

		return documentsManager;
	}







}
