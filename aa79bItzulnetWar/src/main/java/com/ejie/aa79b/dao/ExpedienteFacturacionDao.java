package com.ejie.aa79b.dao;

import java.util.List;

import com.ejie.aa79b.model.DatosFacturacionExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ExpedienteFacturacion;
import com.ejie.aa79b.model.FacturasExpediente;
import com.ejie.aa79b.model.ListaExpediente;
import com.ejie.aa79b.model.RupTableMultiselectionModel;
import com.ejie.x38.dto.JQGridRequestDto;

public interface ExpedienteFacturacionDao extends GenericoDao<ExpedienteFacturacion> {

	/**
	 * 
	 * @param filterExpedienteFacturacion ExpedienteFacturacion
	 * @param jqGridRequestDto            JQGridRequestDto
	 * @param startsWith                  Boolean
	 * @return List<ExpedienteFacturacion>
	 */
	List<ExpedienteFacturacion> confeccionarFacturaExpedientesFilter(ExpedienteFacturacion filterExpedienteFacturacion,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith);

	/**
	 * 
	 * @param filterExpedienteFacturacion ExpedienteFacturacion
	 * @param startsWith                  Boolean
	 * @return Long
	 */
	Long confeccionarFacturaExpedientesFilterCount(ExpedienteFacturacion filterExpedienteFacturacion,
			Boolean startsWith);

	/**
	 * 
	 * @param filterExpedienteFacturacion ExpedienteFacturacion
	 * @param jqGridRequestDto            JQGridRequestDto
	 * @param startsWith                  Boolean
	 * @param isCount                     boolean
	 * @return Object
	 */
	Object revisionFacturacionExpedientesFilter(ExpedienteFacturacion filterExpedienteFacturacion,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith, boolean isCount);

	/**
	 * 
	 * @param filterBorradorFacturaTabla ExpedienteFacturacion
	 * @param jqGridRequestDto           JQGridRequestDto
	 * @param expedientesList            List<Expediente>
	 * @param startsWith                 Boolean
	 * @return List<ExpedienteFacturacion>
	 */
	List<ExpedienteFacturacion> borradorFacturaTablaFilter(ExpedienteFacturacion filterBorradorFacturaTabla,
			JQGridRequestDto jqGridRequestDto, List<Expediente> expedientesList, Boolean startsWith);

	/**
	 * 
	 * @param filterBorradorFacturaTabla
	 * @param expedientesList
	 * @param startsWith                 Boolean
	 * @return Long
	 */
	Long borradorFacturaTablaFilterCount(ExpedienteFacturacion filterBorradorFacturaTabla,
			List<Expediente> expedientesList, Boolean b);

	/**
	 * 
	 * @param expedienteFacturacion ExpedienteFacturacion
	 */
	void realizarCalculoInterpretacion(ExpedienteFacturacion expedienteFacturacion);

	/**
	 * 
	 * @param expedienteFacturacion ExpedienteFacturacion
	 * @param filtroEstado          boolean
	 * @return ExpedienteFacturacion
	 */
	ExpedienteFacturacion obtenerDatosCalculoExpedienteFacturacion(ExpedienteFacturacion expedienteFacturacion,
			boolean filtroEstado);

	/**
	 * 
	 * @param expedienteFacturacionFilter ExpedienteFacturacion
	 * @param jqGridRequestDto            JQGridRequestDto
	 * @param startsWith                  Boolean
	 * @return List<ExpedienteFacturacion>
	 */
	List<ExpedienteFacturacion> calculoExpedienteFacturacionTablaFilter(
			ExpedienteFacturacion expedienteFacturacionFilter, JQGridRequestDto jqGridRequestDto, Boolean startsWith);

	/**
	 * 
	 * @param expedienteFacturacionFilter ExpedienteFacturacion
	 * @param startsWith                  Boolean
	 * @return Long
	 */
	Long calculoExpedienteFacturacionTablaFilterCount(ExpedienteFacturacion expedienteFacturacionFilter,
			Boolean startsWith);

