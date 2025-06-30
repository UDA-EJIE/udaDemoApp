package com.ejie.aa79b.dao;

import com.ejie.aa79b.model.Expediente;
import com.ejie.x38.dto.JQGridRequestDto;

public interface AnulacionesDao extends GenericoDao<Expediente> {

	Object busquedaexpaanular(Expediente filter, JQGridRequestDto jqGridRequestDto, boolean startsWith,
			boolean isCount);

}
