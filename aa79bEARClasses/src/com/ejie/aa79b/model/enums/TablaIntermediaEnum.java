package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum TablaIntermediaEnum implements Serializable {
	TABLA_86(86, "86"), TABLA_87(87, "87"), TABLA_92(92, "92"), TABLA_93(93, "93"), TABLA_96(96, "96");

	private int value;
	private String sValue;

	private TablaIntermediaEnum(int value, String sValue) {
		this.value = value;
		this.sValue = sValue;

	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the sValue
	 */
	public String getsValue() {
		return sValue;
	}

}
