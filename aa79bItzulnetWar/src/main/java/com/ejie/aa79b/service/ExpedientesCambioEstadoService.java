package com.ejie.aa79b.service;

import java.util.List;

import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.Leyenda;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

/**
 * ExpedientesCambioEstadoService
 * 
 * @author mrodriguez
 */
public interface ExpedientesCambioEstadoService extends GenericoService<Expediente> {

	/**
	 * Obtiene un listado de expedientes
	 * 
	 * @param bean
	 *            Expediente
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @return JQGridResponseDto<Expediente>
	 */
	JQGridResponseDto<Expediente> findExpedientesCambioEstado(Expediente bean, JQGridRequestDto jqGridRequestDto);

	/**
	 * Comprueba si hay facturas asociadas al expediente y que no estén anuladas
	 * 
	 * @param anyo
	 *            Long
	 * @param numExp
	 *            Integer
	 * @return long, Devuelve (0/n): 0 - Si el expediente no tiene facturas
	 *         asociadas; n - Si el expediente tiene facturas asociadas y que no
	 *         estén anuladas
	 */
	long comprobarExpFacturado(Long anyo, Integer numExp);

	/**
	 * Cambia de estado el expediente seleccionado
	 * 
	 * @param expediente
	 *            Expediente
	 */
	void cambiarEstadoExp(Expediente expediente);

	/**
	 * Obtiene los datos de la leyenda para el estado/fase del expediente
	 * seleccionado
	 * 
	 * @param bean
	 *            Leyenda
	 * @return List<Leyenda>
	 */
	List<Leyenda> findConfLeyenda(Leyenda bean);

}
