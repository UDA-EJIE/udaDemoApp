package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author eaguirresarobe
 *
 */
public enum EstadoEnvioEmailEnum implements Serializable {

	OK(1), SIN_DESTINATARIOS(-1), ERROR(-2);

	private int value;

	/**
	 * 
	 * @param value int
	 */
	private EstadoEnvioEmailEnum(int value) {
		this.value = value;
	}

	/**
	 * 
	 * @return int
	 */
	public int getValue() {
		return this.value;
	}

}
