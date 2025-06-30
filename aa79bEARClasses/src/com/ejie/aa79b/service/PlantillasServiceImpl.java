package com.ejie.aa79b.service;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.dao.DocumentosGeneralDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.OidsAuxiliarDao;
import com.ejie.aa79b.dao.PlantillasDao;
import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.EntradaPlantilla;
import com.ejie.aa79b.model.FicheroWS;
import com.ejie.aa79b.model.Plantillas;

@Service(value = "plantillasService")
public class PlantillasServiceImpl extends GenericoServiceImpl<Plantillas> implements PlantillasService {

	@Autowired
	private PlantillasDao plantillasDao;
	@Autowired
	private DocumentosGeneralDao documentosGeneralDao;
	@Autowired
	private OidsAuxiliarDao oidsAuxiliarDao;

	@Override
	protected GenericoDao<Plantillas> getDao() {
		return this.plantillasDao;
	}

	@Override
	public Plantillas createNewPlantilla(Plantillas plantilla) {
		Plantillas plantillaAux = new Plantillas();
		DocumentoTareaAdjunto documentoPlantilla = new DocumentoTareaAdjunto();
		documentoPlantilla.setEncriptado(plantilla.getEncriptado());
		documentoPlantilla.setExtension(plantilla.getExtension());
		documentoPlantilla.setNombre(plantilla.getNombre());
		documentoPlantilla.setOid(plantilla.getOidFichero());
		documentoPlantilla.setTamano(plantilla.getTamano());
		documentoPlantilla.setContentType(plantilla.getContentType());
		documentoPlantilla.setTitulo(FilenameUtils.removeExtension(plantilla.getNombre()));
		documentoPlantilla = this.documentosGeneralDao.add(documentoPlantilla);
		plantilla.setIdFicheroPlantilla0A7(documentoPlantilla.getIdFichero());
		plantillaAux = this.plantillasDao.add(plantilla);

		return plantillaAux;
	}

	@Override
	public Plantillas updatePlantilla(Plantillas plantilla) {
		// Recuperar el OID viejo para borrarlo, si no es la primera vez que se
		// sube

		if (StringUtils.isNotEmpty(plantilla.getOidFichero())) {
			// Hemos modificado la plantilla
			DocumentoTareaAdjunto documentoPlantillaAnterior = new DocumentoTareaAdjunto();
			documentoPlantillaAnterior.setIdFichero(plantilla.getIdFicheroPlantilla0A7());
			documentoPlantillaAnterior = this.documentosGeneralDao.find(documentoPlantillaAnterior);
			if (StringUtils.isNotEmpty(documentoPlantillaAnterior.getOid())) {
				this.oidsAuxiliarDao.add(documentoPlantillaAnterior.getOid());
			}

			DocumentoTareaAdjunto documentoPlantillaNuevo = new DocumentoTareaAdjunto();
			documentoPlantillaNuevo.setEncriptado(plantilla.getEncriptado());
			documentoPlantillaNuevo.setExtension(plantilla.getExtension());
			documentoPlantillaNuevo.setNombre(plantilla.getNombre());
			documentoPlantillaNuevo.setOid(plantilla.getOidFichero());
			documentoPlantillaNuevo.setTamano(plantilla.getTamano());
			documentoPlantillaNuevo.setContentType(plantilla.getContentType());
			documentoPlantillaNuevo.setTitulo(FilenameUtils.removeExtension(plantilla.getNombre()));
			documentoPlantillaNuevo.setIdFichero(plantilla.getIdFicheroPlantilla0A7()); // V3.9.50 - update de 88
																						// utilizando
			// ID_FICHERO_PLANTILLA_0A7

			documentoPlantillaNuevo = this.documentosGeneralDao.update(documentoPlantillaNuevo);

			// Borrar OID nuevo de tabla de eliminacion del PID
			this.oidsAuxiliarDao.remove(documentoPlantillaNuevo.getOid());
		}

		this.plantillasDao.update(plantilla);
		// Update de la tabla de plantillas
		return plantilla;
	}

	@Override
	public FicheroWS obtenerDatosDocPlantilla(EntradaPlantilla bean) {
		return this.plantillasDao.obtenerDatosDocPlantilla(bean);
	}

}
