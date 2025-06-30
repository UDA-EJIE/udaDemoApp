package com.ejie.aa79b.dao;

import com.ejie.aa79b.model.Expediente;
import com.ejie.x38.dto.JQGridRequestDto;

/**
 * ExpedientesConfidencialesDao, 02-ene-2019 11:22:48.
 * 
 * @author mrodriguez
 */
public interface ExpedientesConfidencialesDao extends GenericoDao<Expediente> {

	/**
	 * Obtiene los expedientes confidenciales
	 * 
	 * @param filter
	 *            Expediente
	 * @param dni
	 *            String
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @param startsWith
	 *            boolean
	 * @param isCount
	 *            boolean
	 * @return Object
	 */
	Object busquedaexpconf(Expediente filter, String dni, JQGridRequestDto jqGridRequestDto, boolean startsWith,
			boolean isCount);

}
