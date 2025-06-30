package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.util.List;

import com.ejie.aa79b.model.IdiomaWS;

public class Aa79bSolicitud extends IdiomaWS implements Serializable {


	private static final long serialVersionUID = 1L;

	private Long anyo;
	private Integer numExp;
    private Aa79bDescripcionIdioma tipoExpediente;
    private String titulo;
    private Aa79bDatosInterpretacion datosInterpretacion;
    private Aa79bDatosTradRev datosTradRev;
    private List<Aa79bExpedienteRelacionado> expedientesRelacionados;
    private List<Aa79bDocumentoExpediente> documentosExpediente;
    private Aa79bGestorExpediente gestor;
    private Long fechaAlta;
    private Aa79bSolicitante solicitante;
    private Long idEstadoBitacora;
    private String listaDocumentosStr;
    private String expRelSeleccionadosStr;
    private String accionSolicitud;
	
    public Aa79bSolicitud() {
		//Constructor
	}

    
	/**
	 * @return the anyo
	 */
	public Long getAnyo() {
		return anyo;
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
		return numExp;
	}


	/**
	 * @param numExp the numExp to set
	 */
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}


	/**
	 * @return the tipoExpediente
	 */
	public Aa79bDescripcionIdioma getTipoExpediente() {
		return tipoExpediente;
	}

	/**
	 * @param tipoExpediente the tipoExpediente to set
	 */
	public void setTipoExpediente(Aa79bDescripcionIdioma tipoExpediente) {
		this.tipoExpediente = tipoExpediente;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the datosInterpretacion
	 */
	public Aa79bDatosInterpretacion getDatosInterpretacion() {
		return datosInterpretacion;
	}

	/**
	 * @param datosInterpretacion the datosInterpretacion to set
	 */
	public void setDatosInterpretacion(Aa79bDatosInterpretacion datosInterpretacion) {
		this.datosInterpretacion = datosInterpretacion;
	}

	/**
	 * @return the datosTradRev
	 */
	public Aa79bDatosTradRev getDatosTradRev() {
		return datosTradRev;
	}

	/**
	 * @param datosTradRev the datosTradRev to set
	 */
	public void setDatosTradRev(Aa79bDatosTradRev datosTradRev) {
		this.datosTradRev = datosTradRev;
	}

	/**
	 * @return the expedientesRelacionados
	 */
	public List<Aa79bExpedienteRelacionado> getExpedientesRelacionados() {
		return expedientesRelacionados;
	}

	/**
	 * @param expedientesRelacionados the expedientesRelacionados to set
	 */
	public void setExpedientesRelacionados(List<Aa79bExpedienteRelacionado> expedientesRelacionados) {
		this.expedientesRelacionados = expedientesRelacionados;
	}

	/**
	 * @return the documentosExpediente
	 */
	public List<Aa79bDocumentoExpediente> getDocumentosExpediente() {
		return documentosExpediente;
	}

	/**
	 * @param documentosExpediente the documentosExpediente to set
	 */
	public void setDocumentosExpediente(List<Aa79bDocumentoExpediente> documentosExpediente) {
		this.documentosExpediente = documentosExpediente;
	}

	/**
	 * @return the gestor
	 */
	public Aa79bGestorExpediente getGestor() {
		return gestor;
	}

	/**
	 * @param gestor the gestor to set
	 */
	public void setGestor(Aa79bGestorExpediente gestor) {
		this.gestor = gestor;
	}

	/**
	 * @return the fechaAlta
	 */
	public Long getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Long fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the solicitante
	 */
	public Aa79bSolicitante getSolicitante() {
		return solicitante;
	}


	/**
	 * @param solicitante the solicitante to set
	 */
	public void setSolicitante(Aa79bSolicitante solicitante) {
		this.solicitante = solicitante;
	}


	/**
	 * @return the idEstadoBitacora
	 */
	public Long getIdEstadoBitacora() {
		return idEstadoBitacora;
	}


	/**
	 * @param idEstadoBitacora the idEstadoBitacora to set
	 */
	public void setIdEstadoBitacora(Long idEstadoBitacora) {
		this.idEstadoBitacora = idEstadoBitacora;
	}


	/**
	 * @return the listaDocumentosStr
	 */
	public String getListaDocumentosStr() {
		return listaDocumentosStr;
	}


	/**
	 * @param listaDocumentosStr the listaDocumentosStr to set
	 */
	public void setListaDocumentosStr(String listaDocumentosStr) {
		this.listaDocumentosStr = listaDocumentosStr;
	}
	
	/**
	 * @return the listaDocumentosStr
	 */
	public String getExpRelSeleccionadosStr() {
		return expRelSeleccionadosStr;
	}
	
	
	/**
	 * @param listaDocumentosStr the listaDocumentosStr to set
	 */
	public void setExpRelSeleccionadosStr(String expRelSeleccionadosStr) {
		this.expRelSeleccionadosStr = expRelSeleccionadosStr;
	}


	/**
	 * @return the accionSolicitud
	 */
	public String getAccionSolicitud() {
		return accionSolicitud;
	}


	/**
	 * @param accionSolicitud the accionSolicitud to set
	 */
	public void setAccionSolicitud(String accionSolicitud) {
		this.accionSolicitud = accionSolicitud;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Aa79bSolicitud [tipoExpediente=" + tipoExpediente + ", titulo=" + titulo + ", datosInterpretacion="
				+ datosInterpretacion + ", datosTradRev=" + datosTradRev + ", expedientesRelacionados="
				+ expedientesRelacionados + ", documentosExpediente=" + documentosExpediente + ", gestor=" + gestor
				+ ", fechaAlta=" + fechaAlta + "]";
	}
    
    
    
}
