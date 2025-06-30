package com.ejie.aa79b.service;

import java.util.List;

import com.ejie.aa79b.model.CalendarioIcsModel;
import com.ejie.aa79b.model.DatosEstimacion;
import com.ejie.aa79b.model.DatosPlanificacion;
import com.ejie.aa79b.model.ExpTareasResumen;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedientePlanificacion;
import com.ejie.aa79b.model.ResumenGraficas;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * ExpedientePlanificacionService.
 * 
 * @author aresua
 */
public interface ExpedientePlanificacionService {

	/**
	 * Obtiene los datos de planificacion para un expediente de interpretacion
	 * 
	 * @param bean Expediente
	 * @return DatosPlanificacion
	 */
	DatosPlanificacion obtenerDatosConfTareasExpInterpretacion(Expediente bean);

	/**
	 * Obtiene los datos de planificacion para un expediente de traducion o revision
	 * 
	 * @param bean Expediente
	 * @return DatosPlanificacion
	 */
	DatosPlanificacion obtenerDatosConfTareasExpTradRev(Expediente bean);

	/**
	 * 
	 * @param dni String
	 * @return List<List<ResumenGraficas>>
	 */
	List<List<ResumenGraficas>> getChartsData(String dni);

	/**
	 * Filter method in the ExpTareasResumen table.
	 *
	 * @param filter           ExpTareasResumen
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @return JQGridResponseDto<ExpTareasResumen>
	 */
	JQGridResponseDto<ExpTareasResumen> filterResumen(ExpTareasResumen filter, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith);

	/**
	 * Cambiar prioridad de expediente
	 * 
	 * @param anyo
	 * @param numExp
	 * @param prioridad
	 * @return String
	 */
	String cambiarPrioridadExpediente(Long anyo, Integer numExp);

	JQGridResponseDto<ExpTareasResumen> filterDesgloseTareasExpedientes(String idsExpedientes,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith);

	/**
	 * Obtiene los datos para el estudio de la estimacion
	 * 
	 * @param bean Expediente
	 * @return DatosEstimacion
	 */
	DatosEstimacion obtenerDatosEstimacion(Expediente bean);

	/**
	 * 
	 * @param expedientesSeleccionados String
	 * @param iTipoExp                 Integer
	 * @return List<CalendarioIcsModel>
	 */
	List<CalendarioIcsModel> obtenerDatosExpedientes(String expedientesSeleccionados, Integer iTipoExp);

	void addRequerimiento(ExpedientePlanificacion bean);

	/**
	 * 
	 * @param expedientesSeleccionados List<String>
	 * @return Boolean
	 */
	Boolean getComprobarEstadoExpedientesEnCurso(List<String> expedientesSeleccionados);

	/**
	 * 
	 * @param expediente
	 * @return
	 */
	Integer comprobarNecesidadFirmaDocs(Expediente expediente);

	void modificarFechaEntrega(ExpedientePlanificacion bean);

}