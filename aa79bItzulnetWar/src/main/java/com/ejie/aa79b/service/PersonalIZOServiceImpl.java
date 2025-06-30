package com.ejie.aa79b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.dao.GenericoDao;
import com.ejie.aa79b.dao.PersonalIZODao;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Solicitante;

@Service(value = "personalIZOService")
public class PersonalIZOServiceImpl extends GenericoServiceImpl<PersonalIZO> implements PersonalIZOService {

	@Autowired()
	private PersonalIZODao personalIZODao;

	@Override()
	protected GenericoDao<PersonalIZO> getDao() {
		return this.personalIZODao;
	}

	@Override
	public List<PersonalIZO> filterTecnicoAsignadoAEstudio(PersonalIZO filterPersonalIZO) {
		return this.personalIZODao.filterTecnicoAsignadoAEstudio(filterPersonalIZO);
	}

	@Override
	public List<PersonalIZO> findAsignadores(PersonalIZO filterPersonalIZO, String alta) {
		return this.personalIZODao.findAsignadores(filterPersonalIZO, alta);
	}

	@Override
	public List<PersonalIZO> findTraductores(PersonalIZO filterPersonalIZO) {
		return this.personalIZODao.findTraductores(filterPersonalIZO);
	}

	@Override
	public List<PersonalIZO> getAsignadores(List<Long> gruposTrabajo) {
		return this.personalIZODao.getAsignadores(gruposTrabajo);
	}

	@Override
	public List<PersonalIZO> findTradInterpAsign(PersonalIZO filterPersonalIZO) {
		return this.personalIZODao.findTradInterpAsign(filterPersonalIZO);
	}

	@Override
	public List<PersonalIZO> getGestoresActivos(Solicitante gestorFilter) {
		return this.personalIZODao.getGestoresActivos(gestorFilter);
	}

	@Override
	public PersonalIZO getDatosGestor(PersonalIZO personalIZO) {
		return this.personalIZODao.getDatosGestor(personalIZO);
	}

	@Override
	public List<PersonalIZO> getAsignadoresPlanificacion(PersonalIZO filterPersonalIZO) {
		return this.personalIZODao.getAsignadoresPlanificacion(filterPersonalIZO);
	}

}
