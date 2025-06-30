package com.ejie.aa79b.dao;

import java.util.List;

import com.ejie.aa79b.model.CamposControlCalidad;

public interface CamposControlCalidadDao extends GenericoDao<CamposControlCalidad> {
	/**
	 * @param controlCalidad ControlCalidad Bean
	 * @return List<ControlCalidad> lista de tipos.
	 */
	List<CamposControlCalidad> findAllById(CamposControlCalidad controlCalidad);
}