	/**
	 * Obtiene los expedientes no facturados que no estén en estado "en curso" y
	 * fase "pendiente de revisar los datos de facturación"
	 * 
	 * @param filterExpedienteFacturacion ExpedienteFacturacion
	 * @param jqGridRequestDto            JQGridRequestDto
	 * @param startsWith                  Boolean
	 * @param isCount                     boolean
	 * @return Object
	 */
	Object actDatosFacturacionExpedientesFilter(ExpedienteFacturacion filterExpedienteFacturacion,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith, boolean isCount);

	/**
	 * Comprueba si el expediente tiene datos de facturación asociados
	 * 
	 * @param anyo   Long
	 * @param numExp Integer
	 * @return long, Devuelve (0/n): 0 - Si el expediente no contiene datos de
	 *         facturación asociados; n - En caso contrario
	 */
	long comprobarDatosFactExpInter(Long anyo, Integer numExp);

	/**
	 * Comprueba si el expediente tiene datos de facturación asociados
	 * 
	 * @param anyo   Long
	 * @param numExp Integer
	 * @return long, Devuelve (0/n): 0 - Si el expediente no contiene datos de
	 *         facturación asociados; n - En caso contrario
	 */
	long comprobarExpedienteNoFacturado(Long anyo, Integer numExp);

	/**
	 * 
	 * @param rupTableMultiselectionModel RupTableMultiselectionModel
	 * @param esInterpretacion            Boolean
	 * @return DatosFacturacionExpediente
	 */
	DatosFacturacionExpediente obtenerImportesExpedientes(RupTableMultiselectionModel rupTableMultiselectionModel,
			Boolean esInterpretacion);

	/**
	 * 
	 * @param rupTableMultiselectionModel RupTableMultiselectionModel
	 * @param esInterpretacion
	 * @return List<DatosFacturacionExpediente>
	 */
	List<DatosFacturacionExpediente> obtenerExpedientesSeleccionados(
			RupTableMultiselectionModel rupTableMultiselectionModel, Boolean esInterpretacion);

	/**
	 * 
	 * @param estado                   Integer
	 * @param expedientesSeleccionados List<Expediente>
	 * @return Boolean
	 */
	Boolean comprobarEstadoPresupuestos(Integer estado, List<Expediente> expedientesSeleccionados);

	/**
	 * Operacion de obtencion de ids de elementos seleccionados de RUP_TABLE.
	 * 
	 * @param filterExpedienteFacturacion ExpedienteFacturacion
	 * @param tableData                   JQGridRequestDto
	 * @return List<ExpedienteFacturacion>
	 */
	List<ExpedienteFacturacion> revisionFacturacionExpedientesFilterGetSelectedIds(
			ExpedienteFacturacion filterExpedienteFacturacion, JQGridRequestDto tableData);

	/**
	 * obtiene los datos de boe (url) del expediente
	 * 
	 * @param expediente Expediente
	 * @return Expediente
	 */
	Expediente obtenerDatosBoeExp(Expediente expediente);

	/**
	 * 
	 * @param expediente Expediente
	 * @param urlBoe     String
	 * @param indBoe     String
	 * @return Integer
	 */
	Integer guardarDatosBoe(Expediente expediente, String urlBoe, String indBoe);

	/**
	 * 
	 * @param listaExpediente ListaExpediente
	 * @return Integer
	 */
	Integer guardarDatosBoe(ListaExpediente listaExpediente);

	/**
	 * Operacion de obtencion de ids de elementos seleccionados de RUP_TABLE.
	 * 
	 * @param filterExpedienteFacturacion ExpedienteFacturacion
	 * @param tableData                   JQGridRequestDto
	 * @return List<ExpedienteFacturacion>
	 */
	List<ExpedienteFacturacion> actDatosFacturacionExpedientesFilterGetSelectedIds(
			ExpedienteFacturacion filterExpedienteFacturacion, JQGridRequestDto tableData);

	/**
	 * comprueba si los expedientes seleccionados tienen el indboe a no
	 * 
	 * @param listaExpedientes ListaExpediente
	 * @return Integer
	 */
	Integer comprobarExpSelBoeANo(ListaExpediente listaExpedientes);

	/**
	 * 
	 * @param listFacturasExp
	 * @param tipoExp
	 * @return
	 */
	List<ExpedienteFacturacion> listadoExpedientesFacturaVariosPagadores(List<FacturasExpediente> listFacturasExp,
			String tipoExp);

}
