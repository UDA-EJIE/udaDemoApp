package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum BloqueoEnum implements Serializable {
    ABIERTO("N"), BLOQUEADO("S");

    private String value;

    private BloqueoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}