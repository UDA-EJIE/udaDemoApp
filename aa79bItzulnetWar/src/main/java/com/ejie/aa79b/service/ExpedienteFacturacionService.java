package com.ejie.aa79b.service;

import java.io.IOException;
import java.util.List;

import com.ejie.aa79b.model.ContactoFacturacionExpediente;
import com.ejie.aa79b.model.DatosFacturacionExpediente;
import com.ejie.aa79b.model.DetalleDatosFacturacion;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteFacturacion;
import com.ejie.aa79b.model.ListaExpediente;
import com.ejie.aa79b.model.RupTableMultiselectionModel;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

public interface ExpedienteFacturacionService extends GenericoService<ExpedienteFacturacion> {

	/**
	 * @param filterExpedienteFacturacion
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @param startsWith
	 *            Boolean
	 * @return
	 */
	JQGridResponseDto<ExpedienteFacturacion> confeccionarFacturaExpedientesFilter(
			ExpedienteFacturacion filterExpedienteFacturacion, JQGridRequestDto jqGridRequestDto, boolean startsWith);

	/**
	 * 
	 * @param filterExpedienteFacturacion
	 *            ExpedienteFacturacion
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @param startsWith
	 *            boolean
	 * @return JQGridResponseDto<ExpedienteFacturacion>
	 */
	JQGridResponseDto<ExpedienteFacturacion> revisionFacturacionExpedientesFilter(
			ExpedienteFacturacion filterExpedienteFacturacion, JQGridRequestDto jqGridRequestDto);

	DetalleDatosFacturacion getDatosDetalle(Expediente expediente);

	/**
	 * 
	 * @param filterContactoFacturacionExpediente
	 *            ExpedienteFacturacion
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @param listaExpedientes
	 *            String
	 * @param startsWith
	 *            Boolean
	 * @return JQGridResponseDto<ExpedienteFacturacion>
	 * @throws Exception
	 */
	JQGridResponseDto<ExpedienteFacturacion> borradorFacturaTablaFilter(
			ExpedienteFacturacion filterContactoFacturacionExpediente, JQGridRequestDto jqGridRequestDto,
			String listaExpedientes, Boolean startsWith) throws IOException;

	/**
	 * 
	 * @param expedienteFacturacion
	 *            ExpedienteFacturacion
	 */
	void realizarCalculoInterpretacion(ExpedienteFacturacion expedienteFacturacion);

	/**
	 * @param expedienteFacturacion
	 *            ExpedienteFacturacion
	 * @param filtroEstado
	 *            boolean
	 * @return ExpedienteFacturacion
	 */
	ExpedienteFacturacion obtenerDatosCalculoExpedienteFacturacion(ExpedienteFacturacion expedienteFacturacion,
			boolean filtroEstado);

	/**
	 * 
	 * @param expedienteFacturacionFilter
	 *            ExpedienteFacturacion
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @param startsWith
	 *            Boolean
	 * @return JQGridResponseDto<ExpedienteFacturacion>
	 */
	JQGridResponseDto<ExpedienteFacturacion> calculoExpedienteFacturacionTablaFilter(
			ExpedienteFacturacion expedienteFacturacionFilter, JQGridRequestDto jqGridRequestDto, Boolean startsWith);

	void finRevisionFactura(DetalleDatosFacturacion detalleDatosFacturacion);

	void finRevisionContactosEntidades(List<ContactoFacturacionExpediente> contactoFactuExpList);

	/**
	 * Obtiene los expedientes no facturados que no estén en estado "en curso" y
	 * fase "pendiente de revisar los datos de facturación"
	 * 
	 * @param filterExpedienteFacturacion
	 *            ExpedienteFacturacion
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @return JQGridResponseDto<ExpedienteFacturacion>
	 */
	JQGridResponseDto<ExpedienteFacturacion> actDatosFacturacionExpedientesFilter(
			ExpedienteFacturacion filterExpedienteFacturacion, JQGridRequestDto jqGridRequestDto);

