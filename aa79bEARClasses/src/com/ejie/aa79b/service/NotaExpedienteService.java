package com.ejie.aa79b.service;

import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.NotasExpedientes;

/**
 *
 * @author caortiz
 *
 */
public interface NotaExpedienteService extends GenericoService<NotasExpedientes> {

	Boolean tieneNotasExpediente(Expediente expediente);

}
