package com.ejie.aa79b.model;

public class SeccionesControlDeCalidad implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private int tipoSeccion;
	private String tipoSeccionEu;
	private String tipoSeccionEs;
	private String indRespuesta;
	private String descRespuestaEu;
	private String descRespuestaEs;
	private String nombreEu;
	private String indObservaciones;
	private String descObservacionesEu;
	private String descObservacionesEs;
	private String indVisible;
	private String descVisibleEu;
	private String descVisibleEs;
	private int orden;

	public SeccionesControlDeCalidad() {
		super();
	}

	public SeccionesControlDeCalidad(Long id) {
		super();
		this.id = id;
	}

	public SeccionesControlDeCalidad(Long id, int tipoSeccion, String indRespuesta, String nombreEu,
			String indObservaciones, String indVisible, int orden) {
		super();
		this.id = id;
		this.tipoSeccion = tipoSeccion;
		this.indRespuesta = indRespuesta;
		this.nombreEu = nombreEu;
		this.indObservaciones = indObservaciones;
		this.indVisible = indVisible;
		this.orden = orden;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTipoSeccion() {
		return tipoSeccion;
	}

	public void setTipoSeccion(int tipoSeccion) {
		this.tipoSeccion = tipoSeccion;
	}

	public String getTipoSeccionEu() {
		return tipoSeccionEu;
	}

	public void setTipoSeccionEu(String tipoSeccionEu) {
		this.tipoSeccionEu = tipoSeccionEu;
	}

	public String getTipoSeccionEs() {
		return tipoSeccionEs;
	}

	public void setTipoSeccionEs(String tipoSeccionEs) {
		this.tipoSeccionEs = tipoSeccionEs;
	}

	public String getIndRespuesta() {
		return indRespuesta;
	}

	public void setIndRespuesta(String indRespuesta) {
		this.indRespuesta = indRespuesta;
	}

	public String getDescRespuestaEu() {
		return descRespuestaEu;
	}

	public void setDescRespuestaEu(String descRespuestaEu) {
		this.descRespuestaEu = descRespuestaEu;
	}

	public String getDescRespuestaEs() {
		return descRespuestaEs;
	}

	public void setDescRespuestaEs(String descRespuestaEs) {
		this.descRespuestaEs = descRespuestaEs;
	}

	public String getNombreEu() {
		return nombreEu;
	}

	public void setNombreEu(String nombreEu) {
		this.nombreEu = nombreEu;
	}

	public String getIndObservaciones() {
		return indObservaciones;
	}

	public void setIndObservaciones(String indObservaciones) {
		this.indObservaciones = indObservaciones;
	}

	public String getDescObservacionesEu() {
		return descObservacionesEu;
	}

	public void setDescObservacionesEu(String descObservacionesEu) {
		this.descObservacionesEu = descObservacionesEu;
	}

	public String getDescObservacionesEs() {
		return descObservacionesEs;
	}

	public void setDescObservacionesEs(String descObservacionesEs) {
		this.descObservacionesEs = descObservacionesEs;
	}

	public String getIndVisible() {
		return indVisible;
	}

	public void setIndVisible(String indVisible) {
		this.indVisible = indVisible;
	}

	public String getDescVisibleEu() {
		return descVisibleEu;
	}

	public void setDescVisibleEu(String descVisibleEu) {
		this.descVisibleEu = descVisibleEu;
	}

	public String getDescVisibleEs() {
		return descVisibleEs;
	}

	public void setDescVisibleEs(String descVisibleEs) {
		this.descVisibleEs = descVisibleEs;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append(" [ id: ").append(this.id).append(" ]");
		result.append(", [ tipoSeccion: ").append(this.tipoSeccion).append(" ]");
		result.append(", [ indRespuesta: ").append(this.indRespuesta).append(" ]");
		result.append(", [ nombreEu: ").append(this.nombreEu).append(" ]");
		result.append(", [ indObservaciones: ").append(this.indObservaciones).append(" ]");
		result.append(", [ indVisible: ").append(this.indVisible).append(" ]");
		result.append(", [ orden: ").append(this.orden).append(" ]");
		result.append("}");
		return result.toString();
	}
}
