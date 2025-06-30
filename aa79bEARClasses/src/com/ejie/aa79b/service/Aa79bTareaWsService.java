package com.ejie.aa79b.service;

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
import com.ejie.aa79b.model.webservices.Aa79bDocumentoTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaAuditoria;
import com.ejie.aa79b.model.webservices.Aa79bEntradaCamposSeccion;
import com.ejie.aa79b.model.webservices.Aa79bEntradaConsultaTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaEjecutarTarea;
import com.ejie.aa79b.model.webservices.Aa79bEntradaValidarDocumentosTarea;
import com.ejie.aa79b.model.webservices.Aa79bListLoteCombo;
import com.ejie.aa79b.model.webservices.Aa79bSalidaConsultaTarea;
import com.ejie.aa79b.model.webservices.Aa79bSalidaOrigenNoConformidad;
import com.ejie.aa79b.model.webservices.Aa79bSalidaTarea;
import com.ejie.aa79b.model.webservices.Aa79bTarea;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 *
 * @author eaguirresarobe
 *
 */
public interface Aa79bTareaWsService extends GenericoService<Aa79bTarea> {

	/**
	 * Devuelve los lotes asociados a la entidad a la que pertenece el usuario en
	 * sesion
	 *
	 * @param dni String
	 * @return Aa79bListLoteCombo
	 */
	Aa79bListLoteCombo obtenerBuscadorProveedor(String dni);

	/**
	 *
	 * @param bean             Aa79bEntradaConsultaTarea
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param b                boolean
	 * @return JQGridResponseDto<Aa79bSalidaConsultaTarea>
	 */
	JQGridResponseDto<Aa79bSalidaConsultaTarea> consultaTareasProveedor(Aa79bEntradaConsultaTarea bean,
			JQGridRequestDto jqGridRequestDto, boolean b);

	/**
	 *
	 * @param bean             Aa79bEntradaEjecutarTarea
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param b                boolean
	 * @return JQGridResponseDto<Aa79bDocumentoTarea>
	 */
	JQGridResponseDto<Aa79bDocumentoTarea> obtenerDocumentosTraduccion(Aa79bEntradaEjecutarTarea bean,
			JQGridRequestDto jqGridRequestDto, boolean b);

	/**
	 *
	 * @param bean Aa79bEntradaEjecutarTarea
	 * @return Boolean
	 */
	Boolean getAccesoTarea(Aa79bEntradaEjecutarTarea bean);

	Tareas findTareaEntregaCliente(Tareas tarea);

	/**
	 *
	 * @param bean EntradaDatosDocumento
	 * @return Boolean
	 */
	Boolean getAccesoFicheroTarea(EntradaDatosDocumento bean);

	/**
	 *
	 * @param bean             Aa79bEntradaEjecutarTarea
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param b                boolean
	 * @return JQGridResponseDto<Aa79bDocumentoTarea>
	 */
	JQGridResponseDto<Aa79bDocumentoTarea> obtenerDocumentosXliffWS(Aa79bEntradaEjecutarTarea bean,
			JQGridRequestDto jqGridRequestDto, boolean b);

	/**
	 *
	 * @param bean             Aa79bEntradaEjecutarTarea
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param b                boolean
	 * @return JQGridResponseDto<Aa79bDocumentoTarea>
	 */
	JQGridResponseDto<Aa79bDocumentoTarea> obtenerDocumentosRevision(Aa79bEntradaEjecutarTarea bean,
			JQGridRequestDto jqGridRequestDto, boolean b);

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
	 * @param b                boolean
	 * @return JQGridResponseDto<Aa79bDocumentoTarea>
	 */
	JQGridResponseDto<Aa79bDocumentoTarea> obtenerDocumentosTraduccionNoConformidad(Aa79bEntradaEjecutarTarea bean,
			JQGridRequestDto jqGridRequestDto, boolean b);

	/**
	 *
	 * @param bean             Aa79bEntradaEjecutarTarea
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param b                boolean
	 * @return JQGridResponseDto<Aa79bDocumentoTarea>
	 */
	JQGridResponseDto<Aa79bDocumentoTarea> obtenerDocumentosRevisionNoConformidad(Aa79bEntradaEjecutarTarea bean,
			JQGridRequestDto jqGridRequestDto, boolean b);

	/**
	 *
	 * @param bean Aa79bEntradaValidarDocumentosTarea
	 * @return Boolean
	 */
	Boolean validarDocumentosTareaFinalizar(Aa79bEntradaValidarDocumentosTarea bean);

	/**
	 *
	 * @param bean             Aa79bEntradaConsultaTarea
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param b                boolean
	 * @return JQGridResponseDto<Aa79bConsultaTareasReport>
	 */
	JQGridResponseDto<Aa79bConsultaTareasReport> consultaTareasInformeProveedor(Aa79bEntradaConsultaTarea bean,
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
	 * @param b                boolean
	 * @return JQGridResponseDto<Aa79bAuditoria>
	 */
	JQGridResponseDto<Aa79bAuditoria> obtenerAuditorias(Aa79bEntradaAuditoria bean, JQGridRequestDto jqGridRequestDto,
			boolean b);

	Aa79bAuditoria getDatosGeneralesAuditoria(Auditoria auditoria);

	Aa79bAuditoria guardarDatosAuditoria(Aa79bAuditoria auditoria);

	List<Aa79bAuditoriaSeccionExpediente> getSeccionesExpedienteAuditoria(Auditoria auditoria);

	/**
	 *
	 * @param seccionFilter    Aa79bEntradaCamposSeccion
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @return JQGridResponseDto<Aa79bAuditoriaCampoSeccionExpediente>
	 */
	JQGridResponseDto<Aa79bAuditoriaCampoSeccionExpediente> filterCamposSeccion(Aa79bEntradaCamposSeccion seccionFilter,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith);

	/**
	 *
	 * @param filter Aa79bEntradaCamposSeccion
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @return JQGridResponseDto<Aa79bAuditoriaDocumentoSeccionExpediente>
	 */
	JQGridResponseDto<Aa79bAuditoriaDocumentoSeccionExpediente> filterDocumentosSeccion(Aa79bEntradaCamposSeccion filter,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith);

}
