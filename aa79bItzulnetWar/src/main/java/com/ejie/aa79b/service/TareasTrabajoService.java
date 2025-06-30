package com.ejie.aa79b.service;

import java.math.BigDecimal;
import java.util.List;

import com.ejie.aa79b.model.OtrosTrabajos;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasTrabajo;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * @author mrodriguez
 *
 */
public interface TareasTrabajoService extends GenericoService<TareasTrabajo> {

	/**
	 * Crear tarea
	 *
	 * @param tarea
	 * @param documentosSelect
	 * @return int
	 */
	int crearTarea(TareasTrabajo tarea);

	/**
	 * Comprueba si existen tareas que no se pueden eliminar
	 *
	 * @param listSelectedIds
	 * @return long
	 */
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
	 * @return int
	 */
	int reabrirTarea(BigDecimal idTarea);


	/**
	 *
	 * @param tareas
	 * @return
	 */
	List<String> obtenerDestinatarios(TareasTrabajo tareas);

	/**
	 * obtiene datos de la tarea
	 * @param tareaTrabajo TareasTrabajo
	 * @return TareasTrabajo
	 */
	TareasTrabajo getDatosTarea(TareasTrabajo tareaTrabajo);

	/**
	 *
	 * @param tareaTrabajoFilter TareasTrabajo
	 * @param jqGridRequestDto  JQGridRequestDto
	 * @return JQGridResponseDto<TareasTrabajo>
	 */
	JQGridResponseDto<TareasTrabajo> getTareasDependientes(TareasTrabajo tareaTrabajoFilter, JQGridRequestDto jqGridRequestDto);

	/**
	 *
	 * @param persona PersonalIZO
	 * @param obtenerListIds List<String>
	 * @return int
	 */
	int reasignarTareasTrabajo(PersonalIZO persona, List<String> obtenerListIds);

	/**
	 *
	 * @param tareasFilter Tareas
	 * @return Tareas
	 */
	Tareas findConfTareaTrabajo(Tareas tareasFilter);

	/**
	 *
	 * @param tarea TareasTrabajo
	 * @return
	 */
	TareasTrabajo obtenerEstadoEjecucionTarea(TareasTrabajo tarea);

	/**
	 *
	 * @param tareas TareasTrabajo
	 * @return int
	 */
	int comprobarTareaTrabajoAsignador(TareasTrabajo tareas);

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
	 * @return TareasTrabajo
	 */
	TareasTrabajo finalizarTarea(TareasTrabajo tareaTrabajo);

	/**
	 *
	 * @param tareas TareasTrabajo
	 * @return int
	 */
	int enviarEmailEliminacionTareas(TareasTrabajo tareas);

	/**
	 *
	 * @param otrosTrabajos
	 * @return
	 */
	List<TareasTrabajo> getTareasTrabajosExcelDetalle(OtrosTrabajos otrosTrabajos);
}
