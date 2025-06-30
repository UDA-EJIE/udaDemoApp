package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.ConfigPerfilSolicitante;
import com.ejie.aa79b.model.DatosSalidaWS;

/**
 * Aa79bDatosDeclaraciones
 * @author mrodriguez
 */
public class Aa79bDatosDeclaraciones extends DatosSalidaWS implements Serializable {
	
	private static final long serialVersionUID = -3129167956556114817L;
	private ConfigPerfilSolicitante configPerfilSolicitante = new ConfigPerfilSolicitante();

	/**
	 * @return the configPerfilSolicitante
	 */
	public ConfigPerfilSolicitante getConfigPerfilSolicitante() {
		return this.configPerfilSolicitante;
	}

	/**
	 * @param configPerfilSolicitante the configPerfilSolicitante to set
	 */
	public void setConfigPerfilSolicitante(ConfigPerfilSolicitante configPerfilSolicitante) {
		this.configPerfilSolicitante = configPerfilSolicitante;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ configPerfilSolicitante: ").append(this.configPerfilSolicitante).append(" ]");
		result.append("}");
		return result.toString();
	}

}
