package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Entidad implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tipo;
	private Integer codigo;
	private String descAmpEs;
	private String descAmpEu;
	private String descEs;
	private String descEu;
	private String cif;
	private String iva;
	private String facturable;
	private String estado;
	private Direccion direccion = new Direccion();

	private String tipoDesc;
	private String ivaDesc;
	private String facturableDesc;
	private List<GruposTrabajo> listaEntidades;

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return this.tipo;
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
		return this.codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descAmpEs
	 */
	public String getDescAmpEs() {
		return this.descAmpEs;
	}

	/**
	 * @param descAmpEs
	 *            the descAmpEs to set
	 */
	public void setDescAmpEs(String descAmpEs) {
		this.descAmpEs = descAmpEs;
	}

	/**
	 * @return the descAmpEu
	 */
	public String getDescAmpEu() {
		return this.descAmpEu;
	}

	/**
	 * @param descAmpEu
	 *            the descAmpEu to set
	 */
	public void setDescAmpEu(String descAmpEu) {
		this.descAmpEu = descAmpEu;
	}

	/**
	 * @return the descEs
	 */
	public String getDescEs() {
		return this.descEs;
	}

	/**
	 * @param descEs
	 *            the descEs to set
	 */
	public void setDescEs(String descEs) {
		this.descEs = descEs;
	}

	/**
	 * @return the descEu
	 */
	public String getDescEu() {
		return this.descEu;
	}

	/**
	 * @param descEu
	 *            the descEu to set
	 */
	public void setDescEu(String descEu) {
		this.descEu = descEu;
	}

	/**
	 * @return the direccion
	 */
	public Direccion getDireccion() {
		return this.direccion;
	}

	/**
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return String
	 */
	public String getCodigoCompleto() {
		if (StringUtils.isNotBlank(this.tipo) && this.codigo != null) {
			return this.tipo + "_" + this.codigo;
		} else {
			return null;
		}
	}

	public void setCodigoCompleto(String codigo) {
		if (StringUtils.isNotBlank(codigo)) {
			String[] aux = StringUtils.split(codigo, "_");
			if (aux.length == 2) {
				Integer i = 0;
				this.tipo = aux[i++];
				this.codigo = Integer.parseInt(aux[i++]);
			}
		}
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	/**
	 * @return the iva
	 */
	public String getIva() {
		return this.iva;
	}

	/**
	 * @param iva
	 *            the iva to set
	 */
	public void setIva(String iva) {
		this.iva = iva;
	}

	/**
	 * @return the facturable
	 */
	public String getFacturable() {
		return this.facturable;
	}

	/**
	 * @param facturable
	 *            the facturable to set
	 */
	public void setFacturable(String facturable) {
		this.facturable = facturable;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoDesc() {
		return this.tipoDesc;
	}

	public void setTipoDesc(String tipoDesc) {
		this.tipoDesc = tipoDesc;
	}

	public String getIvaDesc() {
		return this.ivaDesc;
	}

	public void setIvaDesc(String ivaDesc) {
		this.ivaDesc = ivaDesc;
	}

	public String getFacturableDesc() {
		return this.facturableDesc;
	}

	public void setFacturableDesc(String facturableDesc) {
		this.facturableDesc = facturableDesc;
	}

	public List<GruposTrabajo> getListaEntidades() {
		return this.listaEntidades;
	}

	public void setListaEntidades(List<GruposTrabajo> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}
}
