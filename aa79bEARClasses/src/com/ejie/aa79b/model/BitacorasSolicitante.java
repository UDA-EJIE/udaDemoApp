package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * BitacorasSolicitante
 * @author mrodriguez
 */
public class BitacorasSolicitante implements Serializable {
	
	private static final long serialVersionUID = 6717899915084609792L;
	private List<BitacoraSolicitante> bitacoraSolicitante = new ArrayList<BitacoraSolicitante>();

	/**
	 * @return the bitacoraSolicitante
	 */
	public List<BitacoraSolicitante> getBitacoraSolicitante() {
		return this.bitacoraSolicitante;
	}

	/**
	 * @param bitacoraSolicitante the bitacoraSolicitante to set
	 */
	public void setBitacoraSolicitante(List<BitacoraSolicitante> bitacoraSolicitante) {
		this.bitacoraSolicitante = bitacoraSolicitante;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ bitacoraSolicitante: ").append(this.bitacoraSolicitante).append(" ]");
		result.append("}");
		return result.toString();
	}

}
