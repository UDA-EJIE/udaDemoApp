package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

/**
 * 
 * @author eaguirresarobe
 *
 */
public class Aa79bEntidad implements Serializable {

	private static final long serialVersionUID = 1L;
	private Aa79bDireccionNora dirNoraEntidad;
	private String entidadDescAmpEu;
	private String entidadDescAmpEs;
	private String tipo;
	private Integer codigo;

	public Aa79bEntidad() {
		// Constructor
	}

	/**
	 * @return the dirNoraEntidad
	 */
	public Aa79bDireccionNora getDirNoraEntidad() {
		return dirNoraEntidad;
	}

	/**
	 * @param dirNoraEntidad
	 *            the dirNoraEntidad to set
	 */
	public void setDirNoraEntidad(Aa79bDireccionNora dirNoraEntidad) {
		this.dirNoraEntidad = dirNoraEntidad;
	}

	/**
	 * @return the entidadDescAmpEu
	 */
	public String getEntidadDescAmpEu() {
		return entidadDescAmpEu;
	}

	/**
	 * @param entidadDescAmpEu
	 *            the entidadDescAmpEu to set
	 */
	public void setEntidadDescAmpEu(String entidadDescAmpEu) {
		this.entidadDescAmpEu = entidadDescAmpEu;
	}

	/**
	 * @return the entidadDescAmpEs
	 */
	public String getEntidadDescAmpEs() {
		return entidadDescAmpEs;
	}

	/**
	 * @param entidadDescAmpEs
	 *            the entidadDescAmpEs to set
	 */
	public void setEntidadDescAmpEs(String entidadDescAmpEs) {
		this.entidadDescAmpEs = entidadDescAmpEs;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

}
