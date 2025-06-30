package com.ejie.aa79b.service;

import java.util.List;

import com.ejie.aa79b.model.CamposControlCalidad;

public interface CamposControlCalidadService extends GenericoService<CamposControlCalidad> {

	/**
	 * @param controlCalidad ControlCalidad Bean
	 * @return List<ControlCalidad> lista de tipos.
	 */
	List<CamposControlCalidad> findAllById(CamposControlCalidad controlCalidad);
}
