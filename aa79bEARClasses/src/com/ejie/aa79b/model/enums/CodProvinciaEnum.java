package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum CodProvinciaEnum implements Serializable {
	BIZKAIA(48), GIPUZKOA(20), ARABA(01);

	private Integer value;

	private CodProvinciaEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

}
