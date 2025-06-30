package com.ejie.aa79b.model.factura;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipoIva" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *         &lt;element name="importeIva" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "tipoIva", "importeIva" })
public class Iva {

	protected String tipoIva;
	protected String importeIva;

	/**
	 * Gets the value of the tipoIva property.
	 * 
	 */
	public String getTipoIva() {
		return tipoIva;
	}

	/**
	 * Sets the value of the tipoIva property.
	 * 
	 */
	public void setTipoIva(String value) {
		this.tipoIva = value;
	}

	/**
	 * Gets the value of the importeIva property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getImporteIva() {
		return importeIva;
	}

	/**
	 * Sets the value of the importeIva property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setImporteIva(String value) {
		this.importeIva = value;
	}

}
