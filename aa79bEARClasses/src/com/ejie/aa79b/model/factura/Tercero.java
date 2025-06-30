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
 *         &lt;element name="codigoPostal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="territorio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="municipio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="razonSocial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primerApellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="segundoApellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoTercero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="calle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dniNif" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "codigoPostal", "territorio", "pais", "municipio", "razonSocial", "primerApellido",
		"segundoApellido", "tipoTercero", "telefono", "email", "calle", "dniNif" })
public class Tercero {

	protected String codigoPostal;
	protected String territorio;
	protected String pais;
	protected String municipio;
	protected String razonSocial;
	protected String primerApellido;
	protected String segundoApellido;
	protected String tipoTercero;
	protected String telefono;
	protected String email;
	protected String calle;
	protected String dniNif;

	/**
	 * Gets the value of the codigoPostal property.
	 * 
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Sets the value of the codigoPostal property.
	 * 
	 */
	public void setCodigoPostal(String value) {
		this.codigoPostal = value;
	}

	/**
	 * Gets the value of the territorio property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTerritorio() {
		return territorio;
	}

	/**
	 * Sets the value of the territorio property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTerritorio(String value) {
		this.territorio = value;
	}

	/**
	 * Gets the value of the pais property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Sets the value of the pais property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPais(String value) {
		this.pais = value;
	}

	/**
	 * Gets the value of the municipio property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * Sets the value of the municipio property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMunicipio(String value) {
		this.municipio = value;
	}

	/**
	 * Gets the value of the razonSocial property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * Sets the value of the razonSocial property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRazonSocial(String value) {
		this.razonSocial = value;
	}

	/**
	 * Gets the value of the primerApellido property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}

	/**
	 * Sets the value of the primerApellido property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPrimerApellido(String value) {
		this.primerApellido = value;
	}

	/**
	 * Gets the value of the segundoApellido property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}

	/**
	 * Sets the value of the segundoApellido property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSegundoApellido(String value) {
		this.segundoApellido = value;
	}

	/**
	 * Gets the value of the tipoTercero property.
	 * 
	 */
	public String getTipoTercero() {
		return tipoTercero;
	}

	/**
	 * Sets the value of the tipoTercero property.
	 * 
	 */
	public void setTipoTercero(String value) {
		this.tipoTercero = value;
	}

	/**
	 * Gets the value of the telefono property.
	 * 
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Sets the value of the telefono property.
	 * 
	 */
	public void setTelefono(String value) {
		this.telefono = value;
	}

	/**
	 * Gets the value of the email property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the value of the email property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEmail(String value) {
		this.email = value;
	}

	/**
	 * Gets the value of the calle property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * Sets the value of the calle property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCalle(String value) {
		this.calle = value;
	}

	/**
	 * Gets the value of the dniNif property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDniNif() {
		return dniNif;
	}

	/**
	 * Sets the value of the dniNif property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDniNif(String value) {
		this.dniNif = value;
	}

}
