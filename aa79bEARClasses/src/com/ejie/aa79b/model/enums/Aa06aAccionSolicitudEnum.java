package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum Aa06aAccionSolicitudEnum implements Serializable{

	SUBSANAR("S"),
	DOCUMENTOS_CON_VERSION("D");
	
	private String label;
	
	private Aa06aAccionSolicitudEnum(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return this.label;
	}
	
}
