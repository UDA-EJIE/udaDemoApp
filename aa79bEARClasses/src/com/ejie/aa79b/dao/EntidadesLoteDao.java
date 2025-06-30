/**
 *
 */
package com.ejie.aa79b.dao;

import com.ejie.aa79b.model.EntidadesLote;

/**
 * @author eaguirresarobe
 *
 */
public interface EntidadesLoteDao extends GenericoDao<EntidadesLote> {

	/**
	 *
	 * @param entidadesLote EntidadesLote
	 */
	void removeWhereID(EntidadesLote entidadesLote);

	/**
	 *
	 * @param bean EntidadesLote
	 * @return Boolean
	 */
	Boolean entidadTieneComentario(EntidadesLote bean);

	/**
	 *
	 * @param bean EntidadesLote
	 * @return Integer
	 */
	Integer anyadirComentarioEntidad(EntidadesLote bean);

	/**
	 *
	 * @param bean EntidadesLote
	 * @return Integer
	 */
	Integer modificarComentarioEntidad(EntidadesLote bean);

	/**
	 *
	 * @param bean EntidadesLote
	 * @return EntidadesLote
	 */
	EntidadesLote findComentarioEntidad(EntidadesLote bean);


}
