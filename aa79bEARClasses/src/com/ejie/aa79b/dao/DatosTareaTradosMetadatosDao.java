package com.ejie.aa79b.dao;

import java.util.List;

import com.ejie.aa79b.model.MetadatosBusqueda;

/**
 * 
 * @author eaguirresarobe
 *
 */
public interface DatosTareaTradosMetadatosDao extends GenericoDao<MetadatosBusqueda> {

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
