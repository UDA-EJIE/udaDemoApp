package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author eaguirresarobe
 *
 */
public class OrigenTareaNoConformidad implements Serializable {

	private static final long serialVersionUID = -2998394593265178923L;
	protected BigDecimal idTareaRelacionada;
	private TiposTarea tipoTarea = new TiposTarea();

	/**
	 * @return the idTareaRelacionada
	 */
	public BigDecimal getIdTareaRelacionada() {
		return idTareaRelacionada;
	}

	/**
	 * @param idTareaRelacionada the idTareaRelacionada to set
	 */
	public void setIdTareaRelacionada(BigDecimal idTareaRelacionada) {
		this.idTareaRelacionada = idTareaRelacionada;
	}

	/**
	 * @return the tipoTarea
	 */
	public TiposTarea getTipoTarea() {
		return tipoTarea;
	}

	/**
	 * @param tipoTarea the tipoTarea to set
	 */
	public void setTipoTarea(TiposTarea tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	@Override
	public String toString() {
		return "OrigenTareaNoConformidad [idTareaRelacionada=" + idTareaRelacionada + ", tipoTarea=" + tipoTarea + "]";
	}

}
