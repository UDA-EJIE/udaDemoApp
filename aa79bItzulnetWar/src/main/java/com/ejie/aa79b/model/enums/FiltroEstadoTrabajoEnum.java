package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum FiltroEstadoTrabajoEnum implements Serializable {
	TRABAJOS_ABIERTOS("1","comun.trabajo.soloabiertos"),
	TRABAJOS_CERRADOS("2","comun.trabajo.solocerrados");

	private String value;
	private String label;

    private FiltroEstadoTrabajoEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
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
    public static FiltroEstadoTrabajoEnum getByKey(Long key) {
        for (FiltroEstadoTrabajoEnum item : FiltroEstadoTrabajoEnum.values()) {
            if (key.equals(item.getValue())) {
                return item;
            }
        }
        return null;
    }

}