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
 *         &lt;element name="nombreTabla" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *         &lt;element name="campoTabla" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *         &lt;element name="campoTablaPk" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "nombreTabla", "campoTabla", "campoTablaPk" })
public class Tabla {

	protected String nombreTabla;
	protected String campoTabla;
	protected String campoTablaPk;

	/**
	 * Gets the value of the nombreTabla property.
	 * 
	 */
	public String getNombreTabla() {
		return nombreTabla;
	}

	/**
	 * Sets the value of the nombreTabla property.
	 * 
	 */
	public void setNombreTabla(String value) {
		this.nombreTabla = value;
	}

	/**
	 * Gets the value of the campoTabla property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCampoTabla() {
		return campoTabla;
	}

	/**
	 * Sets the value of the campoTabla property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCampoTabla(String value) {
		this.campoTabla = value;
	}

	/**
	 * @return the campoTablaPk
	 */
	public String getCampoTablaPk() {
		return campoTablaPk;
	}

	/**
	 * @param campoTablaPk
	 *            the campoTablaPk to set
	 */
	public void setCampoTablaPk(String campoTablaPk) {
		this.campoTablaPk = campoTablaPk;
	}

}
