package com.ejie.aa79b.model.enums;

/**
 * Enum para el tipo seccion de una auditoria
 *
 *
 */
public enum TipoSeccionAuditoriaEnum implements java.io.Serializable {
	SECCION_DE_VALORACION("1", "label.seccionValoracion"), SECCION_DE_INFORMACION("2", "label.seccionInformacion"), SECCION_DE_DOCUMENTOS("3", "label.seccionDocumentos");

	private String value;
	private String label;

	private TipoSeccionAuditoriaEnum(String value, String label) {
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