package com.ejie.aa79b.service;

import javax.servlet.http.HttpServletRequest;

import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ObservacionesExpediente;

/**
 * DatosGeneralesExpedienteService Service para gestionar la pestaña de datos
 * generales del expediente
 * 
 * @author mrodriguez
 */
public interface DatosGeneralesExpedienteService {

	/**
	 * Inserts a single row in the Expediente table.
	 *
	 * @param bean Expediente
	 * @return Expediente
	 */
	Expediente add(Expediente bean, HttpServletRequest request);

	/**
	 * Updates a single row in the Expediente table.
	 *
	 * @param bean   Expediente
	 * @param origen String
	 * @return Expediente
	 */
	Expediente update(Expediente bean, String origen);

	/**
	 * Finds a single row in the Expediente table.
	 *
	 * @param bean   Expediente
	 * @param origen String
	 * @return Expediente
	 */
	Expediente find(Expediente bean, String origen);

	void procCambioTipoExpedientePL(final Long anyo, final Integer numExp, final String tipoExpediente);

	/**
	 * Finds a single row in the ObservacionesExpediente table.
	 * 
	 * @param expediente Expediente
	 * @return ObservacionesExpediente
	 */
	ObservacionesExpediente observacionesFind(Expediente expediente);

	void anotarLibroRegistro(Expediente bean);

	void anotarLibroRegistroSalida(Expediente bean);

}
