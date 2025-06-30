package com.ejie.aa79b.model;

public class Proveedor extends Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String traductor;
	private String interprete;
	
	/**
	 * @return the traductor
	 */
	public String getTraductor() {
		return this.traductor;
	}
	/**
	 * @param traductor the traductor to set
	 */
	public void setTraductor(String traductor) {
		this.traductor = traductor;
	}
	/**
	 * @return the interprete
	 */
	public String getInterprete() {
		return this.interprete;
	}
	/**
	 * @param interprete the interprete to set
	 */
	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}

}
