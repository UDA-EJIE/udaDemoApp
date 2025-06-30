package com.ejie.aa79b.dao;

import com.ejie.aa79b.model.EjecucionTareas;

public interface EjecucionTareasDao extends GenericoDao<EjecucionTareas> {

	/**
	 * Inserta un nuevo registro en la tabla 82 con la fecha y hora de ejecución
	 * 
	 * @param ejecucionTareas
	 *            EjecucionTareas
	 * @return EjecucionTareas
	 */
	EjecucionTareas addConFechaEjec(EjecucionTareas ejecucionTareas);

}
