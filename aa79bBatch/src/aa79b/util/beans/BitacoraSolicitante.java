package aa79b.util.beans;

import java.io.Serializable;

/**
 * @author mrodriguez
 *
 */
public class BitacoraSolicitante implements Serializable {
	
	private static final long serialVersionUID = -3300055941653735307L;
	
	private Long id;
	private Long anyo;
	private Integer numExp;
	private Long idAccion;
	private Long fechaAlta;
	private String usuario;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the anyo
	 */
	public Long getAnyo() {
		return this.anyo;
	}
	/**
	 * @param anyo the anyo to set
	 */
	public void setAnyo(Long anyo) {
		this.anyo = anyo;
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
	 * @return the fechaAlta
	 */
	public Long getFechaAlta() {
		return this.fechaAlta;
	}
	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Long fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return this.usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
