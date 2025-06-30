package com.ejie.aa79b.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ejie.aa79b.common.JsonFechaHoraDeserializer;
import com.ejie.aa79b.common.JsonFechaHoraSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Comunicaciones implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id0D4;
	private Long anyo;
	private Integer numExp;
	private String refTramitagune;
	private Date fecha;
	private String tipo;
	private String remitente;
	private String asunto;
	private String mensaje;
	private BigDecimal idFichero0D4;

	private Fichero fichero;
	private Long codigo;
	private String titulo;
	private String extension;
	private String contentType;
	private Long tamano;
	private String encriptado;
	private String rutaPif;
	private String oidFichero;
	private String nombre;

	//Para el filtro expediente o relacionados
	private String tipoBusqueda;

	public Long getId0D4() {
		return this.id0D4;
	}
	public void setId0D4(Long id0d4) {
		this.id0D4 = id0d4;
	}
	public Long getAnyo() {
		return this.anyo;
	}
	public void setAnyo(Long anyo) {
		this.anyo = anyo;
	}
	public Integer getNumExp() {
		return this.numExp;
	}
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}
	public String getRefTramitagune() {
		return this.refTramitagune;
	}
	public void setRefTramitagune(String refTramitagune) {
		this.refTramitagune = refTramitagune;
	}
	@JsonSerialize(using = JsonFechaHoraSerializer.class)
	public Date getFecha() {
		return this.fecha;
	}
	@JsonDeserialize(using = JsonFechaHoraDeserializer.class)
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTipo() {
		return this.tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getRemitente() {
		return this.remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getAsunto() {
		return this.asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getMensaje() {
		return this.mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Fichero getFichero() {
		return this.fichero;
	}
	public void setFichero(Fichero fichero) {
		this.fichero = fichero;
	}
	public Long getCodigo() {
		return this.codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return this.titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getExtension() {
		return this.extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getContentType() {
		return this.contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Long getTamano() {
		return this.tamano;
	}
	public void setTamano(Long tamano) {
		this.tamano = tamano;
	}
	public String getEncriptado() {
		return this.encriptado;
	}
	public void setEncriptado(String encriptado) {
		this.encriptado = encriptado;
	}
	public String getRutaPif() {
		return this.rutaPif;
	}
	public void setRutaPif(String rutaPif) {
		this.rutaPif = rutaPif;
	}
	public String getOidFichero() {
		return this.oidFichero;
	}
	public void setOidFichero(String oidFichero) {
		this.oidFichero = oidFichero;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigDecimal getIdFichero0D4() {
		return this.idFichero0D4;
	}
	public void setIdFichero0D4(BigDecimal idFichero0D4) {
		this.idFichero0D4 = idFichero0D4;
	}
	public String getTipoBusqueda() {
		return this.tipoBusqueda;
	}
	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}


}
