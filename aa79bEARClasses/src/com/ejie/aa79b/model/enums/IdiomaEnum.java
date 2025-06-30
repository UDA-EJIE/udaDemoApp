package com.ejie.aa79b.model.enums;

/**
 * 
 * @author eaguirresarobe
 *
 */
public enum IdiomaEnum {
	CASTELLANO(1L, "es", "label.castellano"), EUSKERA(2L, "eu", "label.euskera");

	private Long value;
	private String code;
	private String label;

	private IdiomaEnum(Long value, String code, String label) {
		this.value = value;
		this.code = code;
		this.label = label;
	}

	public Long getValue() {
		return this.value;
	}

	public String getCode() {
		return this.code;
	}

	public String getLabel() {
		return this.label;
	}

	public static String getCodeByValue(Long value, Boolean capitalizeFirstLetter) {
		IdiomaEnum enumItem = IdiomaEnum.getByValue(value);
		return enumItem != null
				? capitalizeFirstLetter
						? (enumItem.getCode().substring(0, 1).toUpperCase() + enumItem.getCode().substring(1))
						: enumItem.getCode()
				: "";
	}

	public static IdiomaEnum getByValue(Long value) {
		for (IdiomaEnum item : IdiomaEnum.values()) {
			if (item.getValue().equals(value)) {
				return item;
			}
		}
		return null;
	}
}
