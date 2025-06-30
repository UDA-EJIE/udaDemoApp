package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ejie.aa79b.model.Auditoria;
import com.ejie.aa79b.model.EntradaDatosDocumento;
import com.ejie.aa79b.model.Tareas;
import com.ejie.aa79b.model.webservices.Aa79bAuditoria;
import com.ejie.aa79b.model.webservices.Aa79bAuditoriaCampoSeccionExpediente;
import com.ejie.aa79b.model.webservices.Aa79bAuditoriaDocumentoSeccionExpediente;
import com.ejie.aa79b.model.webservices.Aa79bAuditoriaSeccionExpediente;
import com.ejie.aa79b.model.webservices.Aa79bConsultaTareasReport;
import com.ejie.aa79b.model.webservices.Aa79bEntradaAuditoria;
import com.ejie.aa79b.model.webservices.Aa79bEntradaCamposSeccion;
import com.ejie.aa79b.model.webservices.Aa79bEntradaConsultaTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaEjecutarTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaValidarDocumentosTarea;
import com.ejie.aa79b.model.webservices.Aa79bLoteCombo;
import com.ejie.aa79b.model.webservices.Aa79bSalidaOrigenNoConformidad;
import com.ejie.aa79b.model.webservices.Aa79bSalidaTarea;
import com.ejie.aa79b.model.webservices.Aa79bTarea;
import com.ejie.x38.dto.JQGridRequestDto;

public interface Aa79bTareaWsDao extends GenericoDao<Aa79bTarea> {

	/**
	 *
	 * @param dni String
	 * @return List<Aa79bLoteCombo>
	 */
	List<Aa79bLoteCombo> obtenerBuscadorProveedor(String dni);

	/**
	 *
	 * @param bean             Aa79bEntradaConsultaTarea
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @param isCount          Boolean
	 * @return Object
	 */
	Object consultaTareasProveedor(Aa79bEntradaConsultaTarea bean, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith, Boolean isCount);

	/**
	 *
	 * @param bean             Aa79bEntradaEjecutarTarea
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @param isCount          Boolean
	 * @return Object
	 */
	Object obtenerDocumentosTraduccion(Aa79bEntradaEjecutarTarea bean, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith, Boolean isCount);

	/**
	 *
	 * @param bean Aa79bEntradaEjecutarTarea
	 * @return Boolean
	 */
	Boolean getAccesoTarea(Aa79bEntradaEjecutarTarea bean);

	Tareas findTareaEntregaCliente(Tareas tarea);

	Boolean getAccesoFicheroTarea(EntradaDatosDocumento bean);

	/**
	 *
	 * @param bean             Aa79bEntradaEjecutarTarea
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @param isCount          Boolean
	 * @return Object
	 */
	Object obtenerDocumentosXliffWS(Aa79bEntradaEjecutarTarea bean, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith, Boolean isCount);

	/**
	 *
	 * @param bean             Aa79bEntradaEjecutarTarea
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @param isCount          Boolean
	 * @return Object
	 */
	Object obtenerDocumentosRevision(Aa79bEntradaEjecutarTarea bean, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith, Boolean isCount);

	/**
	 *
	 * @param bean Aa79bEntradaEjecutarTarea
	 * @return Aa79bSalidaOrigenNoConformidad
	 */
	Aa79bSalidaOrigenNoConformidad obtenerOrigenNoConformidad(Aa79bEntradaEjecutarTarea bean);

	/**
	 *
	 * @param bean             Aa79bEntradaEjecutarTarea
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @param isCount          Boolean
	 * @return Object
	 */
	Object obtenerDocumentosTraduccionNoConformidad(Aa79bEntradaEjecutarTarea bean, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith, Boolean isCount);

	/**
	 *
	 * @param bean             Aa79bEntradaEjecutarTarea
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       boolean
	 * @param isCount          boolean
	 * @return Object
	 */
	Object obtenerDocumentosRevisionNoConformidad(Aa79bEntradaEjecutarTarea bean, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith, Boolean isCount);

	/**
	 *
	 * @param bean Aa79bEntradaValidarDocumentosTarea
	 * @return Integer
	 */
	Integer validarDocumentosTareaFinalizar(Aa79bEntradaValidarDocumentosTarea bean);

	/**
	 *
	 * @param bean             Aa79bEntradaConsultaTarea
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param b                boolean
	 * @param c
	 * @return
	 */
	List<Aa79bConsultaTareasReport> consultaTareasInformeProveedor(Aa79bEntradaConsultaTarea bean,
			JQGridRequestDto jqGridRequestDto, boolean b);

	/**
	 *
	 * @param idTarea BigDecimal
	 * @return Aa79bSalidaTarea
	 */
	Aa79bSalidaTarea getDatosTareaEjecutada(BigDecimal idTarea);

	/**
	 *
	 * @param bean             Aa79bEntradaAuditoria
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       BooleanOS
	 * @param isCount          Boolean
	 * @return Object
	 */
	Object obtenerAuditorias(Aa79bEntradaAuditoria bean, JQGridRequestDto jqGridRequestDto, Boolean startsWith,
			Boolean isCount);

	Aa79bAuditoria getDatosGeneralesAuditoria(Auditoria auditoria);

	Aa79bAuditoria confirmarAuditoria(Aa79bAuditoria auditoria);

	Aa79bAuditoriaSeccionExpediente guardarDatosSeccion(Aa79bAuditoriaSeccionExpediente seccion);

	Aa79bAuditoriaCampoSeccionExpediente guardarDatosCampo(Aa79bAuditoriaCampoSeccionExpediente campo);

	List<Aa79bAuditoriaSeccionExpediente> getSeccionesExpedienteAuditoria(Auditoria auditoria);

	/**
	 *
	 * @param seccionFilter    Aa79bEntradaCamposSeccion
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @return List<Aa79bAuditoriaCampoSeccionExpediente>
	 */
	List<Aa79bAuditoriaCampoSeccionExpediente> filterCamposSeccion(Aa79bEntradaCamposSeccion seccionFilter,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith);

	/**
	 *
	 * @param seccionFilter Aa79bEntradaCamposSeccion
	 * @param startsWith    Boolean
	 * @return Long
	 */
	Long filterCamposSeccionCount(Aa79bEntradaCamposSeccion seccionFilter, Boolean startsWith);

	/**
	 *
	 * @param filter    Aa79bEntradaCamposSeccion
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @return List<Aa79bAuditoriaDocumentoSeccionExpediente>
	 */
	List<Aa79bAuditoriaDocumentoSeccionExpediente> filterDocumentosSeccion(Aa79bEntradaCamposSeccion filter,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith);

	/**
	 *
	 * @param seccionFilter Aa79bEntradaCamposSeccion
	 * @param startsWith    Boolean
	 * @return Long
	 */
	Long filterDocumentosSeccionCount(Aa79bEntradaCamposSeccion filter, Boolean startsWith);

}
