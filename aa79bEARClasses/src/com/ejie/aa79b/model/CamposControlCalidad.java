package com.ejie.aa79b.model;

public class CamposControlCalidad implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idCampoControlCalidad;
	private String nombreCampo;
	private String indObservaciones;
	private String indObligatorio;
	private String notaOk;
	private String notaNoOk;
	private PesosValoracionAuditoria peso;
	private Long tipoCampo;
	private String indVisible;
	private SeccionesControlDeCalidad seccion = new SeccionesControlDeCalidad();
	private Long orden;

	private String tipoCampoEU;

	public CamposControlCalidad() {
		// Constructor
	}

	public CamposControlCalidad(Long idCampoControlCalidad) {
		this.idCampoControlCalidad = idCampoControlCalidad;
	}

	public CamposControlCalidad(Long idCampoControlCalidad, String nombreCampo, Long tipoCampo, String indVisible,
			String idSeccion, Long ordenSeccion, SeccionesControlDeCalidad seccion) {
		this.idCampoControlCalidad = idCampoControlCalidad;
		this.nombreCampo = nombreCampo;
		this.tipoCampo = tipoCampo;
		this.indVisible = indVisible;
		this.orden = ordenSeccion;
		this.seccion = seccion;

	}

	public Long getIdCampoControlCalidad() {
		return this.idCampoControlCalidad;
	}

	public void setIdCampoControlCalidad(Long idCampoControlCalidad) {
		this.idCampoControlCalidad = idCampoControlCalidad;
	}

	public String getNombreCampo() {
		return nombreCampo;
	}

	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

	public Long getTipoCampo() {
		return tipoCampo;
	}

	public void setTipoCampo(Long tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

	public String getIndVisible() {
		return indVisible;
	}

	public void setIndVisible(String indVisible) {
		this.indVisible = indVisible;
	}

	public Long getOrden() {
		return orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

	public String getIndObservaciones() {
		return indObservaciones;
	}

	public void setIndObservaciones(String indObservaciones) {
		this.indObservaciones = indObservaciones;
	}

	public String getNotaOk() {
		return notaOk;
	}

	public void setNotaOk(String notaOk) {
		this.notaOk = notaOk;
	}

	public String getNotaNoOk() {
		return notaNoOk;
	}

	public void setNotaNoOk(String notaNoOk) {
		this.notaNoOk = notaNoOk;
	}

	public PesosValoracionAuditoria getPeso() {
		return peso;
	}

	public void setPeso(PesosValoracionAuditoria peso) {
		this.peso = peso;
	}

	public String getTipoCampoEU() {
		return tipoCampoEU;
	}

	public void setTipoCampoEU(String tipoCampoEU) {
		this.tipoCampoEU = tipoCampoEU;
	}

	@Override
	public String toString() {
		return "ControlCalidad [idCampoControlCalidad=" + idCampoControlCalidad + ", nombreCampo=" + nombreCampo
				+ ", indicadorObservaciones=" + indObservaciones + ", notaOk=" + notaOk + ", notaNoOk=" + notaNoOk
				+ ", peso=" + peso + ", tipoCampo=" + tipoCampo + ", indVisible=" + indVisible + ", ordenSeccion="
				+ orden + ", tipoCampoEU=" + tipoCampoEU + "]";
	}

	public SeccionesControlDeCalidad getSeccion() {
		return seccion;
	}

	public void setSeccion(SeccionesControlDeCalidad seccion) {
		this.seccion = seccion;
	}

	public String getIndObligatorio() {
		return indObligatorio;
	}

	public void setIndObligatorio(String indObligatorio) {
		this.indObligatorio = indObligatorio;
	}

}
