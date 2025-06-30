package com.ejie.aa79b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.PropiedadDao;
import com.ejie.aa79b.model.Propiedad;

@Service(value = "bloqueoService")
public class PropiedadServiceImpl extends GenericoServiceImpl<Propiedad> implements PropiedadService {

    @Autowired
    private PropiedadDao propiedadDao;

    @Override
    protected GenericoDao<Propiedad> getDao() {
        return this.propiedadDao;
    }

	@Override
	public Boolean esAplicacionBOPV(String codAplic) {
		return this.propiedadDao.esAplicacionBOPV(codAplic);
	}
	@Override
	public Boolean esAplicacionComunicaciones(String codAplic) {
		return this.propiedadDao.esAplicacionComunicaciones(codAplic);
	}
}
