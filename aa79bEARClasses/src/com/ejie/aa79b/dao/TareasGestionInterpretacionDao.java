package com.ejie.aa79b.dao;

import java.math.BigDecimal;

import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasGestionInterpretacion;

/**
 * 
 * @author dmuchuari
 *
 */
public interface TareasGestionInterpretacionDao extends GenericoDao<TareasGestionInterpretacion> {

	TareasGestionInterpretacion findTareaInterpretacion(BigDecimal idTarea);

	TareasGestionInterpretacion getInfoDocumentoPresupuestoInterpretacion(Long anyo, Integer numExp);

	TareasGestionInterpretacion getInfoDocumentoPresupuestoInterpretacionSinEjecutar(Long anyo, Integer numExp);

	int updateInt(TareasGestionInterpretacion tareasGestionInterpretacion);

	/**
	 * 
	 * @param tarea Tareas
	 * @return Tareas
	 */
	Tareas findEjecTareasPrevInterpretacion(Tareas tarea);

	/**
	 * 
	 * @param tarea Tareas
	 * @return Integer
	 */
	Integer actualizarTareasPrevInterpretacion(Tareas tarea);

}
