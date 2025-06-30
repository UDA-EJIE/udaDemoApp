package com.ejie.aa79b.dao;

import com.ejie.aa79b.model.LimitePalConcor;

/**
 * 
 * @author eaguirresarobe
 *
 */
public interface LimitePalConcorProvExtDao extends GenericoDao<LimitePalConcor> {

	/**
	 * 
	 * @param limitePalConcor LimitePalConcor
	 * @return Boolean
	 */
	Boolean isLimPalConcorStored(LimitePalConcor limitePalConcor);

	/**
	 * 
	 * @param limitePalConcor LimitePalConcor
	 * @return Integer
	 */
	Integer updateLimPalConcor(LimitePalConcor limitePalConcor);

	/**
	 * 
	 * @param limitePalConcor LimitePalConcor
	 * @return Integer
	 */
	Integer addLimPalConcor(LimitePalConcor limitePalConcor);

	/**
	 * 
	 * @param idDatosBasicos Integer
	 * @return LimitePalConcor
	 */
	LimitePalConcor findLimPalConcor(final Integer idDatosBasicos);
}
