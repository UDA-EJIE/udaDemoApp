package com.ejie.aa79b.model.webservices;

/**
 * @author aresua
 *
 */
public class Aa79bTiposTarea implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String descEs;
	private String descEu;
	private String indGestionAsoc;
	private String indReqCierre;
	private String estado;
	private String descEstadoEs;
	private String descEstadoEu;
	private String descGestionEs;
	private String descGestionEu;
	private String descCierreEs;
	private String descCierreEu;
	private String indHorasEjecucion;
	private String indFacturable;

	/**
	 * Method 'TiposTarea'.
	 */
	public Aa79bTiposTarea() {
		// Contructor
	}

	public String getDescEstadoEs() {
		return this.descEstadoEs;
	}

	public void setDescEstadoEs(String descEstadoEs) {
		this.descEstadoEs = descEstadoEs;
	}

	public String getDescEstadoEu() {
		return this.descEstadoEu;
	}

	public void setDescEstadoEu(String descEstadoEu) {
		this.descEstadoEu = descEstadoEu;
	}

	public String getDescGestionEs() {
		return this.descGestionEs;
	}

	public void setDescGestionEs(String descGestionEs) {
		this.descGestionEs = descGestionEs;
	}

	public String getDescGestionEu() {
		return this.descGestionEu;
	}

	public void setDescGestionEu(String descGestionEu) {
		this.descGestionEu = descGestionEu;
	}

	public String getDescCierreEs() {
		return this.descCierreEs;
	}

	public void setDescCierreEs(String descCierreEs) {
		this.descCierreEs = descCierreEs;
	}

	public String getDescCierreEu() {
		return this.descCierreEu;
	}

	public void setDescCierreEu(String descCierreEu) {
		this.descCierreEu = descCierreEu;
	}

	/**
	 * Method 'Aa79bTiposTarea'.
	 * 
	 * @param id
	 *            Long
	 */
	public Aa79bTiposTarea(Long id) {
		this.id = id;
	}

	/**
	 * Method 'getId'.
	 *
	 * @return Long
	 */

	public Long getId() {
		return this.id;
	}

	/**
	 * Method 'setId'.
	 *
	 * @param id
	 *            Long
	 * @return
	 */

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method 'getDescEs'.
	 *
	 * @return String
	 */

	public String getDescEs() {
		return this.descEs;
	}

	/**
	 * Method 'setDescEs'.
	 *
	 * @param descEs
	 *            String
	 * @return
	 */

	public void setDescEs(String descEs) {
		this.descEs = descEs;
	}

	/**
	 * Method 'getDescEu'.
	 *
	 * @return String
	 */

	public String getDescEu() {
		return this.descEu;
	}

	/**
	 * Method 'setDescEu'.
	 *
	 * @param descEu
	 *            String
	 * @return
	 */

	public void setDescEu(String descEu) {
		this.descEu = descEu;
	}

	/**
	 * Method 'getIndGestionAsoc'.
	 *
	 * @return String
	 */

	public String getIndGestionAsoc() {
		return this.indGestionAsoc;
	}

	/**
	 * Method 'setIndGestionAsoc'.
	 *
	 * @param indGestionAsoc
	 *            String
	 * @return
	 */

	public void setIndGestionAsoc(String indGestionAsoc) {
		this.indGestionAsoc = indGestionAsoc;
	}

	/**
	 * Method 'getIndReqCierre'.
	 *
	 * @return String
	 */

	public String getIndReqCierre() {
		return this.indReqCierre;
	}

	/**
	 * Method 'setIndReqCierre'.
	 *
	 * @param indReqCierre
	 *            String
	 * @return
	 */

	public void setIndReqCierre(String indReqCierre) {
		this.indReqCierre = indReqCierre;
	}

	/**
	 * Method 'getEstado'.
	 *
	 * @return String
	 */

	public String getEstado() {
		return this.estado;
	}

	/**
	 * Method 'setEstado'.
	 *
	 * @param estado
	 *            String
	 * @return
	 */

	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the indHorasEjecucion
	 */
	public String getIndHorasEjecucion() {
		return this.indHorasEjecucion;
	}

	/**
	 * @param indHorasEjecucion
	 *            the indHorasEjecucion to set
	 */
	public void setIndHorasEjecucion(String indHorasEjecucion) {
		this.indHorasEjecucion = indHorasEjecucion;
	}

	/**
	 * @return the indFacturable
	 */
	public String getIndFacturable() {
		return this.indFacturable;
	}

	/**
	 * @param indFacturable
	 *            the indFacturable to set
	 */
	public void setIndFacturable(String indFacturable) {
		this.indFacturable = indFacturable;
	}

}
