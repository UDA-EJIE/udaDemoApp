package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * BitacoraSolicitante
 * @author mrodriguez
 */
public class BitacoraSolicitante implements Serializable {
	
	private static final long serialVersionUID = 7566701774544209783L;
	private Long id;
	private Long anyo;
	private Integer numExp;
	private Long idAccionBitacora;
	private Long fechaAlta;
	private String horaAlta;
	private String usuario;
	
	/* campos adicionales */
	private String descAccionBitacoraEu;
	private String descAccionBitacoraEs;
	private String classStyle;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
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
	 * @return the idAccionBitacora
	 */
	public Long getIdAccionBitacora() {
		return this.idAccionBitacora;
	}
	
	/**
	 * @param idAccionBitacora the idAccionBitacora to set
	 */
	public void setIdAccionBitacora(Long idAccionBitacora) {
		this.idAccionBitacora = idAccionBitacora;
	}
	
	/**
	 * @return the fechaAlta
	 */
	public Long getFechaAlta() {
		return this.fechaAlta;
	}

	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Long fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the horaAlta
	 */
	public String getHoraAlta() {
		return this.horaAlta;
	}
	
	/**
	 * @param horaAlta the horaAlta to set
	 */
	public void setHoraAlta(String horaAlta) {
		this.horaAlta = horaAlta;
	}
	
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return this.usuario;
	}
	
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * @return the descAccionBitacoraEu
	 */
	public String getDescAccionBitacoraEu() {
		return this.descAccionBitacoraEu;
	}

	/**
	 * @param descAccionBitacoraEu the descAccionBitacoraEu to set
	 */
	public void setDescAccionBitacoraEu(String descAccionBitacoraEu) {
		this.descAccionBitacoraEu = descAccionBitacoraEu;
	}

	/**
	 * @return the descAccionBitacoraEs
	 */
	public String getDescAccionBitacoraEs() {
		return this.descAccionBitacoraEs;
	}

	/**
	 * @param descAccionBitacoraEs the descAccionBitacoraEs to set
	 */
	public void setDescAccionBitacoraEs(String descAccionBitacoraEs) {
		this.descAccionBitacoraEs = descAccionBitacoraEs;
	}

	/**
	 * @return the classStyle
	 */
	public String getClassStyle() {
		return this.classStyle;
	}

	/**
	 * @param classStyle the classStyle to set
	 */
	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ id: ").append(this.id).append(" ]");
		result.append(", [ anyo: ").append(this.anyo).append(" ]");
		result.append(", [ numExp: ").append(this.numExp).append(" ]");
		result.append(", [ idAccionBitacora: ").append(this.idAccionBitacora).append(" ]");
		result.append(", [ descAccionBitacoraEu: ").append(this.descAccionBitacoraEu).append(" ]");
		result.append(", [ descAccionBitacoraEs: ").append(this.descAccionBitacoraEs).append(" ]");
		result.append(", [ fechaAlta: ").append(this.fechaAlta).append(" ]");
		result.append(", [ horaAlta: ").append(this.horaAlta).append(" ]");
		result.append(", [ usuario: ").append(this.usuario).append(" ]");
		result.append(", [ classStyle: ").append(this.classStyle).append(" ]");
		result.append("}");
		return result.toString();
	}

}
