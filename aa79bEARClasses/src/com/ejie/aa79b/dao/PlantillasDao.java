package com.ejie.aa79b.dao;

import com.ejie.aa79b.model.EntradaPlantilla;
import com.ejie.aa79b.model.FicheroWS;
import com.ejie.aa79b.model.Plantillas;

public interface PlantillasDao extends GenericoDao<Plantillas> {

	/**
	 * 
	 * @param bean
	 *            EntradaPlantilla
	 * @return FicheroWS
	 */
	FicheroWS obtenerDatosDocPlantilla(EntradaPlantilla bean);

}
