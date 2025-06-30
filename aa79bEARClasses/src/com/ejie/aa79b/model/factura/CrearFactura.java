package com.ejie.aa79b.model.factura;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "idFactura", "aplicacion", "concepto", "importeTotal", "importeBase", "devengo",
		"sinIVA", "fueraDeUE", "iva", "pifFactura", "tagFactura", "unidades", "tercero", "tabla" })
@XmlRootElement(name = "crearFactura")
public class CrearFactura {

	protected String idFactura;
	protected String aplicacion;
	protected String concepto;
	protected String importeTotal;
	protected String importeBase;
	protected String devengo;
	protected String sinIVA;
	protected String fueraDeUE;
	protected Iva iva;
	protected String pifFactura;
	protected String tagFactura;
	protected String unidades;
	protected Tercero tercero;
	protected Tabla tabla;

	/**
	 * Gets the value of the idFactura property.
	 *
	 */
	public String getIdFactura() {
		return this.idFactura;
	}

	/**
	 * Sets the value of the idFactura property.
	 *
	 */
	public void setIdFactura(String value) {
		this.idFactura = value;
	}

	/**
	 * Gets the value of the aplicacion property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getAplicacion() {
		return this.aplicacion;
	}

	/**
	 * Sets the value of the aplicacion property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setAplicacion(String value) {
		this.aplicacion = value;
	}

	/**
	 * Gets the value of the concepto property.
	 *
	 */
	public String getConcepto() {
		return this.concepto;
	}

	/**
	 * Sets the value of the concepto property.
	 *
	 */
	public void setConcepto(String value) {
		this.concepto = value;
	}

	/**
	 * Gets the value of the importeTotal property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getImporteTotal() {
		return this.importeTotal;
	}

	/**
	 * Sets the value of the importeTotal property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setImporteTotal(String value) {
		this.importeTotal = value;
	}

	/**
	 * Gets the value of the importeBase property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getImporteBase() {
		return this.importeBase;
	}

	/**
	 * Sets the value of the importeBase property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setImporteBase(String value) {
		this.importeBase = value;
	}

	/**
	 * Gets the value of the iva property.
	 *
	 * @return possible object is {@link Iva }
	 *
	 */
	public Iva getIva() {
		return this.iva;
	}

	/**
	 * Sets the value of the iva property.
	 *
	 * @param value
	 *            allowed object is {@link Iva }
	 *
	 */
	public void setIva(Iva value) {
		this.iva = value;
	}

	/**
	 * Gets the value of the pifFactura property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getPifFactura() {
		return this.pifFactura;
	}

	/**
	 * Sets the value of the pifFactura property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setPifFactura(String value) {
		this.pifFactura = value;
	}

	/**
	 * Gets the value of the tercero property.
	 *
	 * @return possible object is {@link Tercero }
	 *
	 */
	public Tercero getTercero() {
		return this.tercero;
	}

	/**
	 * Sets the value of the tercero property.
	 *
	 * @param value
	 *            allowed object is {@link Tercero }
	 *
	 */
	public void setTercero(Tercero value) {
		this.tercero = value;
	}

	/**
	 * @return the devengo
	 */
	public String getDevengo() {
		return this.devengo;
	}

	/**
	 * @param devengo
	 *            the devengo to set
	 */
	public void setDevengo(String devengo) {
		this.devengo = devengo;
	}

	/**
	 * @return the sinIVA
	 */
	public String getSinIVA() {
		return this.sinIVA;
	}

	/**
	 * @param sinIVA the sinIVA to set
	 */
	public void setSinIVA(String sinIVA) {
		this.sinIVA = sinIVA;
	}

	/**
	 * @return the fueraDeUE
	 */
	public String getFueraDeUE() {
		return this.fueraDeUE;
	}

	/**
	 * @param fueraDeUE the fueraDeUE to set
	 */
	public void setFueraDeUE(String fueraDeUE) {
		this.fueraDeUE = fueraDeUE;
	}

	/**
	 * @return the unidades
	 */
	public String getUnidades() {
		return this.unidades;
	}

	/**
	 * @param unidades
	 *            the unidades to set
	 */
	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}

	/**
	 * @return the tagFactura
	 */
	public String getTagFactura() {
		return this.tagFactura;
	}

	/**
	 * @param tagFactura
	 *            the tagFactura to set
	 */
	public void setTagFactura(String tagFactura) {
		this.tagFactura = tagFactura;
	}

	/**
	 * @return the tabla
	 */
	public Tabla getTabla() {
		return this.tabla;
	}

	/**
	 * @param tabla
	 *            the tabla to set
	 */
	public void setTabla(Tabla tabla) {
		this.tabla = tabla;
	}

}
