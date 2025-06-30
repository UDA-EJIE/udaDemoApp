package com.ejie.aa79b.dao;

import java.util.List;

import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.Leyenda;
import com.ejie.x38.dto.JQGridRequestDto;

/**
 * ExpedientesCambioEstadoDao, 13-feb-2019 14:32:42.
 * 
 * @author mrodriguez
 */
public interface ExpedientesCambioEstadoDao extends GenericoDao<Expediente> {

	/**
	 * Obtiene un listado de expedientes
	 * 
	 * @param bean
	 *            Expediente
	 * @param jqGridRequestDto
	 *            JQGridRequestDto
	 * @param startsWith
	 *            boolean
	 * @param isCount
	 *            boolean
	 * @return Object
	 */
	Object findExpedientesCambioEstado(Expediente bean, JQGridRequestDto jqGridRequestDto, boolean startsWith,
			boolean isCount);

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
	 * Obtiene los datos de la leyenda para el estado/fase del expediente
	 * seleccionado
	 * 
	 * @param bean
	 *            Leyenda
	 * @return List<Leyenda>
	 */
	List<Leyenda> findConfLeyenda(Leyenda bean);

}
