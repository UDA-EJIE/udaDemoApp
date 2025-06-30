package com.ejie.aa79b.model;

/**
 * @author mrodriguez
 */
public class Leyenda implements java.io.Serializable {

	private static final long serialVersionUID = -5193584878817590903L;

	private Long idEstadosExp;
	private Long idFaseExp;
	private Long orden;
	private Long nivel;
	private String texto;

	/**
	 * Method 'Leyenda'.
	 */
	public Leyenda() {
		// Constructor
	}

	/**
	 * @return the idEstadosExp
	 */
	public Long getIdEstadosExp() {
		return idEstadosExp;
	}

	/**
	 * @param idEstadosExp
	 *            the idEstadosExp to set
	 */
	public void setIdEstadosExp(Long idEstadosExp) {
		this.idEstadosExp = idEstadosExp;
	}

	/**
	 * @return the idFaseExp
	 */
	public Long getIdFaseExp() {
		return idFaseExp;
	}

	/**
	 * @param idFaseExp
	 *            the idFaseExp to set
	 */
	public void setIdFaseExp(Long idFaseExp) {
		this.idFaseExp = idFaseExp;
	}

	/**
	 * @return the orden
	 */
	public Long getOrden() {
		return orden;
	}

	/**
	 * @param orden
	 *            the orden to set
	 */
	public void setOrden(Long orden) {
		this.orden = orden;
	}

	/**
	 * @return the nivel
	 */
	public Long getNivel() {
		return nivel;
	}

	/**
	 * @param nivel
	 *            the nivel to set
	 */
	public void setNivel(Long nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto
	 *            the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ idEstadosExp: ").append(this.idEstadosExp).append(" ]");
		result.append(", [ idFaseExp: ").append(this.idFaseExp).append(" ]");
		result.append(", [ orden: ").append(this.orden).append(" ]");
		result.append(", [ nivel: ").append(this.nivel).append(" ]");
		result.append(", [ texto: ").append(this.texto).append(" ]");
		result.append("}");
		return result.toString();
	}

}
