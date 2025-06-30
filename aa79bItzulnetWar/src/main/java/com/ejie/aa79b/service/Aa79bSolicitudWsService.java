package com.ejie.aa79b.service;

import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.webservices.Aa79bRegistroExpediente;
import com.ejie.aa79b.model.webservices.Aa79bSolicitud;

public interface Aa79bSolicitudWsService extends GenericoService<Aa79bSolicitud> {

	/**
	 * 
	 * @param Aa79bSolicitud
	 *            solicitud
	 * @return Aa79bRegistroExpediente
	 */
	Aa79bRegistroExpediente altaSolicitud(Aa79bSolicitud solicitud);

	/**
	 * 
	 * @param Aa79bSolicitud
	 *            solicitud
	 * @return Aa79bRegistroExpediente
	 */
	Aa79bRegistroExpediente modificarSolicitud(Aa79bSolicitud solicitud);

	int addBitacoraSolicitante(BitacoraSolicitante bitacoraSolicitante);

}
