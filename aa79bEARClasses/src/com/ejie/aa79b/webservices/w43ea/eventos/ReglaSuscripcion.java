package com.ejie.aa79b.webservices.w43ea.eventos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Par valor de una regla de suscripción
 * 
 * <p>
 * Java class for reglaSuscripcion complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="reglaSuscripcion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reglaSuscripcion", propOrder = { "nombre", "valor" })
public class ReglaSuscripcion {

	@XmlElement(required = true)
	protected String nombre;
	@XmlElement(required = true)
	protected String valor;

	/**
	 * Gets the value of the nombre property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the value of the nombre property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNombre(String value) {
		this.nombre = value;
	}

	/**
	 * Gets the value of the valor property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Sets the value of the valor property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setValor(String value) {
		this.valor = value;
	}

}
