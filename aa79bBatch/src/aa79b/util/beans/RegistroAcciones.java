package aa79b.util.beans;

import java.io.Serializable;

public class RegistroAcciones implements Serializable {
	
	private static final long serialVersionUID = -744422011517992404L;
	
	private Long anyoExp;
	private Integer numExp;
	private Long idPuntoMenu;
    private Long idAccion;
	private Long idRegistroAccion;
	private Long idEstadoBitacora;
    private String observ;
    
	/**
	 * @return the anyoExp
	 */
	public Long getAnyoExp() {
		return this.anyoExp;
	}
	/**
	 * @param anyoExp the anyoExp to set
	 */
	public void setAnyoExp(Long anyoExp) {
		this.anyoExp = anyoExp;
	}
	/**
	 * @return the numExp
	 */
	public Integer getNumExp() {
		return this.numExp;
	}
	/**
	 * @param numExp the numExp to set
	 */
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}
	/**
	 * @return the idPuntoMenu
	 */
	public Long getIdPuntoMenu() {
		return this.idPuntoMenu;
	}
	/**
	 * @param idPuntoMenu the idPuntoMenu to set
	 */
	public void setIdPuntoMenu(Long idPuntoMenu) {
		this.idPuntoMenu = idPuntoMenu;
	}
	/**
	 * @return the idAccion
	 */
	public Long getIdAccion() {
		return this.idAccion;
	}
	/**
	 * @param idAccion the idAccion to set
	 */
	public void setIdAccion(Long idAccion) {
		this.idAccion = idAccion;
	}
	/**
	 * @return the idRegistroAccion
	 */
	public Long getIdRegistroAccion() {
		return this.idRegistroAccion;
	}
	/**
	 * @param idRegistroAccion the idRegistroAccion to set
	 */
	public void setIdRegistroAccion(Long idRegistroAccion) {
		this.idRegistroAccion = idRegistroAccion;
	}
	/**
	 * @return the idEstadoBitacora
	 */
	public Long getIdEstadoBitacora() {
		return this.idEstadoBitacora;
	}
	/**
	 * @param idEstadoBitacora the idEstadoBitacora to set
	 */
	public void setIdEstadoBitacora(Long idEstadoBitacora) {
		this.idEstadoBitacora = idEstadoBitacora;
	}
	/**
	 * @return the observ
	 */
	public String getObserv() {
		return this.observ;
	}
	/**
	 * @param observ the observ to set
	 */
	public void setObserv(String observ) {
		this.observ = observ;
	}
    
}
