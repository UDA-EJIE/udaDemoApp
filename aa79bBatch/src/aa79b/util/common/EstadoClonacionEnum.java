package aa79b.util.common;

import java.io.Serializable;

/**
 * 
 * @author javarona
 *
 */
public enum EstadoClonacionEnum implements Serializable {
	FINALIZADO(0), ERROR(1), PENDIENTE(2);

	private int value;

	private EstadoClonacionEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}