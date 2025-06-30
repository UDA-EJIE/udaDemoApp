package com.ejie.aa79b.model;

import java.io.Serializable;

public class HorasEstimadas implements Serializable {

	private static final long serialVersionUID = -3130583903146100567L;

	private String horasIZO;
	private String horasProveedor;

	/**
	 * @return the horasIZO
	 */
	public String getHorasIZO() {
		return horasIZO;
	}

	/**
	 * @param horasIZO
	 *            the horasIZO to set
	 */
	public void setHorasIZO(String horasIZO) {
		this.horasIZO = horasIZO;
	}

	/**
	 * @return the horasProveedor
	 */
	public String getHorasProveedor() {
		return horasProveedor;
	}

	/**
	 * @param horasProveedor
	 *            the horasProveedor to set
	 */
	public void setHorasProveedor(String horasProveedor) {
		this.horasProveedor = horasProveedor;
	}

	/**
	 * Intended only for logging and debugging.
	 * 
	 * Here, the contents of every main field are placed into the result.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append(" [ horasIZO: ").append(this.horasIZO).append(" ]");
		result.append(", [ horasProveedor: ").append(this.horasProveedor).append(" ]");
		result.append("}");
		return result.toString();
	}

}
