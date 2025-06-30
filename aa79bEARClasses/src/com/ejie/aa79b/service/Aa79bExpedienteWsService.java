package com.ejie.aa79b.service;

import java.util.List;
import java.util.Locale;

import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.CabeceraExpediente;
import com.ejie.aa79b.model.EntradaDatosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.webservices.Aa79bEntidadContacto;
import com.ejie.aa79b.model.webservices.Aa79bExpediente;
import com.ejie.aa79b.model.webservices.Aa79bExpedienteRelacionado;
import com.ejie.aa79b.model.webservices.Aa79bInformeSolicitudes;
import com.ejie.aa79b.model.webservices.Aa79bInformes;
import com.ejie.aa79b.model.webservices.Aa79bSalidaDatosPresupuestoFacturacion;
import com.ejie.aa79b.model.webservices.Aa79bSolaskides;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

public interface Aa79bExpedienteWsService extends GenericoService<Aa79bExpediente> {

	/**
	 * Obtiene un valor booleano que nos indica si el usuario tiene acceso al
	 * expediente
	 * 
	 * @param bean        Expediente
	 * @param numeroTabla String
	 * @return boolean
	 */
	boolean tieneAccesoExpediente(Expediente bean, String numeroTabla);

	/**
	 * 
	 * @param b
	 * @param Solicitante      solicitante
	 * @param Aa79bExpediente  bean
	 * @param JQGridRequestDto jQGridRequestDto
	 * @return JQGridResponseDto<Aa79bExpediente>
	 */
	JQGridResponseDto<Aa79bExpediente> obtenerExpedientes(Solicitante solicitante, Aa79bExpediente bean,
			JQGridRequestDto jqGridRequestDto, Boolean b);

	/**
	 * 
	 * @param Aa79bExpediente bean
	 * @return Integer
	 */
	Integer eliminarExpediente(Aa79bExpediente bean);

	/**
	 * 
	 * @param Solicitante      solicitante
	 * @param Aa79bExpediente  bean
	 * @param boolean          noRelacionados
	 * @param JQGridRequestDto jQGridRequestDto
	 * @return JQGridResponseDto<Aa79bExpediente>
	 */
	JQGridResponseDto<Aa79bExpediente> buscarExpedientesRelacionados(Solicitante solicitante, Aa79bExpediente bean,
			JQGridRequestDto jQGridRequestDto, Boolean b);

	/**
	 * Finds a single row in the table.
	 * 
	 * @param bean Expediente
	 * @return CabeceraExpediente
	 */
	CabeceraExpediente findCabeceraExpediente(Expediente bean);

	/**
	 * Finds a list of rows in the BitacoraSolicitante table.
	 * 
	 * @param expediente Expediente
	 * @return List<BitacoraSolicitante>
	 */
	List<BitacoraSolicitante> findInfoBitacora(Expediente expediente);

	/**
	 * Finds a single row in the table.
	 * 
	 * @param bean Expediente
	 * @return Aa79bExpediente
	 */
	Aa79bExpediente findDatosExpediente(Expediente bean);

	/**
	 * Funcion que devuelve una lista de los expedientes seleccionados pasándole
	 * como parametro el dni del solicitante y un String que contiene los ids de los
	 * expedientes seleccionados
	 * 
	 * @param solicitante
	 * @param selectedIds
	 * @return
	 */
	List<Aa79bExpedienteRelacionado> obtenerExpedientesSeleccionados(Solicitante solicitante, String selectedIds);

	/**
	 * Finds a list of rows in the Aa79bExpedienteRelacionado table.
	 * 
	 * @param bean Expediente
	 * @return List<Aa79bExpedienteRelacionado>
	 */
	JQGridResponseDto<Aa79bExpedienteRelacionado> findExpedientesRelacionados(Aa79bExpediente bean,
			JQGridRequestDto jqGridRequestDto, Boolean b);

	/**
	 * rup list de aa06a consulta de expedientes
	 * 
	 * @param solicitante      Solicitante
	 * @param bean             Aa79bExpediente
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @return JQGridResponseDto<Aa79bExpediente>
	 */
	JQGridResponseDto<Aa79bExpediente> consultarExpReceptores(Solicitante solicitante, Aa79bExpediente bean,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith);

	/**
	 * 
	 * @param datosExpediente EntradaDatosExpediente
	 * @return Integer
	 */
	Integer obtenerIdRequerimiento(EntradaDatosExpediente datosExpediente);

	/**
	 * 
	 * @param datosExpediente EntradaDatosExpediente
	 * @return Aa79bSalidaDatosPresupuestoFacturacion
	 */
	Aa79bSalidaDatosPresupuestoFacturacion datosFacturacionInterpretacion(EntradaDatosExpediente datosExpediente);

	/**
	 * 
	 * @param datosExpediente EntradaDatosExpediente
	 * @return List<Aa79bEntidadContacto>
	 */
	List<Aa79bEntidadContacto> obtenerDatosEntidadContactoFacturacionInterpretacion(
			EntradaDatosExpediente datosExpediente);

	/**
	 * 
	 * @param datosExpediente EntradaDatosExpediente
	 * @return Aa79bSalidaDatosPresupuestoFacturacion
	 */
	Aa79bSalidaDatosPresupuestoFacturacion datosFacturacionTraduccion(EntradaDatosExpediente datosExpediente);

	/**
	 * 
	 * @param datosExpediente EntradaDatosExpediente
	 * @return List<Aa79bEntidadContacto>
	 */
	List<Aa79bEntidadContacto> obtenerDatosEntidadContactoFacturacionTraduccion(EntradaDatosExpediente datosExpediente);

	List<Aa79bSolaskides> findSolaskides(Aa79bInformes aa79bInformeSolaskides, Locale locale);

	List<Aa79bInformeSolicitudes> findInformeSolicitudes(Aa79bInformes aa79bInformes, Locale locale);

}
