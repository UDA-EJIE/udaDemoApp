package com.ejie.aa79b.model.webservices;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.IdiomaWS;
import com.ejie.aa79b.utils.DateUtils;

/**
 * @author javarona
 * 
 */
/**
 * @author mrodriguez
 *
 */
public class Aa79bExpediente extends IdiomaWS implements java.io.Serializable {

	/**
	 * serialVersionUID: long.
	 */
	private static final long serialVersionUID = 1L;

	private Long anyo;
	private Integer numExp;
	private String anyoNumExp;
	private Aa79bDescripcionIdioma tipoExpediente;
	private Long fechaSolicitud;
	private String horaSolicitud;
	private Long fechaEntrega;
	private String horaEntrega;
	private String titulo;
	private Integer idRequerimiento;
	private Integer idTipoRequerimiento;
	private String origen;
	private Aa79bDescripcionIdioma estadoFase;
	private Aa79bEstadoFaseExpediente estadoFaseExpediente;
	private Aa79bDatosInterpretacion datosInterpretacion;
	private Aa79bDatosTradRev datosTradRev;
	private String indFacturable;
	private String facturableDescEs;
	private String facturableDescEu;
	private String indPagado;
	private String pagadoDescEs;
	private String pagadoDescEu;
	private String indPublicarBOPV;
	private String publicarBOPVDescEs;
	private String publicarBOPVDescEu;
	private Aa79bGestorExpediente gestor;
	private List<Aa79bExpedienteRelacionado> expedientesRelacionados;
	private List<Aa79bDocumentoExpediente> documentosExpediente;
	private String listaDocumentosStr;

	private Long fechaDesdeSolicitud;
	private Long fechaHastaSolicitud;
	private Long fechaDesdeEntrega;
	private Long fechaHastaEntrega;

	private String expRelSeleccionados;

	private Long anyoExpRel;

	private Integer numExpExpRel;

	private Date fechaAlta;
	private String horaAlta;

	private Integer estadoSubsanacion;
	private String indSubsanado;

	/* HITO 7 */
	private String presupuestoAceptado;
	private String tieneDatosFacturacion;
	private List<Aa79bFacturaExpediente> facturaExpediente;
	private Aa79bAnulacionRechazo anulacionRechazo;
	/* HITO 7 */

	private Date fechaSolicitudDate;

	public Aa79bExpediente() {
		// Constructor
	}

	/**
	 * @return the anyo
	 */
	public Long getAnyo() {
		return this.anyo;
	}

	/**
	 * @param anyo
	 *            the anyo to set
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
	 * @param numExp
	 *            the numExp to set
	 */
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	/**
	 * @return the anyoNumExp
	 */
	public String getAnyoNumExp() {
		this.setAnyoNumExp(null);
		if (this.anyo != null && this.numExp != null) {
			this.setAnyoNumExp(String.valueOf(this.anyo).substring(2) + "/" + String.format("%06d", this.numExp));
		}

		return this.anyoNumExp;
	}

	/**
	 * @param anyoNumExp
	 *            the anyoNumExp to set
	 */
	public void setAnyoNumExp(String anyoNumExp) {
		this.anyoNumExp = anyoNumExp;
	}

	/**
	 * @return the tipoExpediente
	 */
	public Aa79bDescripcionIdioma getTipoExpediente() {
		return this.tipoExpediente;
	}

	/**
	 * @param tipoExpediente
	 *            the tipoExpediente to set
	 */
	public void setTipoExpediente(Aa79bDescripcionIdioma tipoExpediente) {
		this.tipoExpediente = tipoExpediente;
	}

	/**
	 * @return the horaSolicitud
	 */
	public String getHoraSolicitud() {
		return this.horaSolicitud;
	}

	/**
	 * @param horaSolicitud
	 *            the horaSolicitud to set
	 */
	public void setHoraSolicitud(String horaSolicitud) {
		this.horaSolicitud = horaSolicitud;
	}

	/**
	 * @return the fechaSolicitud
	 */
	public Long getFechaSolicitud() {
		return this.fechaSolicitud;
	}

