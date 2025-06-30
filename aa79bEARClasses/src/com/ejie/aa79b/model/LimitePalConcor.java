package com.ejie.aa79b.model;

import java.io.Serializable;

public class LimitePalConcor implements Serializable {

	private static final long serialVersionUID = -4439899423301879613L;
	private Integer id;
	private Long limPalConcor;
	private String tiempoExtraGest;

	/**
	 * Method 'LimitePalConcor'.
	 */
	public LimitePalConcor() {
		// Constructor vacío
	}

	public LimitePalConcor(int id) {
		this.id = id;
	}

	public LimitePalConcor(int id, long limPalConcor) {
		this.id = id;
		this.limPalConcor = limPalConcor;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the limPalConcor
	 */
	public Long getLimPalConcor() {
		return limPalConcor;
	}

	/**
	 * @param limPalConcor the limPalConcor to set
	 */
	public void setLimPalConcor(Long limPalConcor) {
		this.limPalConcor = limPalConcor;
	}

	/**
	 * @return the tiempoExtraGest
	 */
	public String getTiempoExtraGest() {
		return tiempoExtraGest;
	}

	/**
	 * @param tiempoExtraGest the tiempoExtraGest to set
	 */
	public void setTiempoExtraGest(String tiempoExtraGest) {
		this.tiempoExtraGest = tiempoExtraGest;
	}

}
