package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.JsonNumbersDeserializer;
import com.ejie.aa79b.common.jackson.JsonImporteSerializer;
import com.ejie.aa79b.common.jackson.JsonTarifaSerializer;
import com.ejie.aa79b.utils.GeneralUtils;
import com.ejie.aa79b.utils.QueryUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Aa79bDatosFacturacionExpediente
 *
 * @author mrodriguez
 */
public class Aa79bDatosFacturacionExpediente implements Serializable {

	private static final long serialVersionUID = -2587131535469818933L;

	private Integer numTotalPalFacturar;
	private Integer numPalConcor084;
	private Integer numPalConcor8594;
	private Integer numPalConcor95100;
	private Integer numPalConcor9599;
	private Integer numPalConcor100;
	private BigDecimal tarifaPal;
	private BigDecimal importeBase;
	private BigDecimal importeDificultad;
	private BigDecimal importeUrgencia;
	private String importeDificultadLabelEs;
	private String importeDificultadLabelEu;
	private String importeUrgenciaLabelEs;
	private String importeUrgenciaLabelEu;
	private BigDecimal importeIva;
	private BigDecimal importeTotal;

	/**
	 * @return the numTotalPalFacturar
	 */
	public Integer getNumTotalPalFacturar() {
		return this.numTotalPalFacturar;
	}

	/**
	 * @param numTotalPalFacturar
	 *            the numTotalPalFacturar to set
	 */
	public void setNumTotalPalFacturar(Integer numTotalPalFacturar) {
		this.numTotalPalFacturar = numTotalPalFacturar;
	}

	/**
	 * @return the numPalConcor084
	 */
	public Integer getNumPalConcor084() {
		return this.numPalConcor084;
	}

	/**
	 * @param numPalConcor084
	 *            the numPalConcor084 to set
	 */
	public void setNumPalConcor084(Integer numPalConcor084) {
		this.numPalConcor084 = numPalConcor084;
	}

	/**
	 * @return the numPalConcor8594
	 */
	public Integer getNumPalConcor8594() {
		return this.numPalConcor8594;
	}

	/**
	 * @param numPalConcor8594
	 *            the numPalConcor8594 to set
	 */
	public void setNumPalConcor8594(Integer numPalConcor8594) {
		this.numPalConcor8594 = numPalConcor8594;
	}

	/**
	 * @return the numPalConcor95100
	 */
	public Integer getNumPalConcor95100() {
		return this.numPalConcor95100;
	}

	/**
	 * @param numPalConcor95100
	 *            the numPalConcor95100 to set
	 */
	public void setNumPalConcor95100(Integer numPalConcor95100) {
		this.numPalConcor95100 = numPalConcor95100;
	}

	/**
	 * @return the tarifaPal
	 */
	@JsonSerialize(using = JsonTarifaSerializer.class)
	public BigDecimal getTarifaPal() {
		return this.tarifaPal;
	}

	/**
	 * @param tarifaPal
	 *            the tarifaPal to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setTarifaPal(BigDecimal tarifaPal) {
		this.tarifaPal = tarifaPal;
	}

	/**
	 * @return the importeBase
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporteBase() {
		return this.importeBase;
	}

	/**
	 * @param importeBase
	 *            the importeBase to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setImporteBase(BigDecimal importeBase) {
		this.importeBase = importeBase;
	}

	/**
	 * @return the importeDificultad
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporteDificultad() {
		return this.importeDificultad;
	}

	/**
	 * @param importeDificultad
	 *            the importeDificultad to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setImporteDificultad(BigDecimal importeDificultad) {
		this.importeDificultad = importeDificultad;
	}

	/**
	 * @return the importeUrgencia
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporteUrgencia() {
		return this.importeUrgencia;
	}

	/**
	 * @param importeUrgencia
	 *            the importeUrgencia to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setImporteUrgencia(BigDecimal importeUrgencia) {
		this.importeUrgencia = importeUrgencia;
	}

	/**
	 * @return the importeIva
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporteIva() {
		return this.importeIva;
	}

	/**
	 * @param importeIva
	 *            the importeIva to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setImporteIva(BigDecimal importeIva) {
		this.importeIva = importeIva;
	}

	/**
	 * @return the importeTotal
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporteTotal() {
		return this.importeTotal;
	}

	/**
	 * @param importeTotal
	 *            the importeTotal to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * @return the importeDificultadLabelEs
	 */
	public String getImporteDificultadLabelEs() {
		QueryUtils qUtils = new QueryUtils();
		this.importeDificultadLabelEs = qUtils.getLabelConImporte(this.importeDificultad, Constants.LANG_CASTELLANO);
		return this.importeDificultadLabelEs;
	}

	/**
	 * @param importeDificultadLabelEs
	 *            the importeDificultadLabelEs to set
	 */
	public void setImporteDificultadLabelEs(String importeDificultadLabelEs) {
		this.importeDificultadLabelEs = importeDificultadLabelEs;
	}

