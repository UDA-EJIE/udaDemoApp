package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

/**
 * Aa79bExpedienteRelacionado
 */
public class Aa79bExpedienteRelacionado implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long anyo;
    private Integer numExp;
    private String anyoNumExp;
    private Aa79bDescripcionIdioma tipoExpediente;
    private String titulo;
    private Long fechaSolicitud;
    private Long fechaEntrega;
    private String dniGestor;

    /**
     * @return the anyo
     */
    public Long getAnyo() {
        return this.anyo;
    }

    /**
     * @param anyo
     *            the anyo to set
     */
    public void setAnyo(Long anyo) {
        this.anyo = anyo;
    }

    /**
     * @return the numExp
     */
    public Integer getNumExp() {
        return this.numExp;
    }

    /**
     * @param numExp
     *            the numExp to set
     */
    public void setNumExp(Integer numExp) {
        this.numExp = numExp;
    }
    
    /**
	 * @return the anyoNumExp
	 */
	public String getAnyoNumExp() {
		this.setAnyoNumExp(null); 
		if (this.anyo != null && this.numExp != null) {
			this.setAnyoNumExp(String.valueOf(this.anyo).substring(2) + "/" + String.format("%06d", this.numExp));
		}
		
		return this.anyoNumExp;
	}

	/**
	 * @param anyoNumExp the anyoNumExp to set
	 */
	public void setAnyoNumExp(String anyoNumExp) {
		this.anyoNumExp = anyoNumExp;
	}

    /**
     * @return the tipoExpediente
     */
    public Aa79bDescripcionIdioma getTipoExpediente() {
        return this.tipoExpediente;
    }

    /**
     * @param tipoExpediente
     *            the tipoExpediente to set
     */
    public void setTipoExpediente(Aa79bDescripcionIdioma tipoExpediente) {
        this.tipoExpediente = tipoExpediente;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * @param titulo
     *            the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the fechaSolicitud
     */
    public Long getFechaSolicitud() {
        return this.fechaSolicitud;
    }

    /**
     * @param fechaSolicitud
     *            the fechaSolicitud to set
     */
    public void setFechaSolicitud(Long fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    /**
     * @return the fechaEntrega
     */
    public Long getFechaEntrega() {
        return this.fechaEntrega;
    }

    /**
     * @param fechaEntrega
     *            the fechaEntrega to set
     */
    public void setFechaEntrega(Long fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /**
	 * @return the dniGestor
	 */
	public String getDniGestor() {
		return dniGestor;
	}

	/**
	 * @param dniGestor the dniGestor to set
	 */
	public void setDniGestor(String dniGestor) {
		this.dniGestor = dniGestor;
	}

	@Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.getClass().getName()).append(" Object { ");
        result.append("[ anyo: ").append(this.anyo).append(" ]");
        result.append(", [ numExp: ").append(this.numExp).append(" ]");
        result.append(", [ anyoNumExp: ").append(this.anyoNumExp).append(" ]");
        result.append(", [ tipoExpediente: ").append(this.tipoExpediente).append(" ]");
        result.append(", [ titulo: ").append(this.titulo).append(" ]");
        result.append(", [ fechaSolicitud: ").append(this.fechaSolicitud).append(" ]");
        result.append(", [ fechaEntrega: ").append(this.fechaEntrega).append(" ]");
        result.append("}");
        return result.toString();
    }

}
