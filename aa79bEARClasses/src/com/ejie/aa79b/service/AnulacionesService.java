package com.ejie.aa79b.service;

import java.util.List;

import com.ejie.aa79b.model.AnulacionExpediente;
import com.ejie.aa79b.model.Expediente;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

public interface AnulacionesService extends GenericoService<Expediente> {

	/**
	 * Obtiene el listado de expedientes susceptibles de anulación
	 * 
	 * @param filter           Expediente
	 * @param jqGridRequestDto JQGridRequestDto
	 * @param isCount          boolean
	 * @return JQGridResponseDto<Expediente>
	 */
	JQGridResponseDto<Expediente> busquedaexpaanular(Expediente filter, JQGridRequestDto jqGridRequestDto,
			boolean isCount);

	/**
	 * Anula los expedientes indicados
	 * 
	 * @param anulacionExpediente AnulacionExpediente
	 * @param listExpedientes     List<Expediente>
	 */
	void anularExpedientes(AnulacionExpediente anulacionExpediente, List<Expediente> listExpedientes);

	/**
	 * Anula automaticamente un expediente
	 * 
	 * @param anyo         Long
	 * @param numExp       Integer
	 * @param obsvAnulacio String
	 */
	void anularExpAutomaticamente(Long anyo, Integer numExp, String obsvAnulacio);

	/**
	 * Anula el expediente indicado
	 * 
	 * @param anulacionExpediente AnulacionExpediente
	 * @param expediente          Expediente
	 */
	void anularExpediente(AnulacionExpediente anulacionExpediente, Expediente expediente);

	/**
	 * @param expediente Expediente
	 * @return int
	 */
	int enviarEmailTareasAceptadasPptoRechazado(Expediente expediente);

}
