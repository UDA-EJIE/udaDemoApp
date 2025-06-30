package com.ejie.aa79b.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ejie.aa79b.dao.GestorExpedienteDao;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.GestorExpediente;

public class ExpedienteUtils extends SpringBeanAutowiringSupport {

	@Autowired()
	private GestorExpedienteDao gestorExpedienteDao;

	public ExpedienteUtils() {
		// Constructor
	}

	/**
	 * Obtiene el email del gestor del expediente
	 * 
	 * @param expediente
	 *            Expediente
	 * @return String
	 */
	public String obtenerEmailContacto(Expediente expediente) {
		String email = null;
		GestorExpediente gestorExpediente = this.gestorExpedienteDao.getDatosContacto(expediente);

		if (gestorExpediente != null && gestorExpediente.getSolicitante() != null
				&& gestorExpediente.getSolicitante().getDatosContacto() != null) {
			email = gestorExpediente.getSolicitante().getDatosContacto().getEmail031();
		}

		return email;
	}

}
