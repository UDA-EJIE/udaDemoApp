package com.ejie.aa79b.dao;

import java.util.List;

import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Solicitante;

public interface PersonalIZODao extends GenericoDao<PersonalIZO> {
	void guardarFoto(PersonalIZO bean);

	List<PersonalIZO> findAsignadoresSinGrupo(PersonalIZO personalIZO);

	List<PersonalIZO> findTraductoresSinGrupo(PersonalIZO personalIZO);

	List<PersonalIZO> filterTecnicoAsignadoAEstudio(PersonalIZO filterPersonalIZO);

	List<PersonalIZO> findAsignadores(PersonalIZO filterPersonalIZO, String alta);

	List<PersonalIZO> findTraductores(PersonalIZO filterPersonalIZO);

	List<PersonalIZO> getAsignadores(List<Long> gruposTrabajo);

	/**
	 * 
	 * @param filterPersonalIZO
	 *            PersonalIZO
	 * @return List<PersonalIZO>
	 */
	List<PersonalIZO> findTradInterpAsign(PersonalIZO filterPersonalIZO);

	/**
	 * 
	 * @param gestorFilter
	 *            Solicitante
	 * @return List<PersonalIZO>
	 */
	List<PersonalIZO> getGestoresActivos(Solicitante gestorFilter);

	/**
	 * 
	 * @param personalIZO
	 *            PersonalIZO
	 * @return PersonalIZO
	 */
	PersonalIZO getDatosGestor(PersonalIZO personalIZO);

	/**
	 * 
	 * @param filterPersonalIZO
	 *            PersonalIZO
	 * @return List<PersonalIZO>
	 */
	List<PersonalIZO> getAsignadoresPlanificacion(PersonalIZO filterPersonalIZO);
}
