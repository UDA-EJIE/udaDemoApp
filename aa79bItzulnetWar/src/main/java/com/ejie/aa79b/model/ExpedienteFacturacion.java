package com.ejie.aa79b.model;

import java.util.Date;

import com.ejie.aa79b.common.Constants;
import com.ejie.x38.serialization.JsonDateDeserializer;
import com.ejie.x38.serialization.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author mrodriguez
 *
 */
public class ExpedienteFacturacion extends Expediente implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer numPalabrasIZO;
	private Date fechaEntrega;
	private String horaEntrega;

	// Para el buscador
	private Date fechaAltaDesde;
	private Date fechaAltaHasta;

	private Gestor entidadContactoFactura;

	private DatosFacturacionExpedienteInterpretacion datosFacturacionInterpretacion;

	private DatosFacturacionCliente datosFacturacionCliente;
	private String titOrdenPreciosPublicos;
	private String indIva;
	private String entidadYContactoFacturacionConcatenadosEs;
	private String entidadYContactoFacturacionConcatenadosEu;
	private Integer numPalabras;
	private Long porIvaOrdenPreciosPublicos;
	private Long porRecargoDif;
	private Long porRecargoUrg;
	private Integer porFactura;
	private Long idFactura;

	public ExpedienteFacturacion() {
		// Constructor vacio
	}

	public Integer getNumPalabrasIZO() {
		return numPalabrasIZO;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public String getHoraEntrega() {
		return horaEntrega;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaAltaDesde() {
		return fechaAltaDesde;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaAltaHasta() {
		return fechaAltaHasta;
	}

	public void setNumPalabrasIZO(Integer numPalabrasIZO) {
		this.numPalabrasIZO = numPalabrasIZO;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public void setHoraEntrega(String horaEntrega) {
		this.horaEntrega = horaEntrega;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaAltaDesde(Date fechaAltaDesde) {
		this.fechaAltaDesde = fechaAltaDesde;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaAltaHasta(Date fechaAltaHasta) {
		this.fechaAltaHasta = fechaAltaHasta;
	}

	/**
	 * @return the entidadContactoFactura
	 */
	public Gestor getEntidadContactoFactura() {
		return entidadContactoFactura;
	}

	/**
	 * @param entidadContactoFactura
	 *            the entidadContactoFactura to set
	 */
	public void setEntidadContactoFactura(Gestor entidadContactoFactura) {
		this.entidadContactoFactura = entidadContactoFactura;
	}

	/**
	 * @return the datosFacturacionInterpretacion
	 */
	public DatosFacturacionExpedienteInterpretacion getDatosFacturacionInterpretacion() {
		return datosFacturacionInterpretacion;
	}

	/**
	 * @param datosFacturacionInterpretacion
	 *            the datosFacturacionInterpretacion to set
	 */
	public void setDatosFacturacionInterpretacion(
			DatosFacturacionExpedienteInterpretacion datosFacturacionInterpretacion) {
		this.datosFacturacionInterpretacion = datosFacturacionInterpretacion;
	}

	/**
	 * @return the datosFacturacionCliente
	 */
	public DatosFacturacionCliente getDatosFacturacionCliente() {
		return datosFacturacionCliente;
	}

	/**
	 * @param datosFacturacionCliente
	 *            the datosFacturacionCliente to set
	 */
	public void setDatosFacturacionCliente(DatosFacturacionCliente datosFacturacionCliente) {
		this.datosFacturacionCliente = datosFacturacionCliente;
	}

	/**
	 * @return the titOrdenPreciosPublicos
	 */
	public String getTitOrdenPreciosPublicos() {
		return titOrdenPreciosPublicos;
	}

	/**
	 * @param titOrdenPreciosPublicos
	 *            the titOrdenPreciosPublicos to set
	 */
	public void setTitOrdenPreciosPublicos(String titOrdenPreciosPublicos) {
		this.titOrdenPreciosPublicos = titOrdenPreciosPublicos;
	}

	/**
	 * @return the indIva
	 */
	public String getIndIva() {
		return indIva;
	}

	/**
	 * @param indIva
	 *            the indIva to set
	 */
	public void setIndIva(String indIva) {
		this.indIva = indIva;
	}

	/**
	 * @return the entidadYContactoFacturacionConcatenados
	 */
	public String getEntidadYContactoFacturacionConcatenadosEs() {
		return entidadYContactoFacturacionConcatenadosEs;
	}

	/**
	 * @param entidadYContactoFacturacionConcatenados
	 *            the entidadYContactoFacturacionConcatenados to set
	 */
	public void setEntidadYContactoFacturacionConcatenadosEs(String entidadYContactoFacturacionConcatenados) {
		this.entidadYContactoFacturacionConcatenadosEs = entidadYContactoFacturacionConcatenados;
	}

	/**
	 * @return the entidadYContactoFacturacionConcatenados
	 */
	public String getEntidadYContactoFacturacionConcatenadosEu() {
		return entidadYContactoFacturacionConcatenadosEu;
	}

	/**
	 * @param entidadYContactoFacturacionConcatenados
	 *            the entidadYContactoFacturacionConcatenados to set
	 */
	public void setEntidadYContactoFacturacionConcatenadosEu(String entidadYContactoFacturacionConcatenados) {
		this.entidadYContactoFacturacionConcatenadosEu = entidadYContactoFacturacionConcatenados;
	}

	/**
	 * @return the numPalabras
	 */
	public Integer getNumPalabras() {
		return numPalabras;
	}

	/**
	 * @param numPalabras
	 *            the numPalabras to set
	 */
	public void setNumPalabras(Integer numPalabras) {
		this.numPalabras = numPalabras;
	}

	/**
	 * @return the porIvaOrdenPreciosPublicos
	 */
	public Long getPorIvaOrdenPreciosPublicos() {
		return porIvaOrdenPreciosPublicos;
	}

	/**
	 * @param porIvaOrdenPreciosPublicos
	 *            the porIvaOrdenPreciosPublicos to set
	 */
	public void setPorIvaOrdenPreciosPublicos(Long porIvaOrdenPreciosPublicos) {
		this.porIvaOrdenPreciosPublicos = porIvaOrdenPreciosPublicos;
	}

	/**
	 * @return the porRecargoDif
	 */
	public Long getPorRecargoDif() {
		return porRecargoDif;
	}

	/**
	 * @param porRecargoDif
	 *            the porRecargoDif to set
	 */
	public void setPorRecargoDif(Long porRecargoDif) {
		this.porRecargoDif = porRecargoDif;
	}

	/**
	 * @return the porRecargoUrg
	 */
	public Long getPorRecargoUrg() {
		return porRecargoUrg;
	}

	/**
	 * @param porRecargoUrg
	 *            the porRecargoUrg to set
	 */
	public void setPorRecargoUrg(Long porRecargoUrg) {
		this.porRecargoUrg = porRecargoUrg;
	}

	/**
	 * @return the entidadContactoFacturaCell
	 */
	public String getEntidadContactoFacturaCell() {
		StringBuilder sbEntContFact = new StringBuilder(Constants.SB_INIT);
		if (this.getEntidadContactoFactura() != null && this.getEntidadContactoFactura().getEntidad() != null) {
			sbEntContFact.append("<div><p class=\"nomb-entidad\">");
			if (this.getEntidadContactoFactura().getEntidad().getDescEu() != null) {
				sbEntContFact.append(this.getEntidadContactoFactura().getEntidad().getDescEu());
			} else {
				sbEntContFact.append(" - ");
			}
			sbEntContFact.append("<br /></p>");

			if (this.getGestorExpediente() != null && this.getGestorExpediente().getSolicitante() != null
					&& this.getGestorExpediente().getSolicitante().getNombreCompleto() != null) {
				sbEntContFact.append("<p class=\"nomb-gestor\">");
				sbEntContFact.append(this.getGestorExpediente().getSolicitante().getNombreCompleto());

				if ((this.getGestorExpediente().getSolicitante().getSolicitanteVinculado() == null
						|| !this.getGestorExpediente().getSolicitante().getSolicitanteVinculado())
						&& this.getGestorExpediente().getSolicitante().getDni() != null) {
					sbEntContFact
							.append(" <i class=\"fa fa-exclamation-circle marginRigt5\" aria-hidden=\"true\"></i> ");
				}

				sbEntContFact.append("</p>");
			}

			sbEntContFact.append("</div>");
		}
		return sbEntContFact.toString();
	}

	/**
	 * @return the porFactura
	 */
	public Integer getPorFactura() {
		return porFactura;
	}

	/**
	 * @param porFactura
	 *            the porFactura to set
	 */
	public void setPorFactura(Integer porFactura) {
		this.porFactura = porFactura;
	}

	/**
	 * @return the idFactura
	 */
	public Long getIdFactura() {
		return idFactura;
	}

	/**
	 * @param idFactura the idFactura to set
	 */
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}
}
