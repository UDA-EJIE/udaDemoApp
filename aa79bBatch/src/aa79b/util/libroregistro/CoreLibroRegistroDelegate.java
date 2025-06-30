/**
 * Fichero: CoreLibroRegistroDelegate.java
 * Autor: javarona
 * Fecha: 2019
 */
package aa79b.util.libroregistro;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import aa79b.util.beans.LibroRegistroEntrada;
import aa79b.util.common.Logger;
import aa79b.util.mail.EmailService;
import aa79b.util.pid.CorePidDelegate;
import aa79b.util.token.TokenClientHandler;
import aa79b.util.token.TokenClientHandlerResolver;
import aa79b.webservices.srp.ArrayOfWSParamPerson;
import aa79b.webservices.srp.SistemaRegistroPresencialWS;
import aa79b.webservices.srp.SistemaRegistroPresencialWSSoap;
import aa79b.webservices.srp.WSInputRegister;
import aa79b.webservices.srp.WSParamInputRegisterEx;
import aa79b.webservices.srp.WSParamPerson;

/**
 * Titulo: CoreLibroRegistroDelegate.
 * Empresa: Eurohelp Consulting
 * Copyright: Copyright (c) 2019
 * @author javarona
 * @version 1.0
 *
 */
public class CoreLibroRegistroDelegate implements LibroRegistroDelegate {

	
	private static final DateFormat SRP_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	/**
	 * Un constructor para CoreLibroRegistroDelegate.
	 * @author javarona
	 */
	public CoreLibroRegistroDelegate() {
		//Constructor vacío
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


	
	// Cambio los RegistroPresencialWebServiceImpl.LOGGER.info por Logger.batchlog
	
	
	@Override
	public LibroRegistroEntrada altaRegistroEntrada(LibroRegistroEntrada libroRegistro) {
		Logger.batchlog(Logger.INFO,"RegistroPresencialWebServiceImpl.altaRegistroEntrada");
		LibroRegistroEntrada libroRegistroEntrada = new LibroRegistroEntrada();
		
		try{
			WSParamInputRegisterEx datas = new WSParamInputRegisterEx();
			
			datas.setUnitCode(CoreLibroRegistroDelegate.obtenerProperty("libroRegistro.unitCode"));
			datas.setDestination(CoreLibroRegistroDelegate.obtenerProperty("libroRegistro.unitCode"));
			datas.setSender(CoreLibroRegistroDelegate.obtenerProperty("libroRegistro.sender"));
			
			datas.setMatter(libroRegistro.getMatter());

			ArrayOfWSParamPerson paramPerson = new ArrayOfWSParamPerson();
			for (String descripcion : libroRegistro.getListaPerson()) {
				WSParamPerson persona = this.newWSParamPerson();
				persona.setPersonName(descripcion);
				paramPerson.getWSParamPerson().add(persona);
			}
			datas.setPersons(paramPerson);

			WSInputRegister salida = this.getWS().wsNewInputRegister(datas);

			Logger.batchlog(Logger.INFO,"Numero registro: " + salida.getNumber());
			Logger.batchlog(Logger.INFO,"Fecha registro: " + salida.getDate().toString());

			libroRegistroEntrada.setNumRegistro(salida.getNumber());
			
			libroRegistroEntrada.setFechaRegistro(SRP_DATE_FORMAT.parse(salida.getDate()));
		} catch (ParseException pe) {
			Logger.batchlog(Logger.INFO,"Excepcion parseando la fecha en altaRegistroEntrada: "+(pe.getMessage()));
		} catch (final Exception e) {
			Logger.batchlog(Logger.INFO, "CoreLibroRegistroDelegate.altaRegistroEntrada: " + e);
		}
		
		return libroRegistroEntrada;

	}

	/**
	 * @return WSParamPerson
	 */
	public WSParamPerson newWSParamPerson() {
		return new WSParamPerson();
	}

	/**
	 * @return SistemaRegistroPresencialWSSoap
	 * @throws Exception
	 *             e
	 */
	@SuppressWarnings(value = "rawtypes")
	public SistemaRegistroPresencialWSSoap getWS() throws MalformedURLException{
		
		SistemaRegistroPresencialWS srpService = new SistemaRegistroPresencialWS(
				new URL(CoreLibroRegistroDelegate.obtenerProperty("webservice.srp.wsdl")),
				new QName(CoreLibroRegistroDelegate.obtenerProperty("qname.srp.namespaceURI"),
						CoreLibroRegistroDelegate.obtenerProperty("qname.srp.localpart")));

		TokenClientHandlerResolver resolver = new TokenClientHandlerResolver();
		List<Handler> listaHandlers = new ArrayList<Handler>();
		listaHandlers.add(new TokenClientHandler(CorePidDelegate.obtenerProperty("ruta.WSToken")));
		resolver.setHandlerChain(listaHandlers);
		srpService.setHandlerResolver(resolver);
		
		String urlSrp = CoreLibroRegistroDelegate.obtenerProperty("webservice.srp.wsdl");
		SistemaRegistroPresencialWSSoap documentsManager = srpService.getSistemaRegistroPresencialWSSoap();
		// Establecimiento del endpoint real. Si no, por defecto se utilizara el
		// que marque el port del WSDL
		((BindingProvider) documentsManager).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, urlSrp);
		((BindingProvider) documentsManager).getRequestContext().put("codApp", "AA79B");

		return documentsManager;
	}
	
}
