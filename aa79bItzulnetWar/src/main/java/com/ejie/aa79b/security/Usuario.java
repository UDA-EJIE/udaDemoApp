package com.ejie.aa79b.security;

import com.ejie.x38.security.UserCredentials;

/**
 * @author dlopez2
 *
 */
public class Usuario extends UserCredentials {

    private static final long serialVersionUID = 1L;
    private String ipPuesto;
    private Boolean bloqueado;

    /**
     * @return String
     */
    public String getIpPuesto() {
        return this.ipPuesto;
    }

    @Override
	public String getNif() {
		String nif = super.getNif();
		return nif.replaceFirst ("^0*", "");
	}

	/**
     * @param ipPuesto
     *            String
     */
    public void setIpPuesto(String ipPuesto) {
        this.ipPuesto = ipPuesto;
    }

    public boolean getTecnico() {
        return this.getUserProfiles() != null && this.getUserProfiles().contains("AA79B_TECNICO_GESTOR");
    }

    public Boolean getBloqueado() {
        return this.bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

}