package aa79b.util.common;

import java.io.Serializable;

public enum EstadoAuditoriaEnum implements Serializable {
	SIN_ENVIAR(1, "label.sinEnviar"), ENVIADA(2, "label.enviada"), CONFIRMADA(3, "label.confirmada");

	private int value;
	private String label;

	private EstadoAuditoriaEnum(int value, String label) {
		this.value = value;
		this.label = label;

	}

	public int getValue() {
		return this.value;
	}

	public String getLabel() {
		return this.label;
	}

}
