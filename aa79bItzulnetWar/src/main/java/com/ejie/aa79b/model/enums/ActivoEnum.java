package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author aresua
 *
 */
public enum ActivoEnum implements Serializable {
    SI("S", "comun.si"), NO("N", "comun.no");

    private String value;
    private String label;

    private ActivoEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

}