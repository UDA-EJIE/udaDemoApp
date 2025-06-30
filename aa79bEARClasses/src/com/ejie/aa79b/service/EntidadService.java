package com.ejie.aa79b.service;

import java.util.List;

import com.ejie.aa79b.model.Entidad;
import com.ejie.x38.dto.JQGridRequestDto;

public interface EntidadService extends GenericoService<Entidad> {

	List<Entidad> getEntidadesGestorasConExpEnEstado(Entidad entidad, Integer idEstadoExp);

	List<Entidad> getEntidadesGestorasConExpEnEstado(Entidad entidad, Integer idEstadoExp, Integer idFaseExp);

	List<Entidad> getEntidadesGestorasConExpEnEstado(Entidad entidad, Integer idEstadoExp, Integer idFaseExp,
			String entidadAFacturar);

	List<Entidad> getEntidadesConExpARelacionar(Entidad entidad);

	List<Entidad> getEntidadesReceptores(Entidad entidad);

	List<Entidad> getEntidadesSolicitantes(Entidad entidad);

	List<Entidad> getEntidadesConContactosSolicitantes(Entidad entidad);

	List<Entidad> findAllLikeGrupoTrabajo(Entidad bean, JQGridRequestDto jqGridRequestDto, Boolean startsWith);

	/**
	 * 
	 * @param entidad
	 *            Entidad
	 * @return Entidad
	 */
	Entidad obtenerDatosConfeccionarFactura(Entidad entidad);

	/**
	 * Obtiene las entidades asociadas a la gestion de anulaciones
	 * 
	 * @param entidad
	 *            Entidad
	 * @return List<Entidad>
	 */
	List<Entidad> getEntidadesGestAnulacion(Entidad entidad);

	List<Entidad> getEntidadesConContactosSolicitantesConBajas(Entidad entidad);

	/**
	 * Comprueba si una entidad está dada como ALTA
	 * 
	 * @param entidad
	 * @return
	 */
	boolean buscarEntidadAlta(Entidad entidad);

	/**
	 * Obtiene las entidades gestoras asociadas a expedientes confidenciales
	 * 
	 * @param entidad
	 *            Entidad
	 * @return List<Entidad>
	 */
	List<Entidad> getEntidadesGestorasConExpConfidenciales(Entidad entidad, String dni);

	/**
	 * Obtiene las entidades gestoras asociadas a expedientes no facturados que
	 * no estén en estado "en curso" y fase "pendiente de revisar los datos de
	 * facturación"
	 * 
	 * @param entidad
	 *            Entidad
	 * @param entidadAFacturar
	 *            String
	 * @return List<Entidad>
	 */
	List<Entidad> getEntidadesGestorasActDatosFacturacion(Entidad entidad, String entidadAFacturar);

	/**
	 * obtiene entidades con gestores activos
	 * 
	 * @param entidad
	 *            Entidad
	 * @return List<Entidad>
	 */
	List<Entidad> getEntidadesConGestorActivo(Entidad entidad);

}
