package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

public class Aa79bDatosTradRev implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer numTotalPalSolic;
	private Long fechaFinalSolic;
	private String indPublicarBopv;
	private String indPrevistoBopv;
	private String indPresupuesto;
	private String refTramitagune;
	private String indCorredaccion;
	private String texto;
	private String tipoRedaccion;
	private String participantes;
	private Aa79bDescripcionIdioma idioma;
	private String descIdiomaEu;
	private String descIdiomaEs;
	private String indConfidencial;
	private Integer numTotalPalIzo;
	private Long fechaFinalIzo;
	private String indObservaciones;
	private String observaciones;
	private String indFichero;
	private Aa79bFicheroObservaciones ficheroObservaciones;
	private String indFacturable;
	private Long fechaEntrega;
	private Long fechaLimite;
	private String indUrgente;
	private Long idRelevancia;
	private String relevanciaDescEs;
	private String relevanciaDescEu;
	private String horaFinalIzo;
	private Long fechaFinalProp;
	private String horaFinalSolic;
	private Integer enPlazoParaLanzarNoConformidad;
	private Aa79bTradosExp datosTrados;
	private Aa79bDatosFacturacionExpediente datosFacturacion;

	/**
	 * @return the numTotalPalSolic
	 */
	public Integer getNumTotalPalSolic() {
		return this.numTotalPalSolic;
	}

	/**
	 * @param numTotalPalSolic
	 *            the numTotalPalSolic to set
	 */
	public void setNumTotalPalSolic(Integer numTotalPalSolic) {
		this.numTotalPalSolic = numTotalPalSolic;
	}

	/**
	 * @return the indPublicarBopv
	 */
	public String getIndPublicarBopv() {
		return this.indPublicarBopv;
	}

	/**
	 * @param indPublicarBopv
	 *            the indPublicarBopv to set
	 */
	public void setIndPublicarBopv(String indPublicarBopv) {
		this.indPublicarBopv = indPublicarBopv;
	}

	/**
	 * @return the indPrevistoBopv
	 */
	public String getIndPrevistoBopv() {
		return indPrevistoBopv;
	}

	/**
	 * @param indPrevistoBopv
	 *            the indPrevistoBopv to set
	 */
	public void setIndPrevistoBopv(String indPrevistoBopv) {
		this.indPrevistoBopv = indPrevistoBopv;
	}

	/**
	 * @return the indPresupuesto
	 */
	public String getIndPresupuesto() {
		return this.indPresupuesto;
	}

	/**
	 * @param indPresupuesto
	 *            the indPresupuesto to set
	 */
	public void setIndPresupuesto(String indPresupuesto) {
		this.indPresupuesto = indPresupuesto;
	}

	/**
	 * @return the refTramitagune
	 */
	public String getRefTramitagune() {
		return this.refTramitagune;
	}

	/**
	 * @param refTramitagune
	 *            the refTramitagune to set
	 */
	public void setRefTramitagune(String refTramitagune) {
		this.refTramitagune = refTramitagune;
	}

	/**
	 * @return the indCorredaccion
	 */
	public String getIndCorredaccion() {
		return this.indCorredaccion;
	}

	/**
	 * @param indCorredaccion
	 *            the indCorredaccion to set
	 */
	public void setIndCorredaccion(String indCorredaccion) {
		this.indCorredaccion = indCorredaccion;
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return this.texto;
	}

	/**
	 * @param texto
	 *            the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * @return the tipoRedaccion
	 */
	public String getTipoRedaccion() {
		return this.tipoRedaccion;
	}

	/**
	 * @param tipoRedaccion
	 *            the tipoRedaccion to set
	 */
	public void setTipoRedaccion(String tipoRedaccion) {
		this.tipoRedaccion = tipoRedaccion;
	}

	/**
	 * @return the participantes
	 */
	public String getParticipantes() {
		return this.participantes;
	}

	/**
	 * @param participantes
	 *            the participantes to set
	 */
	public void setParticipantes(String participantes) {
		this.participantes = participantes;
	}

	/**
	 * @return the idioma
	 */
	public Aa79bDescripcionIdioma getIdioma() {
		return this.idioma;
	}

	/**
	 * @param idioma
	 *            the idioma to set
	 */
	public void setIdioma(Aa79bDescripcionIdioma idioma) {
		this.idioma = idioma;
	}

	/**
	 * @return the descIdiomaEu
	 */
	public String getDescIdiomaEu() {
		return this.descIdiomaEu;
	}

	/**
	 * @param descIdiomaEu
	 *            the descIdiomaEu to set
	 */
	public void setDescIdiomaEu(String descIdiomaEu) {
		this.descIdiomaEu = descIdiomaEu;
	}

	/**
	 * @return the descIdiomaEs
	 */
	public String getDescIdiomaEs() {
		return this.descIdiomaEs;
	}

	/**
	 * @param descIdiomaEs
	 *            the descIdiomaEs to set
	 */
	public void setDescIdiomaEs(String descIdiomaEs) {
		this.descIdiomaEs = descIdiomaEs;
	}

	/**
	 * @return the indConfidencial
	 */
	public String getIndConfidencial() {
		return this.indConfidencial;
	}

	/**
	 * @param indConfidencial
	 *            the indConfidencial to set
	 */
	public void setIndConfidencial(String indConfidencial) {
		this.indConfidencial = indConfidencial;
	}

	/**
	 * @return the numTotalPalIzo
	 */
	public Integer getNumTotalPalIzo() {
		return this.numTotalPalIzo;
	}

	/**
	 * @param numTotalPalIzo
	 *            the numTotalPalIzo to set
	 */
	public void setNumTotalPalIzo(Integer numTotalPalIzo) {
		this.numTotalPalIzo = numTotalPalIzo;
	}

	/**
	 * @return the fechaFinalSolic
	 */
	public Long getFechaFinalSolic() {
		return this.fechaFinalSolic;
	}

	/**
	 * @param fechaFinalSolic
	 *            the fechaFinalSolic to set
	 */
	public void setFechaFinalSolic(Long fechaFinalSolic) {
		this.fechaFinalSolic = fechaFinalSolic;
	}

	/**
	 * @return the fechaFinalIzo
	 */
	public Long getFechaFinalIzo() {
		return this.fechaFinalIzo;
	}

	/**
	 * @param fechaFinalIzo
	 *            the fechaFinalIzo to set
	 */
	public void setFechaFinalIzo(Long fechaFinalIzo) {
		this.fechaFinalIzo = fechaFinalIzo;
	}

	/**
	 * @return the indObservaciones
	 */
	public String getIndObservaciones() {
		return this.indObservaciones;
	}

	/**
	 * @param indObservaciones
	 *            the indObservaciones to set
	 */
	public void setIndObservaciones(String indObservaciones) {
		this.indObservaciones = indObservaciones;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return this.observaciones;
	}

	/**
	 * @param observaciones
	 *            the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the indFichero
	 */
	public String getIndFichero() {
		return this.indFichero;
	}

	/**
	 * @param indFichero
	 *            the indFichero to set
	 */
	public void setIndFichero(String indFichero) {
		this.indFichero = indFichero;
	}

	/**
	 * @return the ficheroObservaciones
	 */
	public Aa79bFicheroObservaciones getFicheroObservaciones() {
		return this.ficheroObservaciones;
	}

	/**
	 * @param ficheroObservaciones
	 *            the ficheroObservaciones to set
	 */
	public void setFicheroObservaciones(Aa79bFicheroObservaciones ficheroObservaciones) {
		this.ficheroObservaciones = ficheroObservaciones;
	}

	/**
	 * @return the indFacturable
	 */
	public String getIndFacturable() {
		return this.indFacturable;
	}

	/**
	 * @param indFacturable
	 *            the indFacturable to set
	 */
	public void setIndFacturable(String indFacturable) {
		this.indFacturable = indFacturable;
	}

	/**
	 * @return the fechaEntrega
	 */
	public Long getFechaEntrega() {
		return this.fechaEntrega;
	}

	/**
	 * @param fechaEntrega
	 *            the fechaEntrega to set
	 */
	public void setFechaEntrega(Long fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * @return the fechaLimite
	 */
	public Long getFechaLimite() {
		return this.fechaLimite;
	}

	/**
	 * @param fechaLimite
	 *            the fechaLimite to set
	 */
	public void setFechaLimite(Long fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public String getIndUrgente() {
		return this.indUrgente;
	}

	public void setIndUrgente(String indUrgente) {
		this.indUrgente = indUrgente;
	}

	public Long getIdRelevancia() {
		return this.idRelevancia;
	}

	public void setIdRelevancia(Long idRelevancia) {
		this.idRelevancia = idRelevancia;
	}

	public String getHoraFinalIzo() {
		return this.horaFinalIzo;
	}

	public void setHoraFinalIzo(String horaFinalIzo) {
		this.horaFinalIzo = horaFinalIzo;
	}

	public Long getFechaFinalProp() {
		return this.fechaFinalProp;
	}

	public void setFechaFinalProp(Long fechaFinalProp) {
		this.fechaFinalProp = fechaFinalProp;
	}

	/**
	 * @return the horaFinalSolic
	 */
	public String getHoraFinalSolic() {
		return this.horaFinalSolic;
	}

	/**
	 * @param horaFinalSolic
	 *            the horaFinalSolic to set
	 */
	public void setHoraFinalSolic(String horaFinalSolic) {
		this.horaFinalSolic = horaFinalSolic;
	}

	/**
	 * @return the enPlazoParaLanzarNoConformidad
	 */
	public Integer getEnPlazoParaLanzarNoConformidad() {
		return enPlazoParaLanzarNoConformidad;
	}

	/**
	 * @param enPlazoParaLanzarNoConformidad
	 *            the enPlazoParaLanzarNoConformidad to set
	 */
	public void setEnPlazoParaLanzarNoConformidad(Integer enPlazoParaLanzarNoConformidad) {
		this.enPlazoParaLanzarNoConformidad = enPlazoParaLanzarNoConformidad;
	}

	/**
	 * @return the datosTrados
	 */
	public Aa79bTradosExp getDatosTrados() {
		return datosTrados;
	}

	/**
	 * @param datosTrados
	 *            the datosTrados to set
	 */
	public void setDatosTrados(Aa79bTradosExp datosTrados) {
		this.datosTrados = datosTrados;
	}

	/**
	 * @return the relevanciaDescEs
	 */
	public String getRelevanciaDescEs() {
		return relevanciaDescEs;
	}

	/**
	 * @param relevanciaDescEs
	 *            the relevanciaDescEs to set
	 */
	public void setRelevanciaDescEs(String relevanciaDescEs) {
		this.relevanciaDescEs = relevanciaDescEs;
	}

	/**
	 * @return the relevanciaDescEu
	 */
	public String getRelevanciaDescEu() {
		return relevanciaDescEu;
	}

	/**
	 * @param relevanciaDescEu
	 *            the relevanciaDescEu to set
	 */
	public void setRelevanciaDescEu(String relevanciaDescEu) {
		this.relevanciaDescEu = relevanciaDescEu;
	}

	/**
	 * @return the datosFacturacion
	 */
	public Aa79bDatosFacturacionExpediente getDatosFacturacion() {
		return datosFacturacion;
	}

	/**
	 * @param datosFacturacion
	 *            the datosFacturacion to set
	 */
	public void setDatosFacturacion(Aa79bDatosFacturacionExpediente datosFacturacion) {
		this.datosFacturacion = datosFacturacion;
	}

	/**
	 * Intended only for logging and debugging.
	 * 
	 * Here, the contents of every main field are placed into the result.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ numTotalPalSolic: ").append(this.numTotalPalSolic).append(" ]");
		result.append(", [ fechaFinalSolic: ").append(this.fechaFinalSolic).append(" ]");
		result.append(", [ indPublicarBopv: ").append(this.indPublicarBopv).append(" ]");
		result.append(", [ indPresupuesto: ").append(this.indPresupuesto).append(" ]");
		result.append(", [ refTramitagune: ").append(this.refTramitagune).append(" ]");
		result.append(", [ indCorredaccion: ").append(this.indCorredaccion).append(" ]");
		result.append(", [ texto: ").append(this.texto).append(" ]");
		result.append(", [ tipoRedaccion: ").append(this.tipoRedaccion).append(" ]");
		result.append(", [ participantes: ").append(this.participantes).append(" ]");
		result.append(", [ idioma: ").append(this.idioma).append(" ]");
		result.append(", [ descIdiomaEu: ").append(this.descIdiomaEu).append(" ]");
		result.append(", [ descIdiomaEs: ").append(this.descIdiomaEs).append(" ]");
		result.append(", [ indConfidencial: ").append(this.indConfidencial).append(" ]");
		result.append(", [ numTotalPalIzo: ").append(this.numTotalPalIzo).append(" ]");
		result.append(", [ fechaFinalIzo: ").append(this.fechaFinalIzo).append(" ]");
		result.append(", [ indObservaciones: ").append(this.indObservaciones).append(" ]");
		result.append(", [ observaciones: ").append(this.observaciones).append(" ]");
		result.append(", [ indFichero: ").append(this.indFichero).append(" ]");
		result.append(", [ ficheroObservaciones: ").append(this.ficheroObservaciones).append(" ]");
		result.append(", [ indFacturable: ").append(this.indFacturable).append(" ]");
		result.append(", [ fechaEntrega: ").append(this.fechaEntrega).append(" ]");
		result.append(", [ fechaLimite: ").append(this.fechaLimite).append(" ]");
		result.append(", [ indUrgente: ").append(this.indUrgente).append(" ]");
		result.append(", [ idRelevancia: ").append(this.idRelevancia).append(" ]");
		result.append(", [ horaFinalIzo: ").append(this.horaFinalIzo).append(" ]");
		result.append(", [ fechaFinalProp: ").append(this.fechaFinalProp).append(" ]");
		result.append(", [ horaFinalSolic: ").append(this.horaFinalSolic).append(" ]");
		result.append("}");

		return result.toString();
	}

}
