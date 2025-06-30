package aa79b.util.common;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum EstadoRequerimientoEnum implements Serializable {
	PENDIENTE(1), ACEPTADA(2), RECHAZADA(3);

	private int value;

	private EstadoRequerimientoEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}