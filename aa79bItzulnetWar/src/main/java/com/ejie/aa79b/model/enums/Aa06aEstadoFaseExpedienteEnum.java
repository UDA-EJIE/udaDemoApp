package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum Aa06aEstadoFaseExpedienteEnum implements Serializable {

	//AA79B --> ESTADO : 2 , FASE : 2
	PENDIENTE_ESTUDIO(1, "label.estadoFaseExpediente.pdteEstudio"),
	//AA79B --> ESTADO : 2 , FASE : 3
	ESTUDIO_EXPEDIENTE(2, "label.estadoFaseExpediente.estudioExp"),
	//AA79B --> ESTADO : 2 , FASE : 4 && ESTADO : 3 , FASE : 8
	PDTE_TRAM_CLIENTE(3, "label.estadoFaseExpediente.pdteTramitacionCliente"),
	//AA79B --> ESTADO : 3 , FASE : 5 , 6 , 7 , 9 , 10
	EN_CURSO(4, "label.estadoFaseExpediente.enCurso"),
	//AA79B --> ESTADO : 6 , FASE : 13
	CERRADO(5, "label.estadoFaseExpediente.cerrado"),
	//AA79B --> ESTADO : 7 , FASE : 14
	FINALIZADO(6, "label.estadoFaseExpediente.finalizado"),
	//AA79B --> ESTADO : 4 , FASE : 11
	RECHAZADO(7, "label.estadoFaseExpediente.rechazado"),
	//AA79B --> ESTADO : 5 , FASE : 12
	ANULADO(8, "label.estadoFaseExpediente.anulado");
	private int value;
	private String label;

	private Aa06aEstadoFaseExpedienteEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}
	
	public String getLabel(){
		return this.label;
	}

	
}
