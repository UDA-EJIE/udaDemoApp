/*
* Copyright 2011 E.J.I.E., S.A.
*
* Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
* Solo podrá usarse esta obra si se respeta la Licencia.
* Puede obtenerse una copia de la Licencia en
*
* http://ec.europa.eu/idabc/eupl.html
*
* Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
* el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
* SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
* Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
* que establece la Licencia.
*/
package com.ejie.x21a.model;

import java.sql.Date;

/**
*  * Departamento generated by UDA 1.0, 26-may-2011 13:45:00.
* @author UDA
 */

public class Expediente implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
   
    private Integer id;
    private String titulo;
    private String nombre;
    private String codigo;
    private String buzonTramitador;
    private String nombreTramitador;
    private String email;
    private String pregunta;
    private Integer idFase;
    private String nombreFaseEs;
    private String nombreFaseEu;
    private Date fecha;
    private Date alertaBajaFecha;
    private Date alertaMediaFecha;
    private Date alertaAltaFecha;
    private Integer idAlerta;
    private String nombreAlertaEs;
    private String nombreAlertaEu;
    
	public Expediente() {
		super();
	}

	public Expediente(Integer id, String titulo, String nombre, String codigo, String buzonTramitador, String nombreTramitador, String email, String pregunta, Integer idFase, String nombreFaseEs, String nombreFaseEu, Date fecha, Date alertaBajaFecha, Date alertaMediaFecha, Date alertaAltaFecha, Integer idAlerta, String nombreAlertaEs, String nombreAlertaEu) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.nombre = nombre;
		this.codigo = codigo;
		this.buzonTramitador = buzonTramitador;
		this.nombreTramitador = nombreTramitador;
		this.email = email;
		this.pregunta = pregunta;
		this.idFase = idFase;
		this.nombreFaseEs = nombreFaseEs;
		this.nombreFaseEu = nombreFaseEu;
		this.fecha = fecha;
		this.alertaBajaFecha = alertaBajaFecha;
		this.alertaMediaFecha = alertaMediaFecha;
		this.alertaAltaFecha = alertaAltaFecha;
		this.idAlerta = idAlerta;
		this.nombreAlertaEs = nombreAlertaEs;
		this.nombreAlertaEu = nombreAlertaEu;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getBuzonTramitador() {
		return buzonTramitador;
	}


	public void setBuzonTramitador(String buzonTramitador) {
		this.buzonTramitador = buzonTramitador;
	}


	public String getNombreTramitador() {
		return nombreTramitador;
	}


	public void setNombreTramitador(String nombreTramitador) {
		this.nombreTramitador = nombreTramitador;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPregunta() {
		return pregunta;
	}


	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}


	public Integer getIdFase() {
		return idFase;
	}


	public void setIdFase(Integer idFase) {
		this.idFase = idFase;
	}


	public String getNombreFaseEs() {
		return nombreFaseEs;
	}


	public void setNombreFaseEs(String nombreFaseEs) {
		this.nombreFaseEs = nombreFaseEs;
	}


	public String getNombreFaseEu() {
		return nombreFaseEu;
	}


	public void setNombreFaseEu(String nombreFaseEu) {
		this.nombreFaseEu = nombreFaseEu;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Date getAlertaBajaFecha() {
		return alertaBajaFecha;
	}


	public void setAlertaBajaFecha(Date alertaBajaFecha) {
		this.alertaBajaFecha = alertaBajaFecha;
	}


	public Date getAlertaMediaFecha() {
		return alertaMediaFecha;
	}


	public void setAlertaMediaFecha(Date alertaMediaFecha) {
		this.alertaMediaFecha = alertaMediaFecha;
	}


	public Date getAlertaAltaFecha() {
		return alertaAltaFecha;
	}


	public void setAlertaAltaFecha(Date alertaAltaFecha) {
		this.alertaAltaFecha = alertaAltaFecha;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}






	public Integer getIdAlerta() {
		return idAlerta;
	}






	public void setIdAlerta(Integer idAlerta) {
		this.idAlerta = idAlerta;
	}






	public String getNombreAlertaEs() {
		return nombreAlertaEs;
	}






	public void setNombreAlertaEs(String nombreAlertaEs) {
		this.nombreAlertaEs = nombreAlertaEs;
	}






	public String getNombreAlertaEu() {
		return nombreAlertaEu;
	}






	public void setNombreAlertaEu(String nombreAlertaEu) {
		this.nombreAlertaEu = nombreAlertaEu;
	}
	
	
	
}

