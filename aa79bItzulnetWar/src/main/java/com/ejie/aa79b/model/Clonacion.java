package com.ejie.aa79b.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.i18n.LocaleContextHolder;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.utils.Utils;

/**
 * @author UDA
 */

/**
 * @author javarona
 *
 */
public class Clonacion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Clave compuesta
	private Long id;
	private Long anyoOrig;
	private Integer numExpOrig;
	private Long anyoClon;
	private Integer numExpClon;
	private Date fecha;
	private Integer estado;
	private String estadoDesc;
	private String tipoExpedienteDesc;
	/* para el buscador */
	private String idTipoExpediente;
	private String tituloExpediente;

	/**
	 * Method 'Clonacion'.
	 */
	public Clonacion() {
		// Constructor vacio
	}

	public Clonacion(Long id) {
		this.id = id;
	}

	/**
	 * Method 'Clonacion'.
	 * 
	 * @param anyoOrig
	 *            Long
	 * @param numExpOrig
	 *            Integer
	 */
	public Clonacion(Long anyoOrig, Integer numExpOrig) {
		this.anyoOrig = anyoOrig;
		this.numExpOrig = numExpOrig;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the anyoOrig
	 */
	public Long getAnyoOrig() {
		return anyoOrig;
	}

	/**
	 * @param anyoOrig
	 *            the anyoOrig to set
	 */
	public void setAnyoOrig(Long anyoOrig) {
		this.anyoOrig = anyoOrig;
	}

	/**
	 * @return the numExpOrig
	 */
	public Integer getNumExpOrig() {
		return numExpOrig;
	}

	/**
	 * @param numExpOrig
	 *            the numExpOrig to set
	 */
	public void setNumExpOrig(Integer numExpOrig) {
		this.numExpOrig = numExpOrig;
	}

	/**
	 * @return the anyoClon
	 */
	public Long getAnyoClon() {
		return anyoClon;
	}

	/**
	 * @param anyoClon
	 *            the anyoClon to set
	 */
	public void setAnyoClon(Long anyoClon) {
		this.anyoClon = anyoClon;
	}

	/**
	 * @return the numExpClon
	 */
	public Integer getNumExpClon() {
		return numExpClon;
	}

	/**
	 * @param numExpClon
	 *            the numExpClon to set
	 */
	public void setNumExpClon(Integer numExpClon) {
		this.numExpClon = numExpClon;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the estado
	 */
	public Integer getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	/**
	 * 
	 * @return
	 */
	public String getAnyoNumExpOrigConcatenado() {
		return Utils.getAnyoNumExpConcatenado(this.anyoOrig, this.numExpOrig);
	}

	/**
	 * 
	 * @return
	 */
	public String getAnyoNumExpClonConcatenado() {
		return Utils.getAnyoNumExpConcatenado(this.anyoClon, this.numExpClon);
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
	 * @return the tituloExpediente
	 */
	public String getTituloExpediente() {
		return tituloExpediente;
	}

	/**
	 * @param tituloExpediente
	 *            the tituloExpediente to set
	 */
	public void setTituloExpediente(String tituloExpediente) {
		this.tituloExpediente = tituloExpediente;
	}

	/**
	 * @return the estadoDesc
	 */
	public String getEstadoDesc() {
		return estadoDesc;
	}

	/**
	 * @param estadoDesc
	 *            the estadoDesc to set
	 */
	public void setEstadoDesc(String estadoDesc) {
		this.estadoDesc = estadoDesc;
	}

	/**
	 * @return the tipoExpedienteDesc
	 */
	public String getTipoExpedienteDesc() {
		return tipoExpedienteDesc;
	}

	/**
	 * @param tipoExpedienteDesc
	 *            the tipoExpedienteDesc to set
	 */
	public void setTipoExpedienteDesc(String tipoExpedienteDesc) {
		this.tipoExpedienteDesc = tipoExpedienteDesc;
	}

	public String getFechaHora() {
		String fechaFormateada = "";
		if (this.fecha != null) {
			SimpleDateFormat formato;
			if (Constants.LANG_CASTELLANO.equals(LocaleContextHolder.getLocale())) {
				formato = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_ES + " " + Constants.HORA_FORMAT);
			} else {
				formato = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_EU + " " + Constants.HORA_FORMAT);
			}
			fechaFormateada = formato.format(this.fecha);
		}
		return fechaFormateada;
	}
}
