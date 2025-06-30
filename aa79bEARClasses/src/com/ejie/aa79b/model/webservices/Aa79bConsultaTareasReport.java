package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.GeneralUtils;

/**
 *
 * @author eaguirresarobe
 *
 */
public class Aa79bConsultaTareasReport extends SpringBeanAutowiringSupport implements Serializable {

	@Autowired()
	private ReloadableResourceBundleMessageSource messageSource;

	private static final long serialVersionUID = 1L;
	protected Long anyo;
	protected String anyoNumExp;
	protected Aa79bDescripcionIdioma estadoEjecucionTarea;
	protected Date fechaFinInterpretacion;
	protected Date fechaInicioInterpretacion;
	protected Date fechaPrevista;
	protected Date fechaReal;
	protected String horaFinInterpretacion;
	protected String horaInicioInterpretacion;
	protected String horaPrevista;
	protected String horaReal;
	protected BigDecimal idTarea;
	protected Aa79bLoteCombo lotes;
	protected Integer numExp;
	protected Integer numPalConcor084;
	protected Integer numPalConcor8594;
	protected Integer numPalConcor95100;
	protected Integer numPalConcor9599;
	protected Integer numPalConcor100;
	protected Integer numPalIzo;
	protected Integer numPalXml;
	protected Aa79bDescripcionIdioma tipoTarea;

