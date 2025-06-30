package com.ejie.aa79b.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ejie.aa79b.model.DatosContacto;
import com.ejie.aa79b.model.ExpedienteFacturacion;
import com.ejie.aa79b.model.Lotes;

public interface DatosContactoDao extends GenericoDao<DatosContacto> {

	/**
	 * 
	 * @param idTarea BigDecimal
	 * @return List<DatosContacto>
	 */
	List<DatosContacto> getMailsProveedoresPorIdTarea(BigDecimal idTarea);

	/**
	 * 
	 * @param idTarea BigDecimal
	 * @return List<DatosContacto>
	 */
	List<DatosContacto> getMailRecursoTareaAuditoria(BigDecimal idAuditoria);

	/**
	 * Obtiene los datos de contacto
	 * 
	 * @param bean DatosContacto
	 * @return DatosContacto
	 */
	DatosContacto findDatosContacto(DatosContacto bean);

	/**
	 * Obtiene nombre y apellidos del solicitante
	 * 
	 * @param bean DatosContacto
	 * @return DatosContacto
	 */
	String findSolicitanteExp(ExpedienteFacturacion expedienteFacturacion);

	/**
	 * 
	 * @param lotes Lotes
	 * @return List<DatosContacto>
	 */
	List<DatosContacto> getMailsProveedoresPorIdLote(Lotes lotes);

}
