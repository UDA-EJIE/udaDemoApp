/**
 *
 */
package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * @author eaguirresarobe
 *
 */
public enum ExtensionFicheroEnum implements Serializable {

	XLIFF("xliff"), SDDLXLIFF("sdlxliff"), TMX("tmx");

	private String value;

	/**
	 *
	 * @param value String
	 */
	private ExtensionFicheroEnum(String value) {
		this.value = value;
	}

	/**
	 *
	 * @return String
	 */
	public String getValue() {
		return this.value;
	}

}
