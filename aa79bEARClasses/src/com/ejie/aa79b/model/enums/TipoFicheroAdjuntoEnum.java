package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum TipoFicheroAdjuntoEnum implements Serializable {

	TIPO_TRADUCCION_REVISION(1), TIPO_JUSTIFICANTE(2), TIPO_XLIFF(3), TIPO_BOE_INFORME(4), TIPO_BOE_DOCUMENTO(
			5), TIPO_TRADOS(6), TIPO_FINAL(7);

	private Integer value;

	private TipoFicheroAdjuntoEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

}
