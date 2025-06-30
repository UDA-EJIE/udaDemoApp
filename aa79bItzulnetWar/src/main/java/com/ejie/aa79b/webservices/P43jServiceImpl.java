package com.ejie.aa79b.webservices;

import java.net.MalformedURLException;
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

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.TokenClientHandler;
import com.ejie.aa79b.common.TokenClientHandlerResolver;
import com.ejie.aa79b.webservices.p43j.P43JBoletinWSClaseSec;
import com.ejie.aa79b.webservices.p43j.P43JBoletinWSClaseSecPort;
import com.ejie.aa79b.webservices.p43j.P43JFinalizacionIzoberriRespuestaType;
import com.ejie.aa79b.webservices.p43j.P43JFinalizacionIzoberriType;

@Service(value = "aa79bP43jService")
public class P43jServiceImpl implements P43jService {

	@Autowired()
	private Properties appConfiguration;

	private static final String COD_APLICACION_AA79B = Constants.CONSTANTE_APLICACION;
	private static final Logger LOGGER = LoggerFactory.getLogger(P43jServiceImpl.class);

	private static final String WSDL = "webservice.p43j.wsdl";

	@Override
	public P43JFinalizacionIzoberriRespuestaType llamadaWSBoletinP43(String expeIzo, String expeApli, String rutaPif)
			throws MalformedURLException {

		P43JBoletinWSClaseSecPort p43JBoletinWSClaseSecPort = this.getP43jServiceConf();
		P43jServiceImpl.LOGGER.info("P43jServiceImpl CONFIGURADO P43j");
		P43JFinalizacionIzoberriType paramFinalizacionIzoberri = new P43JFinalizacionIzoberriType();
		paramFinalizacionIzoberri.setExpeIzo(expeIzo);
		paramFinalizacionIzoberri.setExpeApli(expeApli);
		paramFinalizacionIzoberri.setRutaPif(rutaPif);

		return p43JBoletinWSClaseSecPort.finalizacionIzoberri(paramFinalizacionIzoberri);

	}

	/**
	 * 
	 * @return W43DfPIDService
	 * @throws Exception
	 *             e
	 */
	@SuppressWarnings(value = "rawtypes")
	private P43JBoletinWSClaseSecPort getP43jServiceConf() throws MalformedURLException {
		// ESTABLECER CONFIGURACION DE P43j
		P43jServiceImpl.LOGGER.info("P43jServiceImpl configurando P43j");

		P43JBoletinWSClaseSec p43jService = new P43JBoletinWSClaseSec(new URL(this.appConfiguration.getProperty(WSDL)),
				new QName(this.appConfiguration.getProperty("qname.p43j.namespaceURI"),
						this.appConfiguration.getProperty("qname.p43j.localpart")));

		TokenClientHandlerResolver resolver = new TokenClientHandlerResolver();
		List<Handler> listaHandlers = new ArrayList<Handler>();
		listaHandlers.add(new TokenClientHandler());
		resolver.setHandlerChain(listaHandlers);

		p43jService.setHandlerResolver(resolver);

		String urlP43j = this.appConfiguration.getProperty(WSDL);
		P43JBoletinWSClaseSecPort p43JBoletinWSClaseSecPort = p43jService.getP43JBoletinWSClaseSecPort();
		// Establecimiento del endpoint real. Si no, por defecto se
		// utilizara el
		// que marque el port del WSDL
		((BindingProvider) p43JBoletinWSClaseSecPort).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				urlP43j);
		((BindingProvider) p43JBoletinWSClaseSecPort).getRequestContext().put("codApp", COD_APLICACION_AA79B);

		return p43JBoletinWSClaseSecPort;
	}

}
