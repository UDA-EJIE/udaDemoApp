package com.ejie.aa79b.service;

import com.ejie.aa79b.model.EntradaPlantilla;
import com.ejie.aa79b.model.FicheroWS;
import com.ejie.aa79b.model.Plantillas;

public interface PlantillasService extends GenericoService<Plantillas> {

	/**
	 * 
	 * @param bean EntradaPlantilla
	 * @return FicheroWS
	 */
	FicheroWS obtenerDatosDocPlantilla(EntradaPlantilla bean);

	/**
	 * 
	 * @param plantilla Plantillas
	 * @return Plantillas
	 */
	Plantillas updatePlantilla(Plantillas plantilla);

	/**
	 * 
	 * @param plantilla Plantillas
	 * @return Plantillas
	 */
	Plantillas createNewPlantilla(Plantillas plantilla);

}
