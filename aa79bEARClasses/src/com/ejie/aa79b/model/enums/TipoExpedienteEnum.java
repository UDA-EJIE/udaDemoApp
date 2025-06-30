package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum TipoExpedienteEnum implements Serializable {
    INTERPRETACION("I", "label.tipoExpediente.interpretacion"), TRADUCCION("T", "label.traduccion"), REVISION("R",
            "label.revision");

    private String value;
    private String label;

    private TipoExpedienteEnum(String value, String label) {
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
    public static TipoExpedienteEnum getByKey(String key) {
        for (TipoExpedienteEnum item : TipoExpedienteEnum.values()) {
            if (key.equals(item.getValue())) {
                return item;
            }
        }
        return null;
    }

}