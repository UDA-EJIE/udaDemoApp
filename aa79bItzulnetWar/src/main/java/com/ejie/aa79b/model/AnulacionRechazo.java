package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * Aa79bAnulacionRechazo
 * 
 * @author javarona
 */
public class AnulacionRechazo implements Serializable {

	private static final long serialVersionUID = -4397765206963153544L;
	private String observaciones;
	private String motivoDescEu;
	private String motivoDescEs;

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the motivoDescEu
	 */
	public String getMotivoDescEu() {
		return motivoDescEu;
	}

	/**
	 * @param motivoDescEu the motivoDescEu to set
	 */
	public void setMotivoDescEu(String motivoDescEu) {
		this.motivoDescEu = motivoDescEu;
	}

	/**
	 * @return the motivoDescEs
	 */
	public String getMotivoDescEs() {
		return motivoDescEs;
	}

	/**
	 * @param motivoDescEs the motivoDescEs to set
	 */
	public void setMotivoDescEs(String motivoDescEs) {
		this.motivoDescEs = motivoDescEs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AnulacionRechazo [observaciones=" + observaciones + ", motivoDescEu=" + motivoDescEu + ", motivoDescEs="
				+ motivoDescEs + "]";
	}

}
