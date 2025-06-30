package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum TipoDocumentoPagoEnum implements Serializable {
	CARTA_PAGO(2), FACTURA(3);

	private int value;

	private TipoDocumentoPagoEnum(int value) {
		this.value = value;
		
	}

	public int getValue() {
		return value;
	}
	
}