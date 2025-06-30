package com.ejie.aa79b.model.webservices;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ejie.aa79b.model.DatosSalidaWS;
import com.ejie.aa79b.model.FormatosFichero;
import com.ejie.aa79b.utils.Utils;

/**
 * Aa79bSalidaTarea
 *
 * @author aresua
 */
public class Aa79bSalidaTarea extends DatosSalidaWS implements java.io.Serializable {

	private static final long serialVersionUID = 6914946314137644266L;
	private BigDecimal idTarea;
	private Long anyo;
	private Integer numExp;
	private Long fechaIni;
	private String horaIni;
	private Aa79bEjecucionTareas ejecucionTareas;
	private Aa79bTiposTarea tipoTarea;
	private Aa79bTiposRevision tipoRevision;
	private Aa79bLotes lotes;
	private Aa79bDatosContacto datosContacto;
	private List<Aa79bDocumentoTarea> documentos;
	private Aa79bDatosTareaTrados datosTrados;
	private Long fechaFin;
	private String horaFin;
	private String estadoEjecucionDesc;
	private String observaciones;
	private Aa79bTareasGestionInterpretacion gestionInterpretacion;
	private Aa79bDatosPagoProveedores datosPagoProveedor;
	private String tareaEjecutable;
	private BigDecimal idTareaRel;
	private Long idIdiomaDestino;
	private String indRestrasada;
	private List<FormatosFichero> formatosFichero;
	private String indConfidencialExp;
	private String indMostrarNotasATrad;
	private Aa79bNotasTarea notasTarea;

	/**
	 * @return the idTarea
	 */
	public BigDecimal getIdTarea() {
		return this.idTarea;
	}

	/**
	 * @param idTarea the idTarea to set
	 */
	public void setIdTarea(BigDecimal idTarea) {
		this.idTarea = idTarea;
	}

	/**
	 * @return the anyo
	 */
	public Long getAnyo() {
		return this.anyo;
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
		return this.numExp;
	}

	/**
	 * @param numExp the numExp to set
	 */

	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	/**
	 * @return the datosContacto
	 */
	public Aa79bDatosContacto getDatosContacto() {
		return this.datosContacto;
	}

	/**
	 * @param datosContacto the datosContacto to set
	 */
	public void setDatosContacto(Aa79bDatosContacto datosContacto) {
		this.datosContacto = datosContacto;
	}

	/**
	 * @return the ejecucionTareas
	 */
	public Aa79bEjecucionTareas getEjecucionTareas() {
		return this.ejecucionTareas;
	}

	/**
	 * @param ejecucionTareas the ejecucionTareas to set
	 */
	public void setEjecucionTareas(Aa79bEjecucionTareas ejecucionTareas) {
		this.ejecucionTareas = ejecucionTareas;
	}

	/**
	 * @return the tiposTarea
	 */
	public Aa79bTiposTarea getTipoTarea() {
		return this.tipoTarea;
	}

