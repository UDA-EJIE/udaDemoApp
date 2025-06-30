package com.ejie.aa79b.service;

import com.ejie.aa79b.model.Expediente;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * ExpedientesConfidencialesService, 02-ene-2019 12:05:50.
 * 
 * @author mrodriguez
 */
public interface ExpedientesConfidencialesService extends GenericoService<Expediente> {

	/**
	 * Obtiene expedientes confidenciales
	 * 
	 * @param filter
	 *            Expediente
	 * @param dni
	 *            String
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @param isCount
	 *            boolean
	 * @return JQGridResponseDto<Expediente>
	 */
	JQGridResponseDto<Expediente> busquedaexpconf(Expediente filter, String dni, JQGridRequestDto jqGridRequestDto,
			boolean isCount);

	/**
	 * Graba en el registro de acciones la desencriptación de un fichero
	 *
	 * @param idDoc
	 *            Integer
	 * @param anyo
	 *            Long
	 * @param numExp
	 *            Integer
	 */
	void registrarAccionDesencripFich(Integer idDoc, Long anyo, Integer numExp);

}
