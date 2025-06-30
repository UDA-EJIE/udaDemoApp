package com.ejie.aa79b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.SeccionesControlDeCalidadDao;
import com.ejie.aa79b.model.SeccionesControlDeCalidad;

@Service(value = "seccionesControlDeCalidadService")
public class SeccionesControlDeCalidadServiceImpl extends GenericoServiceImpl<SeccionesControlDeCalidad>
		implements SeccionesControlDeCalidadService {

	@Autowired
	private SeccionesControlDeCalidadDao seccionesControlDeCalidadDao;

	@Override
	protected GenericoDao<SeccionesControlDeCalidad> getDao() {
		return this.seccionesControlDeCalidadDao;
	}

	@Override()
	@Transactional(rollbackFor = Throwable.class)
	public SeccionesControlDeCalidad update(SeccionesControlDeCalidad bean) {
		return this.seccionesControlDeCalidadDao.update(bean);
	}
}
