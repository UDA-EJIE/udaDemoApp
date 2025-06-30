package aa79b.util.common;

import java.io.Serializable;

public enum EstadoFirmaEnum implements Serializable {
	SINFIRMAR(0, "label.estadoFirma.sinFirmar"), FIRMANDO(1, "label.estadoFirma.firmando"), ERROR(2,
			"label.error"), FIRMADO(3, "label.estadoFirma.firmado");

	private int value;
	private String label;

	private EstadoFirmaEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return this.value;
	}

	public String getLabel() {
		return this.label;
	}

	/**
	 * Obtiene un TipoExpedienteEnum por la clave
	 * 
	 * @param key
	 *            la clave
	 * @return TipoExpedienteEnum
	 * 
	 * @author aresua
	 */
	public static EstadoFirmaEnum getByKey(String key) {
		for (EstadoFirmaEnum item : EstadoFirmaEnum.values()) {
			if (key.equals(item.getValue())) {
				return item;
			}
		}
		return null;
	}

}