package com.ejie.aa79b.model.webservices;

import com.ejie.aa79b.model.DatosSalidaWS;

/**
 * Aa79bSalidaTarea
 * 
 * @author aresua
 */
public class Aa79bSalidaAsociacionAlbaran extends DatosSalidaWS implements java.io.Serializable {

	private static final long serialVersionUID = 6914946314137644266L;
	private Boolean estadoCorrecto;

	public Boolean getEstadoCorrecto() {
		return estadoCorrecto;
	}

	public void setEstadoCorrecto(Boolean estadoCorrecto) {
		this.estadoCorrecto = estadoCorrecto;
	}

}