	/**
	 * @param fechaSolicitud
	 *            the fechaSolicitud to set
	 */
	public void setFechaSolicitud(Long fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
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
	 * @return the horaEntrega
	 */
	public String getHoraEntrega() {
		return this.horaEntrega;
	}

	/**
	 * @param horaEntrega
	 *            the horaEntrega to set
	 */
	public void setHoraEntrega(String horaEntrega) {
		this.horaEntrega = horaEntrega;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return this.titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the idRequerimiento
	 */
	public Integer getIdRequerimiento() {
		return idRequerimiento;
	}

	/**
	 * @param idRequerimiento
	 *            the idRequerimiento to set
	 */
	public void setIdRequerimiento(Integer idRequerimiento) {
		this.idRequerimiento = idRequerimiento;
	}

	/**
	 * @return the idTipoRequerimiento
	 */
	public Integer getIdTipoRequerimiento() {
		return idTipoRequerimiento;
	}

	/**
	 * @param idTipoRequerimiento
	 *            the idTipoRequerimiento to set
	 */
	public void setIdTipoRequerimiento(Integer idTipoRequerimiento) {
		this.idTipoRequerimiento = idTipoRequerimiento;
	}

	/**
	 * @return the estadoFase
	 */
	public Aa79bDescripcionIdioma getEstadoFase() {
		return this.estadoFase;
	}

	/**
	 * @param estadoFase
	 *            the estadoFase to set
	 */
	public void setEstadoFase(Aa79bDescripcionIdioma estadoFase) {
		this.estadoFase = estadoFase;
	}

	/**
	 * @return the estadoFaseExpediente
	 */
	public Aa79bEstadoFaseExpediente getEstadoFaseExpediente() {
		return this.estadoFaseExpediente;
	}

	/**
	 * @param estadoFaseExpediente
	 *            the estadoFaseExpediente to set
	 */
	public void setEstadoFaseExpediente(Aa79bEstadoFaseExpediente estadoFaseExpediente) {
		this.estadoFaseExpediente = estadoFaseExpediente;
	}

	/**
	 * @return the datosInterpretacion
	 */
	public Aa79bDatosInterpretacion getDatosInterpretacion() {
		return this.datosInterpretacion;
	}

	/**
	 * @param datosInterpretacion
	 *            the datosInterpretacion to set
	 */
	public void setDatosInterpretacion(Aa79bDatosInterpretacion datosInterpretacion) {
		this.datosInterpretacion = datosInterpretacion;
	}

	/**
	 * @return the datosTradRev
	 */
	public Aa79bDatosTradRev getDatosTradRev() {
		return this.datosTradRev;
	}

	/**
	 * @param datosTradRev
	 *            the datosTradRev to set
	 */
	public void setDatosTradRev(Aa79bDatosTradRev datosTradRev) {
		this.datosTradRev = datosTradRev;
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
	 * @return the facturableDescEs
	 */
	public String getFacturableDescEs() {
		return this.facturableDescEs;
	}

	/**
	 * @param facturableDescEs
	 *            the facturableDescEs to set
	 */
	public void setFacturableDescEs(String facturableDescEs) {
		this.facturableDescEs = facturableDescEs;
	}

	/**
	 * @return the facturableDescEu
	 */
	public String getFacturableDescEu() {
		return this.facturableDescEu;
	}

	/**
	 * @param facturableDescEu
	 *            the facturableDescEu to set
	 */
	public void setFacturableDescEu(String facturableDescEu) {
		this.facturableDescEu = facturableDescEu;
	}

	/**
	 * @return the indPagado
	 */
	public String getIndPagado() {
		return this.indPagado;
	}

	/**
	 * @param indPagado
	 *            the indPagado to set
	 */
	public void setIndPagado(String indPagado) {
		this.indPagado = indPagado;
	}

	/**
	 * @return the pagadoDescEs
	 */
	public String getPagadoDescEs() {
		return this.pagadoDescEs;
	}

	/**
	 * @param pagadoDescEs
	 *            the pagadoDescEs to set
	 */
	public void setPagadoDescEs(String pagadoDescEs) {
		this.pagadoDescEs = pagadoDescEs;
	}

	/**
	 * @return the pagadoDescEu
	 */
	public String getPagadoDescEu() {
		return this.pagadoDescEu;
	}

	/**
	 * @param pagadoDescEu
	 *            the pagadoDescEu to set
	 */
	public void setPagadoDescEu(String pagadoDescEu) {
		this.pagadoDescEu = pagadoDescEu;
	}

	/**
	 * @return the indPublicarBOPV
	 */
	public String getIndPublicarBOPV() {
		return this.indPublicarBOPV;
	}

	/**
	 * @param indPublicarBOPV
	 *            the indPublicarBOPV to set
	 */
	public void setIndPublicarBOPV(String indPublicarBOPV) {
		this.indPublicarBOPV = indPublicarBOPV;
	}

	/**
	 * @return the publicarBOPVDescEs
	 */
	public String getPublicarBOPVDescEs() {
		return this.publicarBOPVDescEs;
	}

	/**
	 * @param publicarBOPVDescEs
	 *            the publicarBOPVDescEs to set
	 */
	public void setPublicarBOPVDescEs(String publicarBOPVDescEs) {
		this.publicarBOPVDescEs = publicarBOPVDescEs;
	}

	/**
	 * @return the publicarBOPVDescEu
	 */
	public String getPublicarBOPVDescEu() {
		return this.publicarBOPVDescEu;
	}

	/**
	 * @param publicarBOPVDescEu
	 *            the publicarBOPVDescEu to set
	 */
	public void setPublicarBOPVDescEu(String publicarBOPVDescEu) {
		this.publicarBOPVDescEu = publicarBOPVDescEu;
	}

	/**
	 * @return the gestor
	 */
	public Aa79bGestorExpediente getGestor() {
		return this.gestor;
	}

	/**
	 * @param gestor
	 *            the gestor to set
	 */
	public void setGestor(Aa79bGestorExpediente gestor) {
		this.gestor = gestor;
	}

	/**
	 * @return the expedientesRelacionados
	 */
	public List<Aa79bExpedienteRelacionado> getExpedientesRelacionados() {
		return this.expedientesRelacionados;
	}

	/**
	 * @param expedientesRelacionados
	 *            the expedientesRelacionados to set
	 */
	public void setExpedientesRelacionados(List<Aa79bExpedienteRelacionado> expedientesRelacionados) {
		this.expedientesRelacionados = expedientesRelacionados;
	}

	/**
	 * @return the documentosExpediente
	 */
	public List<Aa79bDocumentoExpediente> getDocumentosExpediente() {
		return this.documentosExpediente;
	}

	/**
	 * @param documentosExpediente
	 *            the documentosExpediente to set
	 */
	public void setDocumentosExpediente(List<Aa79bDocumentoExpediente> documentosExpediente) {
		this.documentosExpediente = documentosExpediente;
	}

	/**
	 * @return the fechaDesdeSolicitud
	 */
	public Long getFechaDesdeSolicitud() {
		return this.fechaDesdeSolicitud;
	}

	/**
	 * @param fechaDesdeSolicitud
	 *            the fechaDesdeSolicitud to set
	 */
	public void setFechaDesdeSolicitud(Long fechaDesdeSolicitud) {
		this.fechaDesdeSolicitud = fechaDesdeSolicitud;
	}

	/**
	 * @return the fechaHastaSolicitud
	 */
	public Long getFechaHastaSolicitud() {
		return this.fechaHastaSolicitud;
	}

	/**
	 * @param fechaHastaSolicitud
	 *            the fechaHastaSolicitud to set
	 */
	public void setFechaHastaSolicitud(Long fechaHastaSolicitud) {
		this.fechaHastaSolicitud = fechaHastaSolicitud;
	}

	/**
	 * @return the fechaDesdeEntrega
	 */
	public Long getFechaDesdeEntrega() {
		return this.fechaDesdeEntrega;
	}

	/**
	 * @param fechaDesdeEntrega
	 *            the fechaDesdeEntrega to set
	 */
	public void setFechaDesdeEntrega(Long fechaDesdeEntrega) {
		this.fechaDesdeEntrega = fechaDesdeEntrega;
	}

	/**
	 * @return the fechaHastaEntrega
	 */
	public Long getFechaHastaEntrega() {
		return this.fechaHastaEntrega;
	}

	/**
	 * @param fechaHastaEntrega
	 *            the fechaHastaEntrega to set
	 */
	public void setFechaHastaEntrega(Long fechaHastaEntrega) {
		this.fechaHastaEntrega = fechaHastaEntrega;
	}

	/**
	 * @return the anyoExpRel
	 */
	public Long getAnyoExpRel() {
		return this.anyoExpRel;
	}

	/**
	 * @param anyoExpRel
	 *            the anyoExpRel to set
	 */
	public void setAnyoExpRel(Long anyoExpRel) {
		this.anyoExpRel = anyoExpRel;
	}

	/**
	 * @return the numExpExpRel
	 */
	public Integer getNumExpExpRel() {
		return this.numExpExpRel;
	}

	/**
	 * @param numExpExpRel
	 *            the numExpExpRel to set
	 */
	public void setNumExpExpRel(Integer numExpExpRel) {
		this.numExpExpRel = numExpExpRel;
	}

	/**
	 * @return the listaDocumentosStr
	 */
	public String getListaDocumentosStr() {
		return this.listaDocumentosStr;
	}

	/**
	 * @param listaDocumentosStr
	 *            the listaDocumentosStr to set
	 */
	public void setListaDocumentosStr(String listaDocumentosStr) {
		this.listaDocumentosStr = listaDocumentosStr;
	}

	/**
	 * @return the expRelSeleccionados
	 */
	public String getExpRelSeleccionados() {
		return this.expRelSeleccionados;
	}

	/**
	 * @param expRelSeleccionados
	 *            the expRelSeleccionados to set
	 */
	public void setExpRelSeleccionados(String expRelSeleccionados) {
		this.expRelSeleccionados = expRelSeleccionados;
	}

	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return this.origen;
	}

	/**
	 * @param origen
	 *            the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

	/**
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta
	 *            the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the horaAlta
	 */
	public String getHoraAlta() {
		return horaAlta;
	}

	/**
	 * @param horaAlta
	 *            the horaAlta to set
	 */
	public void setHoraAlta(String horaAlta) {
		this.horaAlta = horaAlta;
	}

	/**
	 * @return the estadoSubsanacion
	 */
	public Integer getEstadoSubsanacion() {
		return estadoSubsanacion;
	}

	/**
	 * @param estadoSubsanacion
	 *            the estadoSubsanacion to set
	 */
	public void setEstadoSubsanacion(Integer estadoSubsanacion) {
		this.estadoSubsanacion = estadoSubsanacion;
	}

	/**
	 * @return the indSubsanado
	 */
	public String getIndSubsanado() {
		return indSubsanado;
	}

	/**
	 * @param indSubsanado
	 *            the indSubsanado to set
	 */
	public void setIndSubsanado(String indSubsanado) {
		this.indSubsanado = indSubsanado;
	}

	/**
	 * @return the presupuestoAceptado
	 */
	public String getPresupuestoAceptado() {
		return presupuestoAceptado;
	}

	/**
	 * @param presupuestoAceptado
	 *            the presupuestoAceptado to set
	 */
	public void setPresupuestoAceptado(String presupuestoAceptado) {
		this.presupuestoAceptado = presupuestoAceptado;
	}

	/**
	 * @return the tieneDatosFacturacion
	 */
	public String getTieneDatosFacturacion() {
		return tieneDatosFacturacion;
	}

	/**
	 * @param tieneDatosFacturacion
	 *            the tieneDatosFacturacion to set
	 */
	public void setTieneDatosFacturacion(String tieneDatosFacturacion) {
		this.tieneDatosFacturacion = tieneDatosFacturacion;
	}

	/**
	 * @return the fechaSolicitudDate
	 */
	public Date getFechaSolicitudDate() {
		return fechaSolicitudDate;
	}

	/**
	 * @param fechaSolicitudDate
	 *            the fechaSolicitudDate to set
	 */
	public void setFechaSolicitudDate(Date fechaSolicitudDate) {
		this.fechaSolicitudDate = fechaSolicitudDate;
	}

	// INICIO - Métodos definidos para obtener la fecha y hora de solicitud en
	// el idioma correspondiente al llamar a WS desde AA06
	/**
	 * 
	 * @return String
	 */
	public String getFechaHoraSolicitudEu() {
		Locale locale = new Locale(Constants.LANG_EUSKERA);
		return DateUtils.obtFechaHoraFormateada(this.fechaSolicitudDate, this.horaSolicitud, locale);
	}

	/**
	 * 
	 * @return String
	 */
	public String getFechaHoraSolicitudEs() {
		Locale locale = new Locale(Constants.LANG_CASTELLANO);
		return DateUtils.obtFechaHoraFormateada(this.fechaSolicitudDate, this.horaSolicitud, locale);
	}
	// FIN - Métodos definidos para obtener la fecha y hora de solicitud en el
	// idioma correspondiente al llamar a WS desde AA06

	/**
	 * @return the facturaExpediente
	 */
	public List<Aa79bFacturaExpediente> getFacturaExpediente() {
		return facturaExpediente;
	}

	/**
	 * @param facturaExpediente
	 *            the facturaExpediente to set
	 */
	public void setFacturaExpediente(List<Aa79bFacturaExpediente> facturaExpediente) {
		this.facturaExpediente = facturaExpediente;
	}

	/**
	 * @return the anulacionRechazo
	 */
	public Aa79bAnulacionRechazo getAnulacionRechazo() {
		return anulacionRechazo;
	}

	/**
	 * @param anulacionRechazo
	 *            the anulacionRechazo to set
	 */
	public void setAnulacionRechazo(Aa79bAnulacionRechazo anulacionRechazo) {
		this.anulacionRechazo = anulacionRechazo;
	}

}