	/**
	 * @return the importeDificultadLabelEu
	 */
	public String getImporteDificultadLabelEu() {
		QueryUtils qUtils = new QueryUtils();
		this.importeDificultadLabelEu = qUtils.getLabelConImporte(this.importeDificultad, Constants.LANG_EUSKERA);
		return this.importeDificultadLabelEu;
	}

	/**
	 * @param importeDificultadLabelEu
	 *            the importeDificultadLabelEu to set
	 */
	public void setImporteDificultadLabelEu(String importeDificultadLabelEu) {
		this.importeDificultadLabelEu = importeDificultadLabelEu;
	}

	/**
	 * @return the importeUrgenciaLabelEs
	 */
	public String getImporteUrgenciaLabelEs() {
		QueryUtils qUtils = new QueryUtils();
		this.importeUrgenciaLabelEs = qUtils.getLabelConImporte(this.importeUrgencia, Constants.LANG_CASTELLANO);
		return this.importeUrgenciaLabelEs;
	}

	/**
	 * @param importeUrgenciaLabelEs
	 *            the importeUrgenciaLabelEs to set
	 */
	public void setImporteUrgenciaLabelEs(String importeUrgenciaLabelEs) {
		this.importeUrgenciaLabelEs = importeUrgenciaLabelEs;
	}

	/**
	 * @return the importeUrgenciaLabelEu
	 */
	public String getImporteUrgenciaLabelEu() {
		QueryUtils qUtils = new QueryUtils();
		this.importeUrgenciaLabelEu = qUtils.getLabelConImporte(this.importeUrgencia, Constants.LANG_EUSKERA);
		return this.importeUrgenciaLabelEu;
	}

	/**
	 * @param importeUrgenciaLabelEu
	 *            the importeUrgenciaLabelEu to set
	 */
	public void setImporteUrgenciaLabelEu(String importeUrgenciaLabelEu) {
		this.importeUrgenciaLabelEu = importeUrgenciaLabelEu;
	}

	public String getNumTotalPalConTramos() {
		StringBuilder sbNumPal = new StringBuilder();
		sbNumPal.append("<div class=\"numPalIzoConcor\"><div class=\"numPalIzoConcor1\">");
		if (this.getNumTotalPalFacturar() != null && this.getNumTotalPalFacturar() > 0) {
			sbNumPal.append(this.getNumTotalPalFacturar());

			sbNumPal.append("<br /></div>");
			if (comprobarRangosTrados()) {
				sbNumPal.append("<div class=\"numPalIzoConcor2\"><b>0-84%: </b>");
				sbNumPal.append(this.getNumPalConcor084());
				sbNumPal.append("<br /><b>85-94%: </b>");
				sbNumPal.append(this.getNumPalConcor8594());
				sbNumPal.append("<br /><b>95-100%: </b>");
				sbNumPal.append(this.getNumPalConcor95100());
				sbNumPal.append("<br /></div>");
			}
		}
		sbNumPal.append("</div>");
		return sbNumPal.toString();
	}

	public String getNumTotalPalConTramosPerfectMatch() {
		StringBuilder sbNumPal = new StringBuilder();
		sbNumPal.append("<div class=\"numPalIzoConcor\"><div class=\"numPalIzoConcor1\">");
		if (this.getNumTotalPalFacturar() != null && this.getNumTotalPalFacturar() > 0) {
			sbNumPal.append(this.getNumTotalPalFacturar());

			sbNumPal.append("<br /></div>");
			if (comprobarRangosTradosPerfectMatch()) {
				sbNumPal.append("<div class=\"numPalIzoConcor2\"><b>0-84%: </b>");
				sbNumPal.append(this.getNumPalConcor084());
				sbNumPal.append("<br /><b>85-94%: </b>");
				sbNumPal.append(this.getNumPalConcor8594());
				sbNumPal.append("<br /><b>95-99%: </b>");
				sbNumPal.append(this.getNumPalConcor9599());
				sbNumPal.append("<br /><b>100%: </b>");
				sbNumPal.append(this.getNumPalConcor100());
				sbNumPal.append("<br /></div>");
			}
		}
		sbNumPal.append("</div>");
		return sbNumPal.toString();
	}

	private boolean comprobarRangosTrados() {
		return GeneralUtils.isValidInteger(this.getNumPalConcor084())
				|| GeneralUtils.isValidInteger(this.getNumPalConcor8594())
				|| GeneralUtils.isValidInteger(this.getNumPalConcor95100());
	}

	private boolean comprobarRangosTradosPerfectMatch() {
		return GeneralUtils.isValidInteger(this.getNumPalConcor084())
				|| GeneralUtils.isValidInteger(this.getNumPalConcor8594())
				|| GeneralUtils.isValidInteger(this.getNumPalConcor9599())
				|| GeneralUtils.isValidInteger(this.getNumPalConcor100());
	}

	public Integer getNumPalConcor9599() {
		return this.numPalConcor9599;
	}

	public void setNumPalConcor9599(Integer numPalConcor9599) {
		this.numPalConcor9599 = numPalConcor9599;
	}

	public Integer getNumPalConcor100() {
		return this.numPalConcor100;
	}

	public void setNumPalConcor100(Integer numPalConcor100) {
		this.numPalConcor100 = numPalConcor100;
	}

}
