package com.ejie.aa79b.service;

import java.math.BigDecimal;

import org.springframework.ui.Model;

import com.ejie.aa79b.model.Auditoria;
import com.ejie.aa79b.model.AuditoriaCampoSeccionExpediente;
import com.ejie.aa79b.model.AuditoriaDocumentoSeccionExpediente;
import com.ejie.aa79b.model.AuditoriaSeccionExpediente;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 *
 * @author eaguirresarobe
 *
 */
public interface AuditoriaService extends GenericoService<Auditoria> {

	/**
	 *
	 * @param auditoriaFilter
	 * @param jqGridRequestDto
	 * @param startsWith       boolean
	 * @return JQGridResponseDto<Auditoria>
	 */
	JQGridResponseDto<Auditoria> filterAuditoria(Auditoria auditoriaFilter, JQGridRequestDto jqGridRequestDto,
			boolean startsWith);

	/**
	 *
	 * @param model          Model
	 * @param anyo           Long
	 * @param numExp         Integer
	 * @param idTarea        BigDecimal
	 * @param idTareaAuditar BigDecimal
	 */
	void getDatosDetalleAuditoria(Model model, Long anyo, Integer numExp, BigDecimal idTarea,
			BigDecimal idTareaAuditar);

	/**
	 *
	 * @param model          Model
	 * @param anyo           Long
	 * @param numExp         Integer
	 * @param idTarea        BigDecimal
	 * @param idTareaAuditar BigDecimal
	 */
	Auditoria getDatosBasicosAuditoria(BigDecimal idAuditoria);

	/**
	 *
	 * @param seccionFilter    AuditoriaSeccionExpediente
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @return JQGridResponseDto<AuditoriaCampoSeccionExpediente>
	 */
	JQGridResponseDto<AuditoriaCampoSeccionExpediente> filterCamposSeccion(AuditoriaSeccionExpediente seccionFilter,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith);

	/**
	 *
	 * @param auditSecExp AuditoriaSeccionExpediente
	 * @return Long
	 */
	Long getCamposSeccionCount(AuditoriaSeccionExpediente auditSecExp);

	/**
	 *
	 * @param auditoria Auditoria
	 * @return Auditoria
	 */
	Auditoria guardarDatosAuditoria(Auditoria auditoria);

	/**
	 *
	 * @param auditoria Auditoria
	 * @return Long
	 */
	Long llamarPLCrearEstructuraAuditoria(Auditoria auditoria);

	/**
	 *
	 * @param seccionFilter AuditoriaDocumentoSeccionExpediente
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @return JQGridResponseDto<AuditoriaDocumentoSeccionExpediente>
	 */
	JQGridResponseDto<AuditoriaDocumentoSeccionExpediente> filterDocumentosSeccion(
			AuditoriaDocumentoSeccionExpediente seccionFilter, JQGridRequestDto jqGridRequestDto, Boolean startsWith);

	/**
	 *
	 * @param documentoAuditoria AuditoriaDocumentoSeccionExpediente
	 * @return AuditoriaDocumentoSeccionExpediente
	 */
	AuditoriaDocumentoSeccionExpediente anyadirDocAuditoria(AuditoriaDocumentoSeccionExpediente documentoAuditoria);

	/**
	 *
	 * @param docAuditoria AuditoriaDocumentoSeccionExpediente
	 * @return Integer
	 */
	Integer eliminarDocAuditoria(AuditoriaDocumentoSeccionExpediente docAuditoria);

}
