package com.ejie.aa79b.dao;

import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.NotasExpedientes;

/**
 *
 * @author caortiz
 *
 */
public interface NotaExpedienteDao extends GenericoDao<NotasExpedientes> {

	Boolean tieneNotasExpediente(Expediente expediente);

}
