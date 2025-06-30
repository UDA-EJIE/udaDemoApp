package com.ejie.aa79b.dao;

import java.util.List;

import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.x38.dto.JQGridRequestDto;

public interface SolicitanteDao extends GenericoDao<Solicitante> {

	/**
	 *
	 * @param bean
	 *            Solicitante
	 */
	void guardarFoto(Solicitante bean);

	/**
	 *
	 * @param solicitante
	 * @return List<Solicitante>
	 */
	List<Solicitante> findGestoresDeExp(Solicitante solicitante);

	/**
	 * Finds a list of rows in the GestoresRepresentante table.
	 *
	 * @param bean
	 *            Solicitante
	 * @return List<Gestor>
	 */
	List<Gestor> findGestoresRespresentante(Solicitante bean);

	/**
	 * Busca los datos de un gestor y/o coordinador
	 *
	 * @param bean
	 *            Solicitante
	 * @return Solicitante
	 */
	Solicitante findGestorCoordinador(Solicitante bean);

	/**
	 * Busca los gestores en consulta
	 *
	 * @param bean
	 *            Solicitante
	 * @return List<Gestor>
	 */
	List<Gestor> findGestoresConsulta(Solicitante bean);

	/**
	 *
	 * @param solicitanteFilter
	 *            Solicitante
	 * @return List<Solicitante>
	 */
	List<Solicitante> findGestoresDeExpCEntidad(Solicitante solicitanteFilter);

	/**
	 *
	 * @param anyo
	 *            Long
	 * @param numExp
	 *            Integer
	 * @return Solicitante
	 */
	Solicitante getPermisosBopv(Long anyo, Integer numExp);

	/**
	 *
	 * @param solicitanteFilter
	 *            Solicitante
	 * @return List<Solicitante>
	 */
	List<Solicitante> findRevisionContactoFacturarConExpedientes(Solicitante solicitanteFilter);

	/**
	 *
	 * @param bean
	 * @return
	 */
	Solicitante find77(Solicitante bean);

	/**
	 *
	 * @param tipoEnt
	 * @param idEntidad
	 * @param dniContacto
	 * @return
	 */
	int comprobarEntidadYSolicitanteValidos(String tipoEnt, int idEntidad, String dniContacto);

	/**
	 *
	 * @param bean
	 * @return
	 */
	Solicitante findSolicitanteConDatosDireccion(Solicitante bean);

	/**
	 * Obtiene los gestores asociados a expedientes confidenciales.
	 *
	 * @param solicitante
	 *            Solicitante
	 * @return List<Solicitante>
	 */
	List<Solicitante> findGestoresExpConfidenciales(Solicitante solicitante);

	/**
	 * Obtiene los solicitantes asociados a expedientes susceptibles de
	 * anulación
	 *
	 * @param filterSolicitante
	 *            Solicitante
	 * @return List<Solicitante>
	 */
	List<Solicitante> findSolicitantesGestAnul(Solicitante filterSolicitante);

	/**
	 * Obtiene los solicitantes asociados a expedientes no facturados que no
	 * estén en estado "en curso" y fase "pendiente de revisar los datos de
	 * facturación"
	 *
	 * @param solicitanteFilter
	 *            Solicitante
	 * @return List<Solicitante>
	 */
	List<Solicitante> findSolicitanteActDatosFact(Solicitante solicitanteFilter);

	/**
	 * Obtiene los solicitantes a facturar asociados a expedientes no facturados
	 * que no estén en estado "en curso" y fase "pendiente de revisar los datos
	 * de facturación"
	 *
	 * @param solicitanteFilter
	 *            Solicitante
	 * @return List<Solicitante>
	 */
	List<Solicitante> findSolicitanteAFacturarActDatosFact(Solicitante solicitanteFilter);

	/**
	 * Obtiene los gestores asociados a expedientes
	 *
	 * @param bean
	 *            Solicitante
	 * @return List<Solicitante>
	 */
	List<Solicitante> findGestores(Solicitante bean);

	/**
	 *
	 * @param personalIZO
	 *            PersonalIZO
	 * @return PersonalIZO
	 */
	PersonalIZO getDatosGestor(PersonalIZO personalIZO);

	List<Solicitante> findEntidadCentroOrganico(Solicitante bean, JQGridRequestDto jqGridRequestDto, Boolean startsWith);

}
