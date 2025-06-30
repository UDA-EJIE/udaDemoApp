package com.ejie.aa79b.common;

import java.io.Serializable;

/**
 * 
 * @author aresua
 *
 */
public enum BdTablesEnum implements Serializable {
	LOTES(" AA79B29S01 "), CUANTIA_INTER(" AA79B35S01 "), CUANTIA_REV(" AA79B34S01 "), CUANTIA_TRAD(
            " AA79B33S01 "), IVA_PRECIOS(
                    " AA79B31S01 "), TARIF_EXP_TRAD_REV(" AA79B32S01 "), ORDEN_PRECIOS(" AA79B30S01 ");


	private String value;

	private BdTablesEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}