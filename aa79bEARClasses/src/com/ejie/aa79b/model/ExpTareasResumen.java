package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.context.i18n.LocaleContextHolder;

import com.ejie.aa79b.utils.DateUtils;

public class ExpTareasResumen extends Expediente implements Serializable {

	private static final long serialVersionUID = 1L;

	private Tareas tarea;
	private List<DocumentoTarea> docuTareasList;
	private String[] filtroDatos;
	private String grupoTrabajo;
	private Long idGrupo;
	private Long idIdiomaDestino;
	private String conformidad;
	private String sinAsignar;
	private Integer palPresuspuesto;
	private Integer palTrados;
	private Date fechaFin;
	private String horaFin;
	private Boolean esAsignador;
	private String idsGrupoTrabajo;
	private String indUrgente;
	private Persona solicitante;
	private OtrosTrabajos otrosTrabajos;
	private Tareas tareaTrabajo;

	/**
	 * @return the tarea
	 */
	public Tareas getTarea() {
		return this.tarea;
	}

	/**
	 * @param tarea the tarea to set
	 */
	public void setTarea(Tareas tarea) {
		this.tarea = tarea;
	}

	public List<DocumentoTarea> getDocuTareasList() {
		return this.docuTareasList;
	}

	public void setDocuTareasList(List<DocumentoTarea> docuTareasList) {
		this.docuTareasList = docuTareasList;
	}

	/**
	 * @return the filtroDatos
	 */
	public String[] getFiltroDatos() {
		return this.filtroDatos;
	}

	/**
	 * @param filtroDatos the filtroDatos to set
	 */
	public void setFiltroDatos(String[] filtroDatos) {
		this.filtroDatos = filtroDatos;
	}

	/**
	 * @return the grupoTrabajo
	 */
	@Override
	public String getGrupoTrabajo() {
		return this.grupoTrabajo;
	}

	/**
	 * @param grupoTrabajo the grupoTrabajo to set
	 */
	@Override
	public void setGrupoTrabajo(String grupoTrabajo) {
		this.grupoTrabajo = grupoTrabajo;
	}

	/**
	 * @return the grupoTrabajoId
	 */
	public Long getIdGrupo() {
		return this.idGrupo;
	}

	/**
	 * @param grupoTrabajo the grupoTrabajo to set
	 */
	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Long getIdIdiomaDestino() {
		return this.idIdiomaDestino;
	}

	public void setIdIdiomaDestino(Long idIdiomaDestino) {
		this.idIdiomaDestino = idIdiomaDestino;
	}

	public ExpTareasResumen() {
		// Constructor vacio
	}

	/**
	 * @return the conformidad
	 */
	public String getConformidad() {
		return this.conformidad;
	}

	/**
	 * @param conformidad the conformidad to set
	 */
	public void setConformidad(String conformidad) {
		this.conformidad = conformidad;
	}

	/**
	 * @return the sinAsignar
	 */
	public String getSinAsignar() {
		return this.sinAsignar;
	}

	/**
	 * @param sinAsignar the sinAsignar to set
	 */
	public void setSinAsignar(String sinAsignar) {
		this.sinAsignar = sinAsignar;
	}

	public Integer getPalPresuspuesto() {
		return this.palPresuspuesto;
	}

	public void setPalPresuspuesto(Integer palPresuspuesto) {
		this.palPresuspuesto = palPresuspuesto;
	}

	public Integer getPalTrados() {
		return this.palTrados;
	}

	public void setPalTrados(Integer palTrados) {
		this.palTrados = palTrados;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return this.fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
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

	@Override
	public String getFechaHoraFin() {
		return DateUtils.obtFechaHoraFormateada(this.fechaFin, this.horaFin, LocaleContextHolder.getLocale());
	}

	public Boolean getEsAsignador() {
		return this.esAsignador;
	}

	public void setEsAsignador(Boolean esAsignador) {
		this.esAsignador = esAsignador;
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

	/**
	 * @return the indUrgente
	 */
	public String getIndUrgente() {
		return this.indUrgente;
	}

	/**
	 * @param indUrgente the indUrgente to set
	 */
	public void setIndUrgente(String indUrgente) {
		this.indUrgente = indUrgente;
	}

	public Persona getSolicitante() {
		return this.solicitante;
	}

	public void setSolicitante(Persona solicitante) {
		this.solicitante = solicitante;
	}

	/**
	 * @return the otrosTrabajos
	 */
	public OtrosTrabajos getOtrosTrabajos() {
		return this.otrosTrabajos;
	}

	/**
	 * @param otrosTrabajos the otrosTrabajos to set
	 */
	public void setOtrosTrabajos(OtrosTrabajos otrosTrabajos) {
		this.otrosTrabajos = otrosTrabajos;
	}

	/**
	 * @return the tareaTrabajo
	 */
	public Tareas getTareaTrabajo() {
		return tareaTrabajo;
	}

	/**
	 * @param tareaTrabajo the tareaTrabajo to set
	 */
	public void setTareaTrabajo(Tareas tareaTrabajo) {
		this.tareaTrabajo = tareaTrabajo;
	}

}
