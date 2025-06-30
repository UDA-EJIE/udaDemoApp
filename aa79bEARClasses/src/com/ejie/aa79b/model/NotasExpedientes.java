package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.i18n.LocaleContextHolder;

import com.ejie.aa79b.utils.DateUtils;
import com.ejie.x38.serialization.JsonDateDeserializer;
import com.ejie.x38.serialization.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class NotasExpedientes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idNota;
	private Integer anyo;
	private Integer numExp;
	private String usuario;
	private Date fechaNota;
	private String horaNota;
	private String nota;

	public Integer getIdNota() {
		return idNota;
	}

	public void setIdNota(Integer idNota) {
		this.idNota = idNota;
	}

	public Integer getAnyo() {
		return anyo;
	}

	public void setAnyo(Integer anyo) {
		this.anyo = anyo;
	}

	public Integer getNumExp() {
		return numExp;
	}

	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaNota() {
		return fechaNota;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaNota(Date fechaNota) {
		this.fechaNota = fechaNota;
	}

	public String getHoraNota() {
		return horaNota;
	}

	public void setHoraNota(String horaNota) {
		this.horaNota = horaNota;
	}

	/**
	 * Method 'getFechaHoraNota'.
	 *
	 * @return String
	 */
	public String getFechaHoraNota() {
		return DateUtils.obtFechaHoraFormateada(this.fechaNota, this.horaNota, LocaleContextHolder.getLocale());
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

}
