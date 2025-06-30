package com.ejie.aa79b.dao;

import com.ejie.aa79b.model.ConfigHorasPrevistasProveedorExt;
import com.ejie.x38.dto.JQGridRequestDto;

public interface ConfigHorasPrevistasProveedorExtDao extends GenericoDao<ConfigHorasPrevistasProveedorExt> {

	/**
	 * 
	 * @param filterConfigHorasPrevistas ConfigHorasPrevistasProveedorExt
	 * @param jqGridRequestDto           JQGridRequestDto
	 * @param startsWith                 Boolean
	 * @param isCount                    Boolean
	 * @return Object
	 */
	Object datosBasicosFilter(ConfigHorasPrevistasProveedorExt filterConfigHorasPrevistas,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith, Boolean isCount);

	/**
	 * 
	 * @param configHorasPrevistas ConfigHorasPrevistasProveedorExt
	 * @return Integer
	 */
	Integer isConfigHorasPrevistasAlreadyStored(ConfigHorasPrevistasProveedorExt configHorasPrevistas);

}
