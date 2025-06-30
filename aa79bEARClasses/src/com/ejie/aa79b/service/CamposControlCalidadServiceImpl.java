package com.ejie.aa79b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.dao.CamposControlCalidadDao;
import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.model.CamposControlCalidad;

@Service(value = "controlCalidadService")
public class CamposControlCalidadServiceImpl extends GenericoServiceImpl<CamposControlCalidad> implements CamposControlCalidadService {

	@Autowired
	private CamposControlCalidadDao controlCalidadDao;

	@Override
	protected GenericoDao<CamposControlCalidad> getDao() {
		return this.controlCalidadDao;
	}

	/**
	 * @param controlCalidad ControlCalidad Bean
	 * @return List<ControlCalidad> lista de tipos.
	 */
	@Override()
	public List<CamposControlCalidad> findAllById(CamposControlCalidad controlCalidad) {
		return this.controlCalidadDao.findAllById(controlCalidad);
	}
}
