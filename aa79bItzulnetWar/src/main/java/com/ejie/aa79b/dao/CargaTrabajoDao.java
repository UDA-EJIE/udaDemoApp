package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.TareasTrabajo;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.TableRowDto;

public interface CargaTrabajoDao extends GenericoDao<ExpTareasResumen> {

	List<ExpTareasResumen> filtroExpTareas(ExpTareasResumen expTareasResumenFilter, JQGridRequestDto jqGridRequestDto,
			boolean b, boolean orderNombre);

	Long filtroTareasCount(ExpTareasResumen expTareasResumenFilter, boolean b);

	List<TableRowDto<ExpTareasResumen>> reorderSelection(ExpTareasResumen expTareasResumenFilter,
			JQGridRequestDto jqGridRequestDto, boolean startsWith);

	Integer procComprobarEstadoTareasDependientes(final BigDecimal idTarea, final Long anyo, final Integer numExp);

	ExpTareasResumen findTarea(BigDecimal idTarea);

	List<ExpTareasResumen> filtroTareasDependiente(ExpTareasResumen bean, JQGridRequestDto jqGridRequestDto, boolean b);

	Long filtroTareasDependienteCount(ExpTareasResumen expTareasResumenFilter, boolean b);

	ExpTareasResumen updateEstadoAceptacion(ExpTareasResumen expTareasResumen);

	Integer insertObsrvRechazo(BigDecimal idTarea, String motivoRechazo);

	List<ExpTareasResumen> getExpedientesTarea(ExpTareasResumen expTareasResumenFilter);

	List<ExpTareasResumen> getTareasExpediente(ExpTareasResumen expTareasResumenFilter);

	List<DocumentosExpediente> findDocumentosTarea(BigDecimal idTarea);

	List<Tareas> obtenerDatosTareas(String tareasSeleccionados);

	Long comprobarConflictoFechas(String[] listaIdTareas);

	/**
	 * Operacion de obtencion de ids de elementos seleccionados de RUP_TABLE.
	 *
	 * @param expTareasResumenFilter
	 *            ExpTareasResumen
	 * @param tableData
	 *            JQGridRequestDto
	 * @return List<ExpTareasResumen>
	 */
	List<ExpTareasResumen> filtroExpTareasGetSelectedIds(ExpTareasResumen expTareasResumenFilter,
			JQGridRequestDto tableData);

	/**
	 *
	 * @param expTareasResumenFilter ExpTareasResumen
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith boolean
	 * @param orderNombre boolean
	 * @return List<ExpTareasResumen>
	 */
	List<ExpTareasResumen> filtroExpTareasTrabajo(ExpTareasResumen expTareasResumenFilter,
			JQGridRequestDto jqGridRequestDto, boolean startsWith, boolean orderNombre);

	/**
	 *
	 * @param expTareasResumenFilter ExpTareasResumen
	 * @param startsWith boolean
	 * @return Long
	 */
	Long filtroTareasTrabajoCount(ExpTareasResumen expTareasResumenFilter, boolean startsWith);

	/**
	 *
	 * @param expTareasResumenFilter ExpTareasResumen
	 * @param tableData JQGridRequestDto
	 * @return List<ExpTareasResumen>
	 */
	List<ExpTareasResumen> filtroTareasTrabajoGetSelectedIds(ExpTareasResumen expTareasResumenFilter,
			JQGridRequestDto tableData);

	/**
	 *
	 * @param tareas TareasTrabajo
	 * @return Integer
	 */
	Integer updateEstadoAceptacionTareaTrabajo(TareasTrabajo tareas);

	/**
	 *
	 * @param idTarea BigDecimal
	 * @param motivoRechazo String
	 * @return Integer
	 */
	Integer insertObsrvRechazoTareaTrabajo(BigDecimal idTarea, String motivoRechazo);

	/**
	 *
	 * @param tareasSeleccionados String
	 * @return List<TareasTrabajo>
	 */
	List<TareasTrabajo> obtenerDatosTareasTrabajo(String tareasSeleccionados);

	/**
	 *
	 * @param expTareasResumen ExpTareasResumen
	 * @param startsWith boolean
	 * @return List<ExpTareasResumen>
	 */
	List<ExpTareasResumen> getExpTareasGantt(ExpTareasResumen expTareasResumen, boolean startsWith);

}
