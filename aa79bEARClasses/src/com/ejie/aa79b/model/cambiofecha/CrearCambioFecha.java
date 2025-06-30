package com.ejie.aa79b.model.cambiofecha;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "codigoExpediente", "codigoExpedienteExterno", "fechaIzo" })
@XmlRootElement(name = "cambio")
public class CrearCambioFecha {

	protected String codigoExpediente;
	protected String codigoExpedienteExterno;
	protected String fechaIzo;

	/**
	 * Gets the value of the codigoExpediente property.
	 *
	 */
	public String getCodigoExpediente() {
		return this.codigoExpediente;
	}

	/**
	 * Sets the value of the codigoExpediente property.
	 *
	 */
	public void setCodigoExpediente(String value) {
		this.codigoExpediente = value;
	}

	/**
	 * Gets the value of the codigoExpedienteExterno property.
	 *
	 */
	public String getCodigoExpedienteExterno() {
		return this.codigoExpedienteExterno;
	}

	/**
	 * Sets the value of the codigoExpedienteExterno property.
	 *
	 */
	public void setCodigoExpedienteExterno(String value) {
		this.codigoExpedienteExterno = value;
	}

	/**
	 * Gets the value of the fechaIzo property.
	 *
	 */
	public String getFechaIzo() {
		return this.fechaIzo;
	}

	/**
	 * Sets the value of the fechaIzo property.
	 *
	 */
	public void setFechaIzo(String value) {
		this.fechaIzo = value;
	}

}
