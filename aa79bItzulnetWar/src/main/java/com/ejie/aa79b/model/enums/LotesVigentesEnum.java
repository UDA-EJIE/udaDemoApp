package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * @author mrodriguez
 */
public enum LotesVigentesEnum implements Serializable {
	
	SI("1", "comun.si"), NO("0", "comun.no");

    private String value;
    private String label;

    private LotesVigentesEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }
}
