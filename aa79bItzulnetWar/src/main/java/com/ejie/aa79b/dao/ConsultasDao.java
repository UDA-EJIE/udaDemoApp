package com.ejie.aa79b.dao;

import java.util.List;

import com.ejie.aa79b.model.DetalleExpedienteVisionCliente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteConsulta;
import com.ejie.aa79b.model.ListaExpediente;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.x38.dto.JQGridRequestDto;

public interface ConsultasDao extends GenericoDao<ExpedienteConsulta> {

	/**
	 * 
	 * devuelve lista de ExpedienteConsulta o Long en funcion de isCount
	 * 
	 * @param filter
	 *            ExpedienteConsulta
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @param startsWith
	 *            Boolean
	 * @param isCount
	 *            Boolean
	 * @return Object
	 */
	Object consultaexpedientes(ExpedienteConsulta filter, JQGridRequestDto jqGridRequestDto, Boolean startsWith,
			Boolean isCount);

	/**
	 * 
	 * @param listaExpedientes
	 *            ListaExpediente
	 * @param personalAInsertar
	 *            PersonalIZO
	 * @return Integer
	 */
	Integer asignarGestorAExpedientes(ListaExpediente listaExpedientes, PersonalIZO personalAInsertar);

	/**
	 * 
	 * @param dni
	 *            String
	 * @return Integer
	 */
	Integer getEstadoGestor(String dni);

	/**
	 * 
	 * @param expediente
	 *            Expediente
	 * @return Integer
	 */
	Integer comprobarDatosPagoAProveedores(Expediente expediente);

	/**
	 * 
	 * @param filter
	 *            Expediente
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @param startsWith
	 *            boolean
	 * @param isCount
	 *            boolean
	 * @return Object
	 */
	Object datosPagoProveedoresConsulta(Expediente filter, JQGridRequestDto jqGridRequestDto, boolean startsWith,
			boolean isCount);

	/**
	 * 
	 * @param expediente
	 *            Expediente
	 * @return Integer
	 */
	Integer comprobarDatosFacturacionExpedienteConsulta(Expediente expediente);

	/**
	 * 
	 * @param expediente
	 *            Expediente
	 * @return Integer
	 */
	Integer comprobarEsSolicitud(Expediente expediente);

	/**
	 * 
	 * @param expediente
	 *            Expediente
	 * @return DetalleExpedienteVisionCliente
	 */
	DetalleExpedienteVisionCliente findDatosDetalleExpedienteDesdeClienteConsulta(Expediente expediente);

	/**
	 * Operacion de obtencion de ids de elementos seleccionados de RUP_TABLE.
	 * 
	 * @param filterExpedienteConsulta
	 *            ExpedienteConsulta
	 * @param tableData
	 *            JQGridRequestDto
	 * @return List<ExpedienteConsulta>
	 */
	List<ExpedienteConsulta> consultaexpedientesGetSelectedIds(ExpedienteConsulta filterExpedienteConsulta,
			JQGridRequestDto tableData);

}
