package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

public class Aa79bLibroRegistro implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long anyo;
	private Long fechaRegistro;
	private Long id;
	private String matter;
	private Integer numExp;
	private String numRegistro;
	private String telematico;
	
	
	public Long getAnyo() {
		return anyo;
	}
	public void setAnyo(Long anyo) {
		this.anyo = anyo;
	}
	public Long getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Long fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMatter() {
		return matter;
	}
	public void setMatter(String matter) {
		this.matter = matter;
	}
	public Integer getNumExp() {
		return numExp;
	}
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}
	public String getNumRegistro() {
		return numRegistro;
	}
	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}
	public String getTelematico() {
		return telematico;
	}
	public void setTelematico(String telematico) {
		this.telematico = telematico;
	}

	
}
