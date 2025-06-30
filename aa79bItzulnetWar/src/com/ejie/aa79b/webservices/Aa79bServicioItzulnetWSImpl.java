package com.ejie.aa79b.webservices;

import java.util.Locale;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.enums.RespuestaServicioWSEnum;
import com.ejie.aa79b.model.webservices.servicios.Aa79bRespuesta;
import com.ejie.aa79b.model.webservices.servicios.Aa79bSolicitud;
import com.ejie.aa79b.model.webservices.servicios.Aa79bSolicitudComunicacion;
import com.ejie.aa79b.model.webservices.servicios.Aa79bSolicitudEliminar;
import com.ejie.aa79b.model.webservices.servicios.Aa79bSolicitudTramitagune;
import com.ejie.aa79b.service.Aa79bServicioItzulnetWsService;

/**
 * @author aresua
 */
@WebService(serviceName = "aa79bServicioItzulnetWS", portName = "aa79bServicioItzulnetWSPort", targetNamespace = "http://com.ejie.aa79b.webservices")
@SOAPBinding(parameterStyle = ParameterStyle.WRAPPED)
@HandlerChain(file = "server-handlers-servicio.xml")
public class Aa79bServicioItzulnetWSImpl extends SpringBeanAutowiringSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(Aa79bServicioItzulnetWSImpl.class);


	@Autowired()
	private Aa79bServicioItzulnetWsService service;
	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;

	@WebMethod(operationName = "nuevaSolicitud")
	public Aa79bRespuesta nuevaSolicitud(@WebParam(name = "solicitud") Aa79bSolicitud bean) {
		try {
			return this.service.altaSolicitud(bean);
		} catch (Exception e) {
			Aa79bRespuesta respuesta = new Aa79bRespuesta();
			respuesta.setCodigo(RespuestaServicioWSEnum.ERROR_SISTEMA.getCodigo());
			respuesta.setDetalleCastellano(this.appMessageSource.getMessage(RespuestaServicioWSEnum.ERROR_SISTEMA.getMensaje(), null, new Locale(Constants.LANG_CASTELLANO)));
			respuesta.setDetalleEuskera(this.appMessageSource.getMessage(RespuestaServicioWSEnum.ERROR_SISTEMA.getMensaje(), null, new Locale(Constants.LANG_EUSKERA)));
			respuesta.setInformacionAdicional(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			return respuesta;
		}
	}

	@WebMethod(operationName = "nuevaSolicitudTramitagune")
	public Aa79bRespuesta nuevaSolicitudTramitagune(@WebParam(name = "solicitud") Aa79bSolicitudTramitagune bean) {
		try {
			return this.service.altaSolicitudTramitagune(bean);
		} catch (Exception e) {
			Aa79bRespuesta respuesta = new Aa79bRespuesta();
			respuesta.setCodigo(RespuestaServicioWSEnum.ERROR_SISTEMA.getCodigo());
			respuesta.setDetalleCastellano(this.appMessageSource.getMessage(RespuestaServicioWSEnum.ERROR_SISTEMA.getMensaje(), null, new Locale(Constants.LANG_CASTELLANO)));
			respuesta.setDetalleEuskera(this.appMessageSource.getMessage(RespuestaServicioWSEnum.ERROR_SISTEMA.getMensaje(), null, new Locale(Constants.LANG_EUSKERA)));
			respuesta.setInformacionAdicional(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			return respuesta;
		}
	}

	@WebMethod(operationName = "eliminarExpediente")
	public Aa79bRespuesta eliminarExpediente(@WebParam(name = "solicitud") Aa79bSolicitudEliminar bean) {
		try {
			return this.service.eliminarExpediente(bean);
		} catch (Exception e) {
			Aa79bRespuesta respuesta = new Aa79bRespuesta();
			respuesta.setCodigo(RespuestaServicioWSEnum.ERROR_SISTEMA.getCodigo());
			respuesta.setDetalleCastellano(this.appMessageSource.getMessage(RespuestaServicioWSEnum.ERROR_SISTEMA.getMensaje(), null, new Locale(Constants.LANG_CASTELLANO)));
			respuesta.setDetalleEuskera(this.appMessageSource.getMessage(RespuestaServicioWSEnum.ERROR_SISTEMA.getMensaje(), null, new Locale(Constants.LANG_EUSKERA)));
			respuesta.setInformacionAdicional(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			return respuesta;
		}
	}

	@WebMethod(operationName = "nuevaComunicacion")
	public Aa79bRespuesta nuevaComunicacion(@WebParam(name = "comunicacion") Aa79bSolicitudComunicacion bean) {
		try {
			return this.service.altaComunicacion(bean);
		} catch (Exception e) {
			Aa79bRespuesta respuesta = new Aa79bRespuesta();
			respuesta.setCodigo(RespuestaServicioWSEnum.ERROR_SISTEMA.getCodigo());
			respuesta.setDetalleCastellano(this.appMessageSource.getMessage(RespuestaServicioWSEnum.ERROR_SISTEMA.getMensaje(), null, new Locale(Constants.LANG_CASTELLANO)));
			respuesta.setDetalleEuskera(this.appMessageSource.getMessage( RespuestaServicioWSEnum.ERROR_SISTEMA.getMensaje(), null, new Locale(Constants.LANG_EUSKERA)));
			respuesta.setInformacionAdicional(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			return respuesta;
			}
		}

}