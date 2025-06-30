package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TareaTrados implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long anyo;
	private Integer numExp;
	private BigDecimal idTarea;
	private Long idRequerimiento;
	private String numExpFormated;
	private String idTipoExpediente;
	private String tipoExpedienteDescEs;
	private String tipoExpedienteDescEu;
	private String indPresupuesto;
	private String titulo;
	private Date fechaAlta;
	private String horaAlta;
	private IdiomaDestino idioma;
	private TipoRelevancia relevancia;
	private String indUrgente;
	private String urgenteDescEs;
	private String urgenteDescEu;
	private String indPublicarBopv;
	private String indPrevistoBopv;
	private String publicarBopvDescEs;
	private String publicarBopvDescEu;
	private String indDificultad;
	private String dificultadDescEs;
	private String dificultadDescEu;
	private Integer numPalSolic;
	private Integer numPalIzo;
	private Date fechaFinalIzo;
	private String horaFinalIzo;
	private DatosTareaTrados datosTareaTrados;
	private List<DatosTareaTradosDocs> lDocsTarea;
	private List<MetadatosBusqueda> lMetadatos;
	private boolean posibleNegociarNuevaFechaEntregaIzo = false;
	private boolean palXmlSupPalIzoSupDesfaseAsumible = false;
	private String errorMsg;
	private Date fechaLimite;
	private String horaLimite;
	private Date fechaLimiteSeleccionable;
	private String horaLimiteSeleccionable;

	public TareaTrados() {
		// constructor vacio
	}

	/**
	 * @return the anyo
	 */
	public Long getAnyo() {
		return anyo;
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
		return numExp;
	}

	/**
	 * @param numExp
	 *            the numExp to set
	 */
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	/**
	 * @return the idTarea
	 */
	public BigDecimal getIdTarea() {
		return idTarea;
	}

	/**
	 * @param idTarea
	 *            the idTarea to set
	 */
	public void setIdTarea(BigDecimal idTarea) {
		this.idTarea = idTarea;
	}

	/**
	 * @return the idRequerimiento
	 */
	public Long getIdRequerimiento() {
		return idRequerimiento;
	}

	/**
	 * @param idRequerimiento
	 *            the idRequerimiento to set
	 */
	public void setIdRequerimiento(Long idRequerimiento) {
		this.idRequerimiento = idRequerimiento;
	}

	/**
	 * @return the numExpFormated
	 */
	public String getNumExpFormated() {
		return numExpFormated;
	}

	/**
	 * @param numExpFormated
	 *            the numExpFormated to set
	 */
	public void setNumExpFormated(String numExpFormated) {
		this.numExpFormated = numExpFormated;
	}

	/**
	 * @return the idTipoExpediente
	 */
	public String getIdTipoExpediente() {
		return idTipoExpediente;
	}

	/**
	 * @param idTipoExpediente
	 *            the idTipoExpediente to set
	 */
	public void setIdTipoExpediente(String idTipoExpediente) {
		this.idTipoExpediente = idTipoExpediente;
	}

	/**
	 * @return the tipoExpedienteDescEs
	 */
	public String getTipoExpedienteDescEs() {
		return tipoExpedienteDescEs;
	}

	/**
	 * @param tipoExpedienteDescEs
	 *            the tipoExpedienteDescEs to set
	 */
	public void setTipoExpedienteDescEs(String tipoExpedienteDescEs) {
		this.tipoExpedienteDescEs = tipoExpedienteDescEs;
	}

	/**
	 * @return the tipoExpedienteDescEu
	 */
	public String getTipoExpedienteDescEu() {
		return tipoExpedienteDescEu;
	}

	/**
	 * @param tipoExpedienteDescEu
	 *            the tipoExpedienteDescEu to set
	 */
	public void setTipoExpedienteDescEu(String tipoExpedienteDescEu) {
		this.tipoExpedienteDescEu = tipoExpedienteDescEu;
	}

	/**
	 * @return the indPresupuesto
	 */
	public String getIndPresupuesto() {
		return indPresupuesto;
	}

	/**
	 * @param indPresupuesto
	 *            the indPresupuesto to set
	 */
	public void setIndPresupuesto(String indPresupuesto) {
		this.indPresupuesto = indPresupuesto;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	 * @return the idioma
	 */
	public IdiomaDestino getIdioma() {
		return idioma;
	}

	/**
	 * @param idioma
	 *            the idioma to set
	 */
	public void setIdioma(IdiomaDestino idioma) {
		this.idioma = idioma;
	}

	/**
	 * @return the relevancia
	 */
	public TipoRelevancia getRelevancia() {
		return relevancia;
	}

	/**
	 * @param relevancia
	 *            the relevancia to set
	 */
	public void setRelevancia(TipoRelevancia relevancia) {
		this.relevancia = relevancia;
	}

	/**
	 * @return the indUrgente
	 */
	public String getIndUrgente() {
		return indUrgente;
	}

	/**
	 * @param indUrgente
	 *            the indUrgente to set
	 */
	public void setIndUrgente(String indUrgente) {
		this.indUrgente = indUrgente;
	}

	/**
	 * @return the urgenteDescEs
	 */
	public String getUrgenteDescEs() {
		return urgenteDescEs;
	}

	/**
	 * @param urgenteDescEs
	 *            the urgenteDescEs to set
	 */
	public void setUrgenteDescEs(String urgenteDescEs) {
		this.urgenteDescEs = urgenteDescEs;
	}

	/**
	 * @return the urgenteDescEu
	 */
	public String getUrgenteDescEu() {
		return urgenteDescEu;
	}

	/**
	 * @param urgenteDescEu
	 *            the urgenteDescEu to set
	 */
	public void setUrgenteDescEu(String urgenteDescEu) {
		this.urgenteDescEu = urgenteDescEu;
	}

	/**
	 * @return the indPublicarBopv
	 */
	public String getIndPublicarBopv() {
		return indPublicarBopv;
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
	 * @return the publicarBopvDescEs
	 */
	public String getPublicarBopvDescEs() {
		return publicarBopvDescEs;
	}

	/**
	 * @param publicarBopvDescEs
	 *            the publicarBopvDescEs to set
	 */
	public void setPublicarBopvDescEs(String publicarBopvDescEs) {
		this.publicarBopvDescEs = publicarBopvDescEs;
	}

	/**
	 * @return the publicarBopvDescEu
	 */
	public String getPublicarBopvDescEu() {
		return publicarBopvDescEu;
	}

	/**
	 * @param publicarBopvDescEu
	 *            the publicarBopvDescEu to set
	 */
	public void setPublicarBopvDescEu(String publicarBopvDescEu) {
		this.publicarBopvDescEu = publicarBopvDescEu;
	}

	/**
	 * @return the indDificultad
	 */
	public String getIndDificultad() {
		return indDificultad;
	}

	/**
	 * @param indDificultad
	 *            the indDificultad to set
	 */
	public void setIndDificultad(String indDificultad) {
		this.indDificultad = indDificultad;
	}

	/**
	 * @return the dificultadDescEs
	 */
	public String getDificultadDescEs() {
		return dificultadDescEs;
	}

	/**
	 * @param dificultadDescEs
	 *            the dificultadDescEs to set
	 */
	public void setDificultadDescEs(String dificultadDescEs) {
		this.dificultadDescEs = dificultadDescEs;
	}

	/**
	 * @return the dificultadDescEu
	 */
	public String getDificultadDescEu() {
		return dificultadDescEu;
	}

	/**
	 * @param dificultadDescEu
	 *            the dificultadDescEu to set
	 */
	public void setDificultadDescEu(String dificultadDescEu) {
		this.dificultadDescEu = dificultadDescEu;
	}

	/**
	 * @return the numPalSolic
	 */
	public Integer getNumPalSolic() {
		return numPalSolic;
	}

	/**
	 * @param numPalSolic
	 *            the numPalSolic to set
	 */
	public void setNumPalSolic(Integer numPalSolic) {
		this.numPalSolic = numPalSolic;
	}

	/**
	 * @return the numPalIzo
	 */
	public Integer getNumPalIzo() {
		return numPalIzo;
	}

	/**
	 * @param numPalIzo
	 *            the numPalIzo to set
	 */
	public void setNumPalIzo(Integer numPalIzo) {
		this.numPalIzo = numPalIzo;
	}

	/**
	 * @return the fechaFinalIzo
	 */
	public Date getFechaFinalIzo() {
		return fechaFinalIzo;
	}

	/**
	 * @param fechaFinalIzo
	 *            the fechaFinalIzo to set
	 */
	public void setFechaFinalIzo(Date fechaFinalIzo) {
		this.fechaFinalIzo = fechaFinalIzo;
	}

	/**
	 * @return the horaFinalIzo
	 */
	public String getHoraFinalIzo() {
		return horaFinalIzo;
	}

	/**
	 * @param horaFinalIzo
	 *            the horaFinalIzo to set
	 */
	public void setHoraFinalIzo(String horaFinalIzo) {
		this.horaFinalIzo = horaFinalIzo;
	}

	/**
	 * @return the datosTareaTrados
	 */
	public DatosTareaTrados getDatosTareaTrados() {
		return datosTareaTrados;
	}

	/**
	 * @param datosTareaTrados
	 *            the datosTareaTrados to set
	 */
	public void setDatosTareaTrados(DatosTareaTrados datosTareaTrados) {
		this.datosTareaTrados = datosTareaTrados;
	}

	/**
	 * @return the lDocsTarea
	 */
	public List<DatosTareaTradosDocs> getlDocsTarea() {
		return lDocsTarea;
	}

	/**
	 * @param lDocsTarea
	 *            the lDocsTarea to set
	 */
	public void setlDocsTarea(List<DatosTareaTradosDocs> lDocsTarea) {
		this.lDocsTarea = lDocsTarea;
	}

	/**
	 * @return the lMetadatos
	 */
	public List<MetadatosBusqueda> getlMetadatos() {
		return lMetadatos;
	}

	/**
	 * @param lMetadatos
	 *            the lMetadatos to set
	 */
	public void setlMetadatos(List<MetadatosBusqueda> lMetadatos) {
		this.lMetadatos = lMetadatos;
	}

	/**
	 * @return the posibleNegociarNuevaFechaEntregaIzo
	 */
	public boolean isPosibleNegociarNuevaFechaEntregaIzo() {
		return posibleNegociarNuevaFechaEntregaIzo;
	}

	/**
	 * @param posibleNegociarNuevaFechaEntregaIzo
	 *            the posibleNegociarNuevaFechaEntregaIzo to set
	 */
	public void setPosibleNegociarNuevaFechaEntregaIzo(boolean posibleNegociarNuevaFechaEntregaIzo) {
		this.posibleNegociarNuevaFechaEntregaIzo = posibleNegociarNuevaFechaEntregaIzo;
	}

	/**
	 * @return the palXmlSupPalIzoSupDesfaseAsumible
	 */
	public boolean isPalXmlSupPalIzoSupDesfaseAsumible() {
		return palXmlSupPalIzoSupDesfaseAsumible;
	}

	/**
	 * @param palXmlSupPalIzoSupDesfaseAsumible
	 *            the palXmlSupPalIzoSupDesfaseAsumible to set
	 */
	public void setPalXmlSupPalIzoSupDesfaseAsumible(boolean palXmlSupPalIzoSupDesfaseAsumible) {
		this.palXmlSupPalIzoSupDesfaseAsumible = palXmlSupPalIzoSupDesfaseAsumible;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg
	 *            the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the fechaLimite
	 */
	public Date getFechaLimite() {
		return fechaLimite;
	}

	/**
	 * @param fechaLimite
	 *            the fechaLimite to set
	 */
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	/**
	 * @return the horaLimite
	 */
	public String getHoraLimite() {
		return horaLimite;
	}

	/**
	 * @param horaLimite
	 *            the horaLimite to set
	 */
	public void setHoraLimite(String horaLimite) {
		this.horaLimite = horaLimite;
	}

	/**
	 * @return the fechaLimiteSeleccionable
	 */
	public Date getFechaLimiteSeleccionable() {
		return fechaLimiteSeleccionable;
	}

	/**
	 * @param fechaLimiteSeleccionable
	 *            the fechaLimiteSeleccionable to set
	 */
	public void setFechaLimiteSeleccionable(Date fechaLimiteSeleccionable) {
		this.fechaLimiteSeleccionable = fechaLimiteSeleccionable;
	}

	/**
	 * @return the horaLimiteRDateSeleccionable
	 */
	public String getHoraLimiteSeleccionable() {
		return horaLimiteSeleccionable;
	}

	/**
	 * @param horaLimiteSeleccionable
	 *            the horaLimiteSeleccionable to set
	 */
	public void setHoraLimiteSeleccionable(String horaLimiteSeleccionable) {
		this.horaLimiteSeleccionable = horaLimiteSeleccionable;
	}

}
