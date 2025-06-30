package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ejie.aa79b.model.AuditoriaDocumentoSeccionExpediente;
import com.ejie.x38.dto.JQGridRequestDto;

/**
 *
 * @author eaguirresarobe
 *
 */
public interface AuditoriaDocumentosDao extends GenericoDao<AuditoriaDocumentoSeccionExpediente> {

	/**
	 *
	 * @param seccionFilter AuditoriaDocumentoSeccionExpediente
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @return List<AuditoriaDocumentoSeccionExpediente>
	 */
	List<AuditoriaDocumentoSeccionExpediente> filterDocumentosSeccion(AuditoriaDocumentoSeccionExpediente seccionFilter,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith);

	/**
	 *
	 * @param seccionFilter AuditoriaDocumentoSeccionExpediente
	 * @param startsWith       Boolean
	 * @return Long
	 */
	Long filterDocumentosSeccionCount(AuditoriaDocumentoSeccionExpediente seccionFilter, Boolean startsWith);

	/**
	 *
	 * @param idFicheroInterno BigDecimal
	 * @return Integer
	 */
	Integer removeC5(BigDecimal idFicheroInterno);

	/**
	 *
	 * @param documentoAuditoria AuditoriaDocumentoSeccionExpediente
	 * @return AuditoriaDocumentoSeccionExpediente
	 */
	AuditoriaDocumentoSeccionExpediente anyadirDocAuditoria(AuditoriaDocumentoSeccionExpediente documentoAuditoria);

	/**
	 *
	 * @param docAuditoria AuditoriaDocumentoSeccionExpediente
	 * @return AuditoriaDocumentoSeccionExpediente
	 */
	AuditoriaDocumentoSeccionExpediente obtenerDatosDocumentoAuditoria(
			AuditoriaDocumentoSeccionExpediente docAuditoria);


}
