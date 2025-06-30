package com.ejie.aa79b.service;

import java.math.BigDecimal;
import java.util.List;

import com.ejie.aa79b.model.DatosContacto;

/**
 * DatosContactoService, 04-sep-2018 13:25:42.
 * 
 * @author mrodriguez
 */

public interface DatosContactoService extends GenericoService<DatosContacto> {

	/**
	 * Obtiene los datos de contacto
	 * 
	 * @param bean DatosContacto
	 * @return DatosContacto
	 */
	DatosContacto findDatosContacto(DatosContacto bean);

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

}
