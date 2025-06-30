package com.ejie.aa79b.model;

import java.io.Serializable;

import com.ejie.aa79b.model.webservices.Aa79bGestorExpediente;

/**
 * CabeceraExpediente
 * 
 * @author mrodriguez
 */
public class CabeceraExpediente extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = 4647013305128604269L;
	private Long anyo;
	private Integer numExp;
	private String idTipoExpediente;
	private String titulo;
	private String idiomaDescEu;
	private String idiomaDescEs;
	private Aa79bGestorExpediente gestor = new Aa79bGestorExpediente();

	private String anyoNumExp;

	/**
	 * @return the anyo
	 */
	public Long getAnyo() {
		return this.anyo;
	}

	/**
	 * @param anyo the anyo to set
	 */
	public void setAnyo(Long anyo) {
		this.anyo = anyo;
	}

	/**
	 * @return the numExp
	 */
	public Integer getNumExp() {
		return this.numExp;
	}

	/**
	 * @param numExp the numExp to set
	 */
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	/**
	 * @return the idTipoExpediente
	 */
	public String getIdTipoExpediente() {
		return this.idTipoExpediente;
	}

	/**
	 * @param idTipoExpediente the idTipoExpediente to set
	 */
	public void setIdTipoExpediente(String idTipoExpediente) {
		this.idTipoExpediente = idTipoExpediente;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return this.titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIdiomaDescEu() {
		return idiomaDescEu;
	}

	public void setIdiomaDescEu(String idiomaDescEu) {
		this.idiomaDescEu = idiomaDescEu;
	}

	public String getIdiomaDescEs() {
		return idiomaDescEs;
	}

	public void setIdiomaDescEs(String idiomaDescEs) {
		this.idiomaDescEs = idiomaDescEs;
	}

	/**
	 * @return the gestor
	 */
	public Aa79bGestorExpediente getGestor() {
		return this.gestor;
	}

	/**
	 * @param gestor the gestor to set
	 */
	public void setGestor(Aa79bGestorExpediente gestor) {
		this.gestor = gestor;
	}

	/**
	 * @return the anyoNumExp
	 */
	public String getAnyoNumExp() {
		this.setAnyoNumExp(null);
		if (this.anyo != null && this.numExp != null) {
			this.setAnyoNumExp(String.valueOf(this.anyo).substring(2) + "/" + String.format("%06d", this.numExp));
		}

		return this.anyoNumExp;
	}

	/**
	 * @param anyoNumExp the anyoNumExp to set
	 */
	public void setAnyoNumExp(String anyoNumExp) {
		this.anyoNumExp = anyoNumExp;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ anyo: ").append(this.anyo).append(" ]");
		result.append(", [ numExp: ").append(this.numExp).append(" ]");
		result.append(", [ idTipoExpediente: ").append(this.idTipoExpediente).append(" ]");
		result.append(", [ titulo: ").append(this.titulo).append(" ]");
		result.append(", [ gestor: ").append(this.gestor).append(" ]");
		result.append("}");
		return result.toString();
	}

}
