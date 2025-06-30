package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum EstadoRequerimientoEnum implements Serializable {
    PENDIENTE(1, "comun.pendiente"), ACEPTADA(2, "comun.aceptado"), RECHAZADA(3, "comun.rechazado");

    private int value;
    private String label;

    private EstadoRequerimientoEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return this.label;
    }

    /**
     * Obtiene un EstadoRequerimientoEnum por la clave
     * 
     * @param key
     *            la clave
     * @return EstadoRequerimientoEnum
     * 
     * @author aresua
     */
    public static EstadoRequerimientoEnum getByKey(String key) {
        for (EstadoRequerimientoEnum item : EstadoRequerimientoEnum.values()) {
            if (key.equals(item.getValue())) {
                return item;
            }
        }
        return null;
    }

}