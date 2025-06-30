package com.ejie.aa79b.service;

import com.ejie.aa79b.model.Comunicaciones;

public interface ComunicacionesService extends GenericoService<Comunicaciones> {


	/**
	 *
	 * @param comunicacion
	 * @return comunicacion
	 */
	Comunicaciones updateComunicacion(Comunicaciones comunicacion);

	/**
	 *
	 * @param comunicacion
	 * @return comunicacion
	 */
	Comunicaciones createNewComunicacion(Comunicaciones comunicacion);

}
