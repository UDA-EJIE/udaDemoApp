package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.OtrosTrabajos;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasTrabajo;
import com.ejie.x38.dto.JQGridRequestDto;

/**
 * @author mrodriguez
 *
 */
public interface TareasTrabajoDao extends GenericoDao<TareasTrabajo> {

	int comprobarTareaAEliminar(BigDecimal idTarea);

	/**
	 * Finds a single row in the Tareas table.
	 *
	 * @param bean Tareas
	 * @return Tareas
	 */
	TareasTrabajo findConfTarea(TareasTrabajo bean);

	/**
	 * Funcion que reabre la tarea indicada
	 *
	 * @param idTarea BigDecimal
	 */
	void reabrirTarea(BigDecimal idTarea);

	/**
	 * Elimina las observaciones de rechazo para la tarea indicada.
	 *
	 * @param idTarea
	 */
	void removeObsrvRechazo(BigDecimal idTarea);

	/**
	 * Elimina las observaciones de rechazo para el listado de tareas
	 * correspondiente.
	 *
	 * @param listTareas
	 */
	void removeObsrvRechazo(List<TareasTrabajo> listTareas);

	/**
	 *
	 * @param tareaTrabajo TareasTrabajo
	 * @return TareasTrabajo
	 */
	TareasTrabajo getDatosTarea(TareasTrabajo tareaTrabajo);

	/**
	 *
	 * @param tareaTrabajoFilter TareasTrabajo
	 * @param jqGridRequestDto  JQGridRequestDto
	 * @param isCount boolean
	 * @return Object
	 */
	Object getTareasDependientes(TareasTrabajo tareaTrabajoFilter, JQGridRequestDto jqGridRequestDto, boolean isCount);

	/**
	 *
	 * @param tareasFilter Tareas
	 * @return Tareas
	 */
	Tareas findConfTareaTrabajo(Tareas tareasFilter);

	/**
	 *
	 * @param listSelectedIds List<String>
	 * @return List<TareasTrabajo>
	 */
	List<TareasTrabajo> findTareasTrabajoPendientesDeEjecutar(List<String> listSelectedIds);

	/**
	 *
	 * @param tarea TareasTrabajo
	 * @param listSelectedIds List<String>
	 */
	int reasignarTareas(TareasTrabajo tarea, List<String> listSelectedIds);

	/**
	 *
	 * @param listSelectedIds List<String>
	 * @return List<TareasTrabajo>
	 */
	List<TareasTrabajo> findTareas(List<String> listSelectedIds);

	/**
	 *
	 * @param tarea TareasTrabajo
	 * @return TareasTrabajo
	 */
	TareasTrabajo comprobarEstadoEjecucionTarea(TareasTrabajo tarea);

	/**
	 *
	 * @param tareas TareasTrabajo
	 * @return int
	 */
	int comprobarTareaAsignador(TareasTrabajo tareas);

	/**
	 *
	 * @param idTarea BigDecimal
	 * @return TareasTrabajo
	 */
	TareasTrabajo obtenerDatosEjecucionTarea(BigDecimal idTarea);

	/**
	 *
	 * @param idTareaList String[]
	 * @return Long
	 */
	Long comprobarConflictoFechas(String[] idTareaList);

	/**
	 *
	 * @param tareaTrabajo TareasTrabajo
	 * @return Integer
	 */
	Integer finalizarTarea(TareasTrabajo tareaTrabajo);

	/**
	 *
	 * @param tareaTrabajo TareasTrabajo
	 * @return int
	 */
	int insertarObservacionesEjecucionTarea(TareasTrabajo tareaTrabajo);

	/**
	 *
	 * @param tareaTrabajo TareasTrabajo
	 * @return int
	 */
	int borrarObservacionesEjecucionTarea(TareasTrabajo tareaTrabajo);

	/**
	 *
	 * @param tareaTrabajo TareasTrabajo
	 * @return Boolean
	 */
	Boolean tieneTareasPendientesDeEjecutar(TareasTrabajo tareaTrabajo);

	/**
	 *
	 * @param tareaTrabajo TareasTrabajo
	 * @return Integer
	 */
	Integer finalizaTrabajo(TareasTrabajo tareaTrabajo);

	/**
	 *
	 * @param tarea
	 * @return long
	 */
	Integer comprobarTareasPendientesUsuario(TareasTrabajo tarea);

	/**
	 *
	 * @param otrosTrabajos
	 * @return
	 */
	List<TareasTrabajo> getTareasTrabajosExcelDetalle(OtrosTrabajos otrosTrabajos);

	/**
	 *
	 * @param ExpTareaResumen
	 * @param trabajador
	 * @return String
	 */
	Integer comprobarTareasPendientesUsuarioAsignador(ExpTareasResumen expTareaResumen, String trabajador);

}