	/**
	 * Comprueba si el expediente tiene datos de facturación asociados
	 * 
	 * @param anyo
	 *            Long
	 * @param numExp
	 *            Integer
	 * @return long, Devuelve (0/n): 0 - Si el expediente no contiene datos de
	 *         facturación asociados; n - En caso contrario
	 */
	long comprobarDatosFactExpInter(Long anyo, Integer numExp);

	/**
	 * Finaliza la revisión de datos de facturación de un expediente de
	 * interpretación
	 * 
	 * @param detalleDatosFacturacion
	 *            DetalleDatosFacturacion
	 */
	void finRevDatosFact(DetalleDatosFacturacion detalleDatosFacturacion);

	/**
	 * Se incluyen los contactos de facturación para un expediente de
	 * interpretación
	 * 
	 * @param contactoFactuExpList
	 *            List<ContactoFacturacionExpediente>
	 */
	void finRevContactosEntidadesExpInter(List<ContactoFacturacionExpediente> contactoFactuExpList);

	/**
	 * 
	 * @param rupTableMultiselectionModel
	 *            RupTableMultiselectionModel
	 * @param tipoExpediente
	 *            String
	 * @return DatosFacturacionExpediente
	 */
	DatosFacturacionExpediente obtenerImportesExpedientes(RupTableMultiselectionModel rupTableMultiselectionModel,
			String tipoExpediente);

	/**
	 * 
	 * @param rupTableMultiselectionModel
	 *            RupTableMultiselectionModel
	 * @param tipoExpediente
	 *            String
	 * @return List<DatosFacturacionExpediente>
	 */
	List<DatosFacturacionExpediente> obtenerExpedientesSeleccionados(
			RupTableMultiselectionModel rupTableMultiselectionModel, String tipoExpediente);

	/**
	 * 
	 * @param estado
	 *            Integer
	 * @param expedientesSeleccionados
	 *            List<Expediente>
	 * @return Boolean
	 */
	Boolean comprobarEstadoPresupuestos(Integer estado, List<Expediente> expedientesSeleccionados);

	/**
	 * Operacion de obtencion de ids de elementos seleccionados de RUP_TABLE.
	 * 
	 * @param filterExpedienteFacturacion
	 *            ExpedienteFacturacion
	 * @param tableData
	 *            JQGridRequestDto
	 * @return List<ExpedienteFacturacion>
	 */
	List<ExpedienteFacturacion> revisionFacturacionExpedientesFilterGetSelectedIds(
			ExpedienteFacturacion filterExpedienteFacturacion, JQGridRequestDto tableData);

	/**
	 * obtiene los datos de boe (url) del expediente
	 * 
	 * @param expediente
	 *            Expediente
	 * @return Expediente
	 */
	Expediente obtenerDatosBoeExp(Expediente expediente);

	/**
	 * guarda los datos relacionados al BOE de un expediente (indBoe y url)
	 * 
	 * @param expediente
	 *            Expediente
	 * @param urlBoe
	 *            String
	 * @param indBoe
	 *            String
	 * @return Integer
	 */
	Integer guardarDatosBoe(Expediente expediente, String urlBoe, String indBoe);

	/**
	 * guarda los datos relacionados al BOE de varios expedientes (solo indBoe)
	 * 
	 * @param listaExpediente
	 *            ListaExpediente
	 * @return Integer
	 */
	Integer guardarDatosBoe(ListaExpediente listaExpediente);

	/**
	 * Operacion de obtencion de ids de elementos seleccionados de RUP_TABLE.
	 * 
	 * @param filterExpedienteFacturacion
	 *            ExpedienteFacturacion
	 * @param tableData
	 *            JQGridRequestDto
	 * @return List<ExpedienteFacturacion>
	 */
	List<ExpedienteFacturacion> actDatosFacturacionExpedientesFilterGetSelectedIds(
			ExpedienteFacturacion filterExpedienteFacturacion, JQGridRequestDto tableData);

	/**
	 * comprueba si los expedientes seleccionados tienen el indboe a no
	 * 
	 * @param listaExpedientes
	 *            ListaExpediente
	 * @return Integer
	 */
	Integer comprobarExpSelBoeANo(ListaExpediente listaExpedientes);

}