	/**
	 * @param tiposTarea the tiposTarea to set
	 */
	public void setTipoTarea(Aa79bTiposTarea tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	public Aa79bTiposRevision getTipoRevision() {
		return this.tipoRevision;
	}

	public void setTipoRevision(Aa79bTiposRevision tipoRevision) {
		this.tipoRevision = tipoRevision;
	}

	/**
	 * @return the lotes
	 */
	public Aa79bLotes getLotes() {
		return this.lotes;
	}

	/**
	 * @param lotes the lotes to set
	 */
	public void setLotes(Aa79bLotes lotes) {
		this.lotes = lotes;
	}

	/**
	 * @return the documentos
	 */
	public List<Aa79bDocumentoTarea> getDocumentos() {
		return this.documentos;
	}

	/**
	 * @param documentos the documentos to set
	 */
	public void setDocumentos(List<Aa79bDocumentoTarea> documentos) {
		this.documentos = documentos;
	}

	/**
	 * @return the datosTrados
	 */
	public Aa79bDatosTareaTrados getDatosTrados() {
		return this.datosTrados;
	}

	/**
	 * @param datosTrados the datosTrados to set
	 */
	public void setDatosTrados(Aa79bDatosTareaTrados datosTrados) {
		this.datosTrados = datosTrados;
	}

	/**
	 * @return the fechaIni
	 */
	public Long getFechaIni() {
		return this.fechaIni;
	}

	/**
	 * @param fechaIni the fechaIni to set
	 */
	public void setFechaIni(Long fechaIni) {
		this.fechaIni = fechaIni;
	}

	/**
	 * @return the fechaFin
	 */
	public Long getFechaFin() {
		return this.fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Long fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the horaFin
	 */
	public String getHoraFin() {
		return this.horaFin;
	}

	/**
	 * @param horaFin the horaFin to set
	 */
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	/**
	 * @return the estadoEjecucionDesc
	 */
	public String getEstadoEjecucionDesc() {
		return this.estadoEjecucionDesc;
	}

	/**
	 * @param estadoEjecucionDesc the estadoEjecucionDesc to set
	 */
	public void setEstadoEjecucionDesc(String estadoEjecucionDesc) {
		this.estadoEjecucionDesc = estadoEjecucionDesc;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return this.observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getHoraIni() {
		return this.horaIni;
	}

	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}

	public Aa79bTareasGestionInterpretacion getGestionInterpretacion() {
		return this.gestionInterpretacion;
	}

	public void setGestionInterpretacion(Aa79bTareasGestionInterpretacion gestionInterpretacion) {
		this.gestionInterpretacion = gestionInterpretacion;
	}

	public Aa79bDatosPagoProveedores getDatosPagoProveedor() {
		return this.datosPagoProveedor;
	}

	public void setDatosPagoProveedor(Aa79bDatosPagoProveedores datosPagoProveedor) {
		this.datosPagoProveedor = datosPagoProveedor;
	}

	/**
	 * @return the tareaEjecutable
	 */
	public String getTareaEjecutable() {
		return this.tareaEjecutable;
	}

	/**
	 * @param tareaEjecutable the tareaEjecutable to set
	 */
	public void setTareaEjecutable(String tareaEjecutable) {
		this.tareaEjecutable = tareaEjecutable;
	}

	public BigDecimal getIdTareaRel() {
		return this.idTareaRel;
	}

	public void setIdTareaRel(BigDecimal idTareaRel) {
		this.idTareaRel = idTareaRel;
	}

	/**
	 * @return the idIdiomaDestino
	 */
	public Long getIdIdiomaDestino() {
		return this.idIdiomaDestino;
	}

	/**
	 * @param idIdiomaDestino the idIdiomaDestino to set
	 */
	public void setIdIdiomaDestino(Long idIdiomaDestino) {
		this.idIdiomaDestino = idIdiomaDestino;
	}

	public String getAnyoNumExp() {
		return Utils.getAnyoNumExpConcatenado(this.anyo, this.numExp);
	}

	public void setAnyoNumExpConcatenado(String codigo) {
		String[] aux = StringUtils.split(codigo, "/");
		if (aux.length == 4) {
			Integer i = 0;
			this.anyo = Long.parseLong(aux[i++]);
			this.numExp = Integer.parseInt(aux[i++]);
		}
	}

	/**
	 * @return the indRestrasada
	 */
	public String getIndRestrasada() {
		return this.indRestrasada;
	}

	/**
	 * @param indRestrasada the indRestrasada to set
	 */
	public void setIndRestrasada(String indRestrasada) {
		this.indRestrasada = indRestrasada;
	}

	/**
	 * @return the formatosFichero
	 */
	public List<FormatosFichero> getFormatosFichero() {
		return this.formatosFichero;
	}

	/**
	 * @param formatosFichero the formatosFichero to set
	 */
	public void setFormatosFichero(List<FormatosFichero> formatosFichero) {
		this.formatosFichero = formatosFichero;
	}

	/**
	 * @return the indConfidencialExp
	 */
	public String getIndConfidencialExp() {
		return this.indConfidencialExp;
	}

	/**
	 * @param indConfidencialExp the indConfidencialExp to set
	 */
	public void setIndConfidencialExp(String indConfidencialExp) {
		this.indConfidencialExp = indConfidencialExp;
	}

	public String getIndMostrarNotasATrad() {
		return this.indMostrarNotasATrad;
	}

	public void setIndMostrarNotasATrad(String indMostrarNotasATrad) {
		this.indMostrarNotasATrad = indMostrarNotasATrad;
	}

	public Aa79bNotasTarea getNotasTarea() {
		return this.notasTarea;
	}

	public void setNotasTarea(Aa79bNotasTarea notasTarea) {
		this.notasTarea = notasTarea;
	}

}