	public Aa79bConsultaTareasReport() {
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
	 * @return the estadoEjecucionTarea
	 */
	public Aa79bDescripcionIdioma getEstadoEjecucionTarea() {
		return this.estadoEjecucionTarea;
	}

	/**
	 * @param estadoEjecucionTarea
	 *            the estadoEjecucionTarea to set
	 */
	public void setEstadoEjecucionTarea(Aa79bDescripcionIdioma estadoEjecucionTarea) {
		this.estadoEjecucionTarea = estadoEjecucionTarea;
	}

	/**
	 * @return the fechaFinInterpretacion
	 */
	public Date getFechaFinInterpretacion() {
		return this.fechaFinInterpretacion;
	}

	/**
	 * @param fechaFinInterpretacion
	 *            the fechaFinInterpretacion to set
	 */
	public void setFechaFinInterpretacion(Date fechaFinInterpretacion) {
		this.fechaFinInterpretacion = fechaFinInterpretacion;
	}

	/**
	 * @return the fechaInicioInterpretacion
	 */
	public Date getFechaInicioInterpretacion() {
		return this.fechaInicioInterpretacion;
	}

	/**
	 * @param fechaInicioInterpretacion
	 *            the fechaInicioInterpretacion to set
	 */
	public void setFechaInicioInterpretacion(Date fechaInicioInterpretacion) {
		this.fechaInicioInterpretacion = fechaInicioInterpretacion;
	}

	/**
	 * @return the fechaPrevista
	 */
	public Date getFechaPrevista() {
		return this.fechaPrevista;
	}

	/**
	 * @param fechaPrevista
	 *            the fechaPrevista to set
	 */
	public void setFechaPrevista(Date fechaPrevista) {
		this.fechaPrevista = fechaPrevista;
	}

	/**
	 * @return the fechaReal
	 */
	public Date getFechaReal() {
		return this.fechaReal;
	}

	/**
	 * @param fechaReal
	 *            the fechaReal to set
	 */
	public void setFechaReal(Date fechaReal) {
		this.fechaReal = fechaReal;
	}

	/**
	 * @return the horaFinInterpretacion
	 */
	public String getHoraFinInterpretacion() {
		return this.horaFinInterpretacion;
	}

	/**
	 * @param horaFinInterpretacion
	 *            the horaFinInterpretacion to set
	 */
	public void setHoraFinInterpretacion(String horaFinInterpretacion) {
		this.horaFinInterpretacion = horaFinInterpretacion;
	}

	/**
	 * @return the horaInicioInterpretacion
	 */
	public String getHoraInicioInterpretacion() {
		return this.horaInicioInterpretacion;
	}

	/**
	 * @param horaInicioInterpretacion
	 *            the horaInicioInterpretacion to set
	 */
	public void setHoraInicioInterpretacion(String horaInicioInterpretacion) {
		this.horaInicioInterpretacion = horaInicioInterpretacion;
	}

	/**
	 * @return the horaPrevista
	 */
	public String getHoraPrevista() {
		return this.horaPrevista;
	}

	/**
	 * @param horaPrevista
	 *            the horaPrevista to set
	 */
	public void setHoraPrevista(String horaPrevista) {
		this.horaPrevista = horaPrevista;
	}

	/**
	 * @return the horaReal
	 */
	public String getHoraReal() {
		return this.horaReal;
	}

	/**
	 * @param horaReal
	 *            the horaReal to set
	 */
	public void setHoraReal(String horaReal) {
		this.horaReal = horaReal;
	}

	/**
	 * @return the idTarea
	 */
	public BigDecimal getIdTarea() {
		return this.idTarea;
	}

	/**
	 * @param idTarea
	 *            the idTarea to set
	 */
	public void setIdTarea(BigDecimal idTarea) {
		this.idTarea = idTarea;
	}

	/**
	 * @return the lotes
	 */
	public Aa79bLoteCombo getLotes() {
		return this.lotes;
	}

	/**
	 * @param lotes
	 *            the lotes to set
	 */
	public void setLotes(Aa79bLoteCombo lotes) {
		this.lotes = lotes;
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
	 * @return the numPalIzo
	 */
	public Integer getNumPalIzo() {
		return this.numPalIzo;
	}

	/**
	 * @param numPalIzo
	 *            the numPalIzo to set
	 */
	public void setNumPalIzo(Integer numPalIzo) {
		this.numPalIzo = numPalIzo;
	}

	/**
	 * @return the numPalXml
	 */
	public Integer getNumPalXml() {
		return this.numPalXml;
	}

	/**
	 * @param numPalXml
	 *            the numPalXml to set
	 */
	public void setNumPalXml(Integer numPalXml) {
		this.numPalXml = numPalXml;
	}

	/**
	 * @return the tipoTarea
	 */
	public Aa79bDescripcionIdioma getTipoTarea() {
		return this.tipoTarea;
	}

	/**
	 * @param tipoTarea
	 *            the tipoTarea to set
	 */
	public void setTipoTarea(Aa79bDescripcionIdioma tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	/**
	 *
	 * @return String
	 */
	public String getFechaHoraPrevista() {
		if (this.fechaPrevista != null) {
			return this.fechaPrevista + " " + this.horaPrevista;
		}
		return Constants.EMPTY;
	}

	/**
	 *
	 * @return
	 */
	public String getFechaHoraReal() {
		if (this.fechaReal != null) {
			return this.fechaReal + " " + this.horaReal;
		}
		return Constants.EMPTY;
	}

	/**
	 *
	 * @return String
	 */
	public String getFechaHoraInicioInterpretacion() {
		if (this.fechaInicioInterpretacion != null) {
			return this.fechaInicioInterpretacion + " " + this.horaInicioInterpretacion;
		}
		return Constants.EMPTY;
	}

	/**
	 *
	 * @return String
	 */
	public String getFechaHoraFinInterpretacion() {
		if (this.fechaFinInterpretacion != null) {
			return this.fechaFinInterpretacion + " " + this.horaFinInterpretacion;
		}
		return Constants.EMPTY;
	}

	// INICIO - Métodos definidos para obtener la fecha y hora de emisión en el
	// idioma correspondiente al llamar a WS desde AA06
	/**
	 *
	 * @return String
	 */
	public String getFechaHoraFinInterEu() {
		Locale locale = new Locale(Constants.LANG_EUSKERA);
		return DateUtils.obtFechaHoraFormateada(this.fechaFinInterpretacion, this.horaFinInterpretacion, locale);
	}

	/**
	 *
	 * @return String
	 */
	public String getFechaHoraFinInterEs() {
		Locale locale = new Locale(Constants.LANG_CASTELLANO);
		return DateUtils.obtFechaHoraFormateada(this.fechaFinInterpretacion, this.horaFinInterpretacion, locale);
	}

	/**
	 *
	 * @return String
	 */
	public String getFechaHoraIniInterEu() {
		Locale locale = new Locale(Constants.LANG_EUSKERA);
		return DateUtils.obtFechaHoraFormateada(this.fechaInicioInterpretacion, this.horaInicioInterpretacion, locale);
	}

	/**
	 *
	 * @return String
	 */
	public String getFechaHoraIniInterEs() {
		Locale locale = new Locale(Constants.LANG_CASTELLANO);
		return DateUtils.obtFechaHoraFormateada(this.fechaInicioInterpretacion, this.horaInicioInterpretacion, locale);
	}

	/**
	 *
	 * @return String
	 */
	public String getFechaHoraPrevistaEu() {
		Locale locale = new Locale(Constants.LANG_EUSKERA);
		return DateUtils.obtFechaHoraFormateada(this.fechaPrevista, this.horaPrevista, locale);
	}

	/**
	 *
	 * @return String
	 */
	public String getFechaHoraPrevistaEs() {
		Locale locale = new Locale(Constants.LANG_CASTELLANO);
		return DateUtils.obtFechaHoraFormateada(this.fechaPrevista, this.horaPrevista, locale);
	}

	/**
	 *
	 * @return String
	 */
	public String getFechaHoraRealEu() {
		Locale locale = new Locale(Constants.LANG_EUSKERA);
		return DateUtils.obtFechaHoraFormateada(this.fechaReal, this.horaReal, locale);
	}

	/**
	 *
	 * @return String
	 */
	public String getFechaHoraRealEs() {
		Locale locale = new Locale(Constants.LANG_CASTELLANO);
		return DateUtils.obtFechaHoraFormateada(this.fechaReal, this.horaReal, locale);
	}
	// FIN - Métodos definidos para obtener la fecha y hora de emisión en el
	// idioma correspondiente al llamar a WS desde AA06

	public String getNumTotalPalConTramos() {
		StringBuilder sbNumPal = new StringBuilder();
		sbNumPal.append("<div class=\"numPalIzoConcor\"><div class=\"numPalIzoConcor1\">");
		if (this.getNumPalXml() != null && this.getNumPalXml() > 0) {
			sbNumPal.append(this.getNumPalXml());

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
		} else {
			sbNumPal.append(this.getNumPalIzo());
		}
		sbNumPal.append("</div>");
		return sbNumPal.toString();
	}

	public String getNumTotalPalConTramosPerfectMatch() {
		StringBuilder sbNumPal = new StringBuilder();
		sbNumPal.append("<div class=\"numPalIzoConcor\"><div class=\"numPalIzoConcor1\">");
		if (this.getNumPalXml() != null && this.getNumPalXml() > 0) {
			sbNumPal.append(this.getNumPalXml());

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
		} else {
			sbNumPal.append(this.getNumPalIzo());
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

	public String getFechasInterpretacionEu() {
		return this.getFechasInterpretacion(this.getFechaHoraIniInterEu(), this.getFechaHoraFinInterEu(),
				new Locale(Constants.LANG_EUSKERA));
	}

	public String getFechasInterpretacionEs() {
		return this.getFechasInterpretacion(this.getFechaHoraIniInterEs(), this.getFechaHoraFinInterEs(),
				new Locale(Constants.LANG_CASTELLANO));
	}

	public String getFechaPrevistaRealEu() {
		return this.getFechaPrevistaReal(this.getFechaHoraPrevistaEu(), this.getFechaHoraRealEu(),
				new Locale(Constants.LANG_EUSKERA));
	}

	public String getFechaPrevistaRealEs() {
		return this.getFechaPrevistaReal(this.getFechaHoraPrevistaEs(), this.getFechaHoraRealEs(),
				new Locale(Constants.LANG_CASTELLANO));

	}

	private String getFechasInterpretacion(String fechaHoraInicioInterpretacion, String fechaHoraFinInterpretacion,
			Locale locale) {
		StringBuilder sbFPR = new StringBuilder();
		sbFPR.append("<div class=\"ta_left\">");
		sbFPR.append(this.messageSource.getMessage("comun.inicio", null, locale)).append(": ");
		sbFPR.append(fechaHoraInicioInterpretacion);
		sbFPR.append("</div>");
		sbFPR.append("<div class=\"ta_left\"><br />");
		sbFPR.append(this.messageSource.getMessage("comun.final", null, locale)).append(": ");
		sbFPR.append(fechaHoraFinInterpretacion);
		sbFPR.append("</div>");
		return sbFPR.toString();
	}

	private String getFechaPrevistaReal(String fechaHoraPrevista, String fechaHoraReal, Locale locale) {
		StringBuilder sbFPR = new StringBuilder();
		sbFPR.append("<div class=\"ta_left\">");
		sbFPR.append(this.messageSource.getMessage("comun.prevista", null, locale)).append(": ");
		sbFPR.append(fechaHoraPrevista);
		sbFPR.append("</div>");
		sbFPR.append("<div class=\"ta_left\"><br />");
		sbFPR.append(this.messageSource.getMessage("comun.real", null, locale)).append(": ");
		sbFPR.append(fechaHoraReal);
		sbFPR.append("</div>");
		return sbFPR.toString();
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
