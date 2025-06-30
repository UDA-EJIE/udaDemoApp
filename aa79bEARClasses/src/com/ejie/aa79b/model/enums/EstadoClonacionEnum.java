package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum EstadoClonacionEnum implements Serializable {
	FINALIZADO(0, "label.estadoFaseExpediente.finalizado"), ERROR(1, "label.error"), PENDIENTE(2, "comun.pendiente");

	private int value;
	private String label;

	private EstadoClonacionEnum(int value, String label) {
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
	public static EstadoClonacionEnum getByKey(String key) {
		for (EstadoClonacionEnum item : EstadoClonacionEnum.values()) {
			if (key.equals(item.getValue())) {
				return item;
			}
		}
		return null;
	}

}