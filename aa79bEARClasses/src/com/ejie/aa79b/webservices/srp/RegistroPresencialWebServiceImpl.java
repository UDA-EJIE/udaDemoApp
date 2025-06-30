package com.ejie.aa79b.webservices.srp;

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

import com.ejie.aa79b.common.TokenClientHandler;
import com.ejie.aa79b.common.TokenClientHandlerResolver;
import com.ejie.aa79b.model.LibroRegistroModel;
import com.ejie.aa79b.utils.date.DateFormaterFactory;

/**
 * The type RegistroPresencialWebServiceImpl.
 * 
 * @author dlopez
 * 
 */
@Service(value = "aa79bRegistroPresencialWebService")
public class RegistroPresencialWebServiceImpl implements RegistroPresencialWebService {
	@Autowired()
	private Properties appConfiguration;

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistroPresencialWebServiceImpl.class);

	private static final String COD_APLICACION = "AA79B";

	/**
	 * Da de alta en el registro presencial
	 * 
	 * @param libroRegistro LibroRegistroEntrada
	 * @return LibroRegistroEntrada
	 * @throws Exception Exception
	 */
	@Override()
	public LibroRegistroModel altaRegistroEntrada(LibroRegistroModel libroRegistro) throws Exception {
		RegistroPresencialWebServiceImpl.LOGGER.info("RegistroPresencialWebServiceImpl.altaRegistroEntrada");

		WSParamInputRegisterEx datas = new WSParamInputRegisterEx();
		// UnitCode, Matter, Destination: Recuperar de la DB??
		datas.setUnitCode(this.appConfiguration.getProperty("libroRegistro.unitCode"));
		datas.setDestination(this.appConfiguration.getProperty("libroRegistro.unitCode"));
		datas.setSender(this.appConfiguration.getProperty("libroRegistro.sender"));
		datas.setMatter(libroRegistro.getMatter());

		ArrayOfWSParamPerson paramPerson = new ArrayOfWSParamPerson();
		for (String descripcion : libroRegistro.getListaPerson()) {
			WSParamPerson persona = this.newWSParamPerson();
			persona.setPersonName(descripcion);
			paramPerson.getWSParamPerson().add(persona);
		}
		datas.setPersons(paramPerson);

		WSInputRegister salida = this.getWS().wsNewInputRegister(datas);

		RegistroPresencialWebServiceImpl.LOGGER.info("Numero registro: " + salida.getNumber());
		RegistroPresencialWebServiceImpl.LOGGER.info("Fecha registro: " + salida.getDate().toString());

		// Estos datos los vamos a guardar???
		LibroRegistroModel libroRegistroEntrada = new LibroRegistroModel();
		libroRegistroEntrada.setNumRegistro(salida.getNumber());
		libroRegistroEntrada
				.setFechaRegistro(DateFormaterFactory.getInstance().getSrpDateFormater().parse(salida.getDate()));

		return libroRegistroEntrada;

	}

	/**
	 * Da de alta en el registro presencial
	 * 
	 * @param libroRegistro LibroRegistroEntrada
	 * @return LibroRegistroEntrada
	 * @throws Exception Exception
	 */
	@Override()
	public LibroRegistroModel altaRegistroSalida(LibroRegistroModel libroRegistro) throws Exception {
		RegistroPresencialWebServiceImpl.LOGGER.info("RegistroPresencialWebServiceImpl.altaRegistroEntrada");

		WSParamOutputRegisterEx datas = new WSParamOutputRegisterEx();
		// UnitCode, Matter, Destination: Recuperar de la DB??
		datas.setUnitCode(this.appConfiguration.getProperty("libroRegistro.unitCode"));
		datas.setDestination(this.appConfiguration.getProperty("libroRegistro.unitCode"));
		datas.setSender(this.appConfiguration.getProperty("libroRegistro.sender"));
		datas.setMatter(libroRegistro.getMatter());

		ArrayOfWSParamPerson paramPerson = new ArrayOfWSParamPerson();
		for (String descripcion : libroRegistro.getListaPerson()) {
			WSParamPerson persona = this.newWSParamPerson();
			persona.setPersonName(descripcion);
			paramPerson.getWSParamPerson().add(persona);
		}
		datas.setPersons(paramPerson);

		WSOutputRegister salida = this.getWS().wsNewOutputRegister(datas);

		RegistroPresencialWebServiceImpl.LOGGER.info("Numero registro: " + salida.getNumber());
		RegistroPresencialWebServiceImpl.LOGGER.info("Fecha registro: " + salida.getDate().toString());

		// Estos datos los vamos a guardar???
		LibroRegistroModel libroRegistroEntrada = new LibroRegistroModel();
		libroRegistroEntrada.setNumRegistro(salida.getNumber());
		libroRegistroEntrada
				.setFechaRegistro(DateFormaterFactory.getInstance().getSrpDateFormater().parse(salida.getDate()));

		return libroRegistroEntrada;

	}

	/**
	 * @return WSParamPerson
	 */
	private WSParamPerson newWSParamPerson() {
		return new WSParamPerson();
	}

	/**
	 * @return SistemaRegistroPresencialWSSoap
	 * @throws Exception e
	 */
	@SuppressWarnings(value = "rawtypes")
	private SistemaRegistroPresencialWSSoap getWS() throws Exception {
		SistemaRegistroPresencialWS srpService = new SistemaRegistroPresencialWS(
				new URL(this.appConfiguration.getProperty("webservice.srp.wsdl")),
				new QName(this.appConfiguration.getProperty("qname.srp.namespaceURI"),
						this.appConfiguration.getProperty("qname.srp.localpart")));

		TokenClientHandlerResolver resolver = new TokenClientHandlerResolver();
		List<Handler> listaHandlers = new ArrayList<Handler>();
		listaHandlers.add(new TokenClientHandler());
		resolver.setHandlerChain(listaHandlers);
		srpService.setHandlerResolver(resolver);
		String urlSrp = this.appConfiguration.getProperty("webservice.srp.wsdl");
		SistemaRegistroPresencialWSSoap documentsManager = srpService.getSistemaRegistroPresencialWSSoap();
		// Establecimiento del endpoint real. Si no, por defecto se utilizara el
		// que marque el port del WSDL
		((BindingProvider) documentsManager).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, urlSrp);
		((BindingProvider) documentsManager).getRequestContext().put("codApp", COD_APLICACION);

		return documentsManager;
	}
}
