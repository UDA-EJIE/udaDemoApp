package com.ejie.aa79b.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TareaIdaba implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String entidad;
	private Long numExpediente;
	private String tituloExpediente;
	private String indPrioritario;
	// Descripcion relevancia
	private String descReles;
	private String descReleu;
	// DescripcionDocumento
	private String descDoces;
	private String descDoceu;
	// Entidad
	private String descEntidades;
	private String descEntidadeu;
	private String nombreTradCompleto;
	private ObservacionesTareas observaciones;

	/**
	 * 
	 * @param entidad
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	/**
	 * 
	 * @return entidad
	 */
	public String getEntidad() {
		return entidad;
	}

	/**
	 * 
	 * @param tituloExpediente
	 */
	public void setTituloExpediente(String tituloExpediente) {
		this.tituloExpediente = tituloExpediente;
	}

	/**
	 * 
	 * @return tituloExpediente
	 */
	public String getTituloExpediente() {
		return tituloExpediente;
	}

	/**
	 * 
	 * @param numExpediente
	 */
	public void setNumExpediente(Long numExpediente) {
		this.numExpediente = numExpediente;
	}

	/**
	 * 
	 * @return numExpediente
	 */
	public Long getNumExpediente() {
		return numExpediente;
	}

	/**
	 * 
	 * @param observaciones
	 */
	public void setObservaciones(ObservacionesTareas observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * 
	 * @return observaciones
	 */
	public ObservacionesTareas getObservaciones() {
		return observaciones;
	}

	/**
	 * 
	 * @param indPrioritario
	 */
	public void setIndPrioritario(String indPrioritario) {
		this.indPrioritario = indPrioritario;
	}

	/**
	 * 
	 * @return
	 */
	public String getIndPrioritario() {
		return indPrioritario;
	}

	/**
	 * 
	 * @return date
	 */
	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return dateFormat.format(new Date());
	}

	/**
	 * 
	 * @param descReles
	 */
	public void setDescReles(String descReles) {
		this.descReles = descReles;
	}

	/**
	 * 
	 * @param descReleu
	 */
	public void setDescReleu(String descReleu) {
		this.descReleu = descReleu;
	}

	/**
	 * 
	 * @return descReles
	 */
	public String getDescReles() {
		return descReles;
	}

	/**
	 * 
	 * @return descReleu
	 */
	public String getDescReleu() {
		return descReleu;
	}

	/**
	 * 
	 * @param descDoces
	 */
	public void setDescDoces(String descDoces) {
		this.descDoces = descDoces;
	}

	/**
	 * 
	 * @param descDoceu
	 */
	public void setDescDoceu(String descDoceu) {
		this.descDoceu = descDoceu;
	}

	/**
	 * 
	 * @return descDoces
	 */
	public String getDescDoces() {
		return descDoces;
	}

	/**
	 * 
	 * @return descDoceu
	 */
	public String getDescDoceu() {
		return descDoceu;
	}

	/**
	 * 
	 * @param nombreTradCompleto
	 */
	public void setNombreTradCompleto(String nombreTradCompleto) {
		this.nombreTradCompleto = nombreTradCompleto;
	}

	/**
	 * 
	 * @return nombreCompleto traductor
	 */
	public String getNombreTradCompleto() {
		return nombreTradCompleto;
	}

	/**
	 * 
	 * @param descEntidades
	 */
	public void setDescEntidades(String descEntidades) {
		this.descEntidades = descEntidades;
	}

	/**
	 * 
	 * @param descEntidadeu
	 */
	public void setDescEntidadeu(String descEntidadeu) {
		this.descEntidadeu = descEntidadeu;
	}

	/**
	 * 
	 * @return descEntidades
	 */
	public String getDescEntidades() {
		return descEntidades;
	}

	/**
	 * 
	 * @return descEntidadeu
	 */
	public String getDescEntidadeu() {
		return descEntidadeu;
	}
}
