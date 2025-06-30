package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author aresua
 *
 */
public enum TipoNoraEnum implements Serializable {
    CERO("0"), UNO("1"), DOS("2"), TRES("3");

    private String value;

    private TipoNoraEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}