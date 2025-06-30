package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.utils.Utils;

public class Aa79bGestorExpediente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Datos del gestor
	private String tipoEntidadGestor;
	private Integer codigoEntidadGestor;
	private String cifGestor;
	private String descEntidadEsGestor;
	private String descEntidadEuGestor;
	private String descAmpEntidadEsGestor;
	private String descAmpEntidadEuGestor;
	private String preDniGestor;
	private String dniGestor;
	private String sufDniGestor;
	private String nombreGestor;
	private String apellido1Gestor;
	private String apellido2Gestor;
	private String indVIPGestor;
	//Datos del representante
	private String preDniRepresentante;
	private String dniRepresentante;
	private String sufDniRepresentante;
	private String nombreRepresentante;
	private String apellido1Representante;
	private String apellido2Representante;
	//Campos adicionales
	private String nombreApellidosGestor;
	private String nombreApellidosRepresentante;
	private String dniCompletoGestor;
	private String dniCompletoRepresentante;
	
	/**
	 * @return the tipoEntidadGestor
	 */
	public String getTipoEntidadGestor() {
		return this.tipoEntidadGestor;
	}

	/**
	 * @param tipoEntidadGestor the tipoEntidadGestor to set
	 */
	public void setTipoEntidadGestor(String tipoEntidadGestor) {
		this.tipoEntidadGestor = tipoEntidadGestor;
	}

	/**
	 * @return the codigoEntidadGestor
	 */
	public Integer getCodigoEntidadGestor() {
		return this.codigoEntidadGestor;
	}

	/**
	 * @param codigoEntidadGestor the codigoEntidadGestor to set
	 */
	public void setCodigoEntidadGestor(Integer codigoEntidadGestor) {
		this.codigoEntidadGestor = codigoEntidadGestor;
	}

	/**
	 * @return the cifGestor
	 */
	public String getCifGestor() {
		return this.cifGestor;
	}

	/**
	 * @param cifGestor the cifGestor to set
	 */
	public void setCifGestor(String cifGestor) {
		this.cifGestor = cifGestor;
	}

	/**
	 * @return the descEntidadEsGestor
	 */
	public String getDescEntidadEsGestor() {
		return this.descEntidadEsGestor;
	}

	/**
	 * @param descEntidadEsGestor the descEntidadEsGestor to set
	 */
	public void setDescEntidadEsGestor(String descEntidadEsGestor) {
		this.descEntidadEsGestor = descEntidadEsGestor;
	}

	/**
	 * @return the descEntidadEuGestor
	 */
	public String getDescEntidadEuGestor() {
		return this.descEntidadEuGestor;
	}

	/**
	 * @param descEntidadEuGestor the descEntidadEuGestor to set
	 */
	public void setDescEntidadEuGestor(String descEntidadEuGestor) {
		this.descEntidadEuGestor = descEntidadEuGestor;
	}

	/**
	 * @return the descAmpEntidadEsGestor
	 */
	public String getDescAmpEntidadEsGestor() {
		return this.descAmpEntidadEsGestor;
	}

	/**
	 * @param descAmpEntidadEsGestor the descAmpEntidadEsGestor to set
	 */
	public void setDescAmpEntidadEsGestor(String descAmpEntidadEsGestor) {
		this.descAmpEntidadEsGestor = descAmpEntidadEsGestor;
	}

	/**
	 * @return the descAmpEntidadEuGestor
	 */
	public String getDescAmpEntidadEuGestor() {
		return this.descAmpEntidadEuGestor;
	}

	/**
	 * @param descAmpEntidadEuGestor the descAmpEntidadEuGestor to set
	 */
	public void setDescAmpEntidadEuGestor(String descAmpEntidadEuGestor) {
		this.descAmpEntidadEuGestor = descAmpEntidadEuGestor;
	}

	/**
	 * @return the preDniGestor
	 */
	public String getPreDniGestor() {
		return this.preDniGestor;
	}

	/**
	 * @param preDniGestor the preDniGestor to set
	 */
	public void setPreDniGestor(String preDniGestor) {
		this.preDniGestor = preDniGestor;
	}

	/**
	 * @return the dniGestor
	 */
	public String getDniGestor() {
		return this.dniGestor;
	}

	/**
	 * @param dniGestor the dniGestor to set
	 */
	public void setDniGestor(String dniGestor) {
		this.dniGestor = dniGestor;
	}

	/**
	 * @return the sufDniGestor
	 */
	public String getSufDniGestor() {
		return this.sufDniGestor;
	}

	/**
	 * @param sufDniGestor the sufDniGestor to set
	 */
	public void setSufDniGestor(String sufDniGestor) {
		this.sufDniGestor = sufDniGestor;
	}

	/**
	 * @return the nombreGestor
	 */
	public String getNombreGestor() {
		return this.nombreGestor;
	}

	/**
	 * @param nombreGestor the nombreGestor to set
	 */
	public void setNombreGestor(String nombreGestor) {
		this.nombreGestor = nombreGestor;
	}

	/**
	 * @return the apellido1Gestor
	 */
	public String getApellido1Gestor() {
		return this.apellido1Gestor;
	}

	/**
	 * @param apellido1Gestor the apellido1Gestor to set
	 */
	public void setApellido1Gestor(String apellido1Gestor) {
		this.apellido1Gestor = apellido1Gestor;
	}

	/**
	 * @return the apellido2Gestor
	 */
	public String getApellido2Gestor() {
		return this.apellido2Gestor;
	}

	/**
	 * @param apellido2Gestor the apellido2Gestor to set
	 */
	public void setApellido2Gestor(String apellido2Gestor) {
		this.apellido2Gestor = apellido2Gestor;
	}

	/**
	 * @return the indVIPGestor
	 */
	public String getIndVIPGestor() {
		return this.indVIPGestor;
	}

	/**
	 * @param indVIPGestor the indVIPGestor to set
	 */
	public void setIndVIPGestor(String indVIPGestor) {
		this.indVIPGestor = indVIPGestor;
	}

	/**
	 * @return the preDniRepresentante
	 */
	public String getPreDniRepresentante() {
		return this.preDniRepresentante;
	}

	/**
	 * @param preDniRepresentante the preDniRepresentante to set
	 */
	public void setPreDniRepresentante(String preDniRepresentante) {
		this.preDniRepresentante = preDniRepresentante;
	}

	/**
	 * @return the dniRepresentante
	 */
	public String getDniRepresentante() {
		return this.dniRepresentante;
	}

	/**
	 * @param dniRepresentante the dniRepresentante to set
	 */
	public void setDniRepresentante(String dniRepresentante) {
		this.dniRepresentante = dniRepresentante;
	}

	/**
	 * @return the sufDniRepresentante
	 */
	public String getSufDniRepresentante() {
		return this.sufDniRepresentante;
	}

	/**
	 * @param sufDniRepresentante the sufDniRepresentante to set
	 */
	public void setSufDniRepresentante(String sufDniRepresentante) {
		this.sufDniRepresentante = sufDniRepresentante;
	}

	/**
	 * @return the nombreRepresentante
	 */
	public String getNombreRepresentante() {
		return this.nombreRepresentante;
	}

	/**
	 * @param nombreRepresentante the nombreRepresentante to set
	 */
	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}

	/**
	 * @return the apellido1Representante
	 */
	public String getApellido1Representante() {
		return this.apellido1Representante;
	}

	/**
	 * @param apellido1Representante the apellido1Representante to set
	 */
	public void setApellido1Representante(String apellido1Representante) {
		this.apellido1Representante = apellido1Representante;
	}

	/**
	 * @return the apellido2Representante
	 */
	public String getApellido2Representante() {
		return this.apellido2Representante;
	}

	/**
	 * @param apellido2Representante the apellido2Representante to set
	 */
	public void setApellido2Representante(String apellido2Representante) {
		this.apellido2Representante = apellido2Representante;
	}

	/**
	 * @return the dniCompletoGestor
	 */
	public String getDniCompletoGestor() {
		this.setDniCompletoGestor(Utils.getDniCompleto(this.preDniGestor, this.dniGestor, this.sufDniGestor));
		return this.dniCompletoGestor;
	}

	/**
	 * @param dniCompletoGestor the dniCompletoGestor to set
	 */
	public void setDniCompletoGestor(String dniCompletoGestor) {
		this.dniCompletoGestor = dniCompletoGestor;
	}

	/**
	 * @return the dniCompletoRepresentante
	 */
	public String getDniCompletoRepresentante() {
		this.setDniCompletoRepresentante(Utils.getDniCompleto(this.preDniRepresentante, this.dniRepresentante, this.sufDniRepresentante));
		return this.dniCompletoRepresentante;
	}

	/**
	 * @param dniCompletoRepresentante the dniCompletoRepresentante to set
	 */
	public void setDniCompletoRepresentante(String dniCompletoRepresentante) {
		this.dniCompletoRepresentante = dniCompletoRepresentante;
	}

	/**
	 * @return the nombreApellidosGestor
	 */
	public String getNombreApellidosGestor(){
		this.setNombreApellidosGestor(Utils.getNombreApellidos(this.nombreGestor, this.apellido1Gestor, this.apellido2Gestor));
		return this.nombreApellidosGestor;
	}
	
	/**
	 * @param nombreApellidosGestor the nombreApellidosGestor to set
	 */
	public void setNombreApellidosGestor(String nombreApellidosGestor) {
		this.nombreApellidosGestor = nombreApellidosGestor;
	}
	
	/**
	 * @return the nombreApellidosRepresentante
	 */
	public String getNombreApellidosRepresentante() {
		this.setNombreApellidosRepresentante(Utils.getNombreApellidos(this.nombreRepresentante, this.apellido1Representante, this.apellido2Representante));
		return this.nombreApellidosRepresentante;
	}
	
	/**
	 * @param nombreApellidosRepresentante the nombreApellidosRepresentante to set
	 */
	public void setNombreApellidosRepresentante(String nombreApellidosRepresentante) {
		this.nombreApellidosRepresentante = nombreApellidosRepresentante;
	}
	
}
