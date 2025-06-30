/**
 * 
 */
package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * @author aresua
 *
 */
public class ResumenGraficas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long total;
	private Long misExp;
	private Long misGrupos;

	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	/**
	 * @return the misExp
	 */
	public Long getMisExp() {
		return misExp;
	}

	/**
	 * @param misExp
	 *            the misExp to set
	 */
	public void setMisExp(Long misExp) {
		this.misExp = misExp;
	}

	/**
	 * @return the misGrupos
	 */
	public Long getMisGrupos() {
		return misGrupos;
	}

	/**
	 * @param misGrupos
	 *            the misGrupos to set
	 */
	public void setMisGrupos(Long misGrupos) {
		this.misGrupos = misGrupos;
	}

}
