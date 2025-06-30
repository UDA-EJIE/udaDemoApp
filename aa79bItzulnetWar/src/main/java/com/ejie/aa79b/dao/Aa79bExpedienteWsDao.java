package com.ejie.aa79b.dao;

import java.util.List;
import java.util.Locale;

import com.ejie.aa79b.model.BitacoraSolicitante;
import com.ejie.aa79b.model.CabeceraExpediente;
import com.ejie.aa79b.model.EntradaDatosExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.Persona;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.aa79b.model.webservices.Aa79bEntidadContacto;
import com.ejie.aa79b.model.webservices.Aa79bExpediente;
import com.ejie.aa79b.model.webservices.Aa79bExpedienteRelacionado;
import com.ejie.aa79b.model.webservices.Aa79bInformeSolicitudes;
import com.ejie.aa79b.model.webservices.Aa79bInformes;
import com.ejie.aa79b.model.webservices.Aa79bSalidaDatosPresupuestoFacturacion;
import com.ejie.aa79b.model.webservices.Aa79bSolaskides;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.TableRowDto;

/**
 * @author mrodriguez
 *
 */
public interface Aa79bExpedienteWsDao extends GenericoDao<Aa79bExpediente> {

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
	 * Finds a list of Aa79bExpediente
	 * 
	 * @param solicitante
	 * @param bean
	 * @param jqGridRequestDto
	 * @param c
	 * @param startsWith
	 * @return
	 */
	Object obtenerExpedientes(Solicitante solicitante, Aa79bExpediente bean, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith, Boolean isCount);

	/**
	 * Deletes a row
	 * 
	 * @param bean
	 * @return
	 */
	Integer eliminarExpediente(Aa79bExpediente bean);

	/**
	 * Finds a list of Aa79bExpediente
	 * 
	 * @param solicitante
	 * @param bean
	 * @param isCount
	 * @param startsWith
	 * @param jQGridResponseDto
	 * @return
	 */
	Object buscarExpedientesRelacionados(Solicitante solicitante, Aa79bExpediente bean,
			JQGridRequestDto jQGridRequestDto, Boolean startsWith, Boolean isCount);

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
	 * @param bean Expediente
	 * @return List<BitacoraSolicitante>
	 */
	List<BitacoraSolicitante> findInfoBitacora(Expediente bean);

	/**
	 * Finds a single row in the table.
	 * 
	 * @param bean Expediente
	 * @return Aa79bExpediente
	 */
	Aa79bExpediente findDatosExpediente(Expediente bean);

	/**
	 * Finds a list of rows in the Aa79bExpedienteRelacionado table.
	 * 
	 * @param bean             Aa79bExpediente
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @param isCount          Boolean
	 * @return List<Aa79bExpedienteRelacionado>
	 */
	Object findExpedientesRelacionados(Aa79bExpediente bean, JQGridRequestDto jqGridRequestDto, Boolean startsWith,
			Boolean isCount);

	/**
	 * 
	 * @param jQGridRequestDto
	 * @param b
	 * @return
	 */
	List<TableRowDto<Aa79bExpedienteRelacionado>> reorderSelectionFindExpedientesRelacionados(
			JQGridRequestDto jQGridRequestDto, boolean b);

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
	 * 
	 * @param dni
	 * @param bean
	 * @param jQGridRequestDto
	 * @param b
	 * @return
	 */
	List<TableRowDto<Aa79bExpediente>> reorderBuscarSelectionExpedientesRelacionados(String dni, Aa79bExpediente bean,
			JQGridRequestDto jQGridRequestDto, boolean b);

	/**
	 * Busca los datos de una persona en la vista X54JAPI_TRABAJADORES_GIP
	 * 
	 * @param persona Persona
	 * @return Persona
	 */
	Persona findTrabajadorGIP(Persona persona);

	/**
	 * Guarda los datos de una persona en la tabla 77
	 * 
	 * @param persona Persona
	 * @return Integer
	 */
	Integer guardarPersona(Persona persona);

	/**
	 * 
	 * @param solicitante      Solicitante
	 * @param bean             Aa79bExpediente
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @param isCount          Boolean
	 * @return Object
	 */
	Object consultarExpReceptores(Solicitante solicitante, Aa79bExpediente bean, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith, Boolean isCount);

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
