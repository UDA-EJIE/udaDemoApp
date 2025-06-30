package com.ejie.aa79b.service;

import java.util.List;

import com.ejie.aa79b.model.MetadatosBusqueda;

/**
 * 
 * @author eaguirresarobe
 *
 */
public interface DatosTareaTradosMetadatosService extends GenericoService<MetadatosBusqueda> {

	/**
	 * 
	 * @param anyoExp
	 *            Long
	 * @param numExp
	 *            Integer
	 * @return List<MetadatosBusqueda>
	 */
	List<MetadatosBusqueda> getMetadatosTareaTrados(Long anyoExp, Integer numExp);

}
