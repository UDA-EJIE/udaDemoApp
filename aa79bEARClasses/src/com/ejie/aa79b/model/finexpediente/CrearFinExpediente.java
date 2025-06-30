package com.ejie.aa79b.model.finexpediente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "codigoExpediente", "codigoExpedienteExterno", "tipoCierre", "ficheros", "motivo" })
@XmlRootElement(name = "cambio")
public class CrearFinExpediente {

	protected String codigoExpediente;
	protected String codigoExpedienteExterno;
	protected int tipoCierre;
	protected RutasPif ficheros;
	protected String motivo;

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
	 * Gets the value of the tipoCierre property.
	 *
	 */
	public int gettipoCierre() {
		return this.tipoCierre;
	}

	/**
	 * Sets the value of the tipoCierre property.
	 *
	 */
	public void setTipoCierre(int value) {
		this.tipoCierre = value;
	}

	/**
	 * Gets the value of the rutaPIF property.
	 *
	 */
	public RutasPif getRutasPIF() {
		return this.ficheros;
	}

	/**
	 * Sets the value of the rutaPIF property.
	 *
	 */
	public void setRutasPIF(RutasPif value) {
		this.ficheros = value;
	}

	/**
	 * Gets the value of the motivo property.
	 *
	 */
	public String getMotivo() {
		return this.motivo;
	}

	/**
	 * Sets the value of the motivo property.
	 *
	 */
	public void setMotivo(String value) {
		this.motivo = value;
	}
}
