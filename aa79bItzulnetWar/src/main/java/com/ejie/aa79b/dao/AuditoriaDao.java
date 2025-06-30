package com.ejie.aa79b.dao;

import java.util.List;

import com.ejie.aa79b.model.Auditoria;
import com.ejie.aa79b.model.AuditoriaCampoSeccionExpediente;
import com.ejie.aa79b.model.AuditoriaSeccionExpediente;
import com.ejie.x38.dto.JQGridRequestDto;

/**
 *
 * @author eaguirresarobe
 *
 */
public interface AuditoriaDao extends GenericoDao<Auditoria> {

	/**
	 *
	 * @param auditoriaFilter  Auditoria
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @param orderNombre      Boolean
	 * @return List<Auditoria>
	 */
	List<Auditoria> filterAuditoria(Auditoria auditoriaFilter, JQGridRequestDto jqGridRequestDto, Boolean startsWith,
			Boolean orderNombre);

	/**
	 *
	 * @param auditoriaFilter auditoriaFilter
	 * @param b               Boolean
	 * @return Long
	 */
	Long filterAuditoriaCount(Auditoria auditoriaFilter, Boolean b);

	/**
	 *
	 * @param auditoria Auditoria
	 * @return Long
	 */
	Long llamarPLCrearEstructuraAuditoria(Auditoria auditoria);

	/**
	 *
	 * @param auditoria Auditoria
	 * @return Auditoria
	 */
	Auditoria getDatosGeneralesAuditoria(Auditoria auditoria);

	/**
	 *
	 * @param auditoria Auditoria
	 * @return List<AuditoriaSeccionExpediente>
	 */
	List<AuditoriaSeccionExpediente> getSeccionesExpedienteAuditoria(Auditoria auditoria);

	/**
	 *
	 * @param seccionFilter    AuditoriaSeccionExpediente
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param startsWith       Boolean
	 * @return List<AuditoriaCampoSeccionExpediente>
	 */
	List<AuditoriaCampoSeccionExpediente> filterCamposSeccion(AuditoriaSeccionExpediente seccionFilter,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith);

	/**
	 *
	 * @param seccionFilter AuditoriaSeccionExpediente
	 * @param startsWith    Boolean
	 * @return Long
	 */
	Long filterCamposSeccionCount(AuditoriaSeccionExpediente seccionFilter, Boolean startsWith);

	/**
	 *
	 * @param auditoria Auditoria
	 * @return Auditoria
	 */
	Auditoria guardarDatosGeneralesAuditoria(Auditoria auditoria);

	/**
	 *
	 * @param seccion AuditoriaSeccionExpediente
	 * @return AuditoriaSeccionExpediente
	 */
	AuditoriaSeccionExpediente guardarDatosSeccion(AuditoriaSeccionExpediente seccion);

	/**
	 *
	 * @param campo AuditoriaCampoSeccionExpediente
	 * @return AuditoriaCampoSeccionExpediente
	 */
	AuditoriaCampoSeccionExpediente guardarDatosCampo(AuditoriaCampoSeccionExpediente campo);

}
