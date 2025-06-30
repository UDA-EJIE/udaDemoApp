package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author mrodriguez
 *
 */
public enum PerfilEnum implements Serializable {
	
	TECNICO_GESTOR(1, "label.tecnicoGestor"), ASIGNADOR(2, "label.asignador"), TRADUCTOR(3, "label.traductor"), INTERPRETE(4, "label.interprete");

	private int value;
	private String label;

	private PerfilEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return this.value;
	}
	
	public String getLabel() {
		return this.label;
	}

}
