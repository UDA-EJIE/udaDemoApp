package com.ejie.aa79b.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.JsonNumbersDeserializer;
import com.ejie.aa79b.common.jackson.JsonImporteSerializer;
import com.ejie.aa79b.common.jackson.JsonMonedaSerializer;
import com.ejie.aa79b.common.jackson.JsonTarifaSerializer;
import com.ejie.aa79b.utils.GeneralUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * DatosFacturacionExpediente
 * 
 * @author javarona 25/6/18.
 */

public class DatosFacturacionExpediente implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long anyo;
	private Integer numExp;
	private BigDecimal numTotalPalFacturar;
	private BigDecimal numPalConcor084;
	private BigDecimal numPalConcor8594;
	private BigDecimal numPalConcor95100;
	private BigDecimal tarifaPal;
	private BigDecimal importeBase;
	private BigDecimal importeDificultad;
	private BigDecimal importeUrgencia;
	private BigDecimal importeIva;
	private BigDecimal importeTotal;
	private Long idOrden;
	private BigDecimal precioMinimo;
	private BigDecimal porcentajeFacturacion;
	private BigDecimal porcentajeIva;
	private String numTotalPalFacturarStr;
	private String indRevisado;

	/**
	 * Method 'TiposRevision'.
	 */
	public DatosFacturacionExpediente() {
		/* ... */
	}

	public DatosFacturacionExpediente(Long anyo, int numExp, BigDecimal numTotalPalFacturar, BigDecimal numPalConcor084,
			BigDecimal numPalConcor8594, BigDecimal numPalConcor95100) {
		this.anyo = anyo;
		this.numExp = numExp;
		this.numTotalPalFacturar = numTotalPalFacturar;
		this.numPalConcor084 = numPalConcor084;
		this.numPalConcor8594 = numPalConcor8594;
		this.numPalConcor95100 = numPalConcor95100;
	}

	public DatosFacturacionExpediente(Long anyo, int numExp) {
		this.anyo = anyo;
		this.numExp = numExp;
	}

	public Long getAnyo() {
		return this.anyo;
	}

	public void setAnyo(Long anyo) {
		this.anyo = anyo;
	}

	public Integer getNumExp() {
		return this.numExp;
	}

	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	public BigDecimal getNumTotalPalFacturar() {
		return this.numTotalPalFacturar;
	}

	public void setNumTotalPalFacturar(BigDecimal numTotalPalFacturar) {
		this.numTotalPalFacturar = numTotalPalFacturar;
	}

	public BigDecimal getNumPalConcor084() {
		return this.numPalConcor084;
	}

	public void setNumPalConcor084(BigDecimal numPalConcor084) {
		this.numPalConcor084 = numPalConcor084;
	}

	public BigDecimal getNumPalConcor8594() {
		return this.numPalConcor8594;
	}

	public void setNumPalConcor8594(BigDecimal numPalConcor8594) {
		this.numPalConcor8594 = numPalConcor8594;
	}

	public BigDecimal getNumPalConcor95100() {
		return this.numPalConcor95100;
	}

	public void setNumPalConcor95100(BigDecimal numPalConcor95100) {
		this.numPalConcor95100 = numPalConcor95100;
	}

	/**
	 * @return the tarifaPal
	 */
	@JsonSerialize(using = JsonTarifaSerializer.class)
	public BigDecimal getTarifaPal() {
		return tarifaPal;
	}

	/**
	 * @return the tarifaPal
	 */
	@JsonSerialize(using = JsonTarifaSerializer.class)
	public BigDecimal getTarifaPalEur() {
		return tarifaPal;
	}

	/**
	 * @param tarifaPal the tarifaPal to set
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
		return importeBase;
	}

	/**
	 * @return the importeBase
	 */
	@JsonSerialize(using = JsonMonedaSerializer.class)
	public BigDecimal getImporteBaseEur() {
		return importeBase;
	}

	/**
	 * @param importeBase the importeBase to set
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
		return importeDificultad;
	}

	/**
	 * @return the importeDificultad
	 */
	@JsonSerialize(using = JsonMonedaSerializer.class)
	public BigDecimal getImporteDificultadEur() {
		return importeDificultad;
	}

	/**
	 * @param importeDificultad the importeDificultad to set
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
		return importeUrgencia;
	}

	/**
	 * @return the importeUrgencia
	 */
	@JsonSerialize(using = JsonMonedaSerializer.class)
	public BigDecimal getImporteUrgenciaEur() {
		return importeUrgencia;
	}

	/**
	 * @param importeUrgencia the importeUrgencia to set
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
		return importeIva;
	}

	/**
	 * @return the importeIvaEur
	 */
	@JsonSerialize(using = JsonMonedaSerializer.class)
	public BigDecimal getImporteIvaEur() {
		return importeIva;
	}

	/**
	 * @param importeIva the importeIva to set
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
		return importeTotal;
	}

	/**
	 * @return the importeTotalEur
	 */
	@JsonSerialize(using = JsonMonedaSerializer.class)
	public BigDecimal getImporteTotalEur() {
		return importeTotal;
	}

	/**
	 * @param importeTotal the importeTotal to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Long getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(Long idOrden) {
		this.idOrden = idOrden;
	}

	/**
	 * @return the precioMinimo
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getPrecioMinimo() {
		return precioMinimo;
	}

	/**
	 * @return the precioMinimoEur
	 */
	@JsonSerialize(using = JsonMonedaSerializer.class)
	public BigDecimal getPrecioMinimoEur() {
		return precioMinimo;
	}

	/**
	 * @param precioMinimo the precioMinimo to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPrecioMinimo(BigDecimal precioMinimo) {
		this.precioMinimo = precioMinimo;
	}

	/**
	 * @return the porcentajeFacturacion
	 */
	public BigDecimal getPorcentajeFacturacion() {
		return porcentajeFacturacion;
	}

	/**
	 * @param porcentajeFacturacion the porcentajeFacturacion to set
	 */
	public void setPorcentajeFacturacion(BigDecimal porcentajeFacturacion) {
		this.porcentajeFacturacion = porcentajeFacturacion;
	}

	public BigDecimal getPorcentajeIva() {
		return porcentajeIva;
	}

	public void setPorcentajeIva(BigDecimal porcentajeIva) {
		this.porcentajeIva = porcentajeIva;
	}

	public String getNumTotalPalFacturarStr() {
		return numTotalPalFacturarStr;
	}

	public void setNumTotalPalFacturarStr(String numTotalPalFacturarStr) {
		this.numTotalPalFacturarStr = numTotalPalFacturarStr;
	}

	public String getIndRevisado() {
		return indRevisado;
	}

	public void setIndRevisado(String indRevisado) {
		this.indRevisado = indRevisado;
	}

	/**
	 * Devuelve el numPalIzo y los tramos de concordancia maquetados
	 * 
	 * @return
	 */
	public String getNumPalFacturarConTramos() {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		DecimalFormat formatter = new DecimalFormat("0.##", symbols);

		StringBuilder sbNumPal = new StringBuilder(Constants.SB_INIT);
		sbNumPal.append("<div class=\"numPalIzoConcor\"><div class=\"numPalIzoConcor1\">");
		sbNumPal.append(this.getNumTotalPalFacturar() != null ? formatter.format(this.getNumTotalPalFacturar()) : "-");
		sbNumPal.append("<br /></div>");

		if (comprobarRangosTrados()) {
			// El tema de de

			sbNumPal.append("<div class=\"numPalIzoConcor2\"><b>0-84%: </b>");
			sbNumPal.append(formatter.format(this.getNumPalConcor084()));
			sbNumPal.append("<br /><b>85-94%: </b>");
			sbNumPal.append(formatter.format(this.getNumPalConcor8594()));
			sbNumPal.append("<br /><b>95-100%: </b>");
			sbNumPal.append(formatter.format(this.getNumPalConcor95100()));
			sbNumPal.append("<br /></div>");
		}
		sbNumPal.append("</div>");
		return sbNumPal.toString();
	}

	private boolean comprobarRangosTrados() {
		return GeneralUtils.isValidBigdecimal(this.getNumPalConcor084())
				|| GeneralUtils.isValidBigdecimal(this.getNumPalConcor8594())
				|| GeneralUtils.isValidBigdecimal(this.getNumPalConcor95100());
	}

	@Override
	public String toString() {
		return "DatosFacturacionExpediente [anyo=" + this.anyo + ", numExp=" + this.numExp + ", numTotalPalFacturar="
				+ this.numTotalPalFacturar + ", numPalConcor0_84=" + this.numPalConcor084 + ", numPalConcor85_94="
				+ this.numPalConcor8594 + ", numPalConcor95_100=" + this.numPalConcor95100 + "]";
	}

}
