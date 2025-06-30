package com.ejie.aa79b.model;

public class PersonalIZO extends Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tecnicoGestor;
	private String asignador;
	private String traductor;
	private String interprete;
	private String gestorBopv;
	private String gestorExpedientesVIP;
	private CalendarioPersonal calendarioPersonal;

	/**
	 * @return the tecnicoGestor
	 */
	public String getTecnicoGestor() {
		return this.tecnicoGestor;
	}

	/**
	 * @param tecnicoGestor
	 *            the tecnicoGestor to set
	 */
	public void setTecnicoGestor(String tecnicoGestor) {
		this.tecnicoGestor = tecnicoGestor;
	}

	/**
	 * @return the asignador
	 */
	public String getAsignador() {
		return this.asignador;
	}

	/**
	 * @param asignador
	 *            the asignador to set
	 */
	public void setAsignador(String asignador) {
		this.asignador = asignador;
	}

	/**
	 * @return the traductor
	 */
	public String getTraductor() {
		return this.traductor;
	}

	/**
	 * @param traductor
	 *            the traductor to set
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
	 * @param interprete
	 *            the interprete to set
	 */
	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}

	/**
	 * @return the gestorBopv
	 */
	public String getGestorBopv() {
		return gestorBopv;
	}

	/**
	 * @param gestorBopv
	 *            the gestorBopv to set
	 */
	public void setGestorBopv(String gestorBopv) {
		this.gestorBopv = gestorBopv;
	}

	/**
	 * @return the gestorExpedientesVIP
	 */
	public String getGestorExpedientesVIP() {
		return gestorExpedientesVIP;
	}

	/**
	 * @param gestorExpedientesVIP
	 *            the gestorExpedientesVIP to set
	 */
	public void setGestorExpedientesVIP(String gestorExpedientesVIP) {
		this.gestorExpedientesVIP = gestorExpedientesVIP;
	}

	/**
	 * @return the calendarioPersonal
	 */
	public CalendarioPersonal getCalendarioPersonal() {
		return this.calendarioPersonal;
	}

	/**
	 * @param calendarioPersonal
	 *            the calendarioPersonal to set
	 */
	public void setCalendarioPersonal(CalendarioPersonal calendarioPersonal) {
		this.calendarioPersonal = calendarioPersonal;
	}

}
