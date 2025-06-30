package com.ejie.aa79b.service;

import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ListaReceptoresAutorizados;
import com.ejie.aa79b.model.ReceptorAutorizado;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;

public interface ReceptorAutorizadoService extends GenericoService<ReceptorAutorizado> {

	ListaReceptoresAutorizados comprobarYGuardar(ListaReceptoresAutorizados listaReceptoresAutorizados);

	Boolean getPermisosUsuario(Solicitante bean);

	Boolean getAccesoExpediente(Expediente bean);

	JQGridResponseDto<ReceptorAutorizado> getReceptoresAutorizados(Expediente bean, JQGridRequestDto jqGridRequestDto,
			boolean startsWith);

	void guardarReceptoresAutorizados(ListaReceptoresAutorizados listaReceptoresAutorizados);

	void eliminarReceptorAutorizado(ReceptorAutorizado receptorAutorizado);

	/**
	 * Count de expedientes de los que el dni(usuario) es receptor autorizado
	 * 
	 * @param dni
	 *            String
	 * @return Integer
	 */
	Integer getReceptorAutorizadoCount(String dni);

	/**
	 * 
	 * @param dni
	 *            String
	 * @param anyo
	 *            Long
	 * @param numExp
	 *            Integer
	 * @return Boolean
	 */
	Boolean esReceptorAutorizadoYAccesoAExpediente(String dni, Long anyo, Integer numExp);

}
