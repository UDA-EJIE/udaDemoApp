package com.ejie.aa79b.model;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.DaoConstants;
import com.ejie.aa79b.model.enums.TipoExpedienteEnum;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.GeneralUtils;
import com.ejie.x38.serialization.JsonDateDeserializer;
import com.ejie.x38.serialization.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ExpedientePlanificacion extends Expediente implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer numPalabrasIZO;
	private Date fechaEntrega;
	private String horaEntrega;
	private Date fechaLimite;
	private String horaLimite;
	private Date fechaPrevistaEntrega;
	private String horaPrevistaEntrega;
	private String prioridad;
	private String prioridadDescEs;
	private String prioridadDescEu;

	private String idsGrupoTrabajo;
	private String idsFaseExpediente;
	private String indicesBopv;
	private String indGruposBOE;

	private FilterTarea filterTarea;

	private String idsMetadatosBusqueda;

	private String requierePresupuesto;
	private Integer estadoPresupuesto;
	private String requiereNegociacion;
	private Integer estadoNegociacion;
	// Maqueta//////////////////////////////////////////////////////

	private Integer proyectoTradosEstadoAsignacion;
	private Integer proyectoTradosEstadoEjecucion;
	private String negociacionFechaHora;
	private String responsable;
	private Date fechaPrevistaInicio;
	private String horaPrevistaInicio;
	private Date fechaPrevistaFin;
	private String horaPrevistaFin;
	private Integer idEstadoTareasAsignadas;
	private Integer idEstadoNegociacion;
	private String estadoTareasAsignadasEs;
	private String estadoTareasAsignadasEu;
	private Integer palTrados;
	private Integer palPresupuesto;

	private FilterFactura filterFactura;
	private Lotes lotes;
	private Long tipoDocumento;

	public Integer getProyectoTradosEstadoAsignacion() {
		return this.proyectoTradosEstadoAsignacion;
	}

	public void setProyectoTradosEstadoAsignacion(Integer proyectoTradosEstadoAsignacion) {
		this.proyectoTradosEstadoAsignacion = proyectoTradosEstadoAsignacion;
	}

	public Integer getProyectoTradosEstadoEjecucion() {
		return this.proyectoTradosEstadoEjecucion;
	}

	public void setProyectoTradosEstadoEjecucion(Integer proyectoTradosEstadoEjecucion) {
		this.proyectoTradosEstadoEjecucion = proyectoTradosEstadoEjecucion;
	}

	public String getNegociacionFechaHora() {
		return this.negociacionFechaHora;
	}

	public void setNegociacionFechaHora(String negociacionFechaHora) {
		this.negociacionFechaHora = negociacionFechaHora;
	}

	public String getRequierePresupuesto() {
		return this.requierePresupuesto;
	}

	public void setRequierePresupuesto(String requierePresupuesto) {
		this.requierePresupuesto = requierePresupuesto;
	}

	/**
	 * @return the estadoPresupuesto
	 */
	public Integer getEstadoPresupuesto() {
		return this.estadoPresupuesto;
	}

	/**
	 * @param estadoPresupuesto the estadoPresupuesto to set
	 */
	public void setEstadoPresupuesto(Integer estadoPresupuesto) {
		this.estadoPresupuesto = estadoPresupuesto;
	}

	/**
	 * @return the requiereNegociacion
	 */
	public String getRequiereNegociacion() {
		return this.requiereNegociacion;
	}

	/**
	 * @param requiereNegociacion the requiereNegociacion to set
	 */
	public void setRequiereNegociacion(String requiereNegociacion) {
		this.requiereNegociacion = requiereNegociacion;
	}

	/**
	 * @return the estadoNegociacion
	 */
	public Integer getEstadoNegociacion() {
		return this.estadoNegociacion;
	}

	/**
	 * @param estadoNegociacion the estadoNegociacion to set
	 */
	public void setEstadoNegociacion(Integer estadoNegociacion) {
		this.estadoNegociacion = estadoNegociacion;
	}

	public String getResponsable() {
		return this.responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	/////////////////////////////////////////////////////////////

	public ExpedientePlanificacion() {
		// Constructor vacio
	}

	public Integer getNumPalabrasIZO() {
		return this.numPalabrasIZO;
	}

	public void setNumPalabrasIZO(Integer numPalabrasIZO) {
		this.numPalabrasIZO = numPalabrasIZO;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaEntrega() {
		return this.fechaEntrega;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getHoraEntrega() {
		return this.horaEntrega;
	}

	public void setHoraEntrega(String horaEntrega) {
		this.horaEntrega = horaEntrega;
	}

	public String getFechaHoraEntrega() {
		if (this.fechaEntrega != null) {
			return DateUtils.obtFechaHoraFormateada(this.fechaEntrega, this.horaEntrega,
					LocaleContextHolder.getLocale());
		} else {
			return "";
		}
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaLimite() {
		return this.fechaLimite;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public String getHoraLimite() {
		return this.horaLimite;
	}

	public void setHoraLimite(String horaLimite) {
		this.horaLimite = horaLimite;
	}

	public String getFechaHoraLimite() {
		if (this.fechaLimite != null && StringUtils.isNotBlank(this.horaLimite)) {
			return DateUtils.obtFechaHoraFormateada(this.fechaLimite, this.horaLimite, LocaleContextHolder.getLocale());
		} else {
			return "";
		}
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaPrevistaEntrega() {
		return this.fechaPrevistaEntrega;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaPrevistaEntrega(Date fechaPrevistaEntrega) {
		this.fechaPrevistaEntrega = fechaPrevistaEntrega;
	}

	public String getHoraPrevistaEntrega() {
		return this.horaPrevistaEntrega;
	}

	public void setHoraPrevistaEntrega(String horaPrevistaEntrega) {
		this.horaPrevistaEntrega = horaPrevistaEntrega;
	}

	public String getPrioridad() {
		return this.prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * @return the prioridadDescEs
	 */
	public String getPrioridadDescEs() {
		return this.prioridadDescEs;
	}

	/**
	 * @param prioridadDescEs the prioridadDescEs to set
	 */
	public void setPrioridadDescEs(String prioridadDescEs) {
		this.prioridadDescEs = prioridadDescEs;
	}

	/**
	 * @return the prioridadDescEu
	 */
	public String getPrioridadDescEu() {
		return this.prioridadDescEu;
	}

	/**
	 * @param prioridadDescEu the prioridadDescEu to set
	 */
	public void setPrioridadDescEu(String prioridadDescEu) {
		this.prioridadDescEu = prioridadDescEu;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaPrevistaInicio() {
		return this.fechaPrevistaInicio;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaPrevistaInicio(Date fechaPrevistaInicio) {
		this.fechaPrevistaInicio = fechaPrevistaInicio;
	}

	public String getHoraPrevistaInicio() {
		return this.horaPrevistaInicio;
	}

	public void setHoraPrevistaInicio(String horaPrevistaInicio) {
		this.horaPrevistaInicio = horaPrevistaInicio;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaPrevistaFin() {
		return this.fechaPrevistaFin;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaPrevistaFin(Date fechaPrevistaFin) {
		this.fechaPrevistaFin = fechaPrevistaFin;
	}

	public String getHoraPrevistaFin() {
		return this.horaPrevistaFin;
	}

	public void setHoraPrevistaFin(String horaPrevistaFin) {
		this.horaPrevistaFin = horaPrevistaFin;
	}

	/**
	 * @return the idsGrupoTrabajo
	 */
	public String getIdsGrupoTrabajo() {
		return this.idsGrupoTrabajo;
	}

	/**
	 * @param idsGrupoTrabajo the idsGrupoTrabajo to set
	 */
	public void setIdsGrupoTrabajo(String idsGrupoTrabajo) {
		this.idsGrupoTrabajo = idsGrupoTrabajo;
	}

	public String getIdsFaseExpediente() {
		return this.idsFaseExpediente;
	}

	public void setIdsFaseExpediente(String idsFaseExpediente) {
		this.idsFaseExpediente = idsFaseExpediente;
	}

	/**
	 * @return the indicesBopv
	 */
	public String getIndicesBopv() {
		return this.indicesBopv;
	}

	/**
	 * @param indicesBopv the indicesBopv to set
	 */
	public void setIndicesBopv(String indicesBopv) {
		this.indicesBopv = indicesBopv;
	}

	/**
	 * @return the filterTarea
	 */
	public FilterTarea getFilterTarea() {
		return this.filterTarea;
	}

	/**
	 * @param filterTarea the filterTarea to set
	 */
	public void setFilterTarea(FilterTarea filterTarea) {
		this.filterTarea = filterTarea;
	}

	/**
	 * @return the idEstadoTareasAsignadas
	 */
	public Integer getIdEstadoTareasAsignadas() {
		return this.idEstadoTareasAsignadas;
	}

	/**
	 * @param idEstadoTareasAsignadas the idEstadoTareasAsignadas to set
	 */
	public void setIdEstadoTareasAsignadas(Integer idEstadoTareasAsignadas) {
		this.idEstadoTareasAsignadas = idEstadoTareasAsignadas;
	}

	/**
	 * @return the estadoTareasAsignadasEs
	 */
	public String getEstadoTareasAsignadasEs() {
		return this.estadoTareasAsignadasEs;
	}

	/**
	 * @param estadoTareasAsignadasEs the estadoTareasAsignadasEs to set
	 */
	public void setEstadoTareasAsignadasEs(String estadoTareasAsignadasEs) {
		this.estadoTareasAsignadasEs = estadoTareasAsignadasEs;
	}

	/**
	 * @return the estadoTareasAsignadasEu
	 */
	public String getEstadoTareasAsignadasEu() {
		return this.estadoTareasAsignadasEu;
	}

	/**
	 * @param estadoTareasAsignadasEu the estadoTareasAsignadasEu to set
	 */
	public void setEstadoTareasAsignadasEu(String estadoTareasAsignadasEu) {
		this.estadoTareasAsignadasEu = estadoTareasAsignadasEu;
	}

	/**
	 * @return the palPresupuesto
	 */
	public Integer getPalPresupuesto() {
		return this.palPresupuesto;
	}

	/**
	 * @param palPresupuesto the palPresupuesto to set
	 */
	public void setPalPresupuesto(Integer palPresupuesto) {
		this.palPresupuesto = palPresupuesto;
	}

	/**
	 * @return the palTrados
	 */
	public Integer getPalTrados() {
		return this.palTrados;
	}

	/**
	 * @param palTrados the palTrados to set
	 */
	public void setPalTrados(Integer palTrados) {
		this.palTrados = palTrados;
	}

	public String getFechaHoraPrevistaEntrega() {
		return DateUtils.obtFechaHoraFormateada(this.fechaPrevistaEntrega, this.horaPrevistaEntrega,
				LocaleContextHolder.getLocale());
	}

	public String getFechaHoraPrevistaInicio() {
		return DateUtils.obtFechaHoraFormateada(this.fechaPrevistaInicio, this.horaPrevistaInicio,
				LocaleContextHolder.getLocale());
	}

	public String getFechaHoraPrevistaFin() {
		return DateUtils.obtFechaHoraFormateada(this.fechaPrevistaFin, this.horaPrevistaFin,
				LocaleContextHolder.getLocale());
	}

	public String getIdsMetadatosBusqueda() {
		return this.idsMetadatosBusqueda;
	}

	public void setIdsMetadatosBusqueda(String idsMetadatosBusqueda) {
		this.idsMetadatosBusqueda = idsMetadatosBusqueda;
	}

	/**
	 * Devuelve el numPalIzo y los tramos de concordancia maquetados
	 *
	 * @return
	 */
	public String getNumTotalPalConTramosExcelReport() {
		StringBuilder sbNumPal = new StringBuilder(Constants.SB_INIT);
		if (this.getExpedienteTradRev() != null) {
			String contenido = this.getExpedienteTradRev().getNumTotalPalConTramos().toString().replaceAll("<br />",
					"\n");
			contenido = contenido.toString().replaceAll("\\<[^>]*>", "");
			sbNumPal.append(contenido);
		}
		return sbNumPal.toString();
	}

	/**
	 * Devuelve el numPalIzo y los tramos de concordancia perfect match maquetados
	 *
	 * @return
	 */
	public String getNumTotalPalConTramosPerfectMatchExcelReport() {
		StringBuilder sbNumPal = new StringBuilder(Constants.SB_INIT);
		if (this.getExpedienteTradRev() != null) {
			String contenido = this.getExpedienteTradRev().getNumTotalPalConTramosPerfectMatch().toString().replaceAll("<br />",
					"\n");
			contenido = contenido.toString().replaceAll("\\<[^>]*>", "");
			sbNumPal.append(contenido);
		}
		return sbNumPal.toString();
	}

	/**
	 *
	 * @return
	 */
	public String getNumPalTramos() {
		StringBuilder sbNumPal = new StringBuilder(Constants.SB_INIT);
		if (this.getExpedienteTradRev() != null
				&& !TipoExpedienteEnum.INTERPRETACION.getValue().equals(this.getIdTipoExpediente())) {
			sbNumPal.append("<div class=\"numPalIzoConcor\"><div class=\"numPalIzoConcor1\">");
			if (this.getExpedienteTradRev().getTradosExp() != null
					&& GeneralUtils.isValidInteger(this.getExpedienteTradRev().getTradosExp().getNumTotalPal())) {
				sbNumPal.append(this.getExpedienteTradRev().getTradosExp().getNumTotalPal());
				sbNumPal.append("<br /></div>");
				if (this.getExpedienteTradRev().getTradosExp().getNumPalConcor084() != Constants.CERO
						|| this.getExpedienteTradRev().getTradosExp().getNumPalConcor8594() != Constants.CERO
						|| this.getExpedienteTradRev().getTradosExp().getNumPalConcor95100() != Constants.CERO) {
					sbNumPal.append("<div class=\"numPalIzoConcor2\"><b>0-84%: </b>");
					sbNumPal.append(this.getExpedienteTradRev().getTradosExp().getNumPalConcor084());
					sbNumPal.append("<br /><b>85-94%: </b>");
					sbNumPal.append(this.getExpedienteTradRev().getTradosExp().getNumPalConcor8594());
					sbNumPal.append("<br /><b>95-100%: </b>");
					sbNumPal.append(this.getExpedienteTradRev().getTradosExp().getNumPalConcor95100());
					sbNumPal.append("<br /></div>");
				}

			} else if (GeneralUtils.isValidInteger(this.getExpedienteTradRev().getNumTotalPalIzo())) {
				sbNumPal.append(this.getExpedienteTradRev().getNumTotalPalIzo());
			} else if (GeneralUtils.isValidInteger(this.getExpedienteTradRev().getNumTotalPalSolic())) {
				sbNumPal.append(this.getExpedienteTradRev().getNumTotalPalSolic());
			} else {
				sbNumPal.append(Constants.CERO);
			}
			sbNumPal.append("</div>");
		} else {
			sbNumPal.append(DaoConstants.SIGNO_GUION);
		}

		return sbNumPal.toString();
	}

	/**
	 *
	 * @return
	 */
	public String getNumPalTramosPerfectMatch() {
		StringBuilder sbNumPal = new StringBuilder(Constants.SB_INIT);
		if (this.getExpedienteTradRev() != null
				&& !TipoExpedienteEnum.INTERPRETACION.getValue().equals(this.getIdTipoExpediente())) {
			sbNumPal.append("<div class=\"numPalIzoConcor\"><div class=\"numPalIzoConcor1\">");
			if (this.getExpedienteTradRev().getTradosExp() != null
					&& GeneralUtils.isValidInteger(this.getExpedienteTradRev().getTradosExp().getNumTotalPal())) {
				sbNumPal.append(this.getExpedienteTradRev().getTradosExp().getNumTotalPal());
				sbNumPal.append("<br /></div>");
				if (this.getExpedienteTradRev().getTradosExp().getNumPalConcor084() != Constants.CERO
						|| this.getExpedienteTradRev().getTradosExp().getNumPalConcor8594() != Constants.CERO
						|| this.getExpedienteTradRev().getTradosExp().getNumPalConcor9599() != Constants.CERO
						|| this.getExpedienteTradRev().getTradosExp().getNumPalConcor100() != Constants.CERO) {
					sbNumPal.append("<div class=\"numPalIzoConcor2\"><b>0-84%: </b>");
					sbNumPal.append(this.getExpedienteTradRev().getTradosExp().getNumPalConcor084());
					sbNumPal.append("<br /><b>85-94%: </b>");
					sbNumPal.append(this.getExpedienteTradRev().getTradosExp().getNumPalConcor8594());
					sbNumPal.append("<br /><b>95-99%: </b>");
					sbNumPal.append(this.getExpedienteTradRev().getTradosExp().getNumPalConcor9599());
					sbNumPal.append("<br /><b>100%: </b>");
					sbNumPal.append(this.getExpedienteTradRev().getTradosExp().getNumPalConcor100());
					sbNumPal.append("<br /></div>");
				}

			} else if (GeneralUtils.isValidInteger(this.getExpedienteTradRev().getNumTotalPalIzo())) {
				sbNumPal.append(this.getExpedienteTradRev().getNumTotalPalIzo());
			} else if (GeneralUtils.isValidInteger(this.getExpedienteTradRev().getNumTotalPalSolic())) {
				sbNumPal.append(this.getExpedienteTradRev().getNumTotalPalSolic());
			} else {
				sbNumPal.append(Constants.CERO);
			}
			sbNumPal.append("</div>");
		} else {
			sbNumPal.append(DaoConstants.SIGNO_GUION);
		}

		return sbNumPal.toString();
	}

	public Integer getIdEstadoNegociacion() {
		return this.idEstadoNegociacion;
	}

	public void setIdEstadoNegociacion(Integer idEstadoNegociacion) {
		this.idEstadoNegociacion = idEstadoNegociacion;
	}

	public FilterFactura getFilterFactura() {
		return this.filterFactura;
	}

	public void setFilterFactura(FilterFactura filterFactura) {
		this.filterFactura = filterFactura;
	}

	public Lotes getLotes() {
		return this.lotes;
	}

	public void setLotes(Lotes lotes) {
		this.lotes = lotes;
	}

	/**
	 * @return the tipoDocumento
	 */
	public Long getTipoDocumento() {
		return this.tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(Long tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getIndGruposBOE() {
		return this.indGruposBOE;
	}

	public void setIndGruposBOE(String indGruposBOE) {
		this.indGruposBOE = indGruposBOE;
	}

	public String getEntidadDeGestorEs() {
		return hasEntidadGestor() ? this.getGestorExpediente().getEntidad().getDescAmpEs() : "-";
	}

	public String getEntidadDeGestorEu() {
		return hasEntidadGestor() ? this.getGestorExpediente().getEntidad().getDescAmpEu() : "-";
	}

	public String getGestorExpedienteEs() {
		return hasGestorExpediente() ? this.getGestorExpediente().getSolicitante().getNombreCompleto() : "-";
	}

	public String getGestorExpedienteEu() {
		return hasGestorExpediente() ? this.getGestorExpediente().getSolicitante().getNombreCompleto() : "-";
	}

	public String getPalabrasIzo() {
		StringBuilder sbNumPal = new StringBuilder(Constants.SB_INIT);
		if (isTradRev()) {
			if (hasTradosValues()) {
				sbNumPal.append(this.getExpedienteTradRev().getTradosExp().getNumTotalPal());
			} else if (GeneralUtils.isValidInteger(this.getExpedienteTradRev().getNumTotalPalIzo())) {
				sbNumPal.append(this.getExpedienteTradRev().getNumTotalPalIzo());
			} else if (GeneralUtils.isValidInteger(this.getExpedienteTradRev().getNumTotalPalSolic())) {
				sbNumPal.append(this.getExpedienteTradRev().getNumTotalPalSolic());
			} else {
				sbNumPal.append(Constants.CERO);
			}
		} else {
			sbNumPal.append(DaoConstants.SIGNO_GUION);
		}
		return sbNumPal.toString();
	}

	public String getTradosConcor084() {
		StringBuilder sbNumPal = new StringBuilder(Constants.SB_INIT);
		if (isTradRev() && hasTradosValues()
				&& this.getExpedienteTradRev().getTradosExp().getNumPalConcor084() != Constants.CERO) {
			sbNumPal.append(this.getExpedienteTradRev().getTradosExp().getNumPalConcor084());
		} else {
			sbNumPal.append(DaoConstants.SIGNO_GUION);
		}
		return sbNumPal.toString();
	}

	public String getTradosConcor8594() {
		StringBuilder sbNumPal = new StringBuilder(Constants.SB_INIT);
		if (isTradRev() && hasTradosValues()
				&& this.getExpedienteTradRev().getTradosExp().getNumPalConcor8594() != Constants.CERO) {
			sbNumPal.append(this.getExpedienteTradRev().getTradosExp().getNumPalConcor8594());
		} else {
			sbNumPal.append(DaoConstants.SIGNO_GUION);
		}
		return sbNumPal.toString();
	}

	public String getTradosConcor95100() {
		StringBuilder sbNumPal = new StringBuilder(Constants.SB_INIT);
		if (isTradRev() && hasTradosValues()
				&& this.getExpedienteTradRev().getTradosExp().getNumPalConcor95100() != Constants.CERO) {
			sbNumPal.append(this.getExpedienteTradRev().getTradosExp().getNumPalConcor95100());
		} else {
			sbNumPal.append(DaoConstants.SIGNO_GUION);
		}
		return sbNumPal.toString();
	}

	public String getTradosConcor9599() {
		StringBuilder sbNumPal = new StringBuilder(Constants.SB_INIT);
		if (isTradRev() && hasTradosValues()
				&& this.getExpedienteTradRev().getTradosExp().getNumPalConcor9599() != Constants.CERO) {
			sbNumPal.append(this.getExpedienteTradRev().getTradosExp().getNumPalConcor9599());
		} else {
			sbNumPal.append(DaoConstants.SIGNO_GUION);
		}
		return sbNumPal.toString();
	}

	public String getTradosConcor100() {
		StringBuilder sbNumPal = new StringBuilder(Constants.SB_INIT);
		if (isTradRev() && hasTradosValues()
				&& this.getExpedienteTradRev().getTradosExp().getNumPalConcor100() != Constants.CERO) {
			sbNumPal.append(this.getExpedienteTradRev().getTradosExp().getNumPalConcor100());
		} else {
			sbNumPal.append(DaoConstants.SIGNO_GUION);
		}
		return sbNumPal.toString();
	}

	private boolean hasGestor() {
		return this.getGestorExpediente() != null;
	}

	private boolean hasEntidadGestor() {
		return hasGestor() ? this.getGestorExpediente().getEntidad() != null : false;
	}

	private boolean hasGestorExpediente() {
		return hasGestor() ? this.getGestorExpediente().getSolicitante() != null : false;
	}

	private boolean isTradRev() {
		return this.getExpedienteTradRev() != null
				&& !TipoExpedienteEnum.INTERPRETACION.getValue().equals(this.getIdTipoExpediente());
	}

	private boolean hasTradosValues() {
		return this.getExpedienteTradRev().getTradosExp() != null
				&& GeneralUtils.isValidInteger(this.getExpedienteTradRev().getTradosExp().getNumTotalPal());
	}

}
