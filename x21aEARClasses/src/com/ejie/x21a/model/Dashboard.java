package com.ejie.x21a.model;

import org.hdiv.services.SecureIdentifiable;

public class Dashboard implements SecureIdentifiable<String> {

	private String id;
	
	private String nombre;
	
	private String serializedArray;

	public Dashboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dashboard(String id) {
		super();
		this.id = id;
	}
	
	public Dashboard(String id, String nombre, String serializedArray) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.serializedArray = serializedArray;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSerializedArray() {
		return serializedArray;
	}

	public void setSerializedArray(String serializedArray) {
		this.serializedArray = serializedArray;
	}
	
	
	
	
	
	
}
