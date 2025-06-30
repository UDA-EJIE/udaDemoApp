/**
 *
 */
package com.ejie.aa79b.service;

import com.ejie.aa79b.model.EntidadesLote;

/**
 * @author eaguirresarobe
 *
 */
public interface EntidadesLoteService extends GenericoService<EntidadesLote>  {

	/**
	 *
	 * @param entidadesgrupostrabajo EntidadesLote
	 */
	void removeWhereID(EntidadesLote entidadesgrupostrabajo);

	/**
	 *
	 * @param bean EntidadesLote
	 * @return EntidadesLote
	 */
	EntidadesLote obtenerComentarioEntidad(EntidadesLote bean);

	/**
	 *
	 * @param bean EntidadesLote
	 * @return Integer
	 */
	Integer anyadirComentarioEntidad(EntidadesLote bean);

}
