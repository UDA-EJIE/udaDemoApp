package com.ejie.aa79b.service;

import java.util.List;

import com.ejie.aa79b.model.Gestor;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.x38.dto.JQGridRequestDto;

public interface SolicitanteService extends GenericoService<Solicitante> {

	List<Solicitante> findGestoresDeExp(Solicitante solicitanteFilter);

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
	 * Busca los gestores par combo consulta de expedientes
	 *
	 * @param soli
	 *            Solicitante
	 * @return List<Gestor>
	 */
	List<Gestor> findGestoresConsulta(Solicitante soli);

	/**
	 *
	 * @param solicitanteFilter
	 *            Solicitante
	 * @return List<Solicitante>
	 */
	List<Solicitante> findGestoresDeExpCEntidad(Solicitante solicitanteFilter);

	List<Solicitante> findRevisionContactoFacturarConExpedientes(Solicitante solicitanteFilter);

	/**
	 * Busca un contacto por su DNI en la tabla 77
	 *
	 * @param bean
	 * @return
	 */
	Solicitante find77(Solicitante bean);

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

	List<Solicitante> findEntidadCentroOrganico(Solicitante bean, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith);

}
