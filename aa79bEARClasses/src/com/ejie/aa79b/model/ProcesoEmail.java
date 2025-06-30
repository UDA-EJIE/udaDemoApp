package com.ejie.aa79b.model;

import com.ejie.aa79b.utils.Utils;

public class ProcesoEmail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idProceso;
	private Integer numEmail;
	private Integer idTipoAviso;
	private Long anyo;
	private Integer numExp;
	private String tituloExp;
	private Long idTarea;
	private Long idTipoTarea;
	private String descTipoTareaEs;
	private String descTipoTareaEu;
	private String dniDestinatario;
	private String emailDestinatario;

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(Integer idProceso) {
		this.idProceso = idProceso;
	}

	public Integer getNumEmail() {
		return numEmail;
	}

	public void setNumEmail(Integer numEmail) {
		this.numEmail = numEmail;
	}

	public int getIdTipoAviso() {
		return idTipoAviso;
	}

	public void setIdTipoAviso(Integer idTipoAviso) {
		this.idTipoAviso = idTipoAviso;
	}

	public Long getAnyo() {
		return anyo;
	}

	public void setAnyo(Long anyo) {
		this.anyo = anyo;
	}

	public Integer getNumExp() {
		return numExp;
	}

	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	public String getAnyoNumExpConcatenado() {
		return Utils.getAnyoNumExpConcatenado(this.anyo, this.numExp);
	}

	public String getTituloExp() {
		return tituloExp;
	}

	public void setTituloExp(String tituloExp) {
		this.tituloExp = tituloExp;
	}

	public Long getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(Long idTarea) {
		this.idTarea = idTarea;
	}

	public Long getIdTipoTarea() {
		return idTipoTarea;
	}

	public void setIdTipoTarea(Long idTipoTarea) {
		this.idTipoTarea = idTipoTarea;
	}

	public String getDescTipoTareaEs() {
		return descTipoTareaEs;
	}

	public void setDescTipoTareaEs(String descTipoTareaEs) {
		this.descTipoTareaEs = descTipoTareaEs;
	}

	public String getDescTipoTareaEu() {
		return descTipoTareaEu;
	}

	public void setDescTipoTareaEu(String descTipoTareaEu) {
		this.descTipoTareaEu = descTipoTareaEu;
	}

	public String getDniDestinatario() {
		return dniDestinatario;
	}

	public void setDniDestinatario(String dniDestinatario) {
		this.dniDestinatario = dniDestinatario;
	}

	public String getEmailDestinatario() {
		return emailDestinatario;
	}

	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

}
