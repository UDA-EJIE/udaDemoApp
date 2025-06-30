package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum MesesEnum implements Serializable {
	ENERO("01", "label.mes.enero"), FEBRERO("02", "label.mes.febrero"), MARZO("03", "label.mes.marzo"),
	ABRIL("04", "label.mes.abril"), MAYO("05", "label.mes.mayo"), JUNIO("06", "label.mes.junio"),
	JULIO("07", "label.mes.julio"), AGOSTO("08", "label.mes.agosto"), SEPTIEMBRE("09", "label.mes.septiembre"),
	OCTUBRE("10", "label.mes.octubre"), NOVIEMBRE("11", "label.mes.noviembre"), DICIEMBRE("12", "label.mes.diciembre");

	private String value;
	private String label;

	private MesesEnum(String value, String label) {
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