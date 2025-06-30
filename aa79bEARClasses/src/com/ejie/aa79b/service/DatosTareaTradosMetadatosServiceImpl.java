package com.ejie.aa79b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.dao.DatosTareaTradosMetadatosDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.model.MetadatosBusqueda;

/**
 * 
 * @author eaguirresarobe
 *
 */
@Service(value = "datosTareaTradosMetadatosService")
public class DatosTareaTradosMetadatosServiceImpl extends GenericoServiceImpl<MetadatosBusqueda>
		implements DatosTareaTradosMetadatosService {

	@Autowired()
	private DatosTareaTradosMetadatosDao datosTareaTradosMetadatosDao;

	@Override
	protected GenericoDao<MetadatosBusqueda> getDao() {
		return this.datosTareaTradosMetadatosDao;
	}

	@Override
	public List<MetadatosBusqueda> getMetadatosTareaTrados(Long anyoExp, Integer numExp) {
		return this.datosTareaTradosMetadatosDao.getMetadatosTareaTrados(anyoExp, numExp);
	}

}
