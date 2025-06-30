package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ejie.aa79b.common.JsonFechaHoraDeserializer;
import com.ejie.aa79b.common.JsonFechaHoraSerializer;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.x38.serialization.JsonDateDeserializer;
import com.ejie.x38.serialization.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author eaguirresarobe
 *
 */
public class Auditoria extends Tareas implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idAuditoria;
	private PersonalIZO auditorAsignado;
	private Lotes lote;
	private String idTipoExpediente;
	private Integer estado;
	private String estadoDescEs;
	private String estadoDescEu;
	private String[] filtroDatos;
	private Date fechaEnvio;
	private Date fechaConfirmacion;
	private BigDecimal idTareaAuditar;
	private String indEnviado;
	private List<AuditoriaSeccionExpediente> lAuditoriaSeccionExpediente = new ArrayList<AuditoriaSeccionExpediente>();

	private Integer resulEmail;

	/**
	 * Method 'Auditoria'
	 */
	public Auditoria() {
		// constructor
	}

	/**
	 * @return the idAuditoria
	 */
	public Long getIdAuditoria() {
		return this.idAuditoria;
	}

	/**
	 * @param idAuditoria the idAuditoria to set
	 */
	public void setIdAuditoria(Long idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	/**
	 * @return the auditorAsignado
	 */
	public PersonalIZO getAuditorAsignado() {
		return this.auditorAsignado;
	}

	/**
	 * @param auditorAsignado the auditorAsignado to set
	 */
	public void setAuditorAsignado(PersonalIZO auditorAsignado) {
		this.auditorAsignado = auditorAsignado;
	}

	/**
	 * @return the lote
	 */
	public Lotes getLote() {
		return lote;
	}

	/**
	 * @param lote the lote to set
	 */
	public void setLote(Lotes lote) {
		this.lote = lote;
	}

	public String getIdTipoExpediente() {
		return idTipoExpediente;
	}

	public void setIdTipoExpediente(String idTipoExpediente) {
		this.idTipoExpediente = idTipoExpediente;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	/**
	 * @return the estadoDescEs
	 */
	public String getEstadoDescEs() {
		return this.estadoDescEs;
	}

	/**
	 * @param estadoDescEs the estadoDescEs to set
	 */
	public void setEstadoDescEs(String estadoDescEs) {
		this.estadoDescEs = estadoDescEs;
	}

	/**
	 * @return the estadoDescEu
	 */
	public String getEstadoDescEu() {
		return this.estadoDescEu;
	}

	/**
	 * @param estadoDescEu the estadoDescEu to set
	 */
	public void setEstadoDescEu(String estadoDescEu) {
		this.estadoDescEu = estadoDescEu;
	}

	public String[] getFiltroDatos() {
		return this.filtroDatos;
	}

	public void setFiltroDatos(String[] filtroDatos) {
		this.filtroDatos = filtroDatos;
	}

	@JsonSerialize(using = JsonFechaHoraSerializer.class)
	public Date getFechaEnvio() {
		return this.fechaEnvio;
	}

	public String getFechaHoraEnvio() {
		return DateUtils.obtFechaHoraLargaFormateada(this.fechaEnvio);
	}

	@JsonDeserialize(using = JsonFechaHoraDeserializer.class)
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaConfirmacion() {
		return this.fechaConfirmacion;
	}

	public String getFechaHoraConfirmacion() {
		return DateUtils.obtFechaHoraLargaFormateada(this.fechaConfirmacion);
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaConfirmacion(Date fechaConfirmacion) {
		this.fechaConfirmacion = fechaConfirmacion;
	}

	/**
	 * @return the idTareaAuditar
	 */
	public BigDecimal getIdTareaAuditar() {
		return this.idTareaAuditar;
	}

	/**
	 * @param idTareaAuditar the idTareaAuditar to set
	 */
	public void setIdTareaAuditar(BigDecimal idTareaAuditar) {
		this.idTareaAuditar = idTareaAuditar;
	}

	/**
	 * @return the indEnviado
	 */
	public String getIndEnviado() {
		return this.indEnviado;
	}

	/**
	 * @param indEnviado the indEnviado to set
	 */
	public void setIndEnviado(String indEnviado) {
		this.indEnviado = indEnviado;
	}

	/**
	 * @return the lAuditoriaSeccionExpediente
	 */
	public List<AuditoriaSeccionExpediente> getlAuditoriaSeccionExpediente() {
		return this.lAuditoriaSeccionExpediente;
	}

	/**
	 * @param lAuditoriaSeccionExpediente the lAuditoriaSeccionExpediente to set
	 */
	public void setlAuditoriaSeccionExpediente(List<AuditoriaSeccionExpediente> lAuditoriaSeccionExpediente) {
		this.lAuditoriaSeccionExpediente = lAuditoriaSeccionExpediente;
	}

	/**
	 * @return the resulEmail
	 */
	public Integer getResulEmail() {
		return this.resulEmail;
	}

	/**
	 * @param resulEmail the resulEmail to set
	 */
	public void setResulEmail(Integer resulEmail) {
		this.resulEmail = resulEmail;
	}

}
