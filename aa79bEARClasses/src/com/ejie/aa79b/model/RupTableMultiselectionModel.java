package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.List;

public class RupTableMultiselectionModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<String> ids;
	private Integer selectAll;
	private Integer idTipoExpediente;
	private Integer codEntidad;
	private String tipoEntidad;
	private String dniSolicitante;
	private String multiplePkToken;

	public RupTableMultiselectionModel() {
		// Constructor
	}

	/**
	 * @return the ids
	 */
	public List<String> getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	/**
	 * @return the selectAll
	 */
	public Integer getSelectAll() {
		return selectAll;
	}

	/**
	 * @param selectAll
	 *            the selectAll to set
	 */
	public void setSelectAll(Integer selectAll) {
		this.selectAll = selectAll;
	}

	/**
	 * @return the idTipoExpediente
	 */
	public Integer getIdTipoExpediente() {
		return idTipoExpediente;
	}

	/**
	 * @param idTipoExpediente
	 *            the idTipoExpediente to set
	 */
	public void setIdTipoExpediente(Integer idTipoExpediente) {
		this.idTipoExpediente = idTipoExpediente;
	}

	/**
	 * @return the codEntidad
	 */
	public Integer getCodEntidad() {
		return codEntidad;
	}

	/**
	 * @param codEntidad
	 *            the codEntidad to set
	 */
	public void setCodEntidad(Integer codEntidad) {
		this.codEntidad = codEntidad;
	}

	/**
	 * @return the tipoEntidad
	 */
	public String getTipoEntidad() {
		return tipoEntidad;
	}

	/**
	 * @param tipoEntidad
	 *            the tipoEntidad to set
	 */
	public void setTipoEntidad(String tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}

	/**
	 * @return the dniSolicitante
	 */
	public String getDniSolicitante() {
		return dniSolicitante;
	}

	/**
	 * @param dniSolicitante
	 *            the dniSolicitante to set
	 */
	public void setDniSolicitante(String dniSolicitante) {
		this.dniSolicitante = dniSolicitante;
	}

	/**
	 * @return the multiplePkToken
	 */
	public String getMultiplePkToken() {
		return multiplePkToken;
	}

	/**
	 * @param multiplePkToken
	 *            the multiplePkToken to set
	 */
	public void setMultiplePkToken(String multiplePkToken) {
		this.multiplePkToken = multiplePkToken;
	}

}
