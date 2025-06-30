package com.ejie.aa79b.service;

import java.util.List;

import com.ejie.aa79b.model.DatosPagoProveedores;
import com.ejie.aa79b.model.DetalleExpedienteVisionCliente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteConsulta;
import com.ejie.aa79b.model.ListaExpediente;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

public interface ConsultasService extends GenericoService<ExpedienteConsulta> {

	/**
	 * tabla consulta expedientes
	 * 
	 * @param filter
	 *            ExpedienteConsulta
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @return JQGridResponseDto<ExpedienteConsulta>
	 */
	JQGridResponseDto<ExpedienteConsulta> consultaexpedientes(ExpedienteConsulta filter,
			JQGridRequestDto jqGridRequestDto);

	/**
	 * asignar gestor a expedientes
	 * 
	 * @param listaExpedientes
	 * @return
	 */
	Integer asignarGestorAExpedientes(ListaExpediente listaExpedientes);

	/**
	 * devuelve el estado del gestor en x54
	 * 
	 * @param dni
	 *            String
	 * @return String
	 */
	String getEstadoGestor(String dni);

	/**
	 * devuelve valor si el expediente tiene tareas de pago a proveedor. 1 o mas
	 * si tiene, 0 si no
	 * 
	 * @param expediente
	 *            Expediente
	 * @return Integer
	 */
	Integer comprobarDatosPagoAProveedores(Expediente expediente);

	/**
	 * 
	 * @param filter
	 *            datosPagoProveedoresConsulta
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @param startsWith
	 *            boolean
	 * @return JQGridResponseDto<DatosPagoProveedores>
	 */
	JQGridResponseDto<DatosPagoProveedores> datosPagoProveedoresConsulta(Expediente filter,
			JQGridRequestDto jqGridRequestDto, boolean startsWith);

	/**
	 * devuelve valor si el expediente tiene datos de facturacion. 1 o mas si
	 * tiene, 0 si no
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
