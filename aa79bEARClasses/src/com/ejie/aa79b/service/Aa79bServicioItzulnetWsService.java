package com.ejie.aa79b.service;

import com.ejie.aa79b.model.webservices.servicios.Aa79bRespuesta;
import com.ejie.aa79b.model.webservices.servicios.Aa79bSolicitud;
import com.ejie.aa79b.model.webservices.servicios.Aa79bSolicitudComunicacion;
import com.ejie.aa79b.model.webservices.servicios.Aa79bSolicitudEliminar;
import com.ejie.aa79b.model.webservices.servicios.Aa79bSolicitudTramitagune;

public interface Aa79bServicioItzulnetWsService {

	/**
	 *
	 * @param Aa79bSolicitud
	 *            solicitud
	 * @return Aa79bRegistroExpediente
	 */
	Aa79bRespuesta altaSolicitud(Aa79bSolicitud bean);

	/**
	 *
	 * @param Aa79bSolicitudTramitagune
	 *            solicitud
	 * @return Aa79bRegistroExpediente
	 */
	Aa79bRespuesta altaSolicitudTramitagune(Aa79bSolicitudTramitagune bean);

	/**
	 *
	 * @param Aa79bSolicitudEliminar
	 *            solicitud
	 * @return Aa79bRegistroExpediente
	 */
	Aa79bRespuesta eliminarExpediente(Aa79bSolicitudEliminar bean);

	/**
	 *
	 * @param Aa79bSolicitudComunicacion
	 * @return Aa79bRespuesta
	 */
	Aa79bRespuesta altaComunicacion(Aa79bSolicitudComunicacion bean);

}
