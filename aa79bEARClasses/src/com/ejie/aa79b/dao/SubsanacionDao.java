package com.ejie.aa79b.dao;

import java.math.BigDecimal;

import com.ejie.aa79b.model.DocumentosExpediente;
import com.ejie.aa79b.model.SubsanacionExpediente;

public interface SubsanacionDao extends GenericoDao<SubsanacionExpediente> {

	/**
	 * 
	 * @param anyo
	 *            Long
	 * @param numExp
	 *            Integer
	 * @return SubsanacionExpediente
	 */
	SubsanacionExpediente getSubsanacionExpediente(Long anyo, Integer numExp, Long idTarea);

	/**
	 * 
	 * @param sExp
	 *            SubsanacionExpediente
	 * @return Integer
	 */
	Integer actualizarSubsanacionConIndSubs(SubsanacionExpediente sExp);

	/**
	 * 
	 * @param idRequerimiento
	 * @return
	 */
	BigDecimal getIdTareaConRequerimiento(Long idRequerimiento);

	Integer comprobarSiTieneZIP(DocumentosExpediente documentosExpediente);

}
