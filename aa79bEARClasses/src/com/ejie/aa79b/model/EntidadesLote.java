/**
 *
 */
package com.ejie.aa79b.model;

import org.apache.commons.lang.StringUtils;

/**
 * @author eaguirresarobe
 *
 */
public class EntidadesLote  extends Entidad implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	 private Long idLote;
	 private String comentarioEntidad;
	 private String nombreLote;
	 private String descLoteEs;
	 private String descLoteEu;

	/**
	 * @return the idLote
	 */
	public Long getIdLote() {
		return this.idLote;
	}

	/**
	 * @param idLote the idLote to set
	 */
	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}

	/**
	 * @return the comentarioEntidad
	 */
	public String getComentarioEntidad() {
		return this.comentarioEntidad;
	}

	/**
	 * @param comentarioEntidad the comentarioEntidad to set
	 */
	public void setComentarioEntidad(String comentarioEntidad) {
		this.comentarioEntidad = comentarioEntidad;
	}


	@Override()
	public String getCodigoCompleto() {
		return this.idLote + "_" + this.getTipo() + "_" + this.getCodigo();
	}

	@Override()
	public void setCodigoCompleto(String codigo) {
		String[] split = StringUtils.split(codigo, "_");
		if (split.length == 3) {
			Integer i = 0;
			this.idLote = Long.parseLong(split[i++]);
			this.setTipo(split[i++]);
			this.setCodigo(Integer.parseInt(split[i++]));
		} else if (split.length == 2) {
			super.setCodigoCompleto(codigo);
		}
	}

	/**
	 * @return the nombreLote
	 */
	public String getNombreLote() {
		return this.nombreLote;
	}

	/**
	 * @param nombreLote the nombreLote to set
	 */
	public void setNombreLote(String nombreLote) {
		this.nombreLote = nombreLote;
	}

	/**
	 * @return the descLoteEs
	 */
	public String getDescLoteEs() {
		return this.descLoteEs;
	}

	/**
	 * @param descLoteEs the descLoteEs to set
	 */
	public void setDescLoteEs(String descLoteEs) {
		this.descLoteEs = descLoteEs;
	}

	/**
	 * @return the descLoteEu
	 */
	public String getDescLoteEu() {
		return this.descLoteEu;
	}

	/**
	 * @param descLoteEu the descLoteEu to set
	 */
	public void setDescLoteEu(String descLoteEu) {
		this.descLoteEu = descLoteEu;
	}

}
