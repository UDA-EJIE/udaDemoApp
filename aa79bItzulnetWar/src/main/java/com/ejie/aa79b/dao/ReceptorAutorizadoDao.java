package com.ejie.aa79b.dao;

import java.util.List;

import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.ReceptorAutorizado;
import com.ejie.aa79b.model.Solicitante;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.TableRowDto;

public interface ReceptorAutorizadoDao extends GenericoDao<ReceptorAutorizado> {

	Long comprobarSiExiste(ReceptorAutorizado receptorAutorizado);

	Boolean getPermisosUsuario(Solicitante bean);

	Boolean getAccesoExpediente(Expediente bean);

	Object getReceptoresAutorizados(Expediente bean, JQGridRequestDto jqGridRequestDto, boolean startsWith,
			boolean isCount);

	List<TableRowDto<ReceptorAutorizado>> reorderSelectionReceptoresAutorizados(Expediente bean,
			JQGridRequestDto jqGridRequestDto, boolean b);

	void eliminarReceptorAutorizado(ReceptorAutorizado receptorAutorizado);

	/**
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